package com.klyshov.springbootdemo.repository;

import com.klyshov.springbootdemo.entity.DomainObject;

import java.util.Set;

public interface DataRepository<V extends DomainObject> {

    void persist(V object);

    void delete(V object);

    Set<String> getRandomData();

}
