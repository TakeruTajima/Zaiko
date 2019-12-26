package com.mr2.zaiko.Domain.Product;

import com.mr2.zaiko.Domain.Maker.Maker;

class ProductServiceImpl implements ProductService {
    private ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isDuplicated(Maker maker, Model model) {
        return  repository.existsModel(maker, model);
    }
}
