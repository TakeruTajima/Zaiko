package com.mr2.zaiko.ui.ItemDetailBrowser;

import com.mr2.zaiko.domain.inhouse.equipment.EquipmentId;
import com.mr2.zaiko.domain.inhouse.equipment.Keyword;
import com.mr2.zaiko.domain.outside.commodity.CommodityId;
import com.mr2.zaiko.domain.outside.company.CompanyId;
import com.mr2.zaiko.domain.outside.product.ProductId;
import com.mr2.zaiko.ui.ImageViewer.ImageViewerResource;

public interface ContractItemDetailBrowser {
    interface View{
        void setResource(ItemDetailBrowserResource resource);
        void openImageCapture(); //カメラ起動
        void transitionToItemDetailEditor(ProductId productId); //編集
        void showImageViewer(ImageViewerResource resource, int position); //
//        void hydeImageViewer();
        void transitionToListOfItemByKeyword(Keyword keyword); //キーワード検索
        void transitionToListOfItemByMaker(CompanyId makerId); //メーカー検索
        void transitionToCommodityDetail(CommodityId commodityId); //商品詳細
        void transitionToListOfBackorder(EquipmentId equipmentId); //入荷待ち一覧
        void transitionToListOfExternalBarcode(EquipmentId equipmentId); //社外バーコード一覧
        void transitionToListOfInventoryHistory(EquipmentId equipmentId); //入出庫履歴
        void transitionToListOfPurchaseHistory(EquipmentId equipmentId); //購入履歴
        void showDialog(String message); //Result、Cation等
    }

    interface Presenter{
        void onCreate(ProductId productId); // -> setResource(ItemDetailBrowserResource resource)
        void onResultImageCapture(); // -> showDialog(String message)
        void onClickEdit(); // -> transitionToItemDetailEditor(ProductId productId)
//        void onClickImage(); //ImageViewer
        void onClickAddImage(); // -> openImageCapture(String fileName)
    }
}
