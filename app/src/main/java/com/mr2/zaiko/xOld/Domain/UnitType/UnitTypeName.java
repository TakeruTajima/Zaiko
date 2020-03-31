package com.mr2.zaiko.xOld.Domain.UnitType;

import com.mr2.zaiko.xOld.Domain.ValidateResult;

import java.util.Objects;

public class UnitTypeName {
    private static final int SIZE_MIN = 1;
    private static final int SIZE_MAX = 10;

    private final String name;

    private UnitTypeName(String name) {
        this.name = name;
    }

    public static UnitTypeName of(String name){
        if (validate(name) != ValidateResult.Validity)
            throw new IllegalArgumentException("文字数が不正です");
        return new UnitTypeName(name);
    }

    //presentation層でチェックするといいと思うよ
    public static ValidateResult validate(String name){
        if (null == name)
            return ValidateResult.NullIsNotAllowed;
        if (SIZE_MIN > name.length())
            return ValidateResult.NumberOfCharactersLack;
        if (SIZE_MAX < name.length())
            return ValidateResult.NumberOfCharactersOver;
        return ValidateResult.Validity;
    }

    //presentation層でTextViewなんかに渡すといいかも
    public static String getHint(){
        return SIZE_MIN + "～" + SIZE_MAX + "文字";
    }

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
        UnitTypeName name1 = (UnitTypeName) o;
        return name.equals(name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
