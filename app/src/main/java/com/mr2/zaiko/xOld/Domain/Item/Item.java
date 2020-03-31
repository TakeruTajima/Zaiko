package com.mr2.zaiko.xOld.Domain.Item;

import androidx.annotation.NonNull;

import com.mr2.zaiko.xOld.Domain.Company.Company;
import com.mr2.zaiko.xOld.Domain.CreatedDateTime;
import com.mr2.zaiko.xOld.Domain.DeletedDateTime;
import com.mr2.zaiko.xOld.Domain.Id;
import com.mr2.zaiko.xOld.Domain.UnitType.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * アイテムのEntity。集約ルート。
 */
public class Item { //entity
    private static final int VALUE_MIN = 1;
    //メーカーx型式でユニーク。無いと困るのは…ID、Model、Name、Maker、
    private final Id _id;  //システム内で使うID vo 登録時は-1
    private final ItemModel model;  //型式 vo メーカーが定める商品の型式、品番。原則、英数字のみ。
    private ItemName name;  //品名 vo 社内で定める商品の名前。
    private int primaryImage_id; //主写真ID vo
    private List<ItemImage> imageList;  //画像・写真 vo
    private final Company maker; //メーカー entity　登録時はCompany.getId、読み出しはIdからfindOneしてEntityとして持つ。
    private final InHouseCode inHouseCode;  //社内管理コード vo 新規登録時にRepository…DomainServiceで差し込む。読み込み時はコンストラクタで
    private Unit unit;  //管理単位 vo　同上
    private int value;  //単価 vo 管理上の単価価値。
    private boolean takeStock;  //棚卸要否 vo たな卸しリストの抽出条件
    private CreatedDateTime createdAt;  //作成日時 vo
    private DeletedDateTime deletedAt;  //削除日時 vo
//    private List<Tag> tagList;  //タグ vo
//    private List<JanCode> janCodeList;  //JANコード(外部バーコード) vo
//    private List<Inventory> inventoryList;  //在庫保管場所 entity
//    private List<Catalogue> catalogueList;  //発注先情報 entity
//    private List<Event> eventList;  //イベント履歴 vo

    //タグ、JANコード、在庫保管場所と数量、発注先カタログ、入出庫購入履歴

    //新規
    @Deprecated
    public Item(ItemRepository repository, ItemModel model, ItemName name, Company maker) {
        this._id = Id.getDefault();
        this.model = model;
        this.name = name;
        this.maker = maker;
        int code = repository.extractInHouseCode(maker.get_id().value());
        this.inHouseCode = InHouseCode.of(code);
        //TODO:集約の中からRepositoryを呼ぶのはやめたほうがいいらしいけどService作る？
        //this.unit = Unit.getDefault();
        //this.value = -1;
        //this.takeStock = false;
        //this.createdAt = CreatedDateTime.getDefault();
        //this.deletedAt = DeletedDateTime.getDefault();
    }

    @Deprecated
    public Item(Id _id, ItemModel model, ItemName name, Company maker, InHouseCode inHouseCode,
                Unit unit, int value, boolean takeStock, CreatedDateTime createdAt, DeletedDateTime deletedAt) {
        this._id = _id;
        this.model = model;
        this.name = name;
        this.maker = maker;
        this.inHouseCode = inHouseCode;
        this.unit = unit;
        this.value = value;
        this.takeStock = takeStock;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
    }

    public Item(Builder builder){

        this._id = builder._id;
        this.model = builder.model;
        this.name = builder.name;
        this.primaryImage_id = builder.primaryImage_id;
        this.imageList = builder.imageList;
        this.maker = builder.maker;
        this.inHouseCode = builder.inHouseCode;
        this.unit = builder.unit;
        this.value = builder.value;
        this.takeStock = builder.takeStock;
        this.createdAt = builder.createdAt;
        this.deletedAt = builder.deletedAt;
    }

    //更新
    public void setValue(int value) {
        if (VALUE_MIN <= value) {
            this.value = value;
        }else throw new IllegalArgumentException("単価が不正です");
    }

    public Item setName(ItemName name) {
        this.name = name;
        return this;
    }

    public void setImageList(List<ItemImage> list){
        this.imageList = list;
    }

    public Item setUnit(Unit unit) {
        this.unit = unit;
        return this;
    }

    public Item setTakeStock(boolean takeStock) {
        this.takeStock = takeStock;
        return this;
    }

    //取得
    public Id get_id() {
        return _id;
    }

    public ItemModel getModel() {
        return model;
    }

    public ItemName getName() {
        return name;
    }

    public Company getMaker() {
        return maker;
    }

