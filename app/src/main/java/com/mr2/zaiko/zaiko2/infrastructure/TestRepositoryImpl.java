package com.mr2.zaiko.zaiko2.infrastructure;

import com.mr2.zaiko.zaiko2.domain.test.TestData;
import com.mr2.zaiko.zaiko2.domain.test.TestRepository;

public class TestRepositoryImpl implements TestRepository {
    private TestData persistedData;


    @Override
    public TestData create() {
        return new TestData();
    }

    @Override
    public TestData get() throws IllegalStateException{
        if (null == this.persistedData) throw new IllegalStateException("get(): データが存在しません");
        System.out.println("get(): " + persistedData.name());
        return this.persistedData.clone();
    }

    @Override
    public void save(TestData data) throws IllegalArgumentException{
        if (null == data) throw new IllegalArgumentException("Unsaved null data.");
        if (null != persistedData) {
            System.out.println("save(): " + data.name() + " to " + persistedData.name());
        }
        this.persistedData = data.clone();
        System.out.println("saved: " + data.name() + " to " + persistedData.name());
        System.out.println(" " + persistedData.updateCount() + " updated.");
    }

    @Override
    public void delete(TestData data) throws IllegalArgumentException{
        if (data.equals(persistedData) && data.updateCount() == persistedData.updateCount()) {
            persistedData = null;
            System.out.println("delete(): data is null");
        }else {
            throw new IllegalArgumentException("delete(): 永続化済みデータと一致しません");
        }
    }

    @Override
    public TestData verification(){
        System.out.println("verification(): " + persistedData.name());
        return persistedData;
    }
}
