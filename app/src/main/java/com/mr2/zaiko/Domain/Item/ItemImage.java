package com.mr2.zaiko.Domain.Item;

import com.mr2.zaiko.Domain.CreatedDateTime;
import com.mr2.zaiko.Domain.Id;

public class ItemImage {
    private final Id _id;
    private final String address;
    private final CreatedDateTime createdAt;

    public ItemImage(String address) {
        this._id = Id.getDefault();
        this.address = address;
        this.createdAt = CreatedDateTime.getDefault();
    }

    ItemImage(Id _id, String address, CreatedDateTime createdAt) {
        this._id = _id;
        this.address = address;
        this.createdAt = createdAt;
    }

    public static ItemImage of(Id _id, String address, CreatedDateTime createdAt){
        return new ItemImage(_id, address, createdAt);
    }

    public Id get_id() {
        return _id;
    }

    public String getAddress() {
        return address;
    }

    public CreatedDateTime getCreatedAt() {
        return createdAt;
    }
}
