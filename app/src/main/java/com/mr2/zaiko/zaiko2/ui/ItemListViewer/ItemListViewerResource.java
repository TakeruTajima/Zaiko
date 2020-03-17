package com.mr2.zaiko.zaiko2.ui.ItemListViewer;

import java.util.List;

public class ItemListViewerResource {
    private List<Item> items;

    public ItemListViewerResource(List<Item> items){
        this.items = items;
    }

    private ItemListViewerResource(){
        for (int i = 0; 10 > i; i++){
            items.add(new Item(i + "th HeadlineHeadlineHeadlineHeadlineHeadlineHeadlineHeadline",
                    i + "th OutlineOutlineOutlineOutlineOutlineOutlineOutlineOutlineOutline"));
        }
    }

    public static ItemListViewerResource getTestResource(){ return new ItemListViewerResource(); }

    public int size(){ return items.size(); }

    public String headline(int position){ return items.get(position).headline; }

    public String outline(int position){ return items.get(position).outline; }

    private class Item{
        private String headline;
        private String outline;

        public Item(String headline, String outline) {
            this.headline = headline;
            this.outline = outline;
        }
    }
}
