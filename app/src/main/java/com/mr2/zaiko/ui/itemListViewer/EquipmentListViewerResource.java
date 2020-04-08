package com.mr2.zaiko.ui.itemListViewer;

import com.mr2.zaiko.domain.inhouse.equipment.EquipmentId;

import java.util.ArrayList;
import java.util.List;

public class EquipmentListViewerResource {
    private List<Item> items;

    public EquipmentListViewerResource(List<Item> items){
        this.items = items;
    }

    private EquipmentListViewerResource(){
        items = new ArrayList<>();
        for (int i = 0; 30 > i; i++){
            items.add(new Item(new EquipmentId(),
                    "/data/user/0/com.mr2.zaiko/files/20200309033935.jpg",
                    "エアシリンダー標準型 No." + (i + 1) + "",
                    "SMC、品番: KATASHIKI-0" + (i + 1) + "、" + i + "/15個、" + i + "00円/個"));
        }
    }

    public static EquipmentListViewerResource getTestResource(){ return new EquipmentListViewerResource(); }

    public int size(){ return items.size(); }

    public EquipmentId itemId(int position){ return items.get(position).itemId; }

    public String iconAbstractPath(int position){ return items.get(position).iconAbstractPath; }

    public String headline(int position){ return items.get(position).headline; }

    public String outline(int position){ return items.get(position).outline; }

    public static class Item{
        private EquipmentId itemId;
        private String iconAbstractPath;
        private String headline;
        private String outline;

        public Item(EquipmentId itemId, String iconAbstractPath, String headline, String outline) {
            this.itemId = itemId;
            this.iconAbstractPath = iconAbstractPath;
            this.headline = headline;
            this.outline = outline;
        }
    }
}
