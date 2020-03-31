package com.mr2.zaiko.xOld.Domain.Product;

import com.mr2.zaiko.xOld.Domain.Company.Company;
import com.mr2.zaiko.xOld.Domain.Item.ItemModel;

//idの採番やuniqueなmodelなどDBに依存する処理をProductから切り離す
public interface ProductFactory {
    Product create(Company maker, ItemModel model);
}
