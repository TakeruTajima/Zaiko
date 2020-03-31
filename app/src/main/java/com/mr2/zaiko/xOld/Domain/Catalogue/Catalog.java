package com.mr2.zaiko.xOld.Domain.Catalogue;

import androidx.annotation.NonNull;

import com.mr2.zaiko.xOld.Domain.CreatedAt;
import com.mr2.zaiko.xOld.Domain.DeletedAt;
import com.mr2.zaiko.xOld.Domain.Product.Name;

import java.util.Objects;

public class Catalog {
    private final int productId;
    private final int sellerId;
    @NonNull private Name name;   //品名　可変 get set
    private int value;  //単価　可変 get set
    private String unit; //単位　可変 get set
    private String note; //備考　可変 get set
    private final CreatedAt createdAt;
    private final DeletedAt deletedAt;

    public Catalog( int productId, int sellerId, @NonNull Name name) {
        this.productId = productId;
        this.sellerId = sellerId;
        this.name = name;
        this.createdAt = null;
        this.deletedAt = null;
    }

    Catalog(int productId, int sellerId, @NonNull Name name, int value, String unit, String note, CreatedAt createdAt, DeletedAt deletedAt) {
        this.productId = productId;
        this.sellerId = sellerId;
        this.name = name;
        this.value = value;
        this.unit = unit;
        this.note = note;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
    }

    public void changeName(@NonNull Name newName){
        this.name = newName;
    }

    public void changeValue(int newValue){
        if (0 > newValue) throw new IllegalArgumentException("単価は0以上");
        this.value = newValue;
    }

    public void changeUnit(@NonNull String newUnit){
        this.unit = newUnit;
    }

    public void changeNote(@NonNull String note){
        this.note = note;
    }

    public int getProductId() {
        return productId;
    }

    public int getSellerId() {
        return sellerId;
    }

    @NonNull
    public String getName() {
        return name.getName();
    }

    public int getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    public String getNote() {
        return note;
    }

    public String getCreatedAt() {
        return createdAt != null ? createdAt.toString() : "";
    }

    public String getDeletedAt() {
        return deletedAt != null ? deletedAt.toString() : "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Catalog)) return false;
        Catalog catalog = (Catalog) o;
        return productId == catalog.productId &&
                sellerId == catalog.sellerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, sellerId);
    }
}
