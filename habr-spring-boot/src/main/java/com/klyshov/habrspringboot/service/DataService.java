package com.klyshov.habrspringboot.service;

import java.util.Set;

/**
 * Created by 16688641 on 12.11.2019.
 */
public interface DataService {

    public boolean persist(String problem);

    public Set<String> getRandomData();
}