package com.mr2.zaiko.zaiko2.useCase;

import com.mr2.zaiko.zaiko2.domain.test.TestData;
import com.mr2.zaiko.zaiko2.domain.test.TestRepository;

public class TestApplicationService {
    private TestRepository repository;
    private TestData data;

    public TestApplicationService(TestRepository repository) {
        this.repository = repository;
    }

    public String createData(){
        data = repository.create();
        return data.name();
    }

    public boolean existsData(){
        return null != data;
    }

    public String date(){
        return data.name();
    }

    public void save(){
        repository.save(data);
    }

    public String load(){
        data = repository.get();
        return data.name();
    }

    public String rename(String name){
        data.rename(name);
        return data.name();
    }

    public String delete(){
        repository.delete(this.data);
        return data.name();
    }

    public TestData checkData(){
        return repository.verification();
    }
}
