package com.mr2.zaiko.infrastructure.room;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface EquipmentSubsetDao {
    @Query("select e.name as e_name, " +
            "p.model as model, " +
            "c.name as c_name, " +
            "e.price_name as price, " +
            "e.price_name as currency, " +
            "e.unit, " +
            "sum(s.quantity) as quantity, " +
            "sum(s.max_quantity) as capacity " +
            "from equipments as e " +
            "inner join products as p on e.product_id = p._id " +
            "inner join companies as c on p.company_id = c._id " +
            "inner join storage_locations as s on e._id = s.equipment_id " +
            "group by e._id")
    DataSource.Factory<Integer, EquipmentSubset> equipmentSubset();
}
