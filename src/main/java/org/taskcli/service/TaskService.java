package org.taskcli.service;

public interface TaskService {
    void addTask(String desc);

    void deleteTask(int id);

    void printAllTasks();

    void printDoneTasks();

    void printInProgressTasks();

    void printToDoTasks();
}
