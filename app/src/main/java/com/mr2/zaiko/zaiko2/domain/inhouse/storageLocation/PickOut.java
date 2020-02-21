package com.mr2.zaiko.zaiko2.domain.inhouse.storageLocation;

import androidx.annotation.NonNull;

import com.mr2.zaiko.zaiko2.domain.inhouse.user.UserId;

import java.time.ZonedDateTime;

public class PickOut extends InventoryFluctuations {
    public PickOut(int quantity, @NonNull UserId userId, @NonNull String note) {
        super(quantity, userId, note);
        assertArgumentRange(quantity, 1, 9999, "出庫は1～9999単位で入力してください。");
    }

    public PickOut(int quantity, @NonNull UserId userId, @NonNull ZonedDateTime createdAt, @NonNull String note) {
        super(quantity, userId, createdAt, note);
    }
}
