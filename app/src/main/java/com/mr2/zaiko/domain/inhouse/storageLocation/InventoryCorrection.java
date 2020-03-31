package com.mr2.zaiko.domain.inhouse.storageLocation;

import androidx.annotation.NonNull;

import com.mr2.zaiko.domain.inhouse.user.UserId;

import java.time.ZonedDateTime;

/**
 * 在庫調整の履歴
 *
 */
public class InventoryCorrection extends InventoryFluctuations {
    public InventoryCorrection(int quantity, @NonNull UserId userId, @NonNull String note) {
        super(quantity, userId, note);
//        assertArgumentRange(quantity, 1, 9999, "在庫修正は1～9999単位で入力してください。");
    }

    InventoryCorrection(int quantity, @NonNull UserId userId, @NonNull ZonedDateTime createdAt, @NonNull String note) {
        super(quantity, userId, createdAt, note);
    }
}
