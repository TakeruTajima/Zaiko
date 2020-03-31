package com.mr2.zaiko.xOld.xTestApplication;

import javax.inject.Inject;

public class CoffeeMaker {

    @Inject Heater heater;
    @Inject Pump pump;

    @Inject
    CoffeeMaker(){
    }

    public void drip(){
        heater.heating();
        pump.pumping();
        System.out.println("Complete!");
    }
}
