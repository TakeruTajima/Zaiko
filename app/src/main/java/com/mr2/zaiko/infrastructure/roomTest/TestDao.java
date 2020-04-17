package com.mr2.zaiko.infrastructure.roomTest;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TestDao {
    @Insert
    void insert(TestEntity testEntity);

    @Delete
    void delete(TestEntity testEntity);

    @Update
    void update(TestEntity testEntity);

    @Query("select * from photos")
    List<TestEntity> getAll();
}
