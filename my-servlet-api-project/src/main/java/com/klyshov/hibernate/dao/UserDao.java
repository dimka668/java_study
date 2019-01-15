package com.klyshov.hibernate.dao;

import com.klyshov.hibernate.Auto;
import com.klyshov.hibernate.User;

import java.util.List;

/**
 * Created by 16688641 on 10.01.2019.
 */
public interface UserDao {
    public User findById(int id);
    public void save(User user);
    public void update(User user);
    public void delete(User user);
    public Auto findAutoById(int id);
    public List<User> findAll();
}
