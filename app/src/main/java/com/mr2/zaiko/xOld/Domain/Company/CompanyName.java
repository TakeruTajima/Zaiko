package com.mr2.zaiko.xOld.Domain.Company;

import android.util.Log;

import androidx.annotation.NonNull;

import com.mr2.zaiko.xOld.Domain.ValidateResult;

import java.util.Objects;

public class CompanyName {
    private static final int SIZE_MIN = 1;
    private static final int SIZE_MAX = 50;
    private final String name;

    private CompanyName(String name) {
        this.name = name;
    }

    public static CompanyName of(String name){
        ValidateResult result = validate(name);
        if (result != ValidateResult.Validity)
            throw new IllegalArgumentException("不正です。name{" + name + "} result{" + result.name() + "}");
        return new CompanyName(name);
    }

    public static ValidateResult validate(String name){
        if (null == name) return ValidateResult.NullIsNotAllowed;
            Log.d("", "ValidateResult validate name{" + name + " length=" + name.length());
        if (SIZE_MIN > name.length())return ValidateResult.NumberOfCharactersLack;
        if (SIZE_MAX < name.length())return ValidateResult.NumberOfCharactersOver;

        return ValidateResult.Validity;
    }

    public static String getHint(){
        return SIZE_MIN + "～" + SIZE_MAX + "文字";
    }

    public String value(){
        return name;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyName name1 = (CompanyName) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
