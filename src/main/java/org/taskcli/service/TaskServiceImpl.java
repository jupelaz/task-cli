package org.taskcli.service;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.taskcli.model.Task;
import org.taskcli.repository.FileUtils;
import org.taskcli.repository.TaskRepository;

import java.io.IOException;
import java.util.List;

import static org.taskcli.utils.Errors.*;

@Slf4j
@RequestScoped
public class TaskServiceImpl implements TaskService{
    @Inject
    private FileUtils fileUtils;
    @Inject
    private TaskRepository repository;


    public void addTask(String desc){
        val taskList = readTasks();
        if (taskList == null) return;

        if (!repository.addTaskToList(desc,taskList)){
            serviceError(TASK_ADD_ERROR);
            return;
        }
        writeTasks(taskList);
    }

    @Override
    public void deleteTask(int id) {
        val taskList = readTasks();
        if (taskList == null) return;
        if (!repository.deleteTaskInList(id,taskList)){
            serviceError(TASK_DELETE_ERROR);
            return;
        }
        writeTasks(taskList);
    }

    @Override
    public void printAllTasks() {
        val taskList = readTasks();
        if (taskList == null || taskList.isEmpty()) {
            serviceError(NO_TASKS_ERROR);
            return;
        }
        String output = repository.printAllList(taskList);
        System.out.println(output);
    }

    @Override
    public void printDoneTasks() {
        val taskList = readTasks();
        if (taskList == null || taskList.isEmpty()) {
            serviceError(NO_DONE_TASKS_ERROR);
            return;
        }
        String output = repository.printDoneList(taskList);
        System.out.println(output);
    }

    @Override
    public void printInProgressTasks() {
        val taskList = readTasks();
        if (taskList == null || taskList.isEmpty()) {
            serviceError(NO_IN_PROGRESS_TASKS_ERROR);
            return;
        }
        String output = repository.printInProgressList(taskList);
        System.out.println(output);
    }

    @Override
    public void printToDoTasks() {
        val taskList = readTasks();
        if (taskList == null || taskList.isEmpty()) {
            serviceError(NO_TODO_TASKS_ERROR);
            return;
        }
        String output = repository.printTodoList(taskList);
        System.out.println(output);
    }

    private List<Task> readTasks(){
        try {
            fileUtils.checkIfFileExists();
            return fileUtils.readFile();
        } catch (IOException e) {
            serviceError(FILE_READ_ERROR);
            return null;
        }
    }
    private void writeTasks(List<Task> taskList){
        try {
            fileUtils.writeListToFile(taskList);
        } catch (IOException e) {
            serviceError(FILE_WRITE_ERROR);
        }
    }

    private void serviceError(String error) {
        System.out.println(error);
        log.error(error);
    }
}
