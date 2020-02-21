package com.mr2.zaiko.zaiko2.xTestApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DripCoffeeModule {

    @Provides
    static Pump providePump(Thermosiphon pump){
        return pump;
    }

    @Singleton
    @Provides static Heater provideHeater(){
        return new ElectricHeater();
    }
}
