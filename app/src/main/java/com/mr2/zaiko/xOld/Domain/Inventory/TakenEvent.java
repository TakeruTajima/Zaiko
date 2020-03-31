package com.mr2.zaiko.xOld.Domain.Inventory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mr2.zaiko.xOld.Domain.User.User;

import java.time.ZonedDateTime;

class TakenEvent {
    enum UseStatus {
        NEW_GOODS(1),
        USED(2);
        UseStatus(int i) { }
    }
    @Nullable private final ZonedDateTime when;
    @NonNull private final User who;
    private final int productId;
    @NonNull private final UseStatus which;
    private final int howMany;
    @NonNull private final String fromWhere;
    @NonNull private final String why;
    private int updateCount;
    private boolean updated = false;

    TakenEvent(@NonNull User who, int productId, @NonNull UseStatus which, int howMany, @NonNull String fromWhere, @NonNull String why) {
        this.when = null;
        this.who = who;
        this.productId = productId;
        this.which = which;
        this.howMany = howMany;
        this.fromWhere = fromWhere;
        this.why = why;
        this.updateCount = -1;
    }

    TakenEvent(@Nullable ZonedDateTime when, @NonNull User who, int productId, @NonNull UseStatus which, int howMany, @NonNull String fromWhere, @NonNull String why, int updateCount) {
        this.when = when;
        this.who = who;
        this.productId = productId;
        this.which = which;
        this.howMany = howMany;
        this.fromWhere = fromWhere;
        this.why = why;
        this.updateCount = updateCount;
    }

    @Nullable
    public ZonedDateTime getWhen() {
        return when;
    }

    @NonNull
    public User getWho() {
        return who;
    }

    public int getProductId() {
        return productId;
    }

    @NonNull
    public UseStatus getWhich() {
        return which;
    }

    public int getHowMany() {
        return howMany;
    }

    @NonNull
    public String getFromWhere() {
        return fromWhere;
    }

    @NonNull
    public String getWhy() {
        return why;
    }

    public int getUpdateCount() {
        return updateCount;
    }
}
