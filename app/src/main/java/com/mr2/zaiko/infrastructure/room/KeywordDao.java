package com.mr2.zaiko.infrastructure.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface KeywordDao {
    @Insert
    void insert(Keyword keyword);
    @Update
    void update(Keyword keyword);
    @Delete
    void delete(Keyword keyword);
    @Query("select * from keywords where equipment_id = :equipment_id order by word asc")
    List<Keyword> findByEquipment(String equipment_id);
}
