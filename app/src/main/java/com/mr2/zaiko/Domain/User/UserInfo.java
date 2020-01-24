package com.mr2.zaiko.Domain.User;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.time.LocalDateTime;

/**
 * 永続化に関連する処理を行う際に必要となる。
 * UserRepositoryからログインとして取得し、アプリケーション層にメンバとして一定時間保持する。
 *
 * ログインは有効期限があります。有効期限切れの場合IllegalStateExceptionを返します。
 * 各メソッドを呼び出す前にassertExpiration()で確認すること。
 *
 * 各処理で永続化に添付する「ユーザーコード」、
 * 画面に表示する「ユーザーネーム」、
 * ユーザーの有効期限の「有効期限」、
 * 処理の権限判定に使う「ユーザークラス」、
 *
 */
class UserInfo implements User {
    private static final int EXPIRATION_MINUTES = 5;

    @NonNull private final UserCode userCode;
    @NonNull private UserName userName;
    @Nullable private LocalDateTime expirationDateTime;
    @NonNull private final UserAuthority userAuthority;

    UserInfo(@NonNull UserCode userCode, @NonNull UserName userName, @NonNull UserAuthority userAuthority) {
        this.userCode = userCode;
        this.userName = userName;
        this.userAuthority = userAuthority;
        activateExpiration();
    }

    /**
     * ユーザーの名前を変更します。
     *
     * @param name 新しい名前
     * @throws IllegalStateException-ログイン有効期限切れ
     */
//    @Override
    public void changeName(@NonNull UserName name) throws IllegalStateException {
        checkExpiration();
        this.userName = name;
    }

    /**
     * パスワードを変更します。新しいパスワードは2回入力させて間違いを防止してください。
     *
     * @param oldPassword -現在のパスワード
     * @param newPassword -新しいパスワード
     * @throws IllegalStateException-ログイン有効期限切れ
     */
//    @Override
    public void changePassword(@NonNull String oldPassword, @NonNull String newPassword) throws IllegalStateException {
        checkExpiration();
        if (4 > newPassword.length()) throw new IllegalArgumentException("パスワードは4文字以上");
        throw new UnsupportedOperationException("未実装");
    }

    /**
     * @return ユーザーコードをStringで返します。
     * @throws IllegalStateException-ログイン有効期限切れ
     */
    @NonNull
    @Override
    public String getUserCode() throws IllegalStateException {
        checkExpiration();
        return userCode.getCode();
    }

    /**
     * @return ユーザーのフルネームをStringで返します。
     * @throws IllegalStateException-ログイン有効期限切れ
     */
    @NonNull
    @Override
    public String getUserName() throws IllegalStateException {
        checkExpiration();
        return userName.getFullNameLeftFamily();
    }

    /**
     * @return ユーザーの権限
     * @throws IllegalStateException ログイン有効期限切れ
     */
    @NonNull
    @Override
    public UserAuthority getUserAuthority() throws IllegalStateException {
        checkExpiration();
        return userAuthority;
    }

    /**
     * Userのログイン有効期限の確認を行う
     */
    private void checkExpiration() throws IllegalStateException{
        if (LocalDateTime.now().isBefore(expirationDateTime)) //nullだとどうなる？
            throw new IllegalStateException("ログイン有効期限切れ");
        activateExpiration();
    }

    /**
     * ログイン有効期限の延長/有効化
     */
    private void activateExpiration(){
        expirationDateTime = LocalDateTime.now().plusMinutes(EXPIRATION_MINUTES); //ログインの有効期限として現在時刻のEXPIRATION_TIME分後を設定
    }
}
