package com.mr2.zaiko.ui.itemDetailBrowser;

import com.mr2.zaiko.domain.inhouse.equipment.Keyword;
import com.mr2.zaiko.domain.outside.product.ProductId;

class ItemDetailBrowserPresenter implements ContractItemDetailBrowser.Presenter {
    private static ItemDetailBrowserPresenter instance;
    private ContractItemDetailBrowser.View view;
    private ItemDetailBrowserResource resource;
    //private ItemDetailBrowsingApplication useCase; //?

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
        //resource = useCase.loadItemDerailBrowserResource(productId);
        resource = ItemDetailBrowserResource.getTestResource(1);
    }

    @Override
    public void onCreateView() {
        view.setNoticeMessage("*納入待ちが １件 あります。");

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

        view.setListener();
    }

    @Override
    public void onClickPrimaryName() {
        view.showDialog("・通常、ここに表示されるのはメーカーが命名したこの製品の品名ですが、備品として違う名前をつけることができます。\n \n　例>　メーカー品名：エアシリンダー\n　備品名：0ライン検査台用シリンダー");
        //view.showInputDialog(); //?? 備品名を入力　デフォルト(製品名)に戻す操作もいる？？
    }
//    public void onInputDialogResult(@NonNull String inputEquipmentName){
//        if (inputEquipmentName.equals("")) useCase.changeEquipmentName(inputEquipmentName);
//        else useCase.deleteEquipmentName();
//    } // で備品名の永続化


    @Override
    public void onClickMakerName() {
        view.transitionToListOfItemByMaker(resource.getMaker().id());
    }

    @Override
    public void onClickMoreInventory() {
        view.transitionToInventoryList(resource.getEquipment().equipmentId());
    }

    @Override
    public void onClickPutShoppingCart(int quantityWantToPutCart) {
        //カートに追加する永続化処理
        //useCase.putItemToShoppingCart(resource.getCommodity().commodityId(), quantityWantToPutCart);
        view.showDialog("商品をカートに追加しました。\n　数量: " + quantityWantToPutCart);
        // useCaseは成功・失敗を表現するのにBoolean？　LoaderならVoidじゃないと
    }

    @Override
    public void onClickKeyword(String keyword) {
        view.transitionToListOfItemByKeyword(keyword);
    }

    @Override
    public void onCLickSellerName() {
        view.transitionToListOfCommodity(resource.getCommodity().commodityId());
    }

    @Override
    public void onClickMoreSeller() {
        //if (useCase.existsCommodity(productId))
        view.transitionToListOfSeller(resource.getProduct().productId());
        //else view.dialog("購入先が登録されていません。"); //item not found.
    }

    @Override
    public void onClickMoreStoring() {
        //if (useCase.existsStoring(equipmentId))
        view.transitionToListOfStoringHistory(resource.getEquipment().equipmentId());
        //else view.showDialog("入出庫履歴はありません。"); //Listに遷移はしたあとにitem not found？
    }

    @Override
    public void onClickMoreBuyHistory() {
        //if (useCase.existsBuyHistory(equipmentId))
        view.transitionToListOfBuyHistory(resource.getEquipment().equipmentId());
        //else view.showDialog("購入履歴はありません。"); //item not found.
    }
}
