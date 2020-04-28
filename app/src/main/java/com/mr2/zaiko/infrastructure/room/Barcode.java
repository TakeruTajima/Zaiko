package com.mr2.zaiko.infrastructure.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;

//import org.jetbrains.annotations.NotNull;

@Entity(tableName = "external_barcode", primaryKeys = {"equipment_id", "code"})
public class Barcode {
    @NonNull
    String equipment_id;
    @NonNull
    String code;

    Barcode(@NonNull String equipment_id, @NonNull String code) {
        this.equipment_id = equipment_id;
        this.code = code;
    }
}
