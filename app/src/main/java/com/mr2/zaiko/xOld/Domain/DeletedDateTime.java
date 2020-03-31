package com.mr2.zaiko.xOld.Domain;

import androidx.annotation.NonNull;

import com.mr2.zaiko.xOld.Infra.MyDateFormat;

import java.util.Date;
import java.util.Objects;
@Deprecated
public class DeletedDateTime {
    private final long deletedMilliSec;

    private DeletedDateTime(long deletedMilSec) {
        this.deletedMilliSec = deletedMilSec;
    }

    public static DeletedDateTime of(Date deletedAt){
        return new DeletedDateTime(deletedAt.getTime());
    }

    public static DeletedDateTime getDefault(){
        return new DeletedDateTime(0);
    }

    public Date toDate(){
        return new Date(deletedMilliSec);
    }

    @NonNull
    @Override
    public String toString() {
        return MyDateFormat.dateToString(new Date(deletedMilliSec));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeletedDateTime that = (DeletedDateTime) o;
        return Objects.equals(deletedMilliSec, that.deletedMilliSec);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deletedMilliSec);
    }
}
