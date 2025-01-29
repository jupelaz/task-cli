package org.taskcli.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.taskcli.service.TaskService;
import org.slf4j.Logger;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;

class TaskListTodoTest {

    @Mock
    private TaskService service;

    @Mock
    private Logger log;

    @InjectMocks
    private TaskListTodo taskListTodo;

    AutoCloseable mocks;
    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRun_CallsPrintToDoTasks() {
        taskListTodo.run();
        verify(service, times(1)).printToDoTasks();
    }

    @Test
    void testRun_LogsStartingAndEndingMessages() {
        taskListTodo.run();
        verify(log, times(1)).info("Starting Task List To Do");
        verify(log, times(1)).info("Ending Task List To Do");
    }

    @Test
    void testRun_ServiceThrowsException() {
        doThrow(new RuntimeException("Service failed")).when(service).printToDoTasks();
        assertThrows(RuntimeException.class, () -> taskListTodo.run());
        verify(service, times(1)).printToDoTasks();
    }
}
