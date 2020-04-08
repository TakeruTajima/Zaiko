package com.mr2.zaiko.ui.itemDetailEditor;

public interface ContractItemDetailEditor {
    //Instructions from the presenter
    interface View {
        //Setting/operations a View
        void setExampleView();

        //Screen transition
        void transitionExampleActivity();
    }

    //Notification from View
    interface Presenter {
        void onCreate();
    }
}