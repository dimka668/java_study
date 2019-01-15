package com.klyshov.hibernate.services;

import com.klyshov.hibernate.Auto;
import com.klyshov.hibernate.User;
import com.klyshov.hibernate.dao.*;

import java.util.List;

/**
 * Created by 16688641 on 10.01.2019.
 */

public class UserService {

        private UserDao usersDao = new UserDaoImpl();

        public UserService() {
        }

        public User findUser(int id) {
            return usersDao.findById(id);
        }

        public void saveUser(User user) {
            usersDao.save(user);
        }

        public void deleteUser(User user) {
            usersDao.delete(user);
        }

        public void updateUser(User user) {
            usersDao.update(user);
        }

        public List<User> findAllUsers() {
            return usersDao.findAll();
        }

        public Auto findAutoById(int id) {
            return usersDao.findAutoById(id);
        }

    }
