package lessons.starter;

import org.apache.log4j.LogManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by SBT-Klyshov-DA on 29.05.2018.
 */
public class ResourceStarter {

    public static final org.apache.log4j.Logger LOGGER = LogManager.getLogger(ResourceStarter.class);

    public static void main(String[] args) throws IOException {
        //...
        resourcePath();
    }

    public static void resourcePath() throws IOException {
        LOGGER.info("*****************resourcePath()*********************");
        LOGGER.info("context = new ClassPathXmlApplicationContext()");
        ApplicationContext context = new ClassPathXmlApplicationContext();
        Resource resource = context.getResource("log4j.properties");
        LOGGER.info("log4j.properties exist: " + resource.getFile().exists());
//        resource = context.getResource("src/main/resources/log4j.properties");
//        LOGGER.info("log4j.properties exist: " + resource.getFile().exists()); //IOException
        resource = context.getResource("file:src/main/resources/log4j.properties");
        LOGGER.info("file:src/main/resources/log4j.properties exist: " + resource.getFile().exists());
        resource = context.getResource("log/log.txt");
        LOGGER.info("log/log.txt exist: " + resource.getFile().exists());
        resource = context.getResource("classpath:log/log.txt");
        LOGGER.info("classpath:log/log.txt exist: " + resource.getFile().exists());
        resource = context.getResource("file:log.txt");
        LOGGER.info("file:log.txt exist: " + resource.getFile().exists());
        resource = context.getResource("file:log/log.txt");
        LOGGER.info("file:log/log.txt exist: " + resource.getFile().exists());
        resource = context.getResource("file:src/main/resources/log/log.txt");
        LOGGER.info("file:src/main/resources/log/log.txt exist: " + resource.getFile().exists());
        LOGGER.info("context = new FileSystemXmlApplicationContext()");
        context = new FileSystemXmlApplicationContext();
        resource = context.getResource("log4j.properties");
        LOGGER.info("log4j.properties exist: " + resource.getFile().exists());
        resource = context.getResource("src/main/resources/log4j.properties");
        LOGGER.info("src/main/resources/log4j.properties exist: " + resource.getFile().exists());
        resource = context.getResource("log/log.txt");
        LOGGER.info("log/log.txt exist: " + resource.getFile().exists());
        resource = context.getResource("classpath:log/log.txt");
        LOGGER.info("classpath:log/log.txt exist: " + resource.getFile().exists());
        resource = context.getResource("file:log.txt");
        LOGGER.info("file:log.txt exist: " + resource.getFile().exists());
        resource = context.getResource("file:log/log.txt");
        LOGGER.info("file:log/log.txt exist: " + resource.getFile().exists());
        LOGGER.info("****************************************************");
    }
}
