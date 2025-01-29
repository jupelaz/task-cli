package org.taskcli.commands;

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
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class TaskUpdateTest {

    @Mock
    private FileUtils fileUtils;

    @Mock
    private TaskRepository taskService;

    @InjectMocks
    private TaskUpdate taskUpdate;

    AutoCloseable mocks;
    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateTaskSuccessfully() throws IOException {
        taskUpdate.id = 1;
        taskUpdate.desc = "Updated Task";

        List<Task> taskList = new ArrayList<>();
        taskList.add(Task.builder().id(1).description("Task 1").status(Status.READY).build());

        when(fileUtils.readFile()).thenReturn(taskList);
        when(taskService.updateTaskInList(1, "Updated Task", taskList)).thenReturn(true);

        taskUpdate.run();

        verify(taskService, times(1)).updateTaskInList(1, "Updated Task", taskList);
        verify(fileUtils, times(1)).writeListToFile(taskList);
    }

    @Test
    void testUpdateTaskWithNonExistentId() throws IOException {
        taskUpdate.id = 99;
        taskUpdate.desc = "Non-existent Task";

        List<Task> taskList = new ArrayList<>();
        taskList.add(Task.builder().id(1).description("Task 1").status(Status.READY).build());

        when(fileUtils.readFile()).thenReturn(taskList);
        when(taskService.updateTaskInList(99, "Non-existent Task", taskList)).thenReturn(false);

        taskUpdate.run();

        verify(taskService, times(1)).updateTaskInList(99, "Non-existent Task", taskList);
        verify(fileUtils, times(1)).writeListToFile(taskList);
    }

    @Test
    void testUpdateTaskWithEmptyDescription() throws IOException {
        taskUpdate.id = 1;
        taskUpdate.desc = "";

        List<Task> taskList = new ArrayList<>();
        taskList.add(Task.builder().id(1).description("Task 1").status(Status.READY).build());

        when(fileUtils.readFile()).thenReturn(taskList);
        when(taskService.updateTaskInList(1, "", taskList)).thenReturn(true);

        taskUpdate.run();

        verify(taskService, times(1)).updateTaskInList(1, "", taskList);
        verify(fileUtils, times(1)).writeListToFile(taskList);
    }

    @Test
    void testUpdateTaskWithInvalidId() throws IOException {
        taskUpdate.id = -1;
        taskUpdate.desc = "Invalid Task";

        List<Task> taskList = new ArrayList<>();
        taskList.add(Task.builder().id(1).description("Task 1").status(Status.READY).build());

        when(fileUtils.readFile()).thenReturn(taskList);
        when(taskService.updateTaskInList(-1, "Invalid Task", taskList)).thenReturn(false);

        taskUpdate.run();

        verify(taskService, times(1)).updateTaskInList(-1, "Invalid Task", taskList);
        verify(fileUtils, times(1)).writeListToFile(taskList);
    }

    @Test
    void testUpdateTaskWithNullDescription() throws IOException {
        taskUpdate.id = 1;
        taskUpdate.desc = null;

        List<Task> taskList = new ArrayList<>();
        taskList.add(Task.builder().id(1).description("Task 1").status(Status.READY).build());

        when(fileUtils.readFile()).thenReturn(taskList);
        when(taskService.updateTaskInList(1, null, taskList)).thenReturn(false);

        taskUpdate.run();

        verify(taskService, times(1)).updateTaskInList(1, null, taskList);
        verify(fileUtils, times(1)).writeListToFile(taskList);
    }
}
