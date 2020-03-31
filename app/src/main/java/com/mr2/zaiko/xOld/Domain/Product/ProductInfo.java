package com.mr2.zaiko.xOld.Domain.Product;

import com.mr2.zaiko.xOld.Domain.UnitType.Unit;

public interface ProductInfo {

    /**
     * 管理単位を変更します。
     * 存在しない管理単位(Unit)が指定された場合、IllegalArgumentExceptionを返します。
     * @param unit 管理単位
     */
    void changeUnitType(Unit unit); //1以上

    /**
     * 管理単価を変更します。範囲：0-9,999,999
     * 範囲外を指定した場合、IllegalArgumentExceptionを返します。
     * @param newValue 管理単価
     */
    void changeValue(int newValue); //0-9,999,999

    /**
     * たな卸しを「する」有効にします。
     */
    void enableTakeStock();

    /**
     * たな卸しを「しない」無効にします。
     */
    void disableTakeStock();

    /**
     * 部品の一意の識別子を返します。
     * @return int ProductIdentity 部品の識別子
     */
    int getProductIdentity();

    /**
     * 社内コードを返します。社内コードは、0埋め3桁のメーカーID+0埋め4桁の通し番号で構成されます。バーコード生成用
     * @return int InHouseCode 部品の社内コード
     */
    int getInHouseCode();

    /**
     * 管理単位を文字列で返します。
     * @return 管理単位
     */
    String getUnit();

    /**
     * 管理単価をintで返します。
     * @return 管理単価
     */
    int getValue();

    /**
     * たな卸し設定をBooleanで返します。true→「たな卸しをする」、false→「たな卸しをしない」
     * @return たな卸し設定
     */
    boolean takeStock();

}
