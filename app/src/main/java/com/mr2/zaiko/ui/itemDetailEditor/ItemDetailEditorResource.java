package com.mr2.zaiko.ui.itemDetailEditor;

import com.mr2.zaiko.domain.inhouse.equipment.Equipment;
import com.mr2.zaiko.domain.inhouse.equipment.Keyword;
import com.mr2.zaiko.domain.outside.commodity.Commodity;
import com.mr2.zaiko.domain.outside.company.Company;
import com.mr2.zaiko.domain.outside.product.Product;

import java.util.List;

public class ItemDetailEditorResource {
    //@Final Identity
    //@Final メーカー名
    //@Final 型式
    //製造品名
    //製造単価
    //製造単位　->製造情報
    private Product product;
    private Company maker;

    //管理品名
    //管理単価
    //管理単位　->管理情報
    private Equipment equipment;

    //代表販売商社名
    //代表販売品名
    //代表販売単価
    //代表販売単位　->購入先情報
    // 別画面で登録　タップで開くタイプのリストに追加やら編集削除する感じでどうだろう
    // DetailBrowserからリスト開いてー　商社選択　->品名単価単位登録　
    //　編集削除は　登録済み選択　->編集/削除　で   でも新しい部品を登録するときって順序として逆じゃない？　商社選んでメーカー選んで型式品名入力…
    private List<Commodity> commodityList;
    private List<Company> allCompanyList;

    //キーワード　->閲覧画面で編集する？
    // スペース区切りで入力+登録数順のキーワード一覧から選択　
    // Fragmentとか別画面かな　独自レイアウトのDialogとかでもいいかも
    private List<Keyword> keywordList;

    //ImageResource ->ImageCapture 生成して渡す
    //社外バーコード　->BarcodeReader
    // 編集不可　登録済み一覧から削除
    // ならバーコードは違いわからんし削除対象を読み込む感じになるかな　むしろ削除できなくても？

    //入出庫履歴
    //購入履歴
}
