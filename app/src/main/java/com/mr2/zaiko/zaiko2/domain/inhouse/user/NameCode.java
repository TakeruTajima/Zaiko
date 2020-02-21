package com.mr2.zaiko.zaiko2.domain.inhouse.user;

import androidx.annotation.NonNull;

import com.mr2.zaiko.zaiko2.domain.common.ValueObject;

import java.util.Objects;

public class NameCode extends ValueObject {
    @NonNull private final String code;

    public NameCode(@NonNull String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NameCode nameCode = (NameCode) o;
        return code.equals(nameCode.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
