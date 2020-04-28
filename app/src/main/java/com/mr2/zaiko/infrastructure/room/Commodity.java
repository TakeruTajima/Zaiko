package com.mr2.zaiko.infrastructure.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "commodities")
public class Commodity {
    @PrimaryKey
    @NonNull
    String _id;
    String product_id;
    String company_id;
    String name;
    float price_value;
    String price_name;
    String unit;

    Commodity(@NonNull String _id, @NonNull String product_id, @NonNull String company_id, @NonNull String name, float price_value, @NonNull String price_name, @NonNull String unit) {
        this._id = _id;
        this.product_id = product_id;
        this.company_id = company_id;
        this.name = name;
        this.price_value = price_value;
        this.price_name = price_name;
        this.unit = unit;
    }
}
