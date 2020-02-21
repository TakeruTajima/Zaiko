package com.mr2.zaiko.zaiko2.domain.inhouse.equipment;

import androidx.annotation.NonNull;

import com.mr2.zaiko.zaiko2.domain.outside.company.CompanyId;
import com.mr2.zaiko.zaiko2.domain.outside.product.Model;
import com.mr2.zaiko.zaiko2.domain.outside.product.Name;
import com.mr2.zaiko.zaiko2.domain.outside.product.Price;
import com.mr2.zaiko.zaiko2.domain.outside.product.Product;
import com.mr2.zaiko.zaiko2.domain.outside.product.ProductId;
import com.mr2.zaiko.zaiko2.domain.outside.product.Unit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Equipment extends Product {
    @NonNull private final EquipmentId id;
    @NonNull private final CompanyId sellingCompanyId;
    @NonNull private Name name;
    @NonNull private Unit unit;
    @NonNull private Price price;
    private List<Photo> photos; //get, addPhoto(Photo), removePhoto(Photo), changeTopPhoto(Photo)
    private Set<ExternalBarcode> externalBarcodeSet; //get, addBarcode, removeBarcode
    private Set<Keyword> keywordSet; //get, addKeyword, removeKeyword

    public Equipment(@NonNull Product product, @NonNull CompanyId sellingCompanyId, @NonNull Name name, @NonNull Unit unit, @NonNull Price price){
        super(INITIAL_UNMUTATED_VERSION,
                product.companyId(),
                product.productId(),
                product.model(),
                product.name(),
                product.unit(),
                product.price());
        this.id = new EquipmentId();
        this.sellingCompanyId = sellingCompanyId;
        this.name = name;
        this.unit = unit;
        this.price = price;
        this.photos = new ArrayList<>();
        this.externalBarcodeSet = new HashSet<>();
        this.keywordSet = new HashSet<>();
    }

    Equipment(int version, @NonNull CompanyId creationCompany, @NonNull ProductId productId, @NonNull Model model, @NonNull Name productName, @NonNull Unit productUnit, @NonNull Price productPrice, @NonNull EquipmentId equipmentId, @NonNull CompanyId sellingCompanyId, @NonNull Name equipmentName, @NonNull Unit equipmentUnit, @NonNull Price equipmentPrice, @NonNull List<Photo> photos, @NonNull Set<ExternalBarcode> externalBarcodeSet, @NonNull Set<Keyword> keywordSet) {
        super(version,
                creationCompany,
                productId,
                model,
                productName,
                productUnit,
                productPrice);
        this.id = equipmentId;
        this.sellingCompanyId = sellingCompanyId;
        this.name = equipmentName;
        this.unit = equipmentUnit;
        this.price = equipmentPrice;
        this.photos = photos;
        this.externalBarcodeSet = externalBarcodeSet;
        this.keywordSet = keywordSet;
    }

    @NonNull
    public EquipmentId equipmentId() {
        return this.id;
    }

    @NonNull
    public CompanyId sellingCompanyId() {
        return sellingCompanyId;
    }

    //get,
    public List<Photo> photos(){ return photos; }

    // addPhoto(Photo),
    public void addPhoto(Photo photo){
        assertArgumentTrue(10 >= photos.size(), "写真は最大10枚まで登録できます。");
        assertArgumentTrue(-1 != photos.indexOf(photo), "写真が重複しています。");
        photos.add(photo);
    }

    // removePhoto(Photo),
    public void removePhoto(Photo photo){
        int i = photos.indexOf(photo);
        if (-1 != i)
            photos.remove(photo);
    }

    // changeTopPhoto(Photo)
    public void insertPhotoToTop(Photo photo){
        removePhoto(photo);
        photos.add(0, photo);
    }

    //get,
    public Set<ExternalBarcode> externalBarcodeSet(){ return externalBarcodeSet; }
    // addBarcode,
    public void addBarcode(ExternalBarcode barcode){
        externalBarcodeSet.add(barcode);
    }
    // removeBarcode
    public void removeBarcode(ExternalBarcode barcode){
        externalBarcodeSet.remove(barcode);
    }
    //get,
    public Set<Keyword> keywordSet(){ return keywordSet; }
    // addKeyword,
    public void addKeyword(Keyword keyword){
        keywordSet.add(keyword);
    }
    // removeKeyword
    public void removeKeyword(Keyword keyword){
        keywordSet.remove(keyword);
    }

    @NonNull
    @Override
    public Name name() {
        return this.name;
    }

    @NonNull
    @Override
    public Unit unit() {
        return this.unit;
    }

    @NonNull
    @Override
    public Price price() {
        return this.price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Equipment)) return false;
        Equipment equipment = (Equipment) o;
        return id.equals(equipment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
