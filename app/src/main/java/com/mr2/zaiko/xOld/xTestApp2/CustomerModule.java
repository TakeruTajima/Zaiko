package com.mr2.zaiko.xOld.xTestApp2;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ryosuke on 2018/02/13.
 */
@Module
class CustomerModule {

    @CustomerScope
    @Provides
    static Customer provdeCustomer(){
        return new Customer();
    }
}
