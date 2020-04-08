package com.mr2.zaiko.ui.itemListViewer;

import com.mr2.zaiko.domain.common.Identity;
import com.mr2.zaiko.domain.inhouse.equipment.Photo;
import com.mr2.zaiko.ui.imageViewer.ImageViewerResource;

import java.util.ArrayList;
import java.util.List;

class ItemListViewerPresenter implements ContractItemListViewer.Presenter {
    static private ItemListViewerPresenter instance;
    private ContractItemListViewer.View view;
    private Selection selection;
    enum Selection{
        LIST_EQUIPMENT,
        LIST_PRODUCT,
        LIST_COMMODITY
    }

    private ItemListViewerPresenter(ContractItemListViewer.View view) {
        onAttachView(view);
    }

    static ItemListViewerPresenter getInstance(ContractItemListViewer.View view){
        if (null == instance) instance = new ItemListViewerPresenter(view);
            else instance.onAttachView(view);
        return instance;
    }

    public void onAttachView(ContractItemListViewer.View view){
        this.view = view;
    }

    @Override
    public void onViewCreated() {
        view.setAddItemFAB();
        view.showProgress();
        // selection分岐で取得するResourceと叩くViewAPIの選択
        // TODO: useCase.loadItemListResource() loaderCallbackでsetRecyclerView
        view.setEquipmentList(EquipmentListViewerResource.getTestResource());
//        view.hydeProgress();
    }

    @Override
    public void onDestroy(){
        view = null;
    }

    @Override
    public void onItemSelect(Identity itemId) {
        view.transitionItemDetailBrowser(itemId);
    }

    @Override
    public void onItemHold(int itemId) {
        //未実装（MENUかなんか出す？？
    }

    @Override
    public void onClickImage(Identity itemId) {
        System.out.println("onClickImage item id: " + itemId);
        // useCaseからResource取得してshowImageViewer
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

    @Override
    public void onResume() {
        view.hydeProgress();
    }
}
