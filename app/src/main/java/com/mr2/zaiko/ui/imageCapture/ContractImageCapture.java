package com.mr2.zaiko.ui.imageCapture;

public interface ContractImageCapture {
    interface View{
        void onCaptureResult(String fileName);
    }
    interface Presenter{
        void onCreate(View view);
        void onDestroy(View view);
        void onCaptureResult(String data);
    }
}
