package com.mr2.zaiko.infrastructure;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;

/**
 *  test.db アップデート履歴
 *  -> ver.3
 *  テーブルを追加
 *  m_images
 *
 *  ver.1 -> ver.2
 *  下記テーブルの「created_at」「deleted_at」にDefault「current_timestamp」を追加
 *  m_items
 *  m_companies
 *  m_unit_types
 *
 *  ver.1
 *  テーブルを作成
 *  m_items
 *  m_companies
 *  m_unit_types
 *
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    public static final String TAG = MySQLiteOpenHelper.class.getSimpleName();
    private static final String DB_NAME = "test.db";
    private static final int DB_VERSION = 4;

    MySQLiteOpenHelper(@NonNull Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        Log.d(TAG, "constrictor");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreateView");
        createTableVer4(db);
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

    @Override
    public synchronized void close() {
        super.close();
        Log.d(TAG, "close");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade");
        if (DB_VERSION != newVersion) throw new IllegalStateException("version error. (DB_VERSION != newVersion)");

        switch (oldVersion){
            case 1:
                dropTableVer1(db);
                createTableVer2(db);
                addTableVer3(db);
            case 2:
                addTableVer3(db);
            case 3:
                dropTableVer4(db);
                createTableVer4(db);
            case 4:
            case 5:
            case 6:
            default:
                Log.d(TAG, "Upgrade failed, unknown oldVersion. old ver." + oldVersion );
        }
//        switch (newVersion){
//            case 2:
//                createTableVer2(db);
//                break;
//            case 3:
//                createTableVer2(db);
//                addTableVer3(db);
//                break;
//            case 4:
//            case 5:
//            default:
//                Log.d(TAG, "Upgrade failed, unknown newVersion. new ver." + newVersion);
//        }
//        break;
    }

    private void dropTableVer4(SQLiteDatabase db){
        Log.d(TAG, "dropTableVer1");
        db.execSQL("DROP TABLE m_unit_types;");
        db.execSQL("DROP TABLE m_companies;");
        db.execSQL("DROP TABLE m_items;");
        db.execSQL("DROP TABLE m_images");
    }

    private void createTableVer4(SQLiteDatabase db){
        Log.d(TAG, "createTableVer4");
        db.execSQL("CREATE TABLE `m_unit_types` ( " +
                "`_id` INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "`name` TEXT, " +
                "`created_at` TEXT default current_timestamp, " +
                "`deleted_at` TEXT )");
        db.execSQL("CREATE TABLE `m_companies` ( " +
                "`_id` INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "`name` TEXT, " +
                "`is_maker` INTEGER, " +
                "`is_seller` INTEGER, " +
                "`created_at` TEXT default current_timestamp, " +
                "`deleted_at` TEXT )");
        db.execSQL("CREATE TABLE `m_items` ( " +
                "`_id` INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "`model` TEXT, " +
                "`name` TEXT, " +
                "`primary_image_id` TEXT, " +
                "`company_id` INTEGER, " +
                "`in_house_code` INTEGER, " +
                "`unit_type_id` INTEGER, " +
                "`value` INTEGER, " +
                "`take_stock` INTEGER, " +
                "`created_at` TEXT default current_timestamp, " +
                "`deleted_at` TEXT )");
        db.execSQL("CREATE TABLE `m_images` ( " +
                "`_id` INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "`item_id` INTEGER NOT NULL, " +
                "`address` TEXT NOT NULL UNIQUE, " +
                "`created_at` TEXT DEFAULT current_timestamp )");
    }

    private void addTableVer3(SQLiteDatabase db){
        db.execSQL("CREATE TABLE `m_images` ( " +
                "`_id` INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "`item_id` INTEGER NOT NULL, " +
                "`address` TEXT NOT NULL UNIQUE, " +
                "`is_primary` INTEGER, " +
                "`created_at` TEXT DEFAULT current_timestamp )");
    }

    private void createTableVer2(SQLiteDatabase db){
        Log.d(TAG, "createTableVer2");
        db.execSQL("CREATE TABLE `m_unit_types` ( " +
                "`_id` INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "`name` TEXT, " +
                "`created_at` TEXT default current_timestamp, " +
                "`deleted_at` TEXT )");
        db.execSQL("CREATE TABLE `m_companies` ( " +
                "`_id` INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "`name` TEXT, " +
                "`is_maker` INTEGER, " +
                "`is_seller` INTEGER, " +
                "`created_at` TEXT default current_timestamp, " +
                "`deleted_at` TEXT )");
        db.execSQL("CREATE TABLE `m_items` ( " +
                "`_id` INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "`model` TEXT, " +
                "`name` TEXT, " +
                "`company_id` INTEGER, " +
                "`in_house_code` INTEGER, " +
                "`unit_type_id` INTEGER, " +
                "`value` INTEGER, " +
                "`dont_stocktake` INTEGER, " +
                "`created_at` TEXT default current_timestamp, " +
                "`deleted_at` TEXT )");
    }

    private void dropTableVer1(SQLiteDatabase db){
        Log.d(TAG, "dropTableVer1");
        db.execSQL("DROP TABLE m_unit_types;");
        db.execSQL("DROP TABLE m_companies;");
        db.execSQL("DROP TABLE m_items;");
    }

    private void createTableVer1(SQLiteDatabase db){
        Log.d(TAG, "createTableVer1");
        db.execSQL("CREATE TABLE `m_unit_types` ( " +
                "`_id` INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "`name` TEXT, " +
                "`created_at` TEXT, " +
                "`deleted_at` TEXT )");
        db.execSQL("CREATE TABLE `m_companies` ( " +
                "`_id` INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "`name` TEXT, " +
                "`is_maker` INTEGER, " +
                "`is_seller` INTEGER, " +
                "`created_at` TEXT, " +
                "`deleted_at` TEXT )");
        db.execSQL("CREATE TABLE `m_items` ( " +
                "`_id` INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "`model` TEXT, " +
                "`name` TEXT, " +
                "`company_id` INTEGER, " +
                "`in_house_code` INTEGER, " +
                "`unit_type_id` INTEGER, " +
                "`value` INTEGER, " +
                "`dont_stocktake` INTEGER, " +
                "`created_at` TEXT, " +
                "`deleted_at` TEXT )");
    }
}
