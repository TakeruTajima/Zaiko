package com.mr2.zaiko.infrastructure.room;

public class EquipmentSubset {
    public final String e_name;
    public final String model;
    public final String c_name;
    public final float price;
    public final String currency;
    public final String unit;
    public final int quantity;
    public final int capacity;

    public EquipmentSubset(String e_name, String model, String c_name, float price, String currency, String unit, int quantity, int capacity) {
        this.e_name = e_name;
        this.model = model;
        this.c_name = c_name;
        this.price = price;
        this.currency = currency;
        this.unit = unit;
        this.quantity = quantity;
        this.capacity = capacity;
    }
}
