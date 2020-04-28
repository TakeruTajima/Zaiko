package com.mr2.zaiko.infrastructure.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(tableName = "keywords", primaryKeys = {"equipment_id","word"})
public class Keyword {
    @NonNull
    String equipment_id;
    @NonNull
    String word;

    Keyword(@NonNull String equipment_id, @NonNull String word) {
        this.equipment_id = equipment_id;
        this.word = word;
    }
}
