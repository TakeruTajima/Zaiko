package com.mr2.zaiko.xOld.Domain.Item;

import com.mr2.zaiko.xOld.Domain.Base.SimpleCrudRepository;
import com.mr2.zaiko.xOld.Domain.Company.Company;

import java.util.List;

public interface ItemRepository extends SimpleCrudRepository<Item, Integer> {

    List<Item> findAllByUnDeleted();
    long countByUnDeleted();
    List<Item> findAllByMaker(Company maker);
    long countByMaker(Company maker);

    int extractInHouseCode(int maker_id);

}
