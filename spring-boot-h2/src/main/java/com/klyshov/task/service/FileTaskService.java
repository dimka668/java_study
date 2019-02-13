package com.klyshov.task.service;

import com.klyshov.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

//import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

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
    public Task read() throws FileNotFoundException {
        //ArrayList<Task> taskList = new ArrayList<>();
        File folder = new File(storedir);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                Task task = Task.unmarshalFromFile( file.getAbsolutePath());
                file.delete();
                return task;
            }
        }
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
