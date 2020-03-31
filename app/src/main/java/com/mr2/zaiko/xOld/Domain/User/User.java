package com.mr2.zaiko.xOld.Domain.User;

import androidx.annotation.NonNull;

/**
 *
 */
public interface User {

    /**
     *
     * @return ユーザーコードをStringで返します。
     */
    @NonNull
    String getUserCode();

    /**
     *
     * @return ユーザーのフルネームをStringで返します。
     */
    @NonNull
    String getUserName();

    /**
     *
     * @return ユーザーの権限
     */
    @NonNull
    UserAuthority getUserAuthority();

}
