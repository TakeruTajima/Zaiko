package com.mr2.zaiko.xOld.Domain.Base;

import android.content.Context;

import com.mr2.zaiko.xOld.Infra.DBAdapter;

/**
 *
 */
public class BaseCrudRepository{
    protected DBAdapter adapter;

    protected BaseCrudRepository(Context context) {
        adapter = new DBAdapter(context);
    }

    void begin(){
        adapter.beginTransaction();
    }

    void commit(){
        adapter.commit();
    }

    void rollBack(){
        adapter.rollBack();
    }

    void close(){
        adapter.close();
    }
}
