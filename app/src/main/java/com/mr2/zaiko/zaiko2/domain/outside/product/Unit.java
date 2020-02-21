package com.mr2.zaiko.zaiko2.domain.outside.product;

import androidx.annotation.NonNull;

import com.mr2.zaiko.zaiko2.domain.common.ValueObject;

import java.util.Objects;

public class Unit extends ValueObject {
    @NonNull private final String unit;

    public Unit(@NonNull String unit) {
        assertArgumentLength(unit, 1, 100, "単位は1～100文字で入力してください。");
        this.unit = unit;
    }

    private Unit(){
        this.unit = "";
    }

    public static Unit getDefault(){ return new NullUnit(); }

    public String unit() {
        return unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unit unit1 = (Unit) o;
        return unit.equals(unit1.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit);
    }

    private static class NullUnit extends Unit{
        public NullUnit() { super(); }
    }
}
