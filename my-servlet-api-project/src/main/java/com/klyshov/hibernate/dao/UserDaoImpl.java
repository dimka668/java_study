package com.klyshov.hibernate.dao;

/**
 * Created by 16688641 on 10.01.2019.
 */

import com.klyshov.hibernate.Auto;
import com.klyshov.hibernate.User;
import com.klyshov.listener.AppContextListener;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.klyshov.hibernate.utils.HibernateSessionFactoryUtil;
import org.hibernate.annotations.common.util.impl.LoggerFactory;

import java.util.List;
import java.util.logging.Logger;

public class UserDaoImpl implements UserDao {

    private static final Logger log = Logger.getLogger(UserDaoImpl.class.getName());

    public User findById(int id) {
        log.info("findById: id="+id);
        Session session= HibernateSessionFactoryUtil.getSessionFactory().openSession();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }

    public void save(User user) {
        log.info("save: user=" + user.toString());
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    public void update(User user) {
        log.info("update: user=" + user.toString());
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    public void delete(User user) {
        log.info("delete: user=" + user.toString());
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }

    public Auto findAutoById(int id) {
        log.info("findAutoById: id=" + id);
        Session session= HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Auto auto = session.get(Auto.class, id);
        session.close();
        return auto;
    }

    public List<User> findAll() {
        log.info("findAll");
        Session session= HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<User> users = (List<User>) session.createQuery("From User").list();
        session.close();
        log.info(users.toString());
        return users;
    }
}
