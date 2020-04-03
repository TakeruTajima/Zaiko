package com.mr2.zaiko.domain.inhouse.storageLocation;

import com.mr2.zaiko.domain.inhouse.equipment.Equipment;
import com.mr2.zaiko.domain.inhouse.user.User;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class WarehousingServiceTest {
    private User user;
    private Equipment equipment1;
    private StorageLocation storageLocation1;
    private StorageLocation storageLocation2;
    private StorageLocation storageLocation3;
    private StorageLocation storageLocation4;

    @Before
    public void setUp() throws Exception {
//        user = new User("1281", new com.mr2.zaiko.domain.inhouse.user.Name("建", null, "但馬"), Authority.NORMAL);
//        equipment1 = new Equipment(
//                new Product(
//                        new CompanyId(),
//                        new Model("katashiki1"),
//                        new Name("名前1"),
//                        new Unit("単位1"),
//                        new Price(100, "円")),
//                new CompanyId(),
//                new Name("名前1"),
//                new Unit("単位1"),
//                new Price(100, "円"));
//        storageLocation1 = new StorageLocation(equipment1.equipmentId(), "AA010101", StorageLocation.Condition.BrandNew);
//        storageLocation1.warehousing(user, equipment1, 10, "初期");
//        storageLocation2 = new StorageLocation(equipment1.equipmentId(), "AB010101", StorageLocation.Condition.BrandNew);
//        storageLocation2.warehousing(user, equipment1, 10, "初期");
//        storageLocation3 = new StorageLocation(equipment1.equipmentId(), "AA010101", StorageLocation.Condition.Used);
//        storageLocation3.warehousing(user, equipment1, 10, "初期");
//        storageLocation4 = new StorageLocation(equipment1.equipmentId(), "AB010101", StorageLocation.Condition.Used);
//        storageLocation4.warehousing(user, equipment1, 10, "初期");
    }

    @Test
    public void brandNewTotal() {
        List<StorageLocation> storageLocationList = new ArrayList<>();
        storageLocationList.add(storageLocation1);
        storageLocationList.add(storageLocation2);
        assertEquals(WarehousingService.brandNewTotal(storageLocationList), 20);
    }

    @Test
    public void usedTotal() {
        List<StorageLocation> storageLocationList = new ArrayList<>();
        storageLocationList.add(storageLocation3);
        storageLocationList.add(storageLocation4);
        assertEquals(WarehousingService.usedTotal(storageLocationList), 20);

    }

    @Test
    public void transportInventory() {
        WarehousingService.transportInventory(storageLocation1, storageLocation2, 10, user);
        assertEquals(storageLocation1.currentQuantity(), 0);
        assertEquals(storageLocation2.currentQuantity(), 20);
    }
}