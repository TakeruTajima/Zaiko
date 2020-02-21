package com.mr2.zaiko.zaiko2.domain.inhouse.equipment;

import androidx.annotation.NonNull;

import com.mr2.zaiko.zaiko2.domain.common.ValueObject;

import java.util.Objects;

public final class Photo extends ValueObject {
    @NonNull private final String address;

    public Photo(@NonNull String address) {
        this.address = address;
    }

    @NonNull
    public String address() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Photo)) return false;
        Photo photo = (Photo) o;
        return address.equals(photo.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }
}
