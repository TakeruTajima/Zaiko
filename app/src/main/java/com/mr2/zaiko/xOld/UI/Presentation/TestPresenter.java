package com.mr2.zaiko.xOld.UI.Presentation;

import android.content.Context;
import android.util.Log;

import com.mr2.zaiko.xOld.Application.UnitTypeUseCase;
import com.mr2.zaiko.xOld.Domain.UnitType.UnitTypeRepositoryImpl;

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
