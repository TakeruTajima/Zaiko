package com.mr2.zaiko.xOld.Domain.Item;

import com.mr2.zaiko.xOld.Domain.ValidateResult;

import java.util.Objects;

public class ItemModel {
    private static final int SIZE_MIN = 1;
    private static final int SIZE_MAX = 50;

    private final String name;

    private ItemModel(String name) {
        this.name = name;
    }

    public static ItemModel of(String name){
        if (validate(name) != ValidateResult.Validity)
            throw new IllegalArgumentException("入力が不正です");
        return new ItemModel(name);
    }

    public static ValidateResult validate(String name){
        if (null == name) return ValidateResult.NullIsNotAllowed;
        if (SIZE_MIN > name.length()) return ValidateResult.NumberOfCharactersLack;
        if (SIZE_MAX < name.length()) return ValidateResult.NumberOfCharactersOver;

        return ValidateResult.Validity;
    }

    public static String getHint(){return "文字数:" + SIZE_MIN + "～" + SIZE_MAX + "文字";}

    public String value(){
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemModel model = (ItemModel) o;
        return Objects.equals(name, model.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
