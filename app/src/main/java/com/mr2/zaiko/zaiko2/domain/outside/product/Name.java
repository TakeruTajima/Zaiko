package com.mr2.zaiko.zaiko2.domain.outside.product;

import androidx.annotation.NonNull;

import com.mr2.zaiko.zaiko2.domain.common.ValueObject;

public class Name extends ValueObject {
    @NonNull private final String name;

    public Name(@NonNull String name) {
        assertArgumentLength(name, 1, 150, "品名は1～150文字で入力してください。");
        this.name = name;
    }

    private Name(){
        this.name = "";
    }

    public static Name getDefault(){
        return new NullName();
    }

    public String name(){ return name; }

    private static class NullName extends Name{
        NullName(){ super(); }
    }
}
