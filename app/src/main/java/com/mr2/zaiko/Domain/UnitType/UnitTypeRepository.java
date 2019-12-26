package com.mr2.zaiko.Domain.UnitType;

import com.mr2.zaiko.Domain.Base.SimpleCrudRepository;

import java.util.List;

public interface UnitTypeRepository extends SimpleCrudRepository<Unit, Integer> {

    List<Unit> findAllByUnDeleted();
    long countByUnDeleted();
    boolean existsByName(String name);
    List<Unit> partialByName(String name);

}
