package com.mr2.zaiko.xOld.Domain.Product;

import com.mr2.zaiko.xOld.Domain.Maker.Maker;

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
