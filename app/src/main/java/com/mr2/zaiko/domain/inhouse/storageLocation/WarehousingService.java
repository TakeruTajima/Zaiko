package com.mr2.zaiko.domain.inhouse.storageLocation;

import androidx.annotation.NonNull;

import com.mr2.zaiko.domain.inhouse.equipment.EquipmentId;
import com.mr2.zaiko.domain.inhouse.user.User;

import java.util.List;

public final class WarehousingService {
    public static int brandNewTotal(@NonNull List<StorageLocation> storageLocations){
        if (storageLocations.isEmpty()) return 0;
        int total = 0;
        EquipmentId targetEquipmentId;
        targetEquipmentId = storageLocations.get(0).equipmentId();
        for (StorageLocation s : storageLocations){
            if (targetEquipmentId != s.equipmentId())
                throw new IllegalArgumentException("保管場所の備品が統一されていません。");
            if (StorageLocation.Condition.BrandNew == s.condition())
                total += s.currentQuantity();
        }
        return total;
    }

    public static int usedTotal(@NonNull List<StorageLocation> storageLocations){
        if (storageLocations.isEmpty()) return 0;
        int total = 0;
        EquipmentId targetEquipmentId;
        targetEquipmentId = storageLocations.get(0).equipmentId();
        for (StorageLocation s : storageLocations){
            if (targetEquipmentId != s.equipmentId())
                throw new IllegalArgumentException("保管場所の備品が統一されていません。");
            if (StorageLocation.Condition.Used == s.condition())
                total += s.currentQuantity();
        }
        return total;
    }

    public static void transportInventory(@NonNull StorageLocation from, @NonNull StorageLocation to, int quantity, @NonNull User user){
        if (from.equipmentId() != to.equipmentId()) throw new IllegalArgumentException("備品が一致しません。");
        if (from.condition() != to.condition()) throw new IllegalArgumentException("備品の状態が一致しません。");
        if (0 >= quantity) throw new IllegalArgumentException("数量に0以下は指定できません。");
        if (quantity > from.currentQuantity()) throw new IllegalArgumentException("移行元の備品の数量が足りません。");
        from.inventoryCorrection(user, 0 - quantity);
        to.inventoryCorrection(user, quantity);
    }
}
