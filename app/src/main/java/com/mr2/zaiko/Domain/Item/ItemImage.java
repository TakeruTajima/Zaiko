package com.mr2.zaiko.Domain.Item;

import com.mr2.zaiko.Domain.CreatedDateTime;

public class ItemImage {
    private final String address;
    private final CreatedDateTime onCreated;

    public ItemImage(String address) {
        this.address = address;
        this.onCreated = CreatedDateTime.getDefault();
    }

    ItemImage(String address, CreatedDateTime onCreated) {
        this.address = address;
        this.onCreated = onCreated;
    }

    public String getAddress() {
        return address;
    }

    public CreatedDateTime getOnCreated() {
        return onCreated;
    }
}
