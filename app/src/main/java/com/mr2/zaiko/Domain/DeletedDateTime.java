package com.mr2.zaiko.Domain;

import androidx.annotation.NonNull;

import com.mr2.zaiko.Infra.MyDateFormat;

import java.util.Date;
import java.util.Objects;

public class DeletedDateTime {
    private final long deletedMilSec;

    private DeletedDateTime(long deletedMilSec) {
        this.deletedMilSec = deletedMilSec;
    }

    public static DeletedDateTime of(Date deletedAt){
        return new DeletedDateTime(deletedAt.getTime());
    }

    public static DeletedDateTime getDefault(){
        return new DeletedDateTime(0);
    }

    public Date toDate(){
        return new Date(deletedMilSec);
    }

    @NonNull
    @Override
    public String toString() {
        return MyDateFormat.dateToString(new Date(deletedMilSec));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeletedDateTime that = (DeletedDateTime) o;
        return Objects.equals(deletedMilSec, that.deletedMilSec);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deletedMilSec);
    }
}
