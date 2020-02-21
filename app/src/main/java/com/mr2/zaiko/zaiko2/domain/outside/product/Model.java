package com.mr2.zaiko.zaiko2.domain.outside.product;

import com.mr2.zaiko.zaiko2.domain.common.ValueObject;

import java.util.Objects;

public class Model extends ValueObject {
    private final String model;

    public Model(String model) {
        assertAlgumentAlphanumeric(model, "型式は半角英数字で入力してください。");
        assertArgumentLength(model, 1, 150, "型式は1～150文字で入力してください。");
        this.model = model;
    }

    public String model(){ return model; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Model model1 = (Model) o;
        return model.equals(model1.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model);
    }
}
