package com.klyshov.spring.mvc.validator_example;

/**
 * Created by 16688641 on 23.10.2019.
 */
import java.util.Date;
public class User {
    private String name;
    private String password;
    private String email;
    private Date dob;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Date getDob() {
        return dob;
    }
    public void setDob(Date dob) {
        this.dob = dob;
    }
}
