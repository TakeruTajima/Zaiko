package com.mr2.zaiko.zaiko2;

import android.app.Application;
import android.content.res.Configuration;

import androidx.annotation.NonNull;

import com.mr2.zaiko.zaiko2.domain.test.TestData;
import com.mr2.zaiko.zaiko2.infrastructure.TestRepositoryImpl;
import com.mr2.zaiko.zaiko2.ui.contractor.ContractTest;
import com.mr2.zaiko.zaiko2.ui.presentation.TestPresenter;
import com.mr2.zaiko.zaiko2.useCase.TestApplicationService;

public class TestApplication extends Application {

    /* ---------------------------------------------------------------------- */
    /* Lifecycle                                                              */
    /* ---------------------------------------------------------------------- */
    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("////////////////TestTestApplication onCreate()");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        System.out.println("////////////////TestTestApplication onTerminate()");
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

    /* ---------------------------------------------------------------------- */
    /* other method                                                           */
    /* ---------------------------------------------------------------------- */

    private TestData testData;
    private ContractTest.Presenter testPresenter;

    public void test(){
        System.out.println("////////////////TestTestApplication test()");
        if (null == testData) testData = new TestData();
    }

    public TestData testData(){
        return testData;
    }

    public void testDataSystemOutput(){
        testData.systemOutput();
    }

    public ContractTest.Presenter testPresenter(ContractTest.View view){
        if (null == testPresenter){
            testPresenter = new TestPresenter(new TestApplicationService(new TestRepositoryImpl()));
        }
        return testPresenter;
    }
}
