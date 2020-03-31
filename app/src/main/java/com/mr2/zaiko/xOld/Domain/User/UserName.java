package com.mr2.zaiko.xOld.Domain.User;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Objects;

/**
 * ValueObject
 */
public class UserName {
    @NonNull private final String firstName;
    @Nullable private final String middleName;
    @NonNull private final String familyName;

    public UserName(@NonNull String firstName, @Nullable String middleName, @NonNull String familyName) {
        if (1 > firstName.length() || 1 > familyName.length()) throw new IllegalArgumentException("氏名を入力してください。");
        this.firstName = firstName;
        this.middleName = middleName;
        this.familyName = familyName;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    @NonNull
    public String getMiddleName() {
        if (null == middleName) return "";
        return middleName;
    }

    @NonNull
    public String getFamilyName() {
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
        return "UserName{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", familyName='" + familyName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserName)) return false;
        UserName userName = (UserName) o;
        return firstName.equals(userName.firstName) &&
                Objects.equals(middleName, userName.middleName) &&
                familyName.equals(userName.familyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, middleName, familyName);
    }
}
