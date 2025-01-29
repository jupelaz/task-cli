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

class TaskListTest {

    @Mock
    private TaskService service;

    @Mock
    private Logger log;

    @InjectMocks
    private TaskList taskList;

    AutoCloseable mocks;
    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRun_CallsPrintAllTasks() {
        taskList.run();
        verify(service, times(1)).printAllTasks();
    }

    @Test
    void testRun_LogsStartingMessage() {
        taskList.run();
        verify(log, times(1)).info("Starting Task List All");
    }

    @Test
    void testRun_ServiceThrowsException() {
        doThrow(new RuntimeException("Service failed")).when(service).printAllTasks();
        assertThrows(RuntimeException.class, () -> taskList.run());
        verify(service, times(1)).printAllTasks();
    }
}
