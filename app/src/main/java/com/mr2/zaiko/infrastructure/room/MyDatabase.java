package com.mr2.zaiko.infrastructure.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.UUID;

@Database(entities = {
        Barcode.class,
        Commodity.class,
        Company.class,
        Equipment.class,
        Keyword.class,
        Photo.class,
        Product.class,
        StorageLocation.class}, version = 1, exportSchema = true)
public abstract class MyDatabase extends RoomDatabase {
    private static MyDatabase instance;
    /**
     * DBのインスタンスは基本的にシングルトンパターンにしたほうがよい。
     */
    public static MyDatabase getInstance(Context context){
        if (null != instance) return instance;
        instance = Room.databaseBuilder(context,
                MyDatabase.class,
                "my_database.db").build();
//        instance.setupSampleData();
        return instance;
    }

    public abstract BarcodeDao barcodeDao();
    public abstract CommodityDao commodityDao();
    public abstract CompanyDao companyDao();
    public abstract EquipmentDao equipmentDao();
    public abstract KeywordDao keywordDao();
    public abstract PhotoDao photoDao();
    public abstract ProductDao productDao();
    public abstract StorageLocationDao storageLocationDao();
    public abstract EquipmentSubsetDao equipmentSubsetDao();

    private void setupSampleData(){
        new Thread(()->{
            Company c = new Company(UUID.randomUUID().toString(), "sample company");
            companyDao().insert(c);
        }).start();
    }
}
