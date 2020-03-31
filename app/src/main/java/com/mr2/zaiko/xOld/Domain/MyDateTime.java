package com.mr2.zaiko.xOld.Domain;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public abstract class MyDateTime {
    private final Instant ustDateTimeInstant;
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private final ZoneId zoneId;

    /**
     * dateにSystemDefaultのタイムゾーンを付与し、Instantにして格納します。
     * @param date UTC時刻
     */
    @Deprecated
    MyDateTime(Date date) {
        this.zoneId = ZoneOffset.systemDefault();//SystemDefaultのタイムゾーンを取得
        Instant instant = date.toInstant();//DateをInstant変換
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("UTC"));//仮にUTCとしてZonedDateTimeにする
        zonedDateTime = zonedDateTime.withZoneSameLocal(zoneId);//タイムゾーンだけをSystemDefaultに変更
        this.ustDateTimeInstant = zonedDateTime.toInstant();//Instantに戻して（UTC化）格納
    }

    /**
     * dateにSystemDefaultのタイムゾーンを付与し、Instantにして格納します。
     * @param date UTC時刻
     * @param zoneId タイムゾーン指定。Nullの場合はSystemDefaultのタイムゾーンを使用します。
     */
    MyDateTime(@NonNull Date date, @Nullable ZoneId zoneId) {
        if (null == zoneId) zoneId = ZoneOffset.systemDefault();//タイムゾーン指定なしならSystemDefaultのタイムゾーンで代替
        this.zoneId = zoneId;
        Instant instant = date.toInstant();//Instant変換を経由して
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("UTC"));//仮にUTCとしてZonedDateTimeにする
        zonedDateTime = zonedDateTime.withZoneSameLocal(this.zoneId);//タイムゾーンだけを変更
        this.ustDateTimeInstant = zonedDateTime.toInstant();//Instantに戻して（UTC化）格納
    }

    /**
     * LocalDateTimeに指定したタイムゾーンを付与し、Instantとして格納します。
     * Instantは時刻をUTCとして保持します。
     * @param localDateTime 現地時刻
     * @param zoneId タイムゾーン情報。Nullの場合、SystemDefaultのタイムゾーンを使用します。
     */
    MyDateTime(@NonNull LocalDateTime localDateTime, @Nullable ZoneId zoneId) {
        if (null == zoneId) zoneId = ZoneOffset.systemDefault(); //タイムゾーン指定NullならSystemDefaultを使用
        this.zoneId = zoneId;
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("UTC"));//一度UTCとしてZonedDateTime化

        zonedDateTime = zonedDateTime.withZoneSameLocal(this.zoneId);//タイムゾーンだけを変更
        this.ustDateTimeInstant = zonedDateTime.toInstant();
    }

    /**
     * タイムゾーン付きZonedDateTimeをInstantとして格納します。
     * 【zonedDateTimeはLocalDateTime+タイムゾーン情報で構成されますが、内部的にtoInstant()でUTCに変換されます。
     * 　　例：ZonedDateTime 10:30:00-JST(+9h)　→　Instant 01:30:00-UTC(+0h)】
     */
    MyDateTime(ZonedDateTime zonedDateTime) {
        this.zoneId = zonedDateTime.getZone();
        this.ustDateTimeInstant = zonedDateTime.toInstant();
    }

    public abstract boolean validate(Date date);

    public static DateTimeFormatter getDateTimeFormatter() { return DATETIME_FORMATTER; }

    public LocalDateTime toLocalDateTime() {  return LocalDateTime.ofInstant(ustDateTimeInstant, zoneId); }

    public ZonedDateTime toZonedDateTime() { return ZonedDateTime.ofInstant(ustDateTimeInstant, zoneId); }

    /**
     * 内部のInstantをDateに変換して返します。
     * 【Dateはタイムゾーンの概念がありませんが、toString()の返り値にはシステムデフォルトのタイムゾーンが適応されます。】
     * 【Dateは非推奨です。】
     */
    @Deprecated
    public Date toDate(){
        return Date.from(ustDateTimeInstant);
    }

    /**
     * @return instance生成時のタイムゾーンを適応した日時を返します。フォーマット："yyyy/MM/dd HH:mm:ss"
     */
    @NonNull
    @Override
    public String toString() {
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(ustDateTimeInstant, zoneId);
        return zonedDateTime.format(DATETIME_FORMATTER);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyDateTime that = (MyDateTime) o;
        return Objects.equals(ustDateTimeInstant, that.ustDateTimeInstant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ustDateTimeInstant);
    }
}

//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//
//import java.time.ZonedDateTime;
//import java.time.LocalDateTime;
//import java.time.ZoneOffset;
//import java.time.ZoneId;
//
//public class Main {
//    public static void main(String[] args) throws Exception {
//        int あ = 10;
//        System.out.println(あ);
//
//        LocalDateTime local = LocalDateTime.now();
//        System.out.println(local.toString());
//        ZonedDateTime zonedDateTime = local.atZone(ZoneId.of("Asia/Tokyo"));
//        System.out.println(zonedDateTime.toString());
//
//    }
//}
