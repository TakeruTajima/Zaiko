package com.mr2.zaiko.Infra;

import android.util.Log;

import androidx.annotation.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyDateFormat {
    public static final String TAG = MyDateFormat.class.getSimpleName();
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static Date stringToDate(@NonNull String date){
        try {
            Date result = new SimpleDateFormat(DATE_FORMAT, Locale.JAPAN).parse(date);
            Log.d(TAG, "String:" + date);
            Log.d(TAG, "toDate:" + result.toString());
            return result;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String dateToString(Date date){
        String result = new SimpleDateFormat(DATE_FORMAT, Locale.JAPAN).format(date);
        Log.d(TAG, date.toString());
        Log.d(TAG, result);
        return result;
    }
}
