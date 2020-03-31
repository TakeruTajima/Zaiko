package com.mr2.zaiko.domain.outside.company;

import androidx.annotation.NonNull;

import com.mr2.zaiko.domain.common.Entity;
import com.mr2.zaiko.domain.inhouse.equipment.Equipment;
import com.mr2.zaiko.domain.outside.product.Model;
import com.mr2.zaiko.domain.outside.product.Name;
import com.mr2.zaiko.domain.outside.product.Price;
import com.mr2.zaiko.domain.outside.product.Product;
import com.mr2.zaiko.domain.outside.product.Unit;

public class Company extends Entity {
    @NonNull private final CompanyId id;
    @NonNull private String name;
    //TODO:製品/商品のIDリストとか持たせといたほうが都合よくない？
    // メリット⇒　製品/商品の件数、製品/商品リストのクエリ発行用にIDList渡し、
    // デメリット⇒　会社一覧が重くなるし重複する内容が多すぎる
    // …シングルトンの出番か？⇒　シングルトンは「クラスのインスタンスを一つに保証する」ものだからちょっと違くね？

    public Company(@NonNull String name){
        super(INITIAL_UNMUTATED_VERSION);
        this.id = new CompanyId();
        setName(name);
    }

    Company(int version, @NonNull CompanyId id, @NonNull String name) {
        super(version);
        this.id = id;
        this.name = name;
    }

    public void changeName(@NonNull String name){
        setName(name);
    }

    private void setName(@NonNull String name){
        assertArgumentLength(name, 1, 150, "会社名の文字数が不正です");
        this.name = name;
    }

    public Product createProduct(@NonNull Model model, @NonNull Name name, @NonNull Unit unit, @NonNull Price price){
        return new Product(id, model, name, unit, price);
    }

    public Equipment registerEquipment(@NonNull Product product, @NonNull Name name, @NonNull Unit unit, @NonNull Price price){
        return new Equipment(product, this.id, name, unit, price);
    }

    public CompanyId id() {
        return id;
    }

    public String name() {
        return name;
    }

}
