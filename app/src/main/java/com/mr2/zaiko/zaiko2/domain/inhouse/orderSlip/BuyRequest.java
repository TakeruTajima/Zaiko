package com.mr2.zaiko.zaiko2.domain.inhouse.orderSlip;

import androidx.annotation.NonNull;

import com.mr2.zaiko.zaiko2.domain.common.ValueObject;
import com.mr2.zaiko.zaiko2.domain.inhouse.user.UserId;
import com.mr2.zaiko.zaiko2.domain.outside.commodity.CommodityId;

import java.time.ZonedDateTime;
import java.util.Objects;

public class BuyRequest extends ValueObject {
    @NonNull private final CommodityId commodityId; //商品
    private final int quantity; //数量
    @NonNull private final UserId userId; //ユーザ
    @NonNull private final ZonedDateTime createdAt; //登録日時
    @NonNull private final String note; //備考

    public BuyRequest(@NonNull CommodityId commodityId, int quantity, @NonNull UserId userId, @NonNull String note) {
        this.commodityId = commodityId;
        this.quantity = quantity;
        this.userId = userId;
        createdAt = ZonedDateTime.now();
        this.note = note;
    }

    BuyRequest(@NonNull CommodityId commodityId, int quantity, @NonNull UserId userId, @NonNull ZonedDateTime createdAt, @NonNull String note) {
        this.commodityId = commodityId;
        this.quantity = quantity;
        this.userId = userId;
        this.createdAt = createdAt;
        this.note = note;
    }

    @NonNull
    public CommodityId commodityId() {
        return commodityId;
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
        if (!(o instanceof BuyRequest)) return false;
        BuyRequest that = (BuyRequest) o;
        return quantity == that.quantity &&
                commodityId.equals(that.commodityId) &&
                userId.equals(that.userId) &&
                createdAt.equals(that.createdAt) &&
                note.equals(that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commodityId, quantity, userId, createdAt, note);
    }
}
