package com.klyshov.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.*;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by 16688641 on 10.01.2019.
 */
@WebListener
public class AppContextListener implements ServletContextListener {

    private static final Logger log = Logger.getLogger(AppContextListener.class.getName());

    public void contextInitialized(ServletContextEvent servletContextEvent){

        log.info(">>>>>>>>> Starting AppContextListener...");
        ServletContext ctx = servletContextEvent.getServletContext();
        ctx.getContextPath();

        log.info(">>>>>>>>> Reading system property 'app.config.properties'");
        String appConfigPath = System.getProperty("app.config.properties");
        if (appConfigPath == null)
            throw new NullPointerException("System property app.config.properties was not found. \n" +
                    "\tPlease, add start server jvm argument '-Dapp.config.properties=<existing file on server>'. \n");
//                    "\t\t*Example, for tomcat, add JVM_OPTS=-Dapp.config.properties=/home/user/config.properties\n");
//                    "\tPlease, \n" +
//                    "\t1. Add start server jvm argument '-Dapp.config.properties=<existing file on server>'. \n" +
//                    "\t\t*Example, for tomcat, add JVM_OPTS=-Dapp.config.properties=/home/user/config.properties\n" +
//                    "\t2. Add property application.name to here\n" +
//                    "\t\t*Example: application.name=SomeName");
//        File configDir = new File(System.getProperty("catalina.base"), "conf");
//        File configFile = new File(configDir, "myconfig.properties");
        log.info(">>>>>>>>> Reading 'app.config.properties'");
        File configFile = new File(appConfigPath);

        InputStream stream = null;
        try {
            stream = new FileInputStream(configFile);
            Properties props = new Properties();
            props.load(stream);
            log.info(props.toString());
            String applicationName = props.getProperty("application.name");
            log.info(">>>>>>>>> Reading application.name from app.config.properties");
            log.info(">>>>>>>>> application.name=" + props.getProperty("application.name"));
            if (applicationName == null)
                throw new NullPointerException("Property application.name not found in app.config.properties. Please write application.name=some_name to app.config.properties");
            ctx.setAttribute("application.name", props.getProperty("application.name"));
            log.info(">>>>>>>>> Set context attribute application.name="+props.getProperty("application.name").toString()+" from 'app.config.properties'");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        //String url = ctx.getInitParameter("DBURL");
        //ctx.setAttribute("DBManager", dbManager);

        //Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        //session.close();



    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log.info(">>>>>>>>> destroy context");
    }

}
