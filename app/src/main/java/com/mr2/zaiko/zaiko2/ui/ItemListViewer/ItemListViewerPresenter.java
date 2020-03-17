package com.mr2.zaiko.zaiko2.ui.ItemListViewer;

import com.mr2.zaiko.zaiko2.domain.inhouse.equipment.Photo;
import com.mr2.zaiko.zaiko2.ui.ImageViewer.ImageViewerResource;

import java.util.ArrayList;
import java.util.List;

class ItemListViewerPresenter implements ContractItemListViewer.Presenter {
    static private ItemListViewerPresenter instance;
    private ContractItemListViewer.View view;

    private ItemListViewerPresenter(ContractItemListViewer.View view) {
        this.view = view;
    }

    static ItemListViewerPresenter getInstance(ContractItemListViewer.View view){
        if (null == instance) instance = new ItemListViewerPresenter(view);
        return instance;
    }

    @Override
    public void onViewCreated() {
        view.setAddItemFAB();
        view.setResource(ItemListViewerResource.getTestResource());

    }

    @Override
    public void onDestroy(){}

    @Override
    public void onItemSelect(int itemId) {
        view.transitionItemDetailBrowser(itemId);
    }

    @Override
    public void onItemHold(int itemId) {
        //未実装（MENUかなんか出す？？
    }

    @Override
    public void onClickImage(int position) {
        List<Photo> photos = new ArrayList<>();
        for (int i = 0; 9 > i; i++)
            photos.add(new Photo("20200309033935.jpg"));
        view.showImageViewer(new ImageViewerResource(photos, view.getFileAbsolutePath()));
    }

    @Override
    public void onClickAddItem() {
        view.transitionItemRegister();
    }

    @Override
    public void onChangedSortConditions(ContractItemListViewer.SortCondition sortCondition) {
        //未実装
    }

    @Override
    public void onChangedSearchWords(String searchWord) {
        //未実装
    }
}
