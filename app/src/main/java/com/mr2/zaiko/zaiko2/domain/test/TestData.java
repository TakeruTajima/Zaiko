package com.mr2.zaiko.zaiko2.domain.test;

import androidx.annotation.NonNull;

import com.mr2.zaiko.zaiko2.domain.common.Entity;

import java.util.Objects;
import java.util.UUID;

public class TestData extends Entity {
    private String id;
    private int updateCount;
    private String name;

    public TestData() {
        super(0);
        id = UUID.randomUUID().toString();
        updateCount = 0;
        name = "default name";
    }

    private TestData(int unmutatedVersion, String id, int updateCount, String name) {
        super(unmutatedVersion);
        this.id = id;
        this.updateCount = updateCount;
        this.name = name;
    }

    @NonNull
    @Override
    public TestData clone(){
        return new TestData(unmutatedVersion(), id, updateCount, name);
    }

    public String id() {
        return id;
    }

    public String name(){
        if (null == name) return "";
        return name;
    }

    public int updateCount(){
        return updateCount;
    }

    public void systemOutput(){
        System.out.println("TestData:");
        System.out.println("name: " + name);
        System.out.println("update count: " + updateCount + "回");
    }

    public void rename(String name){
        this.name = name;
        updateCount++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestData)) return false;
        TestData testData = (TestData) o;
        return Objects.equals(id, testData.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
