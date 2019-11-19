package com.mr2.zaiko.Domain.Company;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.mr2.zaiko.Domain.Base.BaseCrudRepository;

import java.util.List;

public class CompanyRepositoryImpl extends BaseCrudRepository implements CompanyRepository {
    public static final String TAG = CompanyRepositoryImpl.class.getSimpleName();

    public CompanyRepositoryImpl(Context context) {
        super(context);
    }

    @Override
    public Company findOne(Integer integer) {
        Cursor c = adapter.findOneRecordById("m_companies", integer);
        List<Company> list = CompanyConverter.convert(c);
        if (null == list) {
            return null;
        }else return list.get(0);
    }

    @Override
    public boolean exists(Integer integer) {
        Cursor c = adapter.findOneRecordById("m_companies", integer);
        return c.moveToFirst();
    }

    @Override
    public List<Company> findAll() {
        Cursor c = adapter.getAllRecords("m_companies");
        return CompanyConverter.convert(c);
    }

    @Override
    public long count() {
        Cursor c = adapter.getAllRecords("m_companies");
        return c.getCount();
    }

    @Override
    public Company save(Company entity) {
        ContentValues values = CompanyConverter.convert(entity);
        adapter.beginTransaction();
        int result = (int) adapter.insertRecords("m_companies", values);
        if (result != -1){
            adapter.commit();
            return findOne(result);
        }
        adapter.rollBack();
        throw new IllegalStateException("CompanyRepositoryImpl.save(Company entity) :" +
                "DBAdapter.insertRecords(String name, ContentValues values) is Failed.");
        //TODO:Entityのgetterを撤廃してEntityにsaveを命じるなら、
        // saveの引数をバラけさせて、saveのアクセスをEntityのみに許可する仕組みが必要？
    }

    @Override
    public void delete(Company entity) {
        adapter.updateRecordsByCurrentTimestamp("m_companies", "deleted_at", entity.get_id().value());
    }

    @Override
    public List<Company> findAllByUnDeleted() {
        Cursor c = adapter.findAllRecordsByNull("m_companies", "deleted_at");
        return CompanyConverter.convert(c);
    }

    @Override
    public List<Company> findAllMakerByUnDeleted() {
        Cursor c = adapter.findAllRecordsByNull("m_companies", "deleted_at");
        List<Company> list = CompanyConverter.convert(c);

        if (null != list){
            Company company;
            for (int i = 0; i < list.size(); i++){
                company = list.get(i);
                if (!company.isMaker()){
                    list.remove(company);
                }
            }
        }
        return list;
    }

    @Override
    public List<Company> findAllSellerByUnDeleted() {
        Cursor c = adapter.findAllRecordsByNull("m_companies", "deleted_at");
        List<Company> list = CompanyConverter.convert(c);
        if (null != list){
            Company company;
            for (int i = 0; i < list.size(); i++){
                company = list.get(i);
                if (!company.isSeller()){
                    list.remove(company);
                }
            }
        }
        return list;
    }

    @Override
    public long countByUnDeleted() {
        Cursor c = adapter.findAllRecordsByNull("m_companies", "deleted_at");
        List<Company> list = CompanyConverter.convert(c);
        if (null == list){
            return -1;
        }
        return list.size();
    }

    @Override
    public long countMakerByUnDeleted() {
        Cursor c = adapter.findAllRecordsByNull("m_companies", "deleted_at");
        List<Company> list = CompanyConverter.convert(c);
        if (null != list){
            Company company;
            for (int i = 0; i < list.size(); i++){
                company = list.get(i);
                if (!company.isMaker()){
                    list.remove(company);
                }
            }
        }else return -1;
        return list.size();
    }

    @Override
    public long countSellerByUnDeleted() {
        Cursor c = adapter.findAllRecordsByNull("m_companies", "deleted_at");
        List<Company> list = CompanyConverter.convert(c);
        if (null != list){
            Company company;
            for (int i = 0; i < list.size(); i++){
                company = list.get(i);
                if (!company.isMaker()){
                    list.remove(company);
                }
            }
        }else return -1;
        return list.size();
    }

    @Override
    public boolean existsByName(String name) {
        Cursor c = adapter.findAllRecordExactMatch("m_companies", "name", name);
        return c.moveToFirst();
    }

    @Override
    public List<Company> partialByName(String name) {
        Cursor c = adapter.findAllRecordPartialMatch("m_companies", "name", name);
        return CompanyConverter.convert(c);
    }
}
