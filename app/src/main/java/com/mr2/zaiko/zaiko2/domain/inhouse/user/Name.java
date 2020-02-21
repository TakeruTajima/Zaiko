package com.mr2.zaiko.zaiko2.domain.inhouse.user;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mr2.zaiko.zaiko2.domain.common.ValueObject;

import java.util.Objects;

public class Name extends ValueObject {
    @NonNull private final String firstName;
    @Nullable private final String middleName;
    @NonNull private final String familyName;
    //読み仮名いる？オブジェクト指向的にはアリかもだけど入力させるの？
    // ミドルネームやセカンドネーム、ファーストネームが複数ある国もあるらしいよ

    public Name(@NonNull String firstName, @Nullable String middleName, @NonNull String familyName) {
        super();
        assertArgumentNotEmpty(firstName, "名前を入力してください。");
        assertArgumentNotEmpty(familyName, "苗字を入力してください。");
        this.firstName = firstName;
        this.middleName = middleName;
        this.familyName = familyName;
    }

    @NonNull
    public String firstName() {
        return firstName;
    }

    @NonNull
    public String middleName() {
        if (null == middleName) return "";
        return middleName;
    }

    @NonNull
    public String familyName() {
        return familyName;
    }

    public String getFullNameLeftFamily(){
        if (null == middleName) return familyName + " " + firstName;
        return familyName + " " + middleName + " " + firstName;
    }

    public String getFullNameRightFamily(){
        if (null == middleName) return firstName + " " + familyName;
        return firstName + " " + middleName + " " + familyName;
    }

    @NonNull
    @Override
    public String toString() {
        return "Name{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", familyName='" + familyName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return firstName.equals(name.firstName) &&
                Objects.equals(middleName, name.middleName) &&
                familyName.equals(name.familyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, middleName, familyName);
    }
}
