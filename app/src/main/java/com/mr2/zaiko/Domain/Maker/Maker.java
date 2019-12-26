package com.mr2.zaiko.Domain.Maker;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Maker {
    @NonNull private final Code companyCode;
    @NonNull private String companyName;

    /**
     * @param companyCode 会社コード
     * @param companyName 会社名
     * @throws IllegalArgumentException-名前の文字数が足りない場合
     */
    public Maker(@NonNull Code companyCode, @NonNull String companyName) throws IllegalArgumentException{
        this.companyCode = companyCode;
        inspectName(companyName);
        this.companyName = companyName;
    }

    /**
     * @param name 会社名
     * @return 変更した会社
     * @throws IllegalArgumentException-文字数が足りない場合
     */
    public Maker changeName(String name) throws IllegalArgumentException{
        inspectName(name);
        this.companyName = name;
        return this;
    }

    private void inspectName(@NonNull String name){
        if (0 == name.length()) throw new IllegalArgumentException("文字数が足りません");
    }

    public int getCode() {
        return companyCode.getCompanyCode();
    }

    @NonNull
    public String getName() {
        return companyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Maker)) return false;
        Maker maker = (Maker) o;
        return companyCode == maker.companyCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyCode);
    }
}
