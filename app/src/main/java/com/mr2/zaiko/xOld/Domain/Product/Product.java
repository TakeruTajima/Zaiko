package com.mr2.zaiko.xOld.Domain.Product;

public interface Product {
    //* Setter *//
    void changeProductName(String newName); //NN 1-150文字

    //* Getter *//
    int getProductIdentity();
    String getModel();
    String getProductName();
    int getMakerCode();
    String getCreatedAt();
    String getUpdateAt();
    String getDeletedAt();
}
