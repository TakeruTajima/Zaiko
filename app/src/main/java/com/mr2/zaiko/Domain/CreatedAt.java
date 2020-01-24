package com.mr2.zaiko.Domain;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class CreatedAt extends MyDateTime {

    CreatedAt(@NonNull Date date, @Nullable ZoneId zoneId) {
        super(date, zoneId);
    }

    CreatedAt(@NonNull LocalDateTime localDateTime, @Nullable ZoneId zoneId) {
        super(localDateTime, zoneId);
    }

    public CreatedAt(ZonedDateTime zonedDateTime) {
        super(zonedDateTime);
    }

    @Override
    public boolean validate(Date date) {
        return false;
    }
}
