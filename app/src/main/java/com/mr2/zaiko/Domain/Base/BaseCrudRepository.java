package com.mr2.zaiko.Domain.Base;

import android.content.Context;
import com.mr2.zaiko.Infra.DBAdapter;

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
