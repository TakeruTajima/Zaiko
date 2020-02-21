package com.mr2.zaiko.zaiko2.ui.presentation;

import com.mr2.zaiko.zaiko2.ui.contractor.ContractTest;
import com.mr2.zaiko.zaiko2.useCase.TestApplicationService;

public class TestPresenter implements ContractTest.Presenter {
    private ContractTest.View view;
    private TestApplicationService testApplicationService;

    public TestPresenter(TestApplicationService testApplicationService) {
        this.testApplicationService = testApplicationService;
    }

    @Override
    public void onCreate(ContractTest.View view) {
        registerView(view);
        String name;
        if (testApplicationService.existsData()){
            name = testApplicationService.date();
        }else {
            name = testApplicationService.createData();
            this.view.showToast("On created.");
        }
        this.view.changeText(name);
    }

    @Override
    public void onDestroy(ContractTest.View view) {
        unregisterView(view);
    }

    //save
    @Override
    public void event_1() {
        try {
            testApplicationService.save();
            view.showToast("Data saved.");
        }catch (IllegalArgumentException e){
            view.showToast("Unsaved null data. Message: " + e.getMessage());
        }
    }

    //load
    @Override
    public void event_2() {
        try {
            view.changeText(testApplicationService.load());
            view.showToast("Data loaded.");
        }catch (IllegalStateException e){
            view.showToast("Data does not exist. Message: " + e.getMessage());
        }

    }

    //rename
    @Override
    public void event_3(String name) {
        view.changeText(testApplicationService.rename(name));
        view.showToast("Data name changed.");
    }

    //delete
    @Override
    public void event_4() {
        try {
            view.changeText(testApplicationService.delete());
            view.showToast("Data is deleted.");
        }catch (IllegalArgumentException e){
            view.showToast("Wrong data Message: " + e.getMessage());
        }
    }

    private void registerView(ContractTest.View view){
        this.view = view;
    }

    private void unregisterView(ContractTest.View view){
        if (this.view != view) throw new IllegalArgumentException("登録されているViewと異なります。");
        view = null;
    }
}
