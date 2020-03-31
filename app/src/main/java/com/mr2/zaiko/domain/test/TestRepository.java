package com.mr2.zaiko.domain.test;

public interface TestRepository {
    TestData create();
    TestData get();
    void save(TestData data);
    void delete(TestData data);
    TestData verification();
}
