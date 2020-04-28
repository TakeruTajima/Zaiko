package com.mr2.zaiko.infrastructure.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PhotoDao {
    @Insert
    void insert(Photo photo);
    @Update
    void update(Photo photo);
    @Delete
    void delete(Photo photo);
    @Query("select * from photos where equipment_id = :equipment_id order by file_name asc")
    List<Photo> findByEquipment(String equipment_id);
}
