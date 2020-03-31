package com.mr2.zaiko.xOld.UI.View.test;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {
    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();
    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();
    private static final int COUNT = 25;

    // staticイニシャライザ：classにstaticアクセスしたタイミングで上から順に一度だけ実行される
    static { // ダミー
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position) {
        return new DummyItem(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final String content;
        public final String details;

        public DummyItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }

    public class UnitTypeDummy{
        private static final int ID_NIN = 0;
        private static final int NAME_SIZ_MIN = 1;
        private static final int NAME_SIZ_MAX = 10;
        private static final String ERROR_NAME = "UnitTypeの名前の文字数が不正です。name.length() = ";
        private static final String ERROR_ID = "UnitTypeのIDが不正です。_id = ";

        private int _id;  //自動振り分けで変更不可、
        private String name;  //新規時:　変更:不可　制約:UQ、NN、スペースのみNG
        private Date createdAt;
        private Date deletedAt;

        /**
         * 新規作成時
         * @param name String 単位名称 文字数1～10
         */
        public UnitTypeDummy(@NonNull String name) {
            if(name.length() >= NAME_SIZ_MIN && name.length() <= NAME_SIZ_MAX){
                throw new IllegalArgumentException(ERROR_NAME + name.length());
            }

            this._id = -1;
            this.name = name;
            this.createdAt = null;
            this.deletedAt = null;
        }

        /**
         * データ読み込み時
         * @param _id int
         * @param name String
         * @param createdAt Date
         * @param deletedAt Date
         */
        public UnitTypeDummy(int _id, String name, Date createdAt, @Nullable Date deletedAt) {
            if(_id <= ID_NIN){
                throw new IllegalArgumentException(ERROR_ID + _id );
            }
            if(name.length() >= NAME_SIZ_MIN && name.length() <= NAME_SIZ_MAX){
                throw new IllegalArgumentException(ERROR_NAME + name.length());
            }

            this._id = _id;
            this.name = name;
            this.createdAt = createdAt;
            this.deletedAt = deletedAt;
        }

        /**
         *
         * @return 新規作成時でID割り振り以前なら-1を返します。
         */
        public int get_id() {
            return _id;
        }

        public String getName() {
            return name;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

        public Date getDeletedAt() {
            return deletedAt;
        }
    }
}
