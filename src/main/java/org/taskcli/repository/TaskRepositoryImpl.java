package org.taskcli.repository;

import jakarta.enterprise.context.RequestScoped;
import org.taskcli.model.Status;
import org.taskcli.model.Task;

import java.time.LocalDateTime;
import java.util.List;

@RequestScoped
public class TaskRepositoryImpl implements TaskRepository {

    public boolean addTaskToList(String description, List<Task> taskList){
        int id = taskList.stream().mapToInt(Task::getId).min().orElse(0) + 1;
        LocalDateTime now = LocalDateTime.now();
        Task newTask = Task.builder()
                .id(id)
                .description(description)
                .status(Status.READY)
                .createdAt(now)
                .updatedAt(now)
                .build();
        taskList.add(newTask);
        return true;
    }

    public boolean updateTaskInList(int id, String desc, List<Task> list){
        boolean updated = false;
        for (Task t : list){
            if (t.getId() == id) {
                t.setDescription(desc);
                t.setUpdatedAt(LocalDateTime.now());
                updated = true;
            }
        }
        return updated;
    }

    public boolean deleteTaskInList(int id, List<Task> list) {
        Task currentTask = list.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
        if(currentTask == null) return false;
        list.removeIf(t -> t.getId() == id);
        return true;
    }

    public boolean markInProgress(int id, List<Task> list) {
        return mark(id,Status.IN_PROGRESS,list);
    }

    public boolean markDone(int id, List<Task> list) {
        return mark(id,Status.DONE,list);
    }

    public String printAllList(List<Task> taskList) {
        return printList(taskList,null);
    }

    public String printDoneList(List<Task> taskList) {
        return printList(taskList,Status.DONE);
    }

    public String printInProgressList(List<Task> taskList) {
        return printList(taskList,Status.IN_PROGRESS);
    }

    public String printTodoList(List<Task> taskList) {
        return printList(taskList,Status.READY);
    }

    private boolean mark(int id, Status status, List<Task> list){
        boolean updated = false;
        for (Task t : list){
            if (t.getId() == id) {
                t.setStatus(status);
                t.setUpdatedAt(LocalDateTime.now());
                updated = true;
            }
        }
        return updated;
    }

    private String printList(List<Task> taskList, Status status) {
        if (status == null)
            return taskList.toString();
        else
            return taskList.stream().filter(task -> task.getStatus().equals(status)).toList().toString();
    }
}
