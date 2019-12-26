package com.mr2.zaiko.Domain.User;

import androidx.annotation.NonNull;

import java.util.Objects;

class UserHeader implements User {
    @NonNull private final UserCode userCode;
    @NonNull private final UserName userName;
    @NonNull private final UserAuthority userAuthority;

    public UserHeader(@NonNull UserCode userCode, @NonNull UserName userName, @NonNull UserAuthority userAuthority) {
        this.userCode = userCode;
        this.userName = userName;
        this.userAuthority = userAuthority;
    }

    /**
     * @return ユーザーコードをStringで返します。
     */
    @NonNull
    @Override
    public String getUserCode() {
        return userCode.getCode();
    }

    /**
     * @return ユーザーのフルネームをStringで返します。
     */
    @NonNull
    @Override
    public String getUserName() {
        return userName.getFullNameLeftFamily();
    }

    /**
     * @return ユーザーの権限
     */
    @NonNull
    @Override
    public UserAuthority getUserAuthority() {
        return userAuthority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserHeader)) return false;
        UserHeader that = (UserHeader) o;
        return userCode.equals(that.userCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userCode);
    }
}
