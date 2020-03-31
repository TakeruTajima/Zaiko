package com.mr2.zaiko.xOld.Domain.Company;

import com.mr2.zaiko.xOld.Domain.Base.SimpleCrudRepository;

import java.util.List;

public interface CompanyRepository extends SimpleCrudRepository<Company, Integer> {

    List<Company> findAllByUnDeleted();
    List<Company> findAllMakerByUnDeleted();
    List<Company> findAllSellerByUnDeleted();
    long countByUnDeleted();
    long countMakerByUnDeleted();
    long countSellerByUnDeleted();
    boolean existsByName(String name);
    List<Company> partialByName(String name);

}
