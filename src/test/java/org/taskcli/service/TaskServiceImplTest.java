package org.taskcli.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.taskcli.model.Status;
import org.taskcli.model.Task;
import org.taskcli.repository.FileUtils;
import org.taskcli.repository.TaskRepository;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

class TaskServiceImplTest {

    @Mock
    private FileUtils fileUtils;

    @Mock
    private TaskRepository repository;

    @InjectMocks
    private TaskServiceImpl taskService;

    AutoCloseable mocks;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddTask_Success() throws IOException {
        List<Task> taskList = Collections.emptyList();
        when(repository.addTaskToList(eq("New Task"), eq(taskList))).thenReturn(true);
        taskService.addTask("New Task");
        verify(repository, times(1)).addTaskToList("New Task", taskList);
        verify(fileUtils, times(1)).writeListToFile(taskList);
    }

    @Test
    void testAddTask_Failure() throws IOException {
        List<Task> taskList = Collections.emptyList();
        when(fileUtils.readFile()).thenReturn(taskList);
        when(repository.addTaskToList(anyString(), eq(taskList))).thenReturn(false);

        taskService.addTask("New Task");

        verify(repository, times(1)).addTaskToList("New Task", taskList);
        verify(fileUtils, never()).writeListToFile(taskList);
    }

    @Test
    void testDeleteTask_Success() throws IOException {
        List<Task> taskList = Collections.emptyList();
        when(fileUtils.readFile()).thenReturn(taskList);
        when(repository.deleteTaskInList(anyInt(), eq(taskList))).thenReturn(true);

        taskService.deleteTask(1);

        verify(repository, times(1)).deleteTaskInList(1, taskList);
        verify(fileUtils, times(1)).writeListToFile(taskList);
    }

    @Test
    void testDeleteTask_Failure() throws IOException {
        List<Task> taskList = Collections.emptyList();
        when(fileUtils.readFile()).thenReturn(taskList);
        when(repository.deleteTaskInList(anyInt(), eq(taskList))).thenReturn(false);

        taskService.deleteTask(1);

        verify(repository, times(1)).deleteTaskInList(1, taskList);
        verify(fileUtils, never()).writeListToFile(taskList);
    }

    @Test
    void testPrintAllTasks_Success() throws IOException {
        List<Task> taskList = Collections.singletonList(Task.builder().status(Status.READY).build());
        when(fileUtils.readFile()).thenReturn(taskList);
        when(repository.printAllList(taskList)).thenReturn("Task List");

        taskService.printAllTasks();

        verify(repository, times(1)).printAllList(taskList);
    }

    @Test
    void testPrintAllTasks_EmptyList() throws IOException {
        when(fileUtils.readFile()).thenReturn(Collections.emptyList());

        taskService.printAllTasks();

        verify(repository, never()).printAllList(anyList());
    }

    @Test
    void testPrintDoneTasks_Success() throws IOException {
        List<Task> taskList = Collections.singletonList(Task.builder().status(Status.DONE).build());
        when(fileUtils.readFile()).thenReturn(taskList);
        when(repository.printDoneList(taskList)).thenReturn("Done Tasks");

        taskService.printDoneTasks();

        verify(repository, times(1)).printDoneList(taskList);
    }

    @Test
    void testPrintDoneTasks_EmptyList() throws IOException {
        when(fileUtils.readFile()).thenReturn(Collections.emptyList());

        taskService.printDoneTasks();

        verify(repository, never()).printDoneList(anyList());
    }

    @Test
    void testPrintInProgressTasks_Success() throws IOException {
        List<Task> taskList = Collections.singletonList(Task.builder().status(Status.IN_PROGRESS).build());
        when(fileUtils.readFile()).thenReturn(taskList);
        when(repository.printInProgressList(taskList)).thenReturn("In Progress Tasks");

        taskService.printInProgressTasks();

        verify(repository, times(1)).printInProgressList(taskList);
    }

    @Test
    void testPrintInProgressTasks_EmptyList() throws IOException {
        when(fileUtils.readFile()).thenReturn(Collections.emptyList());

        taskService.printInProgressTasks();

        verify(repository, never()).printInProgressList(anyList());
    }

    @Test
    void testPrintToDoTasks_Success() throws IOException {
        List<Task> taskList = Collections.singletonList(Task.builder().status(Status.READY).build());
        when(fileUtils.readFile()).thenReturn(taskList);
        when(repository.printTodoList(taskList)).thenReturn("ToDo Tasks");

        taskService.printToDoTasks();

        verify(repository, times(1)).printTodoList(taskList);
    }

    @Test
    void testPrintToDoTasks_EmptyList() throws IOException {
        when(fileUtils.readFile()).thenReturn(Collections.emptyList());

        taskService.printToDoTasks();

        verify(repository, never()).printTodoList(anyList());
    }

    @Test
    void testReadTasks_IOException() throws IOException {
        when(fileUtils.readFile()).thenThrow(new IOException());

        taskService.printAllTasks();

        verify(fileUtils, times(1)).readFile();
    }
}