package com.mr2.zaiko.infrastructure.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "companies")
public class Company {
    @PrimaryKey @NonNull
    String _id;
    String name;

    Company(@NonNull String _id, @NonNull String name) {
        this._id = _id;
        this.name = name;
    }
}
