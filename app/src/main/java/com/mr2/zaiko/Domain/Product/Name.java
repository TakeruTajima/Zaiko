package com.mr2.zaiko.Domain.Product;

import androidx.annotation.NonNull;
import java.util.Objects;

public class Name {
    private final String name;

    public Name(String name) throws IllegalArgumentException{
        if (!validate(name)) throw new IllegalArgumentException("1～150文字");
        this.name = name;
    }

    public static boolean validate(@NonNull String name){
        return 1 <= name.length() && 150 >= name.length();
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
