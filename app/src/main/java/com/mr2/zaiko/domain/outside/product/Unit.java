package com.mr2.zaiko.domain.outside.product;

import androidx.annotation.NonNull;

import com.mr2.zaiko.domain.common.ValueObject;

import java.util.Objects;

public class Unit extends ValueObject {
    private final float quantity;
    @NonNull private final String name;

    public Unit(float quantity, @NonNull String name){
        this.quantity = quantity;
        this.name = name;
    }

    public Unit(@NonNull String name) {
        assertArgumentLength(name, 1, 100, "単位は1～100文字で入力してください。");
        this.quantity = 1;
        this.name = name;
    }

    private Unit(){
        this.quantity = 1;
        this.name = "";
    }

    public static Unit getDefault(){ return new NullUnit(); }

    public String unit() { return "* " + quantity + name; }

    public float quantity() { return quantity; }

    public String name() {
        return name;
    }

    private static class NullUnit extends Unit{
        public NullUnit() { super(); }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Unit)) return false;
        Unit unit = (Unit) o;
        return Float.compare(unit.quantity, quantity) == 0 &&
                name.equals(unit.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, name);
    }
}
