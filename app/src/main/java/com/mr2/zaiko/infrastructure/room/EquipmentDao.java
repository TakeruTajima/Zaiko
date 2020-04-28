package com.mr2.zaiko.infrastructure.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EquipmentDao {
    @Insert
    void insert(Equipment equipment);
    @Update
    void update(Equipment equipment);
    @Query("select * from equipments where product_id = :product_id order by _id")
    List<Equipment> findByProduct(String product_id);
}
