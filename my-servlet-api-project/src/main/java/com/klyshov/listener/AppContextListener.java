package com.klyshov.listener;

import com.klyshov.hibernate.dao.UserDaoImpl;
import com.klyshov.hibernate.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.logging.Logger;

/**
 * Created by 16688641 on 10.01.2019.
 */
@WebListener
public class AppContextListener implements ServletContextListener {

    private static final Logger log = Logger.getLogger(AppContextListener.class.getName());

    public void contextInitialized(ServletContextEvent servletContextEvent) {

        log.info("========= init");
        ServletContext ctx = servletContextEvent.getServletContext();
        ctx.getContextPath();
        //String url = ctx.getInitParameter("DBURL");
        //ctx.setAttribute("DBManager", dbManager);

        //Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        //session.close();



    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log.info("========= destroy");
    }

}
