package com.mr2.zaiko.zaiko2.ui.ItemDetailBrowser;

public interface ContractItemDetailBrowser {
    interface View{
        void transitionImageCapture();
        void transitionInformationEditor();
        void showImageViewer();
        void hydeImageViewer();
    }

    interface Presenter{
        void onCreate();
        void onResultImageCapture();
        void onClickEdit();
        void onClickImage();
        void onClickAddImage();
    }
}
