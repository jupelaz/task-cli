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

class TaskListInProgressTest {

    @Mock
    private TaskService service;

    @Mock
    private Logger log;

    @InjectMocks
    private TaskListInProgress taskListInProgress;

    AutoCloseable mocks;
    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRun_CallsPrintInProgressTasks() {
        taskListInProgress.run();
        verify(service, times(1)).printInProgressTasks();
    }

    @Test
    void testRun_LogsStartingAndEndingMessages() {
        taskListInProgress.run();
        verify(log, times(1)).info("Starting Task List In Progress");
        verify(log, times(1)).info("Ending Task List In Progress");
    }

    @Test
    void testRun_ServiceThrowsException() {
        doThrow(new RuntimeException("Service failed")).when(service).printInProgressTasks();
        assertThrows(RuntimeException.class, () -> taskListInProgress.run());
        verify(service, times(1)).printInProgressTasks();
    }
}
