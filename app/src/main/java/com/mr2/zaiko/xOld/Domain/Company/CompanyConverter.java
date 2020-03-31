package com.mr2.zaiko.xOld.Domain.Company;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.mr2.zaiko.xOld.Domain.CreatedDateTime;
import com.mr2.zaiko.xOld.Domain.DeletedDateTime;
import com.mr2.zaiko.xOld.Domain.Id;
import com.mr2.zaiko.xOld.Infra.MyDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class CompanyConverter {
    public static final String TAG = CompanyConverter.class.getSimpleName();

    static ContentValues convert(Company entity){
        ContentValues values = new ContentValues();
        if (null != entity.get_id() && entity.get_id().value() != -1){
            values.put("_id", entity.get_id().value());
        }
        values.put("name", entity.getName().value());
        if (entity.isMaker()) {
            values.put("is_maker", 1);
        }
        if (entity.isSeller()){
            values.put("is_seller", 1);
        }
        return values;
    }

    static List<Company> convert(Cursor c){
        List<Company> list = new ArrayList<>();
        if (c.moveToFirst()){
            do{
                int _id = -1;
                String name = null;
                boolean isMaker = false;
                boolean isSeller = false;
                Date createdAt = null;
                Date deletedAt = null;

                _id = c.getInt(c.getColumnIndex("_id"));
                name = c.getString(c.getColumnIndex("name"));
                if (1 <= c.getInt(c.getColumnIndex("is_maker"))){
                    isMaker = true;
                }
                if (1 <= c.getInt(c.getColumnIndex("is_seller"))){
                    isSeller = true;
                }
                if (null != c.getString(c.getColumnIndex("created_at"))){
                    createdAt = MyDateFormat.stringToDate(c.getString(c.getColumnIndex("created_at")));
                }
                if (null != c.getString(c.getColumnIndex("deleted_at"))){
                    deletedAt = MyDateFormat.stringToDate(c.getString(c.getColumnIndex("deleted_at")));
                }
                Company company = new Company(
                        Id.of(_id),
                        CompanyName.of(name),
                        Category.of(isMaker, isSeller),
                        CreatedDateTime.of(createdAt),
                        DeletedDateTime.of(deletedAt));
                list.add(company);
            }while (c.moveToNext());
        }else Log.d(TAG, "Cursor.moveToFirst() value failed. Cursor.getCount(" + c.getCount() + ")");
        return list;
    }
}
