package com.mr2.zaiko.xOld.Domain.Item;

import com.mr2.zaiko.xOld.Domain.ValidateResult;

import java.util.Objects;

public class ItemName {
    private static final int SIZE_MIN = 1;
    private static final int SIZE_MAX = 150;

    private final String itemName;

    private ItemName(String itemName) {
        this.itemName = itemName;
    }

    public static ItemName of(String itemName){
        if (validate(itemName) != ValidateResult.Validity)
            throw new IllegalArgumentException("不正です");
        return new ItemName(itemName);
    }

    public static ValidateResult validate(String itemName){
        if (null == itemName) return ValidateResult.NullIsNotAllowed;
        if (SIZE_MIN > itemName.length()) return ValidateResult.NumberOfCharactersLack;
        if (SIZE_MAX < itemName.length()) return ValidateResult.NumberOfCharactersOver;

        return ValidateResult.Validity;
    }

    public String value(){
        return itemName;
    }

    @Override
    public String toString() {
        return itemName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemName itemName1 = (ItemName) o;
        return Objects.equals(itemName, itemName1.itemName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName);
    }
}
