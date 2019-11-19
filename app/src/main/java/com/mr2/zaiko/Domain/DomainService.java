package com.mr2.zaiko.Domain;

import android.content.Context;

import com.mr2.zaiko.Domain.Company.Company;
import com.mr2.zaiko.Domain.Company.CompanyRepository;
import com.mr2.zaiko.Domain.Company.CompanyRepositoryImpl;
import com.mr2.zaiko.Domain.Item.ItemRepository;
import com.mr2.zaiko.Domain.Item.ItemRepositoryImpl;
import com.mr2.zaiko.Domain.UnitType.UnitType;
import com.mr2.zaiko.Domain.UnitType.UnitTypeRepository;
import com.mr2.zaiko.Domain.UnitType.UnitTypeRepositoryImpl;

import java.util.List;

public class DomainService {

    public static ItemRepository getItemRepository(Context context){
        CompanyRepository companyRepository = new CompanyRepositoryImpl(context);
        List<Company> makerList = companyRepository.findAllMakerByUnDeleted();
        UnitTypeRepository unitTypeRepository = new UnitTypeRepositoryImpl(context);
        List<UnitType> unitTypeList = unitTypeRepository.findAllByUnDeleted();
        return new ItemRepositoryImpl(context, makerList, unitTypeList);
    }

    public static UnitTypeRepository getUnitTypeRepository(Context context){
        return new UnitTypeRepositoryImpl(context);
    }

    public static CompanyRepository getCompanyRepository(Context context){
        return new CompanyRepositoryImpl(context);
    }
}
