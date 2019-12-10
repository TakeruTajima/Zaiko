package com.mr2.zaiko.Domain;

import androidx.annotation.NonNull;

import java.util.Date;
import java.util.Objects;

public abstract class MyDateTime {
    private final long dateTimeMilliSec;

    protected MyDateTime(long dateTimeMilliSec) {
        this.dateTimeMilliSec = dateTimeMilliSec;
    }


    public Date get(){
        return new Date(dateTimeMilliSec);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyDateTime that = (MyDateTime) o;
        return dateTimeMilliSec == that.dateTimeMilliSec;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTimeMilliSec);
    }

    @NonNull
    @Override
    public String toString() {
        return new Date(dateTimeMilliSec).toString();
    }
}
