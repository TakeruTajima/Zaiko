package com.mr2.zaiko.domain.inhouse.user;

public enum Authority {
    GUEST, //見るだけ
    NORMAL, //一般　部品登録とか出庫とか購入依頼とか登録系ができる
    MANAGER, //管理職　発注出力とか他ユーザーのクラス変更とかできる
    ADMIN //システム管理者　マスタの編集とかシステム回りが出来る　管理職権限はない
}
