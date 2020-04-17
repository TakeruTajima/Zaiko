package com.mr2.zaiko.infrastructure.roomTest;

import android.content.Context;

import androidx.room.Room;

public class TestDatabaseSingleton {
    private static TestDatabase instance = null;

    public TestDatabaseSingleton() { }

    public static TestDatabase getInstance(Context context){
        if (null != instance) return instance;

        instance = Room.databaseBuilder(context, TestDatabase.class, "test_database").build();
        return instance;
    }
}
