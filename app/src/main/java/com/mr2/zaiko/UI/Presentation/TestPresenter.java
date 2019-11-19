package com.mr2.zaiko.UI.Presentation;

import android.content.Context;
import android.util.Log;

import com.mr2.zaiko.Domain.UnitType.UnitTypeRepositoryImpl;
import com.mr2.zaiko.Application.UnitTypeUseCase;

public class TestPresenter {
    public static final String TAG = TestPresenter.class.getSimpleName();
    private Context c;

    public TestPresenter(Context c) {
        this.c = c;
    }

    public void onCreate(){
        Log.d(TAG, "onCreateView");
        UnitTypeUseCase useCase = new UnitTypeUseCase(new UnitTypeRepositoryImpl(c));
        try{
            useCase.test();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Log.d("", "/////////////////");
        }
    }

}
