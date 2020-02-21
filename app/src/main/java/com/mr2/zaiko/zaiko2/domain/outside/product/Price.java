package com.mr2.zaiko.zaiko2.domain.outside.product;

import com.mr2.zaiko.zaiko2.domain.common.ValueObject;

import java.util.Objects;

public class Price extends ValueObject {
    private final int price;
    private final String currencyUnit;

    public Price(int price, String currencyUnit) {
        assertArgumentRange(price, 0, 9999999, "金額が不正です。");
        assertArgumentLength(currencyUnit, 1, 150, "単位を入力してください。");
        this.price = price;
        this.currencyUnit = currencyUnit;
    }

    private Price(){ this.price = -1; this.currencyUnit = ""; }

    public static Price getDefault(){ return new NullPrice(); }

    public String price(){
        return price + currencyUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price1 = (Price) o;
        return price == price1.price &&
                currencyUnit.equals(price1.currencyUnit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, currencyUnit);
    }

    private static class NullPrice extends Price{
        public NullPrice(){ super(); }
    }
}
