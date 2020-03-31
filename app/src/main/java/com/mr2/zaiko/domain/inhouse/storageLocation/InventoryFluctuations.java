package com.mr2.zaiko.domain.inhouse.storageLocation;

import androidx.annotation.NonNull;

import com.mr2.zaiko.domain.common.ValueObject;
import com.mr2.zaiko.domain.inhouse.user.UserId;

import java.time.ZonedDateTime;
import java.util.Objects;

abstract class InventoryFluctuations extends ValueObject {
    private final int quantity;//数量
    @NonNull
    private final UserId userId;//ユーザ
    @NonNull private final ZonedDateTime createdAt;//日時
    @NonNull private final String note;//備考

    public InventoryFluctuations(int quantity, @NonNull UserId userId, @NonNull String note) {
        this.quantity = quantity;
        this.userId = userId;
        this.createdAt = ZonedDateTime.now();
        this.note = note;
    }

    InventoryFluctuations(int quantity, @NonNull UserId userId, @NonNull ZonedDateTime createdAt, @NonNull String note) {
        this.quantity = quantity;
        this.userId = userId;
        this.createdAt = createdAt;
        this.note = note;
    }

    public int quantity() {
        return quantity;
    }

    @NonNull
    public UserId userId() {
        return userId;
    }

    @NonNull
    public ZonedDateTime createdAt() {
        return createdAt;
    }

    @NonNull
    public String note() {
        return note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InventoryFluctuations)) return false;
        InventoryFluctuations that = (InventoryFluctuations) o;
        return quantity == that.quantity &&
                userId.equals(that.userId) &&
                createdAt.equals(that.createdAt) &&
                note.equals(that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, userId, createdAt, note);
    }
}
