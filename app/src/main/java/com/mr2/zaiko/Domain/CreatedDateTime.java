package com.mr2.zaiko.Domain;

import androidx.annotation.NonNull;

import com.mr2.zaiko.Infra.MyDateFormat;

import java.util.Date;
import java.util.Objects;

public class CreatedDateTime {
    private final long createdMilliSec;

    private CreatedDateTime(long createdMilSec) {
        this.createdMilliSec = createdMilSec;
    }

    public static CreatedDateTime of(Date createdAt){
        return new CreatedDateTime(createdAt.getTime());
    }

    public static CreatedDateTime getDefault(){
        return new CreatedDateTime(0);
    }

    public Date toDate(){
        return new Date(createdMilliSec);
    }

    @NonNull
    @Override
    public String toString() {
        Date createdAt = new Date(createdMilliSec);
        return MyDateFormat.dateToString(createdAt);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreatedDateTime that = (CreatedDateTime) o;
        return Objects.equals(createdMilliSec, that.createdMilliSec);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdMilliSec);
    }

}
