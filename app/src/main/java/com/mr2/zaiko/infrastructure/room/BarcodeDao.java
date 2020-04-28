package com.mr2.zaiko.infrastructure.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BarcodeDao {
    @Insert
    void insert(Barcode barcode);
    @Update
    void update(Barcode barcode);
    @Delete
    void delete(Barcode barcode);
    @Query("select * from external_barcode where equipment_id = :equipment_id order by code asc")
    List<Barcode> findByEquipment(String equipment_id);
}
