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

class TaskMarkInProgressTest {

    @Mock
    private TaskService service;

    @Mock
    private Logger log;

    @InjectMocks
    private TaskMarkInProgress taskMarkInProgress;

    AutoCloseable mocks;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRun_CallsPrintInProgressTasks() {
        taskMarkInProgress.run();
        verify(service, times(1)).printInProgressTasks();
    }

    @Test
    void testRun_LogsStartingAndCompletedMessages() {
        taskMarkInProgress.run();
        verify(log, times(1)).info("Starting Task Mark In Progress: Id is {}", taskMarkInProgress.id);
        verify(log, times(1)).info("Task Mark In Progress Completed");
    }

    @Test
    void testRun_ServiceThrowsException() {
        doThrow(new RuntimeException("Service failed")).when(service).printInProgressTasks();
        assertThrows(RuntimeException.class, () -> taskMarkInProgress.run());
        verify(service, times(1)).printInProgressTasks();
    }

    @Test
    void testRun_IdParameterIsLogged() {
        taskMarkInProgress.id = 5; // Set a specific ID
        taskMarkInProgress.run();
        verify(log, times(1)).info("Starting Task Mark In Progress: Id is {}", 5);
    }
}