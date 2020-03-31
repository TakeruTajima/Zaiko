package com.mr2.zaiko.xOld.xTestApplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mr2.zaiko.R;


public class MainActivity extends AppCompatActivity {

    private CoffeeShop coffeeShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coffeeShop = DaggerCoffeeShop
                .builder()
//                .dripCoffeeModule(new DripCoffeeModule())
//** Module内のメソッドが全て静的の場合Moduleのインスタンスは必要なし **//
                .build();
        // 「DaggerMainActivity_CoffeeShop」はビルド時に@Componentから自動生成
        //

        CoffeeMaker coffeeMaker = coffeeShop.maker();
        // 「CoffeeShop」インターフェースの
        // 「maker()」メソッドから返ってくる「CoffeeMaker」型の
        coffeeMaker.drip();
        // 「drip()」を叩いている。
    }
}
