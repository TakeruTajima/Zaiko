package com.mr2.zaiko.Domain.User;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

//TODO:ここにドメインやアプリケーションの制約を持ち込まないこと！
// Repositoryの関心事は永続化のみ。
// 続行不可能な場合はDomainやApplication層の責任として例外を投げる。他のことは知らんもんでヨシ


public interface UserRepository{
    /**
     * 新規ユーザーを登録します。
     * @param userCode ユーザーID・氏名コード。社内で管理されている一意の文字列
     * @param userName ユーザーの氏名
     * @param password パスワード
     * @return 登録したユーザー
     * @throws IllegalArgumentException
     * ユーザーコードが不正の場合、パスワードが規格外の場合、
     */
    @Nullable
    UserInfo registerUser(@NonNull String userCode, @NonNull UserName userName, @NonNull String password)
            throws IllegalArgumentException;

    /**
     * ユーザー新規登録前のユーザーコード取得メソッド。
     * @param userCode 使いたいユーザーコードのString
     * @return ユーザーコード。userCodeが登録済みだった場合はNull。
     */
    @Nullable
    UserHeader existsUser(@NonNull String userCode);

    /**
     *
     * @return 登録済のユーザーのリスト。0件の場合はsize=0のList
     */
    List<UserHeader> getUserList();

    /**
     * @param userCode ユーザーコード
     * @param password パスワード
     * @return ログインに成功したユーザー。失敗した場合はNull
     */
    @Nullable
    UserInfo userLogin(String userCode, String password);

    /**
     * ユーザー情報を更新する。現状は氏名のみ。
     * @param user 対象となるユーザー
     * @return ユーザーを再度読み出し。
     * @throws IllegalArgumentException ユーザーが不正
     */
    @Nullable
    UserInfo updateUserInfo(@NonNull UserInfo user) throws IllegalArgumentException;

    //todo:logout いる？ログ残す？タイムアウトでUserが無効になったらこのメソッド叩く？じどうで？
//    void userLogout(User user);

    /**
     * 他ユーザーに権限を与える
     * @param manager Manager権限を持つユーザー
     * @param targetUserCode 権限を受け取るユーザーのユーザーコード
     * @param authority 与えられる権限
     * @throws IllegalArgumentException
     * managerにManager権限がない場合(UN_AUTHORIZED_USER)、targetUserCodeが間違っている場合(WRONG_USER_CODE)
     */
    void authorizeUser(@NonNull UserInfo manager, @NonNull String targetUserCode, @NonNull UserAuthority authority)
            throws IllegalArgumentException;

    /**
     * 他ユーザーのパスワードを変更する
     * @param manager Manager権限を持つユーザー
     * @param targetUserCode 対象となるユーザーのユーザーコード
     * @param newPassword 新しいパスワード
     * @throws IllegalArgumentException
     * managerにManager権限がない場合(UN_AUTHORIZED_USER)、
     * targetUserCodeが間違っている場合(WRONG_USER_CODE)、newPasswordが規格外の場合(WRONG_PASSWORD)
     */
     void changePasswordByManager(@NonNull UserInfo manager, @NonNull String targetUserCode, @NonNull String newPassword)
             throws IllegalArgumentException;

    /**
     * ユーザーを非活性化します
     * @param user 無効化したいユーザー
     * @throws IllegalArgumentException-ユーザーが存在しない
     */
     void deactivateUser(User user) throws IllegalArgumentException;

    /**
     * 非活性のユーザーを活性化させます
     * @param userCode 対象のユーザーコード
     * @throws IllegalArgumentException-ユーザーが存在しない
     */
     void activateUser(int userCode) throws IllegalArgumentException;

    /**
     * 非活性ユーザーのリストを取得
     * @return 非活性ユーザーのリスト
     */
     List<UserHeader> getInactiveUser();

}
