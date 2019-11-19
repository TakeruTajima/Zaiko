package com.mr2.zaiko.Domain.Item;

import androidx.annotation.NonNull;

import com.mr2.zaiko.Domain.Company.Company;
import com.mr2.zaiko.Domain.CreatedDateTime;
import com.mr2.zaiko.Domain.DeletedDateTime;
import com.mr2.zaiko.Domain.Id;
import com.mr2.zaiko.Domain.UnitType.UnitType;

import java.util.Objects;

public class Item {
    private static final int VALUE_MIN = 1;

    private final Id _id;  //システム内で使うID。登録時は-1
    private final ItemModel model;  //型式 メーカーが定める商品の型式、品番。原則、英数字のみ。
    private ItemName name;  //品名 社内で定める商品の名前。
//    private List<Image> imageList;  //画像・写真
    private final Company maker; //メーカー　登録時はCompany.getId、読み出しはIdからfindOneしてEntityとして持つ。
    private final InHouseCode inHouseCode;  //社内管理コード 新規登録時にRepository…DomainServiceで差し込む。読み込み時はコンストラクタで
    private UnitType unitType;  //管理単位　同上
    private int value;  //単価 管理上の単価価値。
    private boolean doNotStockTake;  //棚卸要否 たな卸しリストの抽出条件
    private CreatedDateTime createdAt;  //作成日時
    private DeletedDateTime deletedAt;  //削除日時
//    private List<Tag> tagList;  //タグ
//    private List<JanCode> janCodeList;  //JANコード
//    private List<Inventory> inventoryList;  //在庫保管場所
//    private List<Catalogue> catalogueList;  //発注先情報
//    private List<Event> eventList;  //イベント履歴

    //タグ、JANコード、在庫保管場所と数量、発注先カタログ、入出庫購入履歴

    public Item(ItemRepository repository, ItemModel model, ItemName name, Company maker) {
        this._id = null;
        this.model = model;
        this.name = name;
        this.maker = maker;
        int code = repository.extractInHouseCode(maker.get_id().value());
        this.inHouseCode = InHouseCode.of(code);
        //TODO:集約の中からRepositoryを呼ぶのはやめたほうがいいらしいけどService作る？
    }

    public Item(Id _id, ItemModel model, ItemName name, Company maker, InHouseCode inHouseCode,
                UnitType unitType, int value, boolean doNotStockTake, CreatedDateTime createdAt, DeletedDateTime deletedAt) {
        this._id = _id;
        this.model = model;
        this.name = name;
        this.maker = maker;
        this.inHouseCode = inHouseCode;
        this.unitType = unitType;
        this.value = value;
        this.doNotStockTake = doNotStockTake;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
    }

    //設定
    public void setValue(int value) {
        if (VALUE_MIN <= value) {
            this.value = value;
        }else throw new IllegalArgumentException("単価が不正です");
    }

    public Item setName(ItemName name) {
        this.name = name;
        return this;
    }

    public Item setUnitType(UnitType unitType) {
        this.unitType = unitType;
        return this;
    }

    public Item setDoNotStockTake(boolean doNotStockTake) {
        this.doNotStockTake = doNotStockTake;
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

    public InHouseCode getInHouseCode() {
        return inHouseCode;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public int getValue() {
        return value;
    }

    public boolean isDoNotStockTake() {
        return doNotStockTake;
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
                ", unitType=" + unitType +
                ", value=" + value +
                ", doNotStockTake=" + doNotStockTake +
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
}
