package com.mr2.zaiko.Infra;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import androidx.annotation.NonNull;

import java.util.Date;

public class DBAdapter {
    private SQLiteDatabase db;
    private MySQLiteOpenHelper helper;

    public DBAdapter(@NonNull Context context) {
        helper = new MySQLiteOpenHelper(context);
        db = helper.getWritableDatabase();
    }

    public void beginTransaction(){
        db.beginTransaction();
    }

    public void commit(){
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void rollBack(){
        db.endTransaction();
    }

    public void close(){
        helper.close();
        db.close();
        db = null;
    }

    /**
     *
     * @return 新しく挿入された行の行ID。エラーが発生した場合は-1
     */
    public long insertRecords(String tableName, ContentValues values){
        return db.insert(tableName, null, values);
    }

    public Cursor getAllRecords(String tableName){
        String query = "SELECT * FROM " + tableName + ";";
        return db.rawQuery(query, null);
    }

    public Cursor findAllRecordsBeforeDate(String tableName, String columnName, Date date){
        String sDate = MyDateFormat.dateToString(date);
        String query = "SELECT * FROM " + tableName +
                " WHERE " + columnName + "  < datetime('" + sDate + "', 'utc');";
        return db.rawQuery(query, null);
    }

    public Cursor findAllRecordsAfterDate(String tableName, String columnName, Date date){
        String sDate = MyDateFormat.dateToString(date);
        String query = "SELECT * FROM " + tableName +
                " WHERE " + columnName + "  > datetime('" + sDate + "', 'utc');";
        return db.rawQuery(query, null);
    }

    public Cursor findAllRecordsByNull(String tableName, String columnName){
        String query = "SELECT * FROM " + tableName + " WHERE " + columnName + " IS NULL;";
        return db.rawQuery(query, null);
    }

    public Cursor findAllRecordsByNotNull(String tableName, String columnName){
        String query = "SELECT * FROM " + tableName + " WHERE " + columnName + " IS NOT NULL;";
        return db.rawQuery(query, null);
    }

    public Cursor findOneRecordById(String tableName, int _id){
        checkId(_id);
        String query = "SELECT * FROM " + tableName + " WHERE _id = " + _id + ";";
        Cursor c = db.rawQuery(query, null);
        if (c.getCount() >= 2){
            throw new IllegalStateException("IDで二つ以上のレコードが見つかりました。");
        }
        return c;
    }

    public Cursor findAllRecordExactMatch(String tableName, String columnName, String value){
        return db.rawQuery("SELECT * FROM " + tableName + " WHERE " + columnName + " = '" + value + "';", null);
    }

    public Cursor findAllRecordPartialMatch(String tableName, String columnName, String value){
        return db.rawQuery("SELECT * FROM " + tableName + " WHERE " + columnName + " LIKE '%" + value + "%';", null);
    }

    /**
     *
     * @return 影響を受ける行の数。
     */
    public long updateRecords(String tableName, ContentValues values, int _id){
        checkId(_id);
        return db.update(tableName, values, "_id=?", new String[]{""+ _id});
        }

    public void updateRecordsByCurrentTimestamp(String tableName, String columnName, int _id){
        checkId(_id);
        db.execSQL("UPDATE " + tableName +
                " SET " + columnName + " = current_timestamp WHERE _id = " + _id + ";");
    }

    public void command(String s){
        db.execSQL(s);
    }

    public Cursor query(String s){
        return db.rawQuery(s, null);
    }

    private Cursor checkCursor(Cursor c, String query){
        if(c == null){
            throw new SQLiteException("Cursor生成に失敗しました。query:" + query);
        }
        return c;
    }

    private void checkId(int _id){
        if(_id <= -1){
            throw new IllegalArgumentException("IDが不正です。ID=" + _id);
        }
    }

}
