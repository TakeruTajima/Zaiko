package com.mr2.zaiko.Domain.User;

import androidx.annotation.NonNull;

import java.util.Objects;

public class UserCode {
    @NonNull private final String code;

    /**
     * 重複、捏造を防ぐためリポジトリからのみインスタンス化すること。
     * @param code ユーザーコード
     */
    UserCode(@NonNull String code) {
        if (1 > code.length()) throw new IllegalArgumentException("ユーザーコードを入力してください。");
        this.code = code;
    }

    @NonNull
    public String getCode() {
        return code;
    }

    @NonNull
    @Override
    public String toString() {
        return "UserCode{" +
                "code='" + code + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserCode)) return false;
        UserCode userCode = (UserCode) o;
        return code.equals(userCode.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
