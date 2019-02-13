package com.klyshov.task.controller;

import com.klyshov.task.Task;
import com.klyshov.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

/**
 * Created by 16688641 on 12.02.2019.
 */
@Controller
public class CreateController {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private TaskService taskService;

    @Scheduled(fixedRate = 5000)
    public void createTask() {
        taskService.send(new Task("user", "command", 2));
    }
}
