package com.mr2.zaiko.xOld.Domain.Product;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mr2.zaiko.xOld.Domain.Item.InHouseCode;
import com.mr2.zaiko.xOld.Domain.UnitType.Unit;

class ProductInfoImpl implements ProductInfo {
    @NonNull private final Identity productIdentity;
    @NonNull private final InHouseCode inHouseCode;
    private int values;
    @Nullable private Unit unit;
    private boolean takeStock;

    public ProductInfoImpl(@NonNull Identity productIdentity, @NonNull InHouseCode inHouseCode) {
        this.productIdentity = productIdentity;
        this.inHouseCode = inHouseCode;
    }

    /**
     * 管理単位を変更します。
     *
     * @param unit 管理単位
     */
    @Override
    public void changeUnitType(Unit unit) {
        //存在チェックを入れたいところ...VOの生成をRepository限定にすれば不要？これが要るならIdとかも必要になるし。
        this.unit = unit;
    }

    /**
     * 管理単価を変更します。範囲：0-9,999,999
     * 範囲外を指定した場合、IllegalArgumentExceptionを返します。
     *
     * @param newValue 管理単価
     */
    @Override
    public void changeValue(int newValue) {
        if (0 > newValue || 9999999 < newValue) throw new IllegalArgumentException("金額が不正です。");

    }

    /**
     * たな卸しを「する」有効にします。
     */
    @Override
    public void enableTakeStock() {
        this.takeStock = true;
    }

    /**
     * たな卸しを「しない」無効にします。
     */
    @Override
    public void disableTakeStock() {
        this.takeStock = false;
    }

    /**
     * 部品の一意の識別子を返します。
     *
     * @return int ProductIdentity 部品の識別子
     */
    @Override
    public int getProductIdentity() {
        return productIdentity.getIdentity();
    }

    /**
     * 社内コードを返します。社内コードは、0埋め3桁のメーカーID+0埋め4桁の通し番号で構成されます。バーコード生成用
     *
     * @return int InHouseCode 部品の社内コード
     */
    @Override
    public int getInHouseCode() {
        return inHouseCode.value();
    }

    /**
     * 管理単位を文字列で返します。
     * 管理単位が設定されていない場合、0文字のStringを返します。
     * @return 管理単位
     */
    @Override
    public String getUnit() {
        if (null == unit) return "";
        return unit.getName().value();
    }

    /**
     * 管理単価をintで返します。
     *
     * @return 管理単価
     */
    @Override
    public int getValue() {
        return values;
    }

    /**
     * たな卸し設定をBooleanで返します。true→「たな卸しをする」、false→「たな卸しをしない」
     *
     * @return たな卸し設定
     */
    @Override
    public boolean takeStock() {
        return takeStock;
    }
}
