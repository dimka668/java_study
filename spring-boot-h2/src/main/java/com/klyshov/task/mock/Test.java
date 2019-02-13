package com.klyshov.task.mock;

import com.klyshov.task.Task;

/**
 * Created by 16688641 on 12.02.2019.
 */
public class Test {
    public static void main(String[] args) {
        Task task = new Task("user", "command", 2);
        task.marshalToFile("./task.txt");
        Task task1 = Task.unmarshalFromFile("./task.txt");
        System.out.println(task1);
    }
}
