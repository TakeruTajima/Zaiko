package com.mr2.zaiko.ui.itemDetailBrowser;

import com.mr2.zaiko.domain.outside.product.ProductId;

class ItemDetailBrowserPresenter implements ContractItemDetailBrowser.Presenter {
    private static ItemDetailBrowserPresenter instance;
    private ContractItemDetailBrowser.View view;

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
        view.setResource(ItemDetailBrowserResource.getTestResource());
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
