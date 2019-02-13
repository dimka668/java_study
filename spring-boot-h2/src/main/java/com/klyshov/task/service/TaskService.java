package com.klyshov.task.service;

import com.klyshov.task.Task;

public interface TaskService {
    public void send(Task task);
    public Task read();
    void readValues();
}
