package com.mr2.zaiko.Domain;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Id {
    private static final int MIN = 1;
    private final int _id;

    private Id(int _id) {
        this._id = _id;
    }

    public static Id of(int _id){
        if (MIN > _id){
            throw new IllegalArgumentException("idが不正です");
        }
        return new Id(_id);
    }

    public static Id getDefault(){
        return new Id(-1);
    }

    public int value(){return _id;}

    @NonNull
    @Override
    public String toString() {
        return String.valueOf(_id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Id id = (Id) o;
        return _id == id._id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id);
    }

}
