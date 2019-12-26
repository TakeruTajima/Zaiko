package com.mr2.zaiko.Domain.Item;

import com.mr2.zaiko.Domain.ValidateResult;

import java.util.Objects;

/**
 * 部品の社内管理番号。バーコードに使用する。
 * 3桁のメーカー番号と4桁のメーカー内部品通し番号で構成。
 * ex) メーカー番号「1」のふたつめの部品　=>　0010002
 */
public class InHouseCode {
    private static final int COMPANY_RANGE_MIN = 1;
    private static final int COMPANY_RANGE_MAX = 999;
    private static final int RANGE_MIN = 1;
    private static final int RANGE_MAX = 9999;

    private final int companyCode;
    private final int code;

    @Deprecated
    private InHouseCode(int code) {
        this.companyCode = -1;
        this.code = code;
    }

    private InHouseCode(int companyCode, int code) {
        this.companyCode = companyCode;
        this.code = code;
    }

    @Deprecated
    public static InHouseCode of(int code){
        if (validate(code) != ValidateResult.Validity)
            throw new IllegalArgumentException("不正です");
        return new InHouseCode(code);
    }

    public static InHouseCode of(int companyCode, int code){
        if (validateCompany(companyCode) != ValidateResult.Validity)
            throw new IllegalArgumentException("会社コードが不正");
        if (validate(code) != ValidateResult.Validity)
            throw new IllegalArgumentException("部品コードが不正");
        return new InHouseCode(companyCode, code);
    }

    public static InHouseCode getDefault(){
        return new InHouseCode(-1,-1);
    }

    public static ValidateResult validateCompany(int companyCode){
        if (COMPANY_RANGE_MIN > companyCode || COMPANY_RANGE_MAX < companyCode)
            return ValidateResult.NumberOutOfRange;
        return ValidateResult.Validity;
    }

    public static ValidateResult validate(int code){
        if (RANGE_MIN > code || RANGE_MAX < code)
            return ValidateResult.NumberOutOfRange;

        return ValidateResult.Validity;
    }

    @Deprecated
    public static String getHint(){
        return RANGE_MIN + "～" + RANGE_MAX;
    }

    @Deprecated
    public int value(){
        return (companyCode * 10000) + code;
    }

    /**
     *
     * @return 左0埋め7桁のString
     */
    public String getCodeString(){
        String inHouseCode = (companyCode * 10000) + code + "";
        return String.format("%7s", inHouseCode).replace(" ", "0");
    }

    @Override
    public String toString() {
        return "InHouseCode{" +
                "companyCode=" + companyCode +
                ", code=" + code +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InHouseCode that = (InHouseCode) o;
        return companyCode == that.companyCode &&
                code == that.code;
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyCode, code);
    }
}
