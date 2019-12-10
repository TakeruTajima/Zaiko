package com.mr2.zaiko.Domain.Product;

import com.mr2.zaiko.Domain.Item.InHouseCode;

public interface ProductDetail {

    void changeUnitType(int unitType_id); //1以上
    void changeValue(int newValue); //1-9,999,999
    void enableTakeStock();
    void disableTakeStock();

    int getProductIdentity();
    int getInHouseCode();
    String getUnitType();
    int getValue();
    boolean takeStock();
}
