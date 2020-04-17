package com.mr2.zaiko.infrastructure.roomTest;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {TestEntity.class}, version = 1)
public abstract class TestDatabase extends RoomDatabase {
    public abstract TestDao testDao();
}
