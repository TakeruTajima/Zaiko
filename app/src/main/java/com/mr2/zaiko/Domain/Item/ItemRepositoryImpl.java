package com.mr2.zaiko.Domain.Item;

import android.content.Context;
import android.database.Cursor;

import com.mr2.zaiko.Domain.Base.BaseCrudRepository;
import com.mr2.zaiko.Domain.Company.Company;
import com.mr2.zaiko.Domain.Id;
import com.mr2.zaiko.Domain.UnitType.Unit;

import java.util.ArrayList;
import java.util.List;

public class ItemRepositoryImpl extends BaseCrudRepository implements ItemRepository {
    public static final String TAG = ItemRepositoryImpl.class.getSimpleName();
    public final List<Company> makerList;
    public final List<Unit> unitList;

    public ItemRepositoryImpl(Context context, List<Company> makerList, List<Unit> unitList) {
        super(context);
        this.makerList = makerList;
        this.unitList = unitList;
        //集約から他の集約のEntityを参照するには、Idによる事後参照（非推奨）か、Repositoryに予め集約を渡しておく。らしい。
    }

    public List<Item> findImageList(List<Item> itemList){
        int size = itemList.size();
        if (0 >= size)
            return null;
        List<Item> listResult = new ArrayList<>(size);
        for (int i = 0; itemList.size() >= i; i++){
            Item item = itemList.get(i);
            listResult.add(item);
        }
        return listResult;
    }

    public Item findImage(Item item){
        Id item_id = item.get_id();
        Cursor cursor = adapter.findAllRecordExactMatch("m_images", "item_id", "" + item_id);
        List<ItemImage> imageList = ItemConverter.convertImage(cursor);
        item.setImageList(imageList);
        return item;
    }

    @Override
    public List<Item> findAllByUnDeleted() {
        Cursor c = adapter.findAllRecordsByNull("m_items", "deleted_at");
        if (null == c || !c.moveToFirst())
            return new ArrayList<>(0);
        List<Item> itemList = ItemConverter.convert(c, makerList, unitList);

        findImageList(itemList);
        return itemList;
    }

    @Override
    public long countByUnDeleted() {
        List<Item> itemList = findAllByUnDeleted();
        return itemList.size();
    }

    @Override
    public List<Item> findAllByMaker(Company maker) {
        Id id = maker.get_id();
//        if (null == id)
        Cursor c = adapter.findAllRecordExactMatch("m_items", "company_id", String.valueOf(id.value()));
        List<Item> itemList = ItemConverter.convert(c, makerList, unitList);
        findImageList(itemList);
        return itemList;
    }

    @Override
    public long countByMaker(Company maker) {
        List<Item> list = findAllByMaker(maker);
        return list.size();
    }

    @Override
    public int extractInHouseCode(int maker_id) {
        //メーカーで抽出したItemリストからinHouseCodeの最大値の+1を求める
        List<Item> list = findAllByMaker(Company.of(Id.of(maker_id)));
        int inHouseCodeResult = 1;
        if (null != list && 0 < list.size()){
            for (int i = 0; i < list.size(); i++){
                Item item = list.get(i);
                int inHouseCode = item.getInHouseCode().value();
                if (inHouseCodeResult < inHouseCode){
                    inHouseCodeResult = inHouseCode;
                }
            }
            inHouseCodeResult++;
        }
        return inHouseCodeResult;
    }

    @Override
    public Item findOne(Integer _id) {
        Cursor c = adapter.findOneRecordById("m_items", _id);
        List<Item> list = ItemConverter.convert(c, makerList, unitList);
        if (null == list || 1 != list.size())
            throw new IllegalStateException("idからレコードの特定に失敗しました。");
        return findImage(list.get(0));
    }

    @Override
    public boolean exists(Integer _id) {
        Cursor c = adapter.findOneRecordById("m_items", _id);
        List<Item> list = ItemConverter.convert(c, makerList, unitList);
        return (null != list && 1 == list.size());
    }

    @Override
    public List<Item> findAll() {
        Cursor c = adapter.getAllRecords("m_items");
        List<Item> itemList = ItemConverter.convert(c, makerList, unitList);
        findImageList(itemList);
        return itemList;
    }

    @Override
    public long count() {
        List<Item> list = findAll();
        return list.size();
    }

    /**
     *
     * @param entity
     * @return
     */
    @Override
    public Item save(Item entity) {
        adapter.beginTransaction();
        long result = -1;
        long resultImage  = -1;
        Id _id = entity.get_id();
        if (null == _id || !exists(_id.value())){
            //新規
            result = adapter.insertRecords("m_items", ItemConverter.convert(entity));
            if (-1 == result){ //挿入に失敗したらロールバックしてNullを返す
                adapter.rollBack();
                return null;
            }//加えて画像addressの保存
            List<ItemImage> itemImageList = entity.getImageList();
            if (0 >= itemImageList.size()) { //中身が無いならコミット、ResultIdでFindOneして返す
                adapter.commit();
                return findOne((int) result);
            }
            for (int i = 0; itemImageList.size() >= i; i++ ) { //Imageのループ挿入
                resultImage = adapter.insertRecords("m_images", ItemConverter.convertImage(itemImageList.get(i)));
                if (-1 == resultImage){ //挿入に失敗したらロールバックしてNullを返す
                    adapter.rollBack();
                    return null;
                }
            }
            adapter.commit(); //挿入完了
            return findOne((int) result);
        }else {
            //更新 returnは更新したレコードの数
            result = adapter.updateRecords("m_items", ItemConverter.convert(entity), _id.value());
            if (0 >= result) { //更新失敗でロールバックNull
                adapter.rollBack();
                return null;
            }//加えて画像addressの保存
            List<ItemImage> itemImageList = entity.getImageList();
            if (0 >= itemImageList.size()) { //中身が無ければコミットして引数のIdでFindOne
                adapter.commit();
                return findOne(_id.value());
            }
            for (int i = 0; itemImageList.size() >= i; i++ ) { //Imageのループ更新　m_imageにレコードがあるなら更新、無いなら挿入
                ItemImage image = itemImageList.get(i);
                Cursor c = adapter.findOneRecordById("m_images", image.get_id().value());
                if (c.moveToFirst()) { //更新
                    resultImage = adapter.updateRecords("m_images", ItemConverter.convertImage(image), image.get_id().value());
                }else { //挿入
                    resultImage = adapter.insertRecords("m_images", ItemConverter.convertImage(image));
                }
                if (0 >= resultImage){ //失敗時resultImage...　挿入:-1　更新:0
                    adapter.rollBack();
                    return null;
                }
            }
            adapter.commit(); //コミットして終了
            return findOne(_id.value());
            //todo:db.insert()の戻り値は挿入されたレコードのIDで
            // .update()は更新されるレコードの数やぞ　他のRepositoryも水戸家
        }
    }

    @Override
    public void delete(Item entity) {
        if (exists(entity.get_id().value())){
            adapter.updateRecordsByCurrentTimestamp("m_items", "deleted_at", entity.get_id().value());
        }
    }
}
