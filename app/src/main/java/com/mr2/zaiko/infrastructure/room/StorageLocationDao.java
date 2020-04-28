package com.mr2.zaiko.infrastructure.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StorageLocationDao {
    @Insert
    void insert(StorageLocation storageLocation);
    @Update
    void update(StorageLocation storageLocation);
    @Delete
    void delete(StorageLocation storageLocation);
    @Query("select * from storage_locations where equipment_id = :equipment_id order by name asc")
    List<StorageLocation> findByEquipment(String equipment_id);
}
