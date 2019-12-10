package com.mr2.zaiko.Domain.Product;

import com.mr2.zaiko.Domain.Company.Company;

import java.util.List;

public interface Product {
    //* Setter *//
    void changeProductName(String newName); //NN 1-150文字

    //* Getter *//
    int getProductIdentity();
    String getModel();
    String getProductName();
    String getMakerName();
    String getCreatedAt();
    String getUpdateAt();
    String getDeletedAt();
}
