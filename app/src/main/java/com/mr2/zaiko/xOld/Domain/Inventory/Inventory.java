package com.mr2.zaiko.xOld.Domain.Inventory;

import androidx.annotation.NonNull;

public class Inventory {
    private final int productIdentity;
    @NonNull private String address;
    private int newGoods;
    private int used;
    private int updateCount;
    private boolean updated;

    public Inventory( int productIdentity, @NonNull String address, int newGoods, int used) {
        this.productIdentity = productIdentity;
        this.address = address;
        this.newGoods = newGoods;
        this.used = used;
        this.updateCount = 0;
    }

    Inventory( int productIdentity, @NonNull String address, int newGoods, int used, int updateCount) {
        this.productIdentity = productIdentity;
        this.address = address;
        this.newGoods = newGoods;
        this.used = used;
        this.updateCount = updateCount;
    }

    @NonNull
    public int getProductIdentity() {
        return productIdentity;
    }

    @NonNull
    public String getAddress() {
        return address;
    }

    public int getNewGoods() {
        return newGoods;
    }

    public int getUsed() {
        return used;
    }

    public int getUpdateCount() {
        return updateCount;
    }

    public boolean isUpdated(){ return updated; }

    void incUpdateCount(){
        if (updated) throw new IllegalStateException("更新済みです");
        updateCount++;
        updated = true;
    }

    public void takeNewGoods(int quantity){
        if (newGoods < quantity) throw new IllegalArgumentException("現在値を超えています");
        newGoods = newGoods - quantity;
    }
}
