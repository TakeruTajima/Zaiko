package com.mr2.zaiko.Domain.Item;

import android.content.ContentValues;
import android.database.Cursor;

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
        if (!c.moveToFirst()) return new ArrayList<>(0);

        final int INDEX_ID = c.getColumnIndexOrThrow("_id");
        final int INDEX_MODEL = c.getColumnIndexOrThrow("model");
        final int INDEX_NAME = c.getColumnIndexOrThrow("name");
        final int INDEX_PRIMARY_IMAGE_ID = c.getColumnIndexOrThrow("primary_image_id");
        final int INDEX_COMPANY_ID = c.getColumnIndexOrThrow("company_id");
        final int INDEX_IN_HOUSE_CODE = c.getColumnIndexOrThrow("in_house_code");
        final int INDEX_UNIT_TYPE_ID = c.getColumnIndexOrThrow("unit_type_id");
        final int INDEX_VALUE = c.getColumnIndexOrThrow("value");
        final int TAKE_STOCK = c.getColumnIndexOrThrow("take_stock");
        final int INDEX_CREATED_AT = c.getColumnIndexOrThrow("created_at");
        final int INDEX_DELETED_AT = c.getColumnIndexOrThrow("deleted_at");
        List<Item> list = new ArrayList<>(c.getCount());

        do {
            int _id = -1;
            String model = null;
            String name = null;
            int primaryImage_id = -1;
            int maker_id = -1;
            Company maker = null;
            int inHouseCode = -1;
            int unitType_id = -1;
            UnitType unitType = null;
            int value = -1;
            boolean takeStock = false;
            Date createdAt = null;
            Date deletedAt = null;

            //必須項目
            model = c.getString(INDEX_MODEL);
            name = c.getString(INDEX_NAME);

            maker_id = c.getInt(INDEX_COMPANY_ID);
            int targetCompanyIndex = makerList.indexOf(Company.of(Id.of(maker_id)));
//            if (-1 == targetCompanyIndex)
//                throw new IllegalArgumentException("ItemConverter.convert(Cursor):渡されたCompanyListから、Cursor内のCompanyIdと一致するものが見つかりませんでした。");
            maker = makerList.get(targetCompanyIndex);

            unitType_id = c.getInt(INDEX_UNIT_TYPE_ID);
            int targetUnitTypeIndex = unitTypeList.indexOf(UnitType.of(Id.of(unitType_id)));
//            if (-1 == targetUnitTypeIndex)
//                throw new IllegalArgumentException("ItemConverter.convert(Cursor):渡されたUnitTypeListから、Cursor内のUnitTypeIdと一致するものが見つかりませんでした。");
            unitType = unitTypeList.get(targetUnitTypeIndex);

            Item.Builder builder =
                    new Item.Builder(ItemModel.of(model), ItemName.of(name), maker, unitType);
            //オプション
            _id = c.getInt(INDEX_ID);
            inHouseCode = c.getInt(INDEX_IN_HOUSE_CODE);
            primaryImage_id = c.getInt(INDEX_PRIMARY_IMAGE_ID);
            value = c.getInt(INDEX_VALUE);
            takeStock = (1 == c.getInt(TAKE_STOCK));
            createdAt = MyDateFormat.stringToDate(c.getString(INDEX_CREATED_AT));
                builder.setCreatedAt(CreatedDateTime.of(createdAt));
            deletedAt = MyDateFormat.stringToDate(c.getString(INDEX_DELETED_AT));
            if (null != deletedAt)
                builder.setDeletedAt(DeletedDateTime.of(deletedAt));
            Item item = builder.setId(Id.of(_id))
                    .setPrimaryImage_id(primaryImage_id)
                    .setInHouseCode(InHouseCode.of(inHouseCode))
                    .setValue(value)
                    .setTakeStock(takeStock)
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
        return list;
    }

    public static List<ItemImage> convertImage(Cursor c){
        if (c.moveToFirst()){
            List<ItemImage> list = new ArrayList<>(c.getCount());
            final int INDEX_ID = c.getColumnIndexOrThrow("_id");
            final int INDEX_ADDRESS = c.getColumnIndexOrThrow("address");
            final int INDEX_CREATED_AT = c.getColumnIndexOrThrow("created_at");
            do{
                Id _id = Id.of(c.getInt(INDEX_ID));
                String address = c.getString(INDEX_ADDRESS);
                Date createdAt = MyDateFormat.stringToDate(c.getString(INDEX_CREATED_AT));
                ItemImage image = ItemImage.of(_id, address, CreatedDateTime.of(createdAt));
                list.add(image);
            } while (c.moveToNext());
            return list;
        } else throw new IllegalArgumentException("カーソルが空です");
    }

    public static ContentValues convert(Item entity){
        ContentValues values = new ContentValues();

        Id _id = entity.get_id();
        ItemModel model = entity.getModel();
        ItemName name = entity.getName();
        Company maker = entity.getMaker();
        int primaryImage_id = entity.getPrimaryImage_id();
        InHouseCode inHouseCode = entity.getInHouseCode();
        UnitType unitType = entity.getUnitType();
        int value = entity.getValue();
        boolean takeStock = entity.isTakeStock();
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
        if (takeStock)
            values.put("takeStock", 1);

        return values;
    }

    public static ContentValues convertImage(ItemImage image){
        ContentValues values = new ContentValues();

        String address = image.getAddress();
        if (null != address)
            values.put("address", address);
        return values;
    }
}
