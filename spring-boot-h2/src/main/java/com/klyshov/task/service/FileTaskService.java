package com.klyshov.task.service;

import com.klyshov.task.Task;
import com.klyshov.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

//import javax.annotation.PostConstruct;
import java.io.File;

/**
 * Created by 16688641 on 12.02.2019.
 */

@Service("taskService")
//@PropertySource(name = "myProperties", value = "application.properties")
public class FileTaskService implements TaskService {

    @Value("${storedir}")
    public String storedir;

    @Autowired
    private Environment environment;

    @Override
    public void send(Task task) {
        task.marshalToFile( storedir + File.separator + FileNameGenerator.next());
    }

    @Override
    public Task read() {
        return null;
    }

    private static class FileNameGenerator{
        public static int counter;
        static String next(){
            return "file"+counter++;
        }
    }

    public void readValues() {
        System.out.println("storedir : " + storedir);
    }

//    @PostConstruct
  //  public void afterInitialize() {
    //    System.out.println("---------------"+storedir);
    //}
}
