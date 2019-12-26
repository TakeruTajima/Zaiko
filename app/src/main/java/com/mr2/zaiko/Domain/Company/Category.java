package com.mr2.zaiko.Domain.Company;

import androidx.annotation.NonNull;

import java.util.Objects;

@Deprecated
public class Category {
    private final boolean isMaker;
    private final boolean isSeller;

    private Category(boolean isMaker, boolean isSeller) {
        this.isMaker = isMaker;
        this.isSeller = isSeller;
    }

    public static Category of(boolean isMaker, boolean isSeller){
        if (!(isMaker || isSeller)){
            throw new IllegalArgumentException("どちらかはtrueにしてください。");
        }
        return new Category(isMaker, isSeller);
    }

    public static Category maker(){
        return new Category(true, false);
    }

    public static Category seller(){
        return new Category(false, true);
    }

    public static Category makerAndSeller(){
        return new Category(true, true);
    }

    public boolean isMaker() {
        return isMaker;
    }

    public boolean isSeller() {
        return isSeller;
    }

    public String value() {
        if (isMaker && !isSeller){
            return "製造";
        }
        if (isSeller && !isMaker){
            return "販売";
        }
        if (isMaker){
            return "製造・販売";
        }
        throw new IllegalStateException("メーカーでも販売会社でもありません。");
    }

    @Override
    public String toString() {
        return "Category{" +
                "isMaker=" + isMaker +
                ", isSeller=" + isSeller +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return isMaker == category.isMaker &&
                isSeller == category.isSeller;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isMaker, isSeller);
    }
}
