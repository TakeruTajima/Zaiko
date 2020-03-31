package com.mr2.zaiko.xOld.xTestApplication;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = DripCoffeeModule.class)
public interface CoffeeShop {
    CoffeeMaker maker();
}
