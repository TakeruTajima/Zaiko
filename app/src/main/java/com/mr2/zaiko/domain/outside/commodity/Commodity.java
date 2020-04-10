package com.mr2.zaiko.domain.outside.commodity;

import androidx.annotation.NonNull;

import com.mr2.zaiko.domain.outside.company.CompanyId;
import com.mr2.zaiko.domain.outside.product.Model;
import com.mr2.zaiko.domain.outside.product.Name;
import com.mr2.zaiko.domain.outside.product.Price;
import com.mr2.zaiko.domain.outside.product.Product;
import com.mr2.zaiko.domain.outside.product.ProductId;
import com.mr2.zaiko.domain.outside.product.Unit;


public class Commodity extends Product {
    @NonNull private final CommodityId id;
    @NonNull private final CompanyId sellingCompanyId;
    @NonNull private Name name;
    @NonNull private Unit unit;
    @NonNull private Price price;

    public Commodity(@NonNull Product product, @NonNull CompanyId sellingCompanyId, @NonNull Name name, @NonNull Unit unit, @NonNull Price price){
        super(INITIAL_UNMUTATED_VERSION,
                product.companyId(),
                product.productId(),
                product.model(),
                product.name(),
                product.unit(),
                product.price());
        this.sellingCompanyId = sellingCompanyId;
        this.id = new CommodityId();
        this.name = name;
        this.unit = unit;
        this.price = price;
    }

    Commodity(int version, @NonNull CompanyId companyId, @NonNull ProductId productId, @NonNull Model model, @NonNull Name productName, @NonNull Unit productUnit, @NonNull Price productPrice, @NonNull CommodityId id, @NonNull CompanyId sellingCompanyId, @NonNull Name name, @NonNull Unit unit, @NonNull Price price) {
        super(version, companyId, productId, model, productName, productUnit, productPrice);
        this.id = id;
        this.sellingCompanyId = sellingCompanyId;
        this.name = name;
        this.unit = unit;
        this.price = price;
    }

    public CommodityId commodityId(){ return id; }
}
