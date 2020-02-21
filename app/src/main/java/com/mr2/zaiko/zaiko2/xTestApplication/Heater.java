package com.mr2.zaiko.zaiko2.xTestApplication;

import javax.inject.Inject;

public class Heater {

    public Boolean isHot = false;

    @Inject
    Heater(){}

    public void heating(){
        isHot = true;
        System.out.println("heating");
    }

    public Boolean isHot(){
        return isHot;
    }
}
