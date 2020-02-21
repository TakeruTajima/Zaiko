package com.mr2.zaiko.zaiko2.xTestApplication;

import javax.inject.Inject;

class Thermosiphon implements Pump{
    private final Heater heater;

    @Inject
    Thermosiphon(Heater heater){
        this.heater = heater;
    }

    @Override
    public void pumping(){
        if(heater.isHot()){
            System.out.println("pumping");
        }
    }
}
