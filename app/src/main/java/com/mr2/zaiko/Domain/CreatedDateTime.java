package com.mr2.zaiko.Domain;

import androidx.annotation.NonNull;

import com.mr2.zaiko.Infra.MyDateFormat;

import java.util.Date;
import java.util.Objects;

public class CreatedDateTime {
    private final Date createdAt;

    private CreatedDateTime(Date createdAt) {
        this.createdAt = createdAt;
    }

    public static CreatedDateTime of(Date createdAt){
        return new CreatedDateTime(createdAt);
    }

    public Date toDate(){
        return createdAt;
    }

    @NonNull
    @Override
    public String toString() {
        return MyDateFormat.dateToString(createdAt);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreatedDateTime that = (CreatedDateTime) o;
        return createdAt.equals(that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdAt);
    }
}
