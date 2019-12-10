package com.mr2.zaiko.Domain.Product;

import com.mr2.zaiko.Domain.Base.BaseCrudRepository;
import com.mr2.zaiko.Domain.Base.SimpleCrudRepository;
import com.mr2.zaiko.Domain.Company.Company;
import java.util.List;

interface ProductRepository extends SimpleCrudRepository<Product, Integer> {

    List<Product> findAllByUnDeleted();
    long countByUnDeleted();
    List<Product> findAllByMaker(Company maker);
    long countByMaker(Company maker);

    int extractInHouseCode(int maker_id);

    boolean existsModel(Company maker, Model model);
}
