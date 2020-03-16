package com.mr2.zaiko.zaiko2.ui.InformationBrowser;

public interface ContractInformationBrowser {
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
