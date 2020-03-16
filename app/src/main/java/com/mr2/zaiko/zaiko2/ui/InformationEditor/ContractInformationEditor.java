package com.mr2.zaiko.zaiko2.ui.InformationEditor;

public interface ContractInformationEditor {
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