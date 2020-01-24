package com.mr2.zaiko.UI.Presentation;

import android.util.Log;
import com.mr2.zaiko.Application.CompanyUseCase;
import com.mr2.zaiko.Application.ItemUseCase;
import com.mr2.zaiko.Domain.Company.Company;
import com.mr2.zaiko.Domain.RepositoryService;
import com.mr2.zaiko.Domain.Item.Item;
import com.mr2.zaiko.UI.View.ItemListAdapter;
import com.mr2.zaiko.UI.View.ItemListFragment;
import java.util.List;

public class ItemListPresenter implements ItemListAdapter.Listener {
    public static final String TAG = ItemListPresenter.class.getSimpleName();

    private ItemListFragment fragment;
    private ItemUseCase.ItemSelection selection;
    private Company selectedMaker;

    public ItemListPresenter(ItemListFragment fragment, ItemUseCase.ItemSelection selection) {
        this.fragment = fragment;
        this.selection = selection;
        this.selectedMaker = null;
    }

    public void onCreate(){
        Log.d(TAG, "onCreate()");
        fragment.hideEmpty();
        fragment.showProgress();
        ItemUseCase useCase = new ItemUseCase(RepositoryService.getItemRepository(fragment.getContext()));
        List<Item> itemList = useCase.getList(selection, selectedMaker);
        if (null == itemList || 0 >= itemList.size()){
            fragment.showEmpty();
            fragment.showToast("アイテムが見つかりません。");
            Log.d(TAG, "item lost");
        }else fragment.setItemList(itemList);
        fragment.hideProgress();
    }

    public void onClickFAB(){
        fragment.setCompanyListFragment(CompanyUseCase.CompanySelection.MAKER);
    }

    @Override
    public void onItemSelected(Item item) {
        fragment.showToast("selected item{" + item.getName() + "}");
        fragment.setItemEditFragment(item);
    }

    @Override
    public boolean onItemHold(Item item) {
        fragment.showToast("hold item{" + item.getName() + "}");
        return true;
    }

    public void onCompanySelectionResult(Company maker) {
        if (null == maker || null == maker.get_id() || 0 >= maker.get_id().value()){
            Log.d(TAG, "companyのリザルトIDが不正です。");
            return;
        }
        fragment.setItemEditFragment(maker);
    }
}
