package com.mr2.zaiko.xOld.Domain.UnitType;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import androidx.annotation.NonNull;

import com.mr2.zaiko.xOld.Domain.CreatedDateTime;
import com.mr2.zaiko.xOld.Domain.DeletedDateTime;
import com.mr2.zaiko.xOld.Domain.Id;
import com.mr2.zaiko.xOld.Infra.MyDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UnitTypeConverter {
    public static final String TAG = UnitTypeConverter.class.getSimpleName();

    public static ContentValues convert(@NonNull Unit entity){
        Log.d(TAG, "convert");
        ContentValues values = new ContentValues();
        Id _id = entity.get_id();
        if(null != _id && _id.value() != -1){
            values.put("_id", entity.get_id().value());
        }
        values.put("name", entity.getName().value());
        return values;
    }

    public static List<Unit> convert(@NonNull Cursor c){
        Log.d(TAG, "convert");
        List<Unit> list = new ArrayList<>();
        if(c.moveToFirst()){
            do{
                int _id = -1;
                String name = null;
                Date createdAt = null;
                Date deletedAt = null;

                _id = c.getInt(c.getColumnIndex("_id")); Log.d(TAG, "_id:" + _id);
                name = c.getString(c.getColumnIndex("name")); Log.d(TAG, "name:" + name);
                if (null != c.getString(c.getColumnIndex("created_at"))) {
                    createdAt = MyDateFormat.stringToDate(c.getString(c.getColumnIndex("created_at")));
                    Log.d(TAG, "created_at:" + createdAt);
                }
                if (null != c.getString(c.getColumnIndex("deleted_at"))) {
                    deletedAt = MyDateFormat.stringToDate(c.getString(c.getColumnIndex("deleted_at")));
                }

                list.add(new Unit(Id.of(_id), UnitTypeName.of(name), CreatedDateTime.of(createdAt), DeletedDateTime.of(deletedAt)));
            }while (c.moveToNext());
        }
        return list;
    }

}
