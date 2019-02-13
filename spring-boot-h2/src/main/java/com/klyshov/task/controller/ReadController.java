package com.klyshov.task.controller;

import com.klyshov.task.Task;
import com.klyshov.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.io.FileNotFoundException;

/**
 * Created by 16688641 on 13.02.2019.
 */
@Controller
public class ReadController {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private TaskService taskService;

    @Scheduled(fixedRate = 5000)
    public void read() throws FileNotFoundException {
        Task task = taskService.read();
        System.out.println(task);
    }
}
