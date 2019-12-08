package com.klyshov.spring.mvc.session_example.domain;

/**
 * Created by 16688641 on 06.10.2019.
 */
public class Person {
    private String name;

    public Person(String name) {
        super();
        this.name = name;
    }

    public String getGreeting(){
        return "Hi " + name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

