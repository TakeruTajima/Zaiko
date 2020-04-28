package com.mr2.zaiko.infrastructure.room;

import com.mr2.zaiko.domain.outside.company.CompanyId;
import com.mr2.zaiko.domain.outside.product.Model;
import com.mr2.zaiko.domain.outside.product.Name;
import com.mr2.zaiko.domain.outside.product.Price;
import com.mr2.zaiko.domain.outside.product.Product;
import com.mr2.zaiko.domain.outside.product.ProductId;
import com.mr2.zaiko.domain.outside.product.ProductRepository;
import com.mr2.zaiko.domain.outside.product.Unit;

import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    ProductDao productDao;

    public ProductRepositoryImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public void save(Product product) {
        com.mr2.zaiko.infrastructure.room.Product roomEntity = new com.mr2.zaiko.infrastructure.room.Product(
                product.productId().id(),
                product.companyId().id(),
                product.model().model(),
                product.name().name(),
                product.price().value(),
                product.price().currencyUnit(),
                product.unit().name(),
                product.unmutatedVersion()
        );
        List<com.mr2.zaiko.infrastructure.room.Product> productList = productDao.findById(roomEntity._id);
        if (productList.isEmpty()) productDao.insert(roomEntity);
        productDao.update(roomEntity);
    }

    @Override
    public Product findOne(ProductId productId) {
        List<com.mr2.zaiko.infrastructure.room.Product> productList = productDao.findById(productId.id());
        if (1 < productList.size()) throw new IllegalStateException();
        if (productList.isEmpty()) return null;
        com.mr2.zaiko.infrastructure.room.Product p = productList.get(0);
        return new Product(
                p.unmutated_version,
                new CompanyId(p.company_id),
                new ProductId(p._id),
                new Model(p.model),
                new Name(p.name),
                new Unit(p.unit),
                new Price(p.price_value, p.price_name));
    }
}
