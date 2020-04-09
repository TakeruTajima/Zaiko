package com.mr2.zaiko.ui.itemDetailBrowser;

import com.mr2.zaiko.domain.inhouse.equipment.Keyword;
import com.mr2.zaiko.domain.outside.product.ProductId;

class ItemDetailBrowserPresenter implements ContractItemDetailBrowser.Presenter {
    private static ItemDetailBrowserPresenter instance;
    private ContractItemDetailBrowser.View view;
    private ItemDetailBrowserResource resource;

    public ItemDetailBrowserPresenter(ContractItemDetailBrowser.View view) {
        this.view = view;
    }

    public static ItemDetailBrowserPresenter getInstance(ContractItemDetailBrowser.View view) {
        if (null == instance) instance = new ItemDetailBrowserPresenter(view);
            else instance.onAttachView(view);
        return instance;
    }

    public void onAttachView(ContractItemDetailBrowser.View view){
        this.view = view;
    }

    @Override
    public void onCreate(ProductId productId) {
        resource = ItemDetailBrowserResource.getTestResource(1);
    }

    @Override
    public void onCreateView() {
        String primaryName = resource.getEquipment().name().name().equals("") ?
                resource.getProduct().name().name() : resource.getEquipment().name().name();
        view.setPrimaryName(primaryName);
        view.setMakerName(resource.getMaker().name());
        view.setModel(resource.getProduct().model().model());
        view.setProductName(resource.getProduct().name().name());
        String productPrice = resource.getProduct().price().price() + "/" + resource.getProduct().unit().name();
        view.setProductPrice(productPrice);
        String inventoryPrice = resource.getEquipment().price().price() + "/" + resource.getEquipment().unit().name();
        view.setInventoryPrice(inventoryPrice);
//        String stock = resource.getStorageLocationList().
//        ((TextView)view.findViewById(R.id.itemDetailBrowserStock))
        String[] array = new String[resource.getEquipment().keywordSet().size()];
        int i = 0;
        for (Keyword k: resource.getEquipment().keywordSet()){
            array[i] = k.word();
            i++;
        }
        view.setKeyword(array);
        view.setSellerName(resource.getSeller().name());
        view.setPrimaryCommodityName(resource.getCommodity().name().name());
        String primaryCommodityPrice = resource.getCommodity().price().price() + "/" + resource.getCommodity().unit().name();
        view.setPrimaryCommodityPrice(primaryCommodityPrice);
        //spinner
        int[] spinnerItem = {1,2,3,4,5,6,7,8,9,10};
        view.setUnitSpinner(spinnerItem);
        view.setPrimaryCommodityUnit(resource.getCommodity().unit().unit());
    }

    @Override
    public void onClickPrimaryName() {
        //テキスト入力ダイアログ
        view.showDialog("・通常、ここに表示されるのはメーカーが命名したこの製品の品名ですが、備品として違う名前をつけることができます。\n \n　例>　メーカー品名：エアシリンダー\n　備品名：0ライン検査台用シリンダー");
    }

    @Override
    public void onClickMakerName() {
        view.transitionToListOfItemByMaker(null);
    }

    @Override
    public void onClickInventoryMore() {
        view.transitionToInventoryList(null);
    }

    @Override
    public void onClickPutShoppingCart(int quantityWantToPutCart) {
        view.showDialog("商品をカートに追加します。\n　数量: " + quantityWantToPutCart);
        //カートに追加する永続化処理
    }

    @Override
    public void onClickKeyword(String keyword) {
        view.transitionToListOfItemByKeyword(null);
    }

    @Override
    public void onCLickSellerName() {
        view.transitionToListOfCommodity(null);
    }

    @Override
    public void onClickCommodityMore() {
        view.transitionToListOfSeller(null);
    }

    @Override
    public void onClickStoringMore() {
        view.transitionToListOfStoringHistory(null);
    }

    @Override
    public void onClickBuyHistoryMore() {
        view.transitionToListOfBuyHistory(null);
    }
}
