package com.mr2.zaiko.Domain.Product;

import com.mr2.zaiko.Domain.Company.Company;

public class ProductImpl implements Product {
    private final Identity productIdentity;
    private final Model model;
    private final Company maker;
    private final Name name;


    public ProductImpl(Identity identity, Model model, Company maker, Name name) {
        this.productIdentity = identity;
        this.model = model;
        this.maker = maker;
        this.name = name;
    }
}
