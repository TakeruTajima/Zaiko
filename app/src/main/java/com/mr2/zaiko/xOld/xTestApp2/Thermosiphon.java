package com.mr2.zaiko.xOld.xTestApp2;

import javax.inject.Inject;

/**
 * Created by ryosuke on 2018/02/13.
 */

public class Thermosiphon implements Pump {

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
