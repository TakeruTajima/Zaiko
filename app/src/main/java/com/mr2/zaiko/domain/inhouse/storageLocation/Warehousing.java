package com.mr2.zaiko.domain.inhouse.storageLocation;

import androidx.annotation.NonNull;

import com.mr2.zaiko.domain.inhouse.user.UserId;

import java.time.ZonedDateTime;

public class Warehousing extends InventoryFluctuations {
    public Warehousing(int quantity, @NonNull UserId userId, @NonNull String note) {
        super(quantity, userId, note);
        assertArgumentRange(quantity, 1, 9999, "入庫は1～9999単位で入力してください。");
    }

    public Warehousing(int quantity, @NonNull UserId userId, @NonNull ZonedDateTime createdAt, @NonNull String note) {
        super(quantity, userId, createdAt, note);
    }
}
