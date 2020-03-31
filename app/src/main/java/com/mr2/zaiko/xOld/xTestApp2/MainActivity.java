package com.mr2.zaiko.xOld.xTestApp2;
//
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mr2.zaiko.R;

import javax.inject.Singleton;

import dagger.Component;

public class MainActivity extends AppCompatActivity {

    private CoffeeShop coffeeShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coffeeShop = DaggerMainActivity_CoffeeShop.builder()
//                .dripCoffeeModule(new DripCoffeeModule())
                .build();

        coffeeShop.open().drip();

        CustomerComponent customerComponent = coffeeShop.customerBuilder().build();

        Customer customer = customerComponent.enter();

        System.out.println("客が入店しました");
        System.out.println(customer.login());

        System.out.println("客が退出しました。");
        customer = null;




    }

    @Singleton
    @Component(modules = { DripCoffeeModule.class, HogeModule.class})
    interface CoffeeShop{
        CoffeeMaker open();
        CustomerComponent.Builder customerBuilder();

    }



}
