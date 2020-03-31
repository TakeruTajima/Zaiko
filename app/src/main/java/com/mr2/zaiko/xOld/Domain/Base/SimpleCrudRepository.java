package com.mr2.zaiko.xOld.Domain.Base;

import java.io.Serializable;
import java.util.List;

public interface SimpleCrudRepository <T, ID extends Serializable> {
    T findOne(ID id);
    boolean exists(ID id);
    List<T> findAll();
    long count();
    T save(T entity);
    void delete(T entity);
}
