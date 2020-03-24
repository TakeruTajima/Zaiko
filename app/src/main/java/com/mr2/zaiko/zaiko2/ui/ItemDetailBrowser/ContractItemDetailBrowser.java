package com.mr2.zaiko.zaiko2.ui.ItemDetailBrowser;

public interface ContractItemDetailBrowser {
    interface View{
        void transitionImageCapture(); //カメラ起動
        void transitionInformationEditor(); //編集
//        void showImageViewer(); //ImageViewerFragmentに実装済み
//        void hydeImageViewer();
        //キーワード検索
        //メーカー検索
        //
    }

    interface Presenter{
        void onCreate();
        void onResultImageCapture();
        void onClickEdit();
        void onClickImage();
        void onClickAddImage();
    }
}
