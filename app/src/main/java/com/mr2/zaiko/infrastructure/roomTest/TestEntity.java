package com.mr2.zaiko.infrastructure.roomTest;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "photos")
public class TestEntity {
    @NonNull
    @PrimaryKey
    @ColumnInfo (name = "file_name")
    private final String fileName;

    public TestEntity(@NonNull String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
