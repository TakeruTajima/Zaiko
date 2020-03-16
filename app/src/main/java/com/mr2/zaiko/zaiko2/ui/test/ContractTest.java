package com.mr2.zaiko.zaiko2.ui.test;

public interface ContractTest {
    interface Presenter{
        void onCreate(View view);
        void onDestroy(View view);
        void event_1();
        void event_2();
        void event_3(String name);
        void event_4();
        void startLoader();
    }
    interface View{
        void changeText(String message);
        void showToast(String message);
        void updateProgress(int percent);
    }
}
