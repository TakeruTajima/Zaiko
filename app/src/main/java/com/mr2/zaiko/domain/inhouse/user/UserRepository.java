package com.mr2.zaiko.domain.inhouse.user;

import java.util.Collection;

public interface UserRepository {
    //ユーザを保存する
    User save(User user);

    //氏名コードから検索する
    User findByNameCode(NameCode nameCode);

    //全てのユーザを取得する
    Collection<User> getAll();

    //ユーザを認証する
    User authenticate(User user);

    //ユーザを削除する
    void remove(User user);

    //全てのユーザを削除する
    void removeAll();

}
