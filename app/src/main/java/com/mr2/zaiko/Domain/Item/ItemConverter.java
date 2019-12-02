package com.mr2.zaiko.Domain.Item;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.mr2.zaiko.Domain.Company.Company;
import com.mr2.zaiko.Domain.CreatedDateTime;
import com.mr2.zaiko.Domain.DeletedDateTime;
import com.mr2.zaiko.Domain.Id;
import com.mr2.zaiko.Domain.UnitType.UnitType;
import com.mr2.zaiko.Infra.MyDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ItemConverter {
    public static final String TAG = ItemConverter.class.getSimpleName();

    public static List<Item> convert(Cursor c, List<Company> makerList, List<UnitType> unitTypeList){
        List<Item> list = new ArrayList<>();
        if (null != c && c.moveToFirst()){
            final int INDEX_ID = c.getColumnIndexOrThrow("_id");
            final int INDEX_MODEL = c.getColumnIndexOrThrow("model");
            final int INDEX_NAME = c.getColumnIndexOrThrow("name");
            final int INDEX_COMPANY_ID = c.getColumnIndexOrThrow("company_id");
            final int INDEX_IN_HOUSE_CODE = c.getColumnIndexOrThrow("in_house_code");
            final int INDEX_UNIT_TYPE_ID = c.getColumnIndexOrThrow("unit_type_id");
            final int INDEX_VALUE = c.getColumnIndexOrThrow("value");
            final int INDEX_DO_NOT_STOCK_TAKE = c.getColumnIndexOrThrow("dont_stocktake");
            final int INDEX_CREATED_AT = c.getColumnIndexOrThrow("created_at");
            final int INDEX_DELETED_AT = c.getColumnIndexOrThrow("deleted_at");

            do {
                int _id = -1;
                String model = null;
                String name = null;
                int maker_id = -1;
                Company maker = null;
                int inHouseCode = -1;
                int unitType_id = -1;
                UnitType unitType = null;
                int value = -1;
                boolean doNotStockTake = false;
                Date createdAt = null;
                Date deletedAt = null;

                //必須項目
                model = c.getString(INDEX_MODEL);
                name = c.getString(INDEX_NAME);
                maker_id = c.getInt(INDEX_COMPANY_ID);

                Company dummyCompany = Company.of(Id.of(maker_id));
                int targetCompanyIndex = makerList.indexOf(dummyCompany); //todo:ここ動くか不安 インスタンス判定かequals判定か
                if (-1 == targetCompanyIndex)
                    throw new IllegalArgumentException("渡されたListからオブジェクトが見つかりません。");
                maker = makerList.get(targetCompanyIndex);

                unitType_id = c.getInt(INDEX_UNIT_TYPE_ID);
                int targetUnitTypeIndex = unitTypeList.indexOf(UnitType.of(Id.of(unitType_id))); //todo:ここも不安
                if (-1 == targetUnitTypeIndex)
                    throw new IllegalArgumentException("渡されたListからオブジェクトが見つかりません。"); //todo:落ちる
                unitType = unitTypeList.get(targetUnitTypeIndex);

                Item.Builder builder =
                        new Item.Builder(ItemModel.of(model), ItemName.of(name), maker, unitType);
                //オプション
                _id = c.getInt(INDEX_ID);
//                if (-1 != _id)
//                    builder.setId();
                inHouseCode = c.getInt(INDEX_IN_HOUSE_CODE);
                value = c.getInt(INDEX_VALUE);
                if (1 == c.getInt(INDEX_DO_NOT_STOCK_TAKE))
                    doNotStockTake = true;
                createdAt = MyDateFormat.stringToDate(c.getString(INDEX_CREATED_AT));
                if (null != createdAt)
                    builder.setCreatedAt(CreatedDateTime.of(createdAt));
                deletedAt = MyDateFormat.stringToDate(c.getString(INDEX_DELETED_AT));
                if (null != deletedAt)
                    builder.setDeletedAt(DeletedDateTime.of(deletedAt));
                Item item = builder.setId(Id.of(_id))
                        .setInHouseCode(InHouseCode.of(inHouseCode))
                        .setValue(value)
                        .setDoNotStockTake(doNotStockTake)
                        .create();

//                Item item = new Item(
//                        Id.of(_id),
//                        ItemModel.of(model),
//                        ItemName.of(name),
//                        maker,
//                        InHouseCode.of(inHouseCode),
//                        unitTypeList.get(targetUnitTypeIndex),
//                        value,
//                        doNotStockTake,
//                        CreatedDateTime.of(createdAt),
//                        DeletedDateTime.of(deletedAt));
                list.add(item);
            }while (c.moveToNext());
        } else Log.d(TAG, "ItemConverter#convert(Cursor c): c.moveToFirst() is Failed.");
        return list;
    }

    public static ContentValues convert(Item entity){
        ContentValues values = new ContentValues();

        Id _id = entity.get_id();
        ItemModel model = entity.getModel();
        ItemName name = entity.getName();
        Company maker = entity.getMaker();
        InHouseCode inHouseCode = entity.getInHouseCode();
        UnitType unitType = entity.getUnitType();
        int value = entity.getValue();
        boolean doNotStockTake = entity.isDoNotStockTake();
        CreatedDateTime createdAt = entity.getCreatedAt();
        DeletedDateTime deletedAt = entity.getDeletedAt();

        if (null != _id)
            values.put("_id", _id.value());
        if (null != model)
            values.put("model", model.value());
        if (null != name)
            values.put("name", name.value());
        if (null != maker)
            values.put("company_id", maker.get_id().value());
        if (null != inHouseCode)
            values.put("in_house_code", inHouseCode.value());
        if (null != unitType)
            values.put("unit_type_id", unitType.get_id().value());
        if (0 != value)
            values.put("value", value);
        if (doNotStockTake)
            values.put("dont_stocktake", 1);
//        if (null != createdAt)
//            values.put("created_at", createdAt.toString());
//        if (null != deletedAt)
//            values.put("deleted_at", deletedAt.toString());

        return values;
    }
}
