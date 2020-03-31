package com.mr2.zaiko.xOld.Domain;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class UpdateAt extends MyDateTime {
    UpdateAt(@NonNull Date date, @Nullable ZoneId zoneId) {
        super(date, zoneId);
    }

    UpdateAt(@NonNull LocalDateTime localDateTime, @Nullable ZoneId zoneId) {
        super(localDateTime, zoneId);
    }

    public UpdateAt(ZonedDateTime zonedDateTime) {
        super(zonedDateTime);
    }

    @Override
    public boolean validate(Date date) {
        return false;
    }
}
