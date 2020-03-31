package com.mr2.zaiko.domain.inhouse.storageLocation;

import androidx.annotation.NonNull;

import com.mr2.zaiko.domain.common.Entity;
import com.mr2.zaiko.domain.inhouse.equipment.Equipment;
import com.mr2.zaiko.domain.inhouse.equipment.EquipmentId;
import com.mr2.zaiko.domain.inhouse.user.User;

import java.util.ArrayList;
import java.util.List;

public class StorageLocation extends Entity{
    public enum Condition{
        Used,
        BrandNew
    }
    @NonNull private final StorageLocationId id;
    @NonNull private EquipmentId equipmentId;
    @NonNull private final String name;
    @NonNull private final Condition condition;
    private final int quantity;
    private List<InventoryFluctuations> inventoryFluctuations;

    public StorageLocation(@NonNull EquipmentId equipmentId, @NonNull String name, @NonNull Condition condition) {
        super(0);
        this.id = new StorageLocationId();
        this.equipmentId = equipmentId;
        this.name = name;
        this.condition = condition;
        this.quantity = 0;
        inventoryFluctuations = new ArrayList<>();
    }

    StorageLocation(int unmutatedVersion, @NonNull StorageLocationId id, @NonNull EquipmentId equipmentId, @NonNull String name, @NonNull Condition condition, int quantity, @NonNull List<InventoryFluctuations> inventoryFluctuations) {
        super(unmutatedVersion);
        this.id = id;
        this.equipmentId = equipmentId;
        this.name = name;
        this.condition = condition;
        this.quantity = quantity;
        this.inventoryFluctuations = inventoryFluctuations;
    }

    @NonNull
    public StorageLocationId id() {
        return id;
    }

    @NonNull
    public EquipmentId equipmentId() {
        return equipmentId;
    }

    @NonNull
    public String name() {
        return name;
    }

    @NonNull
    public Condition condition() {
        return condition;
    }

    public int quantity() {
        return quantity;
    }

    public List<InventoryFluctuations> inventoryFluctuations(){ return inventoryFluctuations; }

    @NonNull
    public List<Warehousing> warehousingList(){
        List<Warehousing> list = new ArrayList<>();
        for (InventoryFluctuations i: inventoryFluctuations){
            if (i instanceof Warehousing) list.add((Warehousing)i);
        }
        return list;
    }

    @NonNull
    public List<PickOut> pickOutList(){
        List<PickOut> list = new ArrayList<>();
        for (InventoryFluctuations i: inventoryFluctuations){
            if (i instanceof PickOut) list.add((PickOut)i);
        }
        return list;
    }

    @NonNull
    public List<InventoryCorrection> inventoryCorrectionList(){
        List<InventoryCorrection> list = new ArrayList<>();
        for (InventoryFluctuations i: inventoryFluctuations){
            if (i instanceof InventoryCorrection) list.add((InventoryCorrection)i);
        }
        return list;
    }

    //入庫する
    public void warehousing(User user, EquipmentId equipmentId, int quantity, String note){
        assertArgumentEquals(equipmentId, this.equipmentId, "異なる備品です。");
        Warehousing warehousing = new Warehousing(quantity, user.id(), note);
        inventoryFluctuations.add(warehousing);
    }
    // 出庫する
    public void pickOut(User user, EquipmentId equipmentId, int quantity, String note){
        assertArgumentEquals(equipmentId, this.equipmentId, "異なる備品です。");
        assertArgumentRange(quantity, 1, currentQuantity(), "出庫は1～現在の在庫数で入力してください。");
        PickOut pickOut = new PickOut(quantity, user.id(), note);
        inventoryFluctuations.add(pickOut);
    }
    // 在庫修正する
    public void inventoryCorrection(User user, Equipment equipment, int quantity){
        assertArgumentEquals(equipment.equipmentId(), equipmentId, "異なる備品です。");
        InventoryCorrection inventoryCorrection = new InventoryCorrection(quantity, user.id(), "在庫修正");
        inventoryFluctuations.add(inventoryCorrection);
    }

    public void inventoryCorrection(User user, int quantity){
        InventoryCorrection inventoryCorrection = new InventoryCorrection(quantity, user.id(), "在庫修正");
        inventoryFluctuations.add(inventoryCorrection);
    }

    // 現在の在庫数
    public int currentQuantity(){
        int currentQuantity = quantity;
        for (InventoryFluctuations i : inventoryFluctuations){
            if (i instanceof Warehousing){
                currentQuantity += i.quantity();
            }else if (i instanceof  PickOut){
                currentQuantity -= i.quantity();
            }else if (i instanceof InventoryCorrection){
                currentQuantity += i.quantity();
            }else {
                throw new IllegalStateException("InventoryFluctuationの型がおかしいです");
            }
        }
        return currentQuantity;
    }
}