    public int getPrimaryImage_id() {
        return primaryImage_id;
    }

    public List<ItemImage> getImageList() {
        return imageList;
    }

    public InHouseCode getInHouseCode() {
        return inHouseCode;
    }

    public Unit getUnit() {
        return unit;
    }

    public int getValue() {
        return value;
    }

    public boolean isTakeStock() {
        return takeStock;
    }

    public CreatedDateTime getCreatedAt() {
        return createdAt;
    }

    public DeletedDateTime getDeletedAt() {
        return deletedAt;
    }

    //TODO: Tell, don't ask. 「求めるな、命じよ」
    // クラスから情報を抜き取って処理を行うのではなく、クラスに処理を命じたほうがええんちゃう？という話


    @NonNull
    @Override
    public String toString() {
        return "Item{" +
                "_id=" + _id +
                ", model='" + model.value() + '\'' +
                ", name='" + name.value() + '\'' +
                ", maker=" + maker +
                ", inHouseCode=" + inHouseCode +
                ", unit=" + unit +
                ", value=" + value +
                ", takeStock=" + takeStock +
                ", createdAt=" + createdAt +
                ", deletedAt=" + deletedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return _id == item._id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id);
    }

    /**
     * builderパターンお試し
     */
    public static class Builder{

        private Id _id;  //システム内で使うID。登録時は-1
        private ItemModel model;  //型式 メーカーが定める商品の型式、品番。原則、英数字のみ。
        private ItemName name;  //品名 社内で定める商品の名前。
        private int primaryImage_id = -1;
        private List<ItemImage> imageList;  //画像・写真
        private Company maker; //メーカー　登録時はCompany.getId、読み出しはIdからfindOneしてEntityとして持つ。
        private InHouseCode inHouseCode;  //社内管理コード 新規登録時にRepository…DomainServiceで差し込む。読み込み時はコンストラクタで
        private Unit unit;  //管理単位　同上
        private int value = -1;  //単価 管理上の単価価値。
        private boolean takeStock = true;  //棚卸要否 たな卸しリストの抽出条件
        private CreatedDateTime createdAt;  //作成日時
        private DeletedDateTime deletedAt;  //削除日時
//    private List<Tag> tagList;  //タグ
//    private List<JanCode> janCodeList;  //JANコード
//    private List<Inventory> inventoryList;  //在庫保管場所
//    private List<Catalogue> catalogueList;  //発注先情報
//    private List<Event> eventList;  //イベント履歴


        public Builder(ItemModel model, ItemName name, Company maker, Unit unit) {
            this.model = model;
            this.name = name;
            this.maker = maker;
            this.unit = unit;
        }

        public Builder setId(Id _id){
            this._id = _id;
            return this;
        }

        public Builder setItemImageSet(int primaryImage_id, List<ItemImage> imageList){
            this.primaryImage_id = primaryImage_id;
            this.imageList = imageList;
            return this;
        }

        public Builder setPrimaryImage_id(int primaryImage_id) {
            this.primaryImage_id = primaryImage_id;
            return this;
        }

//        public Builder setModel(ItemModel model){
//            this.model = model;
//            return this;
//        }

//        public Builder setName(ItemName name) {
//            this.name = name;
//            return this;
//        }

//
//        public Builder setMaker(Company maker) {
//            this.maker = maker;
//            return this;
//        }

        public Builder setInHouseCode(InHouseCode inHouseCode) {
            this.inHouseCode = inHouseCode;
            return this;
        }

//        public Builder setUnit(Unit unit) {
//            this.unit = unit;
//            return this;
//        }

        public Builder setValue(int value) {
            this.value = value;
            return this;
        }

        public Builder setTakeStock(boolean takeStock) {
            this.takeStock = takeStock;
            return this;
        }

        public Builder setCreatedAt(CreatedDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder setDeletedAt(DeletedDateTime deletedAt) {
            this.deletedAt = deletedAt;
            return this;
        }

        public Item create(){
            if (null == model || null == maker || null == name || null == unit)
                throw new IllegalArgumentException("必須項目未設定");

            if (null == _id ) //新規：Null
                this._id = Id.getDefault();
            if (-1 == primaryImage_id)
                this.imageList = new ArrayList<>(); //default値
            if (null == inHouseCode) //新規：NonNull(　todo:DBから抽出する内容なのでsetは後にしないと割り込まれて重複します
                this.inHouseCode = InHouseCode.getDefault();
            if (null == createdAt) //新規：Null
                this.createdAt = CreatedDateTime.getDefault();
            if (null == deletedAt) //新規：Null
                this.deletedAt = DeletedDateTime.getDefault();

            return new Item(this);
        }
    }
}
