package com.mr2.zaiko.zaiko2.ui.ItemDetailBrowser;

public interface ContractItemDetailBrowser {
    interface View{
        void transitionImageCapture(); //カメラ起動
        void transitionInformationEditor(); //編集
//        void showImageViewer(); //ImageViewerFragmentに実装済み
//        void hydeImageViewer();
        void transitionToItemListForKeywordSearch(); //キーワード検索
        void transitionToItemListForMakerSearch(); //メーカー検索
        void transitionToCommodityDetail(); //商品詳細
        void transitionToWaiting();//入荷待ち一覧
        //社外バーコード一覧
        //入出庫履歴
        //購入履歴
    }

    interface Presenter{
        void onCreate();
        void onResultImageCapture();
        void onClickEdit();
        void onClickImage();
        void onClickAddImage();
    }
}
