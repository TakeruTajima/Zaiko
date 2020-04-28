package com.mr2.zaiko.infrastructure.room;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDao {
    @Insert
    void insert(Product product);
    @Update
    void update(Product product);
    @Query("select * from products where _id = :product_id")
    List<Product> findById(String product_id);
    @Query("select * from products where company_id = :company_id order by model asc")
    List<Product> findByCompany(String company_id);
    @Query("select * from products where company_id = :company_id order by model asc")
    LiveData<List<Product>> findLiveDataByCompany(String company_id);
    @Query("select * from products where company_id = :company_id order by model asc")
    DataSource.Factory<Integer, Product> findDataSourceByCompany(String company_id);
}
