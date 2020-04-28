package com.mr2.zaiko.infrastructure.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "products", indices = {@Index(value = {"company_id", "model"}, unique = true)})
public class Product {
    @PrimaryKey @NonNull
    String _id;
    //@ForeignKey
    String company_id;
    String model;
    String name;
    float price_value; //USDなら1.99$等
    String price_name;
    String unit;
    int unmutated_version;

    Product(@NonNull String _id, @NonNull String company_id, @NonNull String model, @NonNull String name, float price_value, @NonNull String price_name, @NonNull String unit, int unmutated_version) {
        this._id = _id;
        this.company_id = company_id;
        this.model = model;
        this.name = name;
        this.price_value = price_value;
        this.price_name = price_name;
        this.unit = unit;
        this.unmutated_version = unmutated_version;
    }
}
