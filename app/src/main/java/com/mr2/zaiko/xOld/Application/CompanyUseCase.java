package com.mr2.zaiko.xOld.Application;

import android.util.Log;

import com.mr2.zaiko.xOld.Domain.Company.Category;
import com.mr2.zaiko.xOld.Domain.Company.Company;
import com.mr2.zaiko.xOld.Domain.Company.CompanyName;
import com.mr2.zaiko.xOld.Domain.Company.CompanyRepository;

import java.util.List;

public class CompanyUseCase {
    private CompanyRepository mRepository;
    public static final String COMPANY_SELECTION = "COMPANY_SELECTION";
    public enum CompanySelection{
        ALL, ALL_UN_DELETED, MAKER, SELLER
    }

    public CompanyUseCase(CompanyRepository repository) {
        this.mRepository = repository;
    }

    public List<Company> getList(CompanySelection selection){
        List<Company> list;
        switch (selection) {
            case ALL:
                list = mRepository.findAll();
                break;
            case ALL_UN_DELETED:
                list = mRepository.findAllByUnDeleted();
                break;
            case MAKER:
                list = mRepository.findAllMakerByUnDeleted();
                break;
            case SELLER:
                list = mRepository.findAllSellerByUnDeleted();
                break;
            default:
                throw new IllegalArgumentException("illegal selection");
        }
        return list;
    }

    public boolean saveCompany(String name, CompanySelection selection){
        Category category;
        switch (selection) {
            case MAKER:
                category = Category.maker();
                break;
            case SELLER:
                category = Category.seller();
                break;
            default:
                throw new IllegalStateException("selection");
        }
        Company entity;
        if (mRepository.existsByName(name)) {
            Log.d("", "重複しています" + name);
            return false; //重複チェック
        }
        try {
            entity = Company.of(CompanyName.of(name), Category.maker());
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            return false;
        }
        try {
            mRepository.save(entity);
        }catch (IllegalStateException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteCompany(Company company){
        if (mRepository.exists(company.get_id().value())){
            mRepository.delete(company);
            return true;
        }else return false;
    }

    public CompanyRepository getRepository(){
        return mRepository;
    }
}
