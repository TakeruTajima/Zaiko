package com.mr2.zaiko.Domain.Product;

import androidx.annotation.NonNull;
import java.util.Objects;

public class Model {
    private final String model;

    public Model(String model) {
        if (!validate(model)) throw new IllegalArgumentException("1～150文字");
        this.model = model;
    }

    public boolean validate(String model){
        return (1 <= model.length() && 150 >= model.length());
    }

    public String getModel() {
        return model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Model that = (Model) o;
        return Objects.equals(model, that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model);
    }

    @NonNull
    @Override
    public String toString() {
        return model;
    }
}
