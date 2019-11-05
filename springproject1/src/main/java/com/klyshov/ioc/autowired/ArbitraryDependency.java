package com.klyshov.ioc.autowired;

import org.springframework.stereotype.Component;

/**
 * Created by 16688641 on 31.10.2019.
 */
@Component
public class ArbitraryDependency {

    private final String label = "Arbitrary Dependency";

    public String toString() {
        return label;
    }
}
