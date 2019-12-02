package com.mr2.zaiko.Domain.Item;

import com.mr2.zaiko.Domain.ValidateResult;

import java.util.Objects;

public class InHouseCode {
    private static final int SIZE_MIN = 1;
    private static final int SIZE_MAX = 99999;

    private final int code;

    private InHouseCode(int code) {
        this.code = code;
    }

    public static InHouseCode of(int code){
        if (validate(code) != ValidateResult.Validity)
            throw new IllegalArgumentException("不正です");
        return new InHouseCode(code);
    }

    public static InHouseCode getDefault(){
        return new InHouseCode(-1);
    }

    public static ValidateResult validate(int code){
        if (SIZE_MIN > code || SIZE_MAX < code)
            return ValidateResult.NumberOutOfRange;

        return ValidateResult.Validity;
    }

    public static String getHint(){
        return SIZE_MIN + "～" + SIZE_MAX;
    }

    public int value(){
        return code;
    }

    public String getCodeString(){
        return String.valueOf(code);
    }

    @Override
    public String toString() {
        return "InHouseCode{" +
                "code=" + code +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InHouseCode that = (InHouseCode) o;
        return code == that.code;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
