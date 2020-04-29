package com.mr2.zaiko;

import android.app.Application;
import android.content.res.Configuration;

import androidx.annotation.NonNull;

import com.mr2.zaiko.domain.inhouse.equipment.EquipmentId;
import com.mr2.zaiko.infrastructure.TestRepositoryImpl;
import com.mr2.zaiko.infrastructure.room.MyDatabase;
import com.mr2.zaiko.ui.imageCapture.ContractImageCapture;
import com.mr2.zaiko.ui.test.ContractTest;
import com.mr2.zaiko.ui.test.TestPresenter;
import com.mr2.zaiko.useCase.TestApplicationService;

import java.util.HashMap;
import java.util.Map;

public class TestApplication extends Application {
    MyDatabase db;

    /* ---------------------------------------------------------------------- */
    /* Lifecycle                                                              */
    /* ---------------------------------------------------------------------- */
    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("////////////////TestTestApplication onCreate()");
        presenters = new HashMap<>();
        db = MyDatabase.getInstance(this);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        System.out.println("////////////////TestTestApplication onConfigurationChanged()");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.out.println("////////////////TestTestApplication onLowMemory()");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        System.out.println("////////////////TestTestApplication onTerminate()");
        testPresenter = null;
        imageCapturePresenter = null;
        targetEquipmentId = null;
        presenters = null;
    }

    /* ---------------------------------------------------------------------- */
    /* other method                                                           */
    /* ---------------------------------------------------------------------- */
    private ContractTest.Presenter testPresenter;
    private ContractImageCapture.Presenter imageCapturePresenter;
    private EquipmentId targetEquipmentId;
    private Map<String, Object> presenters;

    public ContractTest.Presenter testPresenter(){
        if (null == testPresenter){
            testPresenter = new TestPresenter(new TestApplicationService(new TestRepositoryImpl()));
        }
        return testPresenter;
    }

    public void endTestPresenter(){
        testPresenter = null;
    }

    public ContractImageCapture.Presenter imageCapturePresenter(){
        if (null == targetEquipmentId)
            throw new IllegalStateException("target equipment id is null.");
//        if (null == imageCapturePresenter)
//            imageCapturePresenter = new ImageCapturePresenter(new ImagePersistenceService(new EquipmentRepositoryImpl()), targetEquipmentId);
        return imageCapturePresenter;
    }

    public void endImageCapturePresenter(){
        imageCapturePresenter = null;
    }

    public void setTargetEquipmentId(@NonNull EquipmentId equipmentId){
        this.targetEquipmentId = equipmentId;
    }

    public void removeTargetEquipmentId(){
        targetEquipmentId = null;
    }

    public void registerPresenter(String key, Object presenter){
        presenters.put(key, presenter);
    }

    public void clearPresenters(){
        presenters = null;
    }
}
