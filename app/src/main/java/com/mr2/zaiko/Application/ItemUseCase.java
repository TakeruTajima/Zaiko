package com.mr2.zaiko.Application;

import com.mr2.zaiko.Domain.Company.Company;
import com.mr2.zaiko.Domain.Item.Item;
import com.mr2.zaiko.Domain.Item.ItemModel;
import com.mr2.zaiko.Domain.Item.ItemRepository;

import java.util.List;

public class ItemUseCase {
    private ItemRepository repository;

    public static final String ITEM_SELECTION = "ITEM_SELECTION";
    public enum ItemSelection{
        ALL, UN_DELETED, MAKER,
    }

    public ItemUseCase(ItemRepository repository) {
        this.repository = repository;
    }

    public List<Item> getList(ItemSelection selection, Company maker){
        if (ItemSelection.MAKER == selection && null == maker)
            throw new IllegalArgumentException("MAKER is NULL, even though although SELECTION is MAKER.");

        List<Item> list;
        switch (selection) {
            case ALL:
                list = repository.findAll();
                break;
            case UN_DELETED:
                list = repository.findAllByUnDeleted();
                break;
            case MAKER:
                list = repository.findAllByMaker(maker);
                break;
            default:
                throw new IllegalArgumentException("illegal selection.");
        }
        return list;
    }

    public Item saveItem(Item item){
        if (null == item.get_id() || 0 >= item.get_id().value()) {
            //重複チェック
            List<Item> list = repository.findAllByMaker(item.getMaker());
            if (null != list && 0 < list.size()) {
                for (int i = 0; i < list.size(); i++) {
                    ItemModel model = list.get(i).getModel();
                    if (null != model && model.equals(item.getModel()))
                        return null;
                }
            }
        }
        return repository.save(item);
    }
}
