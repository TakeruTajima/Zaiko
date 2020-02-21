package com.mr2.zaiko.zaiko2.ui.contractor;

public interface ContractTest {
    interface Presenter{
        void onCreate(View view);
        void event_1();
        void event_2();
        void event_3(String name);
        void event_4();
//        void registerView(ContractTest.View view);
//        void unregisterView(ContractTest.View view);
        void onDestroy(View view);
    }
    interface View{
        void changeText(String message);
        void showToast(String message);
    }
}
