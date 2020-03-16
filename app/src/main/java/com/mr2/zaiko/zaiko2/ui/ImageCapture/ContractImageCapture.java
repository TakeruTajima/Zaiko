package com.mr2.zaiko.zaiko2.ui.ImageCapture;

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
