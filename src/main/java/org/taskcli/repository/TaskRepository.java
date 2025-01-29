package org.taskcli.repository;

import org.taskcli.model.Task;

import java.util.List;

public interface TaskRepository {
    boolean addTaskToList(String description, List<Task> taskList);
    boolean updateTaskInList(int id, String desc, List<Task> list);
    boolean deleteTaskInList(int id, List<Task> list);
    boolean markInProgress(int id, List<Task> list);
    boolean markDone(int id, List<Task> list);
    String printAllList(List<Task> taskList);
    String printDoneList(List<Task> taskList);
    String printInProgressList(List<Task> taskList);
    String printTodoList(List<Task> taskList);
}
