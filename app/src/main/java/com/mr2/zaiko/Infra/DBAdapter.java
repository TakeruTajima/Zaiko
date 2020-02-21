package com.mr2.zaiko.Infra;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import androidx.annotation.NonNull;

import com.mr2.zaiko.Domain.MyDateTime;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

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

    /**
     * 全てのレコードを返します。
     * @param tableName テーブル名
     * @return Cursor
     */
    public Cursor getAllRecords(String tableName){
        String query = "SELECT * FROM " + tableName + ";";
        return db.rawQuery(query, null);
    }

    /**
     * 指定した日時より過去のレコードを抽出
     * @param tableName テーブル名
     * @param columnName カラム名
     * @param date 指定日時
     * @return Cursor
     */
    public Cursor findAllRecordsBeforeDate(String tableName, String columnName, ZonedDateTime date){
        DateTimeFormatter format = MyDateTime.getDateTimeFormatter();
        String sDate = date.withZoneSameInstant(ZoneId.of("UTC")).format(format);
        String query = "SELECT * FROM " + tableName +
                " WHERE " + columnName + "  < datetime('" + sDate + "', 'utc');";
        return db.rawQuery(query, null);
    }

    /**
     * 指定した日時より未来のレコードを抽出
     * @param tableName テーブル名
     * @param columnName カラム名
     * @param date 指定日時
     * @return Cursor
     */
    public Cursor findAllRecordsAfterDate(String tableName, String columnName, ZonedDateTime date){
        DateTimeFormatter format = MyDateTime.getDateTimeFormatter();
        String sDate = date.withZoneSameInstant(ZoneId.of("UTC")).format(format);
        String query = "SELECT * FROM " + tableName +
                " WHERE " + columnName + "  > datetime('" + sDate + "', 'utc');";
        return db.rawQuery(query, null);
    }

    /**
     * 指定したカラムの内容がNullのレコードをすべて返します。
     * @param tableName テーブル名
     * @param columnName カラム名
     * @return Cursor
     */
    public Cursor findAllRecordsByNull(String tableName, String columnName){
        String query = "SELECT * FROM " + tableName + " WHERE " + columnName + " IS NULL;";
        Cursor cursor = db.rawQuery(query, null);
        if (null == cursor) throw new NullPointerException("＊Cursorの生成時にNullが返りました。SQLiteDataBaseは0件のコレクションではなくNullを返すようです");
        return cursor;
    }

    /**
     * 指定したカラムの内容がNullでないレコードを抽出
     * @param tableName テーブル名
     * @param columnName カラム名
     * @return Cursor
     */
    public Cursor findAllRecordsByNotNull(String tableName, String columnName){
        String query = "SELECT * FROM " + tableName + " WHERE " + columnName + " IS NOT NULL;";
        return db.rawQuery(query, null);
    }

    /**
     * Idからレコードを探します。複数見つかった場合はIllegalStateExceptionをThrowします。見つからなかった場合もCursorを返します。
     * @param tableName テーブル名
     * @param _id Identity
     * @return Cursor
     * @throws IllegalStateException-件数が2以上の場合
     */
    public Cursor findOneRecordById(String tableName, int _id) throws IllegalStateException{
        String query = "SELECT * FROM " + tableName + " WHERE _id = " + _id + ";";
        Cursor c = db.rawQuery(query, null);
        if (c.getCount() >= 2){
            throw new IllegalStateException("IDで二つ以上のレコードが見つかりました。");
        }
        return c;
    }

    /**
     * 完全一致検索
     * @param tableName テーブル名
     * @param columnName カラム名
     * @param value 値
     * @return Cursor
     */
    public Cursor findAllRecordExactMatch(String tableName, String columnName, String value){
        return db.rawQuery("SELECT * FROM " + tableName + " WHERE " + columnName + " = '" + value + "';", null);
    }

    /**
     * 部分一致検索
     * @param tableName テーブル名
     * @param columnName カラム名
     * @param value 値
     * @return Cursor
     */
    public Cursor findAllRecordPartialMatch(String tableName, String columnName, String value){
        return db.rawQuery("SELECT * FROM " + tableName + " WHERE " + columnName + " LIKE '%" + value + "%';", null);
    }

    /**
     * 指定したIDのレコードを更新
     * @return 影響を受けた行の数。（IDが見つからなかった場合は0を返す）
     * @throws IllegalArgumentException-IDが不正（IDが1未満）
     */
    public long updateRecordsById(String tableName, ContentValues values, int _id) throws IllegalArgumentException{
        if(_id <= -1) throw new IllegalArgumentException("IDが不正です。ID=" + _id);
        return db.update(tableName, values, "_id=?", new String[]{""+ _id});
    }

    public long updateRecords(String tableName, ContentValues values, String where, String[] whereArgs){
        return db.update(tableName, values, where, whereArgs);
    }

    /**
     * Idで指定したレコードの指定したカラムにCurrentTimestampで日時を挿入します。
      * @param tableName テーブル名
     * @param columnName カラム名
     * @param _id Identity
     */
    public void updateRecordsByCurrentTimestamp(String tableName, String columnName, int _id){
        db.execSQL("UPDATE " + tableName +
                " SET " + columnName + " = current_timestamp WHERE _id = " + _id + ";");
    }

    /**
     * command系
     * @param s SQL文
     */
    public void command(String s){
        db.execSQL(s);
    }

    /**
     * query系
     * @param s SQL文
     * @return Cursor
     */
    public Cursor query(String s){
        return db.rawQuery(s, null);
    }

    public Cursor findByArray(String tableName, String columnName, String[] selectArgs){
        return db.rawQuery("SELECT * FROM " + tableName + " WHERE " + columnName + " = '?';", selectArgs);
    }

    private Cursor checkCursor(Cursor c, String query){
        if(c == null){
            throw new SQLiteException("Cursor生成に失敗しました。query:" + query);
        }
        return c;
    }
}
