package com.mr2.zaiko.Domain.Company;

import com.mr2.zaiko.Domain.Base.SimpleCrudRepository;
import com.mr2.zaiko.Domain.Company.Company;

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
