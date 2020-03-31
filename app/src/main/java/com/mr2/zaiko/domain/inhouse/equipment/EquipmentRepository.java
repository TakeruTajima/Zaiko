package com.mr2.zaiko.domain.inhouse.equipment;

import com.mr2.zaiko.domain.outside.product.ProductId;

import java.util.List;

public interface EquipmentRepository {

    //Equipmentを保存する(Equipment)：Boolean
    boolean save(Equipment equipment);

    //Equipmentを取得する(EquipmentId)：Equipment
    Equipment get(EquipmentId id);
    List<Equipment> get();
    List<Equipment> get(List<EquipmentId> id);
    List<Equipment> get(ProductId productId);

    //バーコードで検索する(ExternalBarcode)：Equipment
    List<Equipment> findByBarcode(ExternalBarcode code);

    //キーワードで検索する(Keyword)：Equipment
    List<Equipment> findByKeyword(Keyword keyword);

    //要たな卸し備品を取得する()：Equipment
    List<Equipment> getStocktakingList();
}
