package com.mr2.zaiko.Domain.UnitType;

import com.mr2.zaiko.Domain.Base.SimpleCrudRepository;
import com.mr2.zaiko.Domain.UnitType.UnitType;

import java.util.List;

public interface UnitTypeRepository extends SimpleCrudRepository<UnitType, Integer> {

    List<UnitType> findAllByUnDeleted();
    long countByUnDeleted();
    boolean existsByName(String name);
    List<UnitType> partialByName(String name);

}
