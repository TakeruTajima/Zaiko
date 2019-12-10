package com.mr2.zaiko.Domain.Product;

import androidx.annotation.NonNull;
import java.util.Objects;

public class Identity {
    private final int identity;

    public Identity(int identity) {
        if (!validate(identity)) throw new IllegalArgumentException("商品IDは1始まり");
        this.identity = identity;
    }

    public boolean validate(int identity){
        return (identity > 0);
    }

    public int getIdentity() {
        return identity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Identity that = (Identity) o;
        return identity == that.identity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identity);
    }

    @NonNull
    @Override
    public String toString() {
        return "" + identity;
    }
}
