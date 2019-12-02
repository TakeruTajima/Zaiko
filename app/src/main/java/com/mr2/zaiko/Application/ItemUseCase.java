package com.mr2.zaiko.Application;

import androidx.annotation.Nullable;

import com.mr2.zaiko.Domain.Company.Company;
import com.mr2.zaiko.Domain.Id;
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

    /**
     * アイテムを保存するユースケースです。
     * ・Entity内のIdがNullもしくは0以下の場合、追加と見做して同一メーカーで重複する型式がないかチェックします。
     * 重複する場合、保存せずNullを返します。そうでないなら、保存します。
     * ・Idが1以上の場合は、更新としてDBからIDでレコードを検索し、それが存在したなら、ItemRepository\save()に投げます。
     * ・
     * @param item 保存したいItem。
     * @return 保存したItemを再度読み込んで返します。保存出来なかった場合はNullを返します。
     */
    public @Nullable Item saveItem(Item item){
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
            return repository.save(item);
        }else if (repository.exists(item.get_id().value())) {
            return repository.save(item);
        }
        throw new IllegalArgumentException("IDから対象となるアイテムが見つけられませんでした");
    }
}
