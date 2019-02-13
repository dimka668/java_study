package com.klyshov.task.service;

import com.klyshov.task.Task;

import java.io.FileNotFoundException;

public interface TaskService {
    public void send(Task task);
    public Task read() throws FileNotFoundException;
    void readValues();
}
