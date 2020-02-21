package com.mr2.zaiko.zaiko2.xTestApp2;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ryosuke on 2018/02/13.
 */
@Module
class DripCoffeeModule{

    @Provides static Pump providePump(Thermosiphon pump){
        return pump;
    }

    @Singleton
    @Provides
    static Heater provideHeater(){
        return new ElectricHeater();
    }
}

