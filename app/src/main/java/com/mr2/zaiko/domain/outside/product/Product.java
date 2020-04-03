package com.mr2.zaiko.domain.outside.product;

import androidx.annotation.NonNull;

import com.mr2.zaiko.domain.common.Entity;
import com.mr2.zaiko.domain.outside.company.CompanyId;

public class Product extends Entity {
    @NonNull private final CompanyId companyId;
    @NonNull private final ProductId id;
    @NonNull private final Model model;
    @NonNull private Name name;
    @NonNull private Unit unit;
    @NonNull private Price price;

    /**
     * 新規Productの生成時。CompanyのcreateProduct()メソッドから？
     * @param companyId　メーカー
     * @param model　型式/品番
     * @param name　メーカーの定める品名
     * @param unit　メーカーが扱う単位
     * @param price　メーカー希望小売価格
     */
    Product(@NonNull CompanyId companyId, @NonNull Model model, @NonNull Name name, @NonNull Unit unit, @NonNull Price price){
        super(INITIAL_UNMUTATED_VERSION);
        this.companyId = companyId;
        this.id = new ProductId();
        this.model = model;
        this.name = name;
        this.unit = unit;
        this.price = price;
    }

    protected Product(int version, @NonNull CompanyId companyId, @NonNull ProductId id, @NonNull Model model, @NonNull Name name, @NonNull Unit unit, @NonNull Price price) {
        super(version);
        this.companyId = companyId;
        this.id = id;
        this.model = model;
        this.name = name;
        this.unit = unit;
        this.price = price;
    }

    @NonNull
    public CompanyId companyId(){ return this.companyId; }

    @NonNull
    public ProductId productId(){ return this.id; }

    @NonNull
    public Model model() {
        return model;
    }

    @NonNull
    public Name name() {
        return name;
    }

    public void changeName(@NonNull Name name){
        this.name = name;
    }

    @NonNull
    public Unit unit() {
        return unit;
    }

    public void changeUnit(@NonNull Unit unit){ this.unit = unit; }

    @NonNull
    public Price price() {
        return price;
    }

    public void changePrice(@NonNull Price price){ this.price = price; }
}
