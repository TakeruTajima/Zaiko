package com.mr2.zaiko.xOld.Domain.Product;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import androidx.annotation.NonNull;

import com.mr2.zaiko.xOld.Domain.CreatedAt;
import com.mr2.zaiko.xOld.Domain.DeletedAt;
import com.mr2.zaiko.xOld.Domain.Maker.Code;
import com.mr2.zaiko.xOld.Domain.Maker.Maker;
import com.mr2.zaiko.xOld.Domain.MyDateTime;
import com.mr2.zaiko.xOld.Domain.Seller.Seller;
import com.mr2.zaiko.xOld.Domain.UpdateAt;
import com.mr2.zaiko.xOld.Infra.DBAdapter;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class ProductRepositoryImpl implements ProductRepository {
    private final DBAdapter adapter;

    public ProductRepositoryImpl(@NonNull Context context) {
        this.adapter = new DBAdapter(context);
    }

    /**
     * 有効なすべての部品を取得
     * @return 全ての有効な部品、0件のList
     */
    @Override
    public List<Product> getList() {
        Cursor c = adapter.findAllRecordsByNull("m_products", "deleted_at");
        return convertCursor(c);
    }

    /**
     * 新規部品登録
     *
     * @param code  メーカーコード
     * @param model 型式
     * @param name  品名
     * @return 登録した部品
     * @throws IllegalArgumentException- 型式が不正、品名が不正、メーカーコードが不正
     */
    @Override
    public Product register(Code code, String model, String name) throws IllegalArgumentException {
        //型式、品名をバリデーション
        //　NGならIllegalArgumentException
        if (!Model.validate(model)) throw new IllegalArgumentException("型式が不正です");
        if (!Name.validate(name)) throw new IllegalArgumentException("品名が不正です");
        //メーカーが存在するか確認
        //  ないならIllegalArgumentException、複数HitならIllegalStateException
        Cursor c = adapter.findOneRecordById("m_companies", code.getCompanyCode());
        if (0 == c.getCount()) throw new IllegalArgumentException("メーカーが見つかりません");
        //InHouseCode抽出
        Cursor c2 = adapter.findAllRecordExactMatch("m_in_house_codes", "maker_id", "" + code.getCompanyCode());
        int inHouseCode = c2.getInt(c2.getColumnIndexOrThrow("next_code"));

        //トランザクション開始//////////////////////////////////////////////////////////////////////
        adapter.beginTransaction();
        //nextInHouseCode更新
        ContentValues v = new ContentValues();
        v.put("next_code", inHouseCode + 1);
        long updateRecords = adapter.updateRecordsById("m_in_house_code", v, code.getCompanyCode()); //** roll back point
        if (0 == updateRecords) {
            adapter.rollBack();
            throw new SQLException("inHouseCodeのupdateに失敗しました。(updateRecordsById = " + updateRecords + ")");
        }
        //newProduct登録
        ContentValues v2 = new ContentValues();
        v2.put("model", model);
        v2.put("name", name);
        v2.put("maker_id", code.getCompanyCode());
        v2.put("in_house_code", inHouseCode);
        long insertedId = adapter.insertRecords("m_products", v2); //** roll back point
        if (1 > insertedId){
            adapter.rollBack();
            throw new SQLException("m_products新しいレコードの挿入に失敗しました。(insertedId = " + insertedId + ")");
        }

        Product result;
        try {
            result = reloadOneEntity((int) insertedId);
        }catch (IllegalStateException|IllegalArgumentException e){
            adapter.rollBack();
            throw e;
        }
        adapter.commit();
        //トランザクションコミット//////////////////////////////////////////////////////////////////

        //return entity

        return result;
    }

    /**
     * 部品基幹情報の更新
     *
     * @param product 更新したい部品
     * @return 更新した部品
     * @throws IllegalArgumentException-不変項目が不正
     */
    @Override
    public Product update(Product product) throws IllegalArgumentException {
        //ContentValues作成
        int id = product.getProductIdentity();
        String name = product.getProductName();
        String updateAt = product.getUpdateAt() + ZoneId.systemDefault().getId(); //末尾にsystemDefaultのZoneIdを追加
        ContentValues values = new ContentValues();
        values.put("name", name);

        //既存のデータを読み出し
        Cursor c = adapter.findOneRecordById("m_products", id);
        //引数のproductのupdateAtで更新日時を比較し、割り込まれを確認
        //　バージョン違いならException投げる
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:sszzz"); //ZoneId付きのFormatterを用意
        String sourceString = c.getString(c.getColumnIndexOrThrow("update_at")) + "Etc/UTC"; //SQLiteはUTCで保存するらしいので末尾に付け足し
        ZonedDateTime sourceZonedDateTime = ZonedDateTime.parse(sourceString, format); //
        ZonedDateTime entityZonedDateTime = ZonedDateTime.parse(updateAt, format); //
        if (!sourceZonedDateTime.isEqual(
                entityZonedDateTime.withZoneSameInstant(ZoneId.of("Etc/UTC")))){
            throw new IllegalStateException("更新予定のデータ・バージョンが違います。操作をやり直してください。");
        }
        adapter.beginTransaction();
        //update
        adapter.updateRecordsById("m_products", values, id);
        //再読出し
        Product result;
        try{
            result = reloadOneEntity(id);
        }catch (IllegalStateException|IllegalArgumentException e){
            adapter.rollBack();
            throw e;
        }
        adapter.commit();

        return result;
    }

    @Override
    public void deactivate(Product product) {
        //存在判定
        int id = product.getProductIdentity();
        Cursor c = adapter.findOneRecordById("m_products", id);
        if (0 == c.getCount()) throw new IllegalArgumentException("productが存在しません");
        //無効化
        String deleted = c.getString(c.getColumnIndexOrThrow("deleted_at"));
        if (deleted == null || deleted.equals("")) //todo:どっちなのかは覚えておきたい
                adapter.updateRecordsByCurrentTimestamp("m_products", "deleted_at", id);
    }

    @Override
    public void activate(int productIdentity) {
        //存在判定
        Cursor c = adapter.findOneRecordById("m_products", productIdentity);
        if (0 == c.getCount()) throw new IllegalArgumentException("productが存在しません");
        //deleted_atをNull
        String deleted = c.getString(c.getColumnIndexOrThrow("deleted_at"));
        if (!(null == deleted || "".equals(deleted)))
            adapter.command("UPDATE m_products SET deleted_at = Null WHERE _id = " + productIdentity + ";");
    }

    /**
     * @return getList()の件数
     */
    @Override
    public long count() {
        Cursor c = adapter.findAllRecordsByNull("m_products", "deleted_at");
        return c.getCount();
    }

    @Override
    public List<Product> getInactiveList() {
        Cursor c = adapter.findAllRecordsByNotNull("m_products", "deleted_at");
        return convertCursor(c);
    }

    @Override
    public long countInactive() {
        return adapter.findAllRecordsByNotNull("m_products", "deleted_at").getCount();
    }

    /**
     * メーカーの部品を検索
     *
     * @param maker 条件となるメーカー
     * @return Product
     */
    @Override
    public List<Product> findAllByMaker(@NonNull Maker maker) {
        Cursor c = adapter.findAllRecordExactMatch("m_products", "maker_id", maker.getCode() + "");
        return convertCursor(c);
    }

    @Override
    public long countByMaker(@NonNull Maker maker) {
        return adapter.findAllRecordExactMatch("m_products", "maker_id", maker.getCode() + "").getCount();
    }

    @Override
    public List<Product> findAllBySeller(@NonNull Seller seller) {
        Cursor c = adapter.query("SELECT DISTINCT product_id FROM m_catalogue WHERE seller_id = " + seller.getCode() + ";");
        if (!c.moveToFirst()) return new ArrayList<>(0);

        String[] ids = new String[c.getCount()];
        for (int i = 0; i < c.getCount(); i++){
            String productId = c.getInt(c.getColumnIndexOrThrow("product_id")) + "";
            ids[i] = productId;
        }
        Cursor products = adapter.findByArray("m_products", "_id", ids);
        return convertCursor(products);
    }

    @Override
    public long countBySeller(Seller seller) {
        Cursor c = adapter.query("SELECT DISTINCT product_id FROM m_catalogue WHERE seller_id = " + seller.getCode() + ";");
        return c.getCount();
    }

    @Override
    public boolean existsModel(Maker maker, Model model) {
        Cursor c = adapter.query("SELECT * FROM m_products WHERE maker_id = " + maker.getCode() + " AND model = '" + model.getModel() + "';");
        if (1 < c.getCount())throw new IllegalStateException("単一メーカーで型式の重複を検出しました。");
        return c.moveToFirst();
    }

    @Override
    public Identity getIdentity(int productIdentity) {
        Cursor c = adapter.findOneRecordById("m_products", productIdentity);
        if (!c.moveToFirst()) throw new IllegalArgumentException("ProductIdが見つかりません");
        return new Identity(productIdentity);
    }

    private Product reloadOneEntity(int identity) throws IllegalStateException, IllegalArgumentException{

        Cursor c = adapter.findOneRecordById("m_products", identity); //** roll back point
        int findsNewRecord = c.getColumnCount();
        if (1 != findsNewRecord) {
            throw new IllegalStateException("IDから再読出ししたレコードが複数見つかりました。(.getCount = " + findsNewRecord + ")");
        }
        //Entity変換
        //　バリデーションNGでIllegalArgumentExceptionの可能性アリ
        final int INDEX_ID = c.getColumnIndexOrThrow("_id");
        final int INDEX_MODEL = c.getColumnIndexOrThrow("model");
        final int INDEX_MAKER_ID = c.getColumnIndexOrThrow("maker_id");
        final int INDEX_NAME = c.getColumnIndexOrThrow("name");
        final int INDEX_CREATED_AT = c.getColumnIndexOrThrow("created_at");
        final int INDEX_UPDATE_AT = c.getColumnIndex("update_at");
        final int INDEX_DELETED_AT = c.getColumnIndexOrThrow("deleted_at");
        Identity id = null;
        Model model = null;
        int maker_id = -1;
        Name name = null;
        DateTimeFormatter format = MyDateTime.getDateTimeFormatter();
        CreatedAt createdAt = null;
        UpdateAt updateAt = null;
        DeletedAt deletedAt = null;

        try {
            id = new Identity(c.getInt(INDEX_ID));
            model = new Model(c.getString(INDEX_MODEL));
            maker_id = c.getInt(INDEX_MAKER_ID);
            name = new Name(c.getString(INDEX_NAME));
        }catch (IllegalArgumentException e)
        {
            e.printStackTrace();
            throw new IllegalStateException("VOの生成に失敗しました。"); //** roll back point
        }
        try {
            createdAt = new CreatedAt(ZonedDateTime.parse(c.getString(INDEX_CREATED_AT), format));
        }catch (DateTimeParseException e){
            e.printStackTrace();
        }
        try {
            updateAt = new UpdateAt(ZonedDateTime.parse(c.getString(INDEX_UPDATE_AT), format));
        }catch (DateTimeParseException e){
            e.printStackTrace();
        }
        try {
            deletedAt = new DeletedAt(ZonedDateTime.parse(c.getString(INDEX_DELETED_AT), format));
        }catch (DateTimeParseException e){
            e.printStackTrace();
        }

        return new ProductImpl(id,
                model,
                maker_id,
                name,
                createdAt,
                updateAt,
                deletedAt);
    }

    private List<Product> convertCursor(Cursor c){

        List<Product> list = new ArrayList<>(c.getCount());
        if (c.moveToFirst()){
            DateTimeFormatter formatter = MyDateTime.getDateTimeFormatter();
            final int INDEX_ID = c.getColumnIndexOrThrow("_id");
            final int INDEX_MODEL = c.getColumnIndexOrThrow("model");
            final int INDEX_MAKER_ID = c.getColumnIndexOrThrow("maker_id");
            final int INDEX_NAME = c.getColumnIndexOrThrow("name");
            final int INDEX_CREATED_AT = c.getColumnIndexOrThrow("created_at");
            final int INDEX_UPDATE_AT = c.getColumnIndex("update_at");
            final int INDEX_DELETED_AT = c.getColumnIndexOrThrow("deleted_at");
            do {
                Identity id = new Identity(c.getInt(INDEX_ID));
                Model model = new Model(c.getString(INDEX_MODEL));
                int maker_id = c.getInt(INDEX_MAKER_ID);
                Name name = new Name(c.getString(INDEX_NAME));
                CreatedAt createdAt = null; //作成日時
                try{
                    ZonedDateTime created = ZonedDateTime.parse(c.getString(INDEX_CREATED_AT), formatter);
                    createdAt = new CreatedAt(created);
                } catch (DateTimeParseException e){e.printStackTrace();}
                UpdateAt updateAt = null; //更新日
                try{
                    ZonedDateTime update = ZonedDateTime.parse(c.getString(INDEX_UPDATE_AT), formatter);
                    updateAt = new UpdateAt(update);
                }catch (DateTimeParseException e){e.printStackTrace();}
                DeletedAt deletedAt = null; //無効化日時
                try{
                    ZonedDateTime deleted = ZonedDateTime.parse(c.getString(INDEX_DELETED_AT), formatter);
                    deletedAt = new DeletedAt(deleted);
                }catch (DateTimeParseException e){e.printStackTrace();}
                Product product = new ProductImpl(id,
                        model,
                        maker_id,
                        name,
                        createdAt,
                        null,
                        deletedAt);
                list.add(product);
            }while (c.moveToNext());
        }
        return list;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public Product findOne(Integer integer) {
        throw new UnsupportedOperationException("未実装");
    }

    @Override
    public boolean exists(Integer integer) {
        throw new UnsupportedOperationException("未実装");
    }

    @Override
    public List<Product> findAll() {
        throw new UnsupportedOperationException("未実装");
    }

    @Override
    public Product save(Product entity) {
        throw new UnsupportedOperationException("未実装");
    }

    @Override
    public void delete(Product entity) {
        throw new UnsupportedOperationException("未実装");
    }

    private String getNewIdentity() {
        return UUID.randomUUID().toString();
    }
}
