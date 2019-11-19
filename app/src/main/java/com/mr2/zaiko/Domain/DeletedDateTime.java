package com.mr2.zaiko.Domain;

import androidx.annotation.NonNull;

import com.mr2.zaiko.Infra.MyDateFormat;

import java.util.Date;
import java.util.Objects;

public class DeletedDateTime {
    private final Date deletedAt;

    private DeletedDateTime(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public static DeletedDateTime of(Date deletedAt){
        return new DeletedDateTime(deletedAt);
    }

    public Date toDate(){
        return deletedAt;
    }

    @NonNull
    @Override
    public String toString() {
        return MyDateFormat.dateToString(deletedAt);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeletedDateTime that = (DeletedDateTime) o;
        return deletedAt.equals(that.deletedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deletedAt);
    }
}
