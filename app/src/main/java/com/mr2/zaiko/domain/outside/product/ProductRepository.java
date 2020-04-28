package com.mr2.zaiko.domain.outside.product;

public interface ProductRepository {
    void save(Product product);
    Product findOne(ProductId productId);
}
