package com.mr2.zaiko.Infra;

import android.util.Log;

import androidx.annotation.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyDateFormat {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final DateFormat dateFormat =
            new SimpleDateFormat(DATE_FORMAT, Locale.JAPAN);

    public static Date stringToDate(String date){
        if (null != date){
            Log.d("///////////////////////", date);
        }else {
            Log.d("///", "catch nullpo");
            return null;
        }
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        catch (NullPointerException e){
//            e.printStackTrace();
//        }
        return null;
    }

    public static String dateToString(Date date){
        if (null != date)
            return dateFormat.format(date);
        return "";
    }
}
