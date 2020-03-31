package com.mr2.zaiko.xOld.Domain.Maker;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Code {
    private final int companyCode;

    public Code(int companyCode) {
        if (0 >= companyCode) throw new IllegalArgumentException("IDは1から始まります");
        this.companyCode = companyCode;
    }

    public int getCompanyCode() {
        return companyCode;
    }

    @NonNull
    @Override
    public String toString() {
        return "" + companyCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Code)) return false;
        Code code = (Code) o;
        return companyCode == code.companyCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyCode);
    }
}
