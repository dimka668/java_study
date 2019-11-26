package com.klyshov.habrspringboot.repository;

import com.klyshov.habrspringboot.entity.DomainObject;

import java.util.Set;

/**
 * Created by 16688641 on 12.11.2019.
 */
public interface DataRepository<V extends DomainObject> {

    void persist(V object);

    void delete(V object);

    Set<String> getRandomData();

}
