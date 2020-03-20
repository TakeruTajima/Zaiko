package com.mr2.zaiko.zaiko2.useCase;

import com.mr2.zaiko.zaiko2.domain.inhouse.equipment.Equipment;
import com.mr2.zaiko.zaiko2.domain.inhouse.equipment.EquipmentId;
import com.mr2.zaiko.zaiko2.domain.inhouse.equipment.EquipmentRepository;
import com.mr2.zaiko.zaiko2.ui.ItemListViewer.EquipmentListViewerResource;

import java.util.ArrayList;
import java.util.List;

public class ItemListViewerService {
    private String fileAbstractPath;
    private EquipmentRepository equipmentRepository;

    public EquipmentListViewerResource getEquipmentListResource(){
        List<Equipment> equipments = equipmentRepository.get();
        List<EquipmentListViewerResource.Item> items = new ArrayList<>();
        for (Equipment e: equipments){
            EquipmentId itemId = e.equipmentId();
                //EquipmentIdやぞStringだとわからんくなって死にそうだな
            String iconFilePath = "";
            if (1 <= e.photos().size())
                iconFilePath = fileAbstractPath + e.photos().get(0).address();
                //一枚以上の写真が含まれるなら、一つ目の写真のFileNameを
            String headline = e.name().name();
                //品名
            String outline = e.model().model() + "、" + e.price().price() + "/" + e.unit();
                //型式、単価/単位
            items.add(new EquipmentListViewerResource.Item(itemId, iconFilePath, headline, outline));
        }
        return new EquipmentListViewerResource(items);
    }
}
