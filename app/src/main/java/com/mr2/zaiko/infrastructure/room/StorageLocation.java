package com.mr2.zaiko.infrastructure.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "storage_locations",
        indices = {@Index(value = {"equipment_id", "name"}, unique = true)})
public class StorageLocation {
    @PrimaryKey @NonNull
    String _id;
    String equipment_id;
    String name;
    int quantity;
    int max_quantity;
    String condition;

    StorageLocation(@NonNull String _id, @NonNull String equipment_id, @NonNull String name, int quantity, int max_quantity, @NonNull String condition) {
        this._id = _id;
        this.equipment_id = equipment_id;
        this.name = name;
        this.quantity = quantity;
        this.max_quantity = max_quantity;
        this.condition = condition;
    }
}
