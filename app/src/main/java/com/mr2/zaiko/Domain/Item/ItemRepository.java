package com.mr2.zaiko.Domain.Item;

import com.mr2.zaiko.Domain.Base.SimpleCrudRepository;
import com.mr2.zaiko.Domain.Company.Company;

import java.util.List;

public interface ItemRepository extends SimpleCrudRepository<Item, Integer> {

    List<Item> findAllByUnDeleted();
    long countByUnDeleted();
    List<Item> findAllByMaker(Company maker);
    long countByMaker(Company maker);

    int extractInHouseCode(int maker_id);

}
