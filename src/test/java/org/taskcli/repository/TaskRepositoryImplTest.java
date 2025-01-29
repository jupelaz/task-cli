package org.taskcli.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.taskcli.model.Status;
import org.taskcli.model.Task;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskRepositoryImplTest {

    private TaskRepositoryImpl taskRepository;
    private List<Task> taskList;

    @BeforeEach
    void setUp() {
        taskRepository = new TaskRepositoryImpl();
        taskList = new ArrayList<>();
    }

    @Test
    void testAddTaskToList() {
        assertTrue(taskRepository.addTaskToList("Task 1", taskList));
        assertEquals(1, taskList.size());
        assertEquals("Task 1", taskList.getFirst().getDescription());
        assertEquals(Status.READY, taskList.getFirst().getStatus());
    }

    @Test
    void testUpdateTaskInList() {
        taskRepository.addTaskToList("Task 1", taskList);
        assertTrue(taskRepository.updateTaskInList(1, "Updated Task 1", taskList));
        assertEquals("Updated Task 1", taskList.getFirst().getDescription());
    }

    @Test
    void testUpdateTaskInList_NonExistentTask() {
        assertFalse(taskRepository.updateTaskInList(1, "Task 1", taskList));
    }

    @Test
    void testDeleteTaskInList() {
        taskRepository.addTaskToList("Task 1", taskList);
        assertTrue(taskRepository.deleteTaskInList(1, taskList));
        assertTrue(taskList.isEmpty());
    }

    @Test
    void testDeleteTaskInList_NonExistentTask() {
        assertFalse(taskRepository.deleteTaskInList(1, taskList));
    }

    @Test
    void testMarkInProgress() {
        taskRepository.addTaskToList("Task 1", taskList);
        assertTrue(taskRepository.markInProgress(1, taskList));
        assertEquals(Status.IN_PROGRESS, taskList.getFirst().getStatus());
    }

    @Test
    void testMarkDone() {
        taskRepository.addTaskToList("Task 1", taskList);
        assertTrue(taskRepository.markDone(1, taskList));
        assertEquals(Status.DONE, taskList.getFirst().getStatus());
    }

    @Test
    void testMarkInProgress_NonExistentTask() {
        assertFalse(taskRepository.markInProgress(1, taskList));
    }

    @Test
    void testPrintAllList() {
        taskRepository.addTaskToList("Task 1", taskList);
        assertNotNull(taskRepository.printAllList(taskList));
    }

    @Test
    void testPrintDoneList() {
        taskRepository.addTaskToList("Task 1", taskList);
        taskRepository.markDone(1, taskList);
        assertNotNull(taskRepository.printDoneList(taskList));
    }

    @Test
    void testPrintInProgressList() {
        taskRepository.addTaskToList("Task 1", taskList);
        taskRepository.markInProgress(1, taskList);
        assertNotNull(taskRepository.printInProgressList(taskList));
    }

    @Test
    void testPrintTodoList() {
        taskRepository.addTaskToList("Task 1", taskList);
        assertNotNull(taskRepository.printTodoList(taskList));
    }

    @Test
    void testPrintList_EmptyList() {
        assertEquals("[]", taskRepository.printAllList(taskList));
    }
}