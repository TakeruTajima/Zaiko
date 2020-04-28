package com.mr2.zaiko.infrastructure.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CompanyDao {
    @Insert
    void insert(Company company);
    @Update
    void update(Company company);
    @Query("select * from companies where _id = :company_id")
    List<Company> findById(String company_id);
}
