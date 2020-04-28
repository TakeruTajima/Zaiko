package com.mr2.zaiko.infrastructure.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CommodityDao {
    @Insert
    void insert(Commodity commodity);
    @Update
    void update(Commodity commodity);
    @Query("select * from commodities where product_id = :product_id order by company_id asc")
    List<Commodity> findByProduct(String product_id);
    @Query("select * from commodities where company_id = :company_id order by product_id asc")
    List<Commodity> findByCompany(String company_id);
}
