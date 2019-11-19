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
        if (c.moveToFirst()){
            do {
                int _id = -1;
                String model = null;
                String name = null;
                int maker_id = -1;
                int inHouseCode = -1;
                int unitType_id = -1;
                int value = -1;
                boolean doNotStockTake = false;
                Date createdAt = null;
                Date deletedAt = null;

                _id = c.getInt(c.getColumnIndex("_id"));
                model = c.getString(c.getColumnIndex("model"));
                name = c.getString(c.getColumnIndex("name"));
                maker_id = c.getInt(c.getColumnIndex("company_id"));
                    Company dummyCompany = Company.of(Id.of(maker_id));
                    int targetCompanyIndex = makerList.indexOf(dummyCompany); //todo:ここ動くか不安 インスタンス判定かequals判定か
                inHouseCode = c.getInt(c.getColumnIndex("in_house_code"));
                unitType_id = c.getInt(c.getColumnIndex("unit_type_id"));
                    int targetUnitTypeIndex = unitTypeList.indexOf(UnitType.of(Id.of(unitType_id))); //todo:ここも不安
                value = c.getInt(c.getColumnIndex("value"));
                if (1 == c.getInt(c.getColumnIndex("dont_stocktake")))
                    doNotStockTake = true;
                createdAt = MyDateFormat.stringToDate(c.getString(c.getColumnIndex("created_at")));
                deletedAt = MyDateFormat.stringToDate(c.getString(c.getColumnIndex("deleted_at")));
                if (-1 == targetCompanyIndex || -1 == targetUnitTypeIndex)
                    throw new IllegalArgumentException("渡されたListからオブジェクトが見つかりません。"); //todo:落ちる
                Company maker = makerList.get(targetCompanyIndex);

                Item item = new Item(
                        Id.of(_id),
                        ItemModel.of(model),
                        ItemName.of(name),
                        maker,
                        InHouseCode.of(inHouseCode),
                        unitTypeList.get(targetUnitTypeIndex),
                        value,
                        doNotStockTake,
                        CreatedDateTime.of(createdAt),
                        DeletedDateTime.of(deletedAt));
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
