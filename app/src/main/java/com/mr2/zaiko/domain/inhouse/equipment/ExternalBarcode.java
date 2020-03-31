package com.mr2.zaiko.domain.inhouse.equipment;

import androidx.annotation.NonNull;

import com.mr2.zaiko.domain.common.ValueObject;

import java.util.Objects;

public final class ExternalBarcode extends ValueObject {
    @NonNull private final String code;

    public ExternalBarcode(@NonNull String code) {
        this.code = code;
    }

    public String code(){ return code; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExternalBarcode)) return false;
        ExternalBarcode that = (ExternalBarcode) o;
        return code.equals(that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
