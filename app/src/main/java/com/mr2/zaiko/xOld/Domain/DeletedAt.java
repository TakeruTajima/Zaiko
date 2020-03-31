package com.mr2.zaiko.xOld.Domain;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class DeletedAt extends MyDateTime {
    DeletedAt(@NonNull Date date, @Nullable ZoneId zoneId) {
        super(date, zoneId);
    }

    DeletedAt(@NonNull LocalDateTime localDateTime, @Nullable ZoneId zoneId) {
        super(localDateTime, zoneId);
    }

    public DeletedAt(ZonedDateTime zonedDateTime) {
        super(zonedDateTime);
    }

    @Override
    public boolean validate(Date date) {
        return false;
    }
}
