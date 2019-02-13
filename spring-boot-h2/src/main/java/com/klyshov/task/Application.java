package com.klyshov.task;

/*
import com.klyshov.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableScheduling;
*/
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
//@Configuration
//@ComponentScan(basePackages = "com.klyshov.task")
//@PropertySource(value = { "classpath:application.properties" })
public class Application {



    public static void main(String[] args) {
//        AbstractApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
//        FileTaskService taskService = new FileTaskService();
        //taskService.send(new Task("user", "command", 2));
//        taskService.readValues();
//        FileService service = (FileService) context.getBean("fileService");
  //      service.readValues();
    //    context.close();


        SpringApplication.run(new Class<?>[] {Application.class}, args);
    }


}

