package com.mr2.zaiko.xOld.Domain.Inventory;

import android.content.ContentValues;
import android.database.Cursor;

import com.mr2.zaiko.xOld.Domain.User.User;
import com.mr2.zaiko.xOld.Infra.DBAdapter;

class InventoryRepositoryImpl implements InventoryRepository {
    private final DBAdapter adapter;

    public InventoryRepositoryImpl(DBAdapter adapter) {
        this.adapter = adapter;
    }

    public Inventory findOne(int productId, String address){
        Cursor c = adapter.query("SELECT * FROM m_inventories WHERE product_id = " + productId + " AND address = " + address + ";");
        return convert(c);
    }

    @Override
    public void takeNewGoods(Inventory inventory, User user, String from, int quantity, String why) {
        if (1 > quantity) throw new IllegalArgumentException("入力値error");
        //イベント生成して
        TakenEvent takenEvent = new TakenEvent(user, inventory.getProductIdentity(), TakenEvent.UseStatus.NEW_GOODS, quantity, from, why);
        //ContentValuesに変換
        ContentValues taken = convert(takenEvent);

        //ここからループスタート　***TODO:このループ、Repositoryの関心事じゃなくない？？　Presentation層に持ってくべきかも
        int i = 0;
        do {
            //在庫数を変更して、DB検索用WHERE句を作り、
            inventory.takeNewGoods(quantity);
            String where = "product_id = " + inventory.getProductIdentity() +
                    " AND address = " + inventory.getAddress() +
                    " AND update_count = " + inventory.getUpdateCount();
            // UpdateCountをインクリしてContentValuesに変換
            inventory.incUpdateCount();
            ContentValues entity = convert(inventory);

            //トランザクション開始
            adapter.beginTransaction();
            //update実行
            long updateResult = adapter.updateRecords("m_inventory", entity, where, null);
            //update成功ならeventを挿入
            if (1 == updateResult){
                long insertResult = adapter.insertRecords("t_taken_event", taken);
                //失敗でthrowException
                if (-1 == insertResult) throw new IllegalStateException("eventの挿入に失敗しました");
                //成功でコミットしてreturn
                adapter.commit();
                return;
            }
            //update失敗ならロールバック・再読み込み・試行回数inc・再試行
            adapter.rollBack();
            inventory = findOne(inventory.getProductIdentity(), inventory.getAddress());
            i++;
        }while (i >= 4);//規定回数オーバーでthrowException
        throw new IllegalStateException("Inventory更新の試行回数が規定値を超えました。");
    }

    private Inventory convert(Cursor cursor){
        return new Inventory(cursor.getInt(cursor.getColumnIndexOrThrow("product_id")),
                cursor.getString(cursor.getColumnIndexOrThrow("address")),
                cursor.getInt(cursor.getColumnIndexOrThrow("new_goods")),
                cursor.getInt(cursor.getColumnIndexOrThrow("used")),
                cursor.getInt(cursor.getColumnIndexOrThrow("update_count")));
    }

    private ContentValues convert(Inventory inventory){
        ContentValues values = new ContentValues();
        values.put("product_id", inventory.getProductIdentity());
        values.put("address", inventory.getAddress());
        values.put("new_goods", inventory.getNewGoods());
        values.put("used", inventory.getUsed());
        values.put("update_count", inventory.getUpdateCount());
        return values;
    }

    private ContentValues convert(TakenEvent event){
        ContentValues values = new ContentValues();
//        values.put("created_at", event.getWhen().format(MyDateTime.getDateTimeFormatter())); //current_datetime?
        values.put("user_id", event.getWho().getUserCode());
        values.put("product_id", event.getProductId());
        values.put("use_status", event.getWhich().name());
        values.put("quantity", event.getHowMany());
        values.put("address", event.getFromWhere());
        values.put("notes", event.getWhy());
        return values;
    }
}
