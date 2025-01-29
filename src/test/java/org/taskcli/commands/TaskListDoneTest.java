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

class TaskListDoneTest {

    @Mock
    private TaskService service;

    @Mock
    private Logger log;

    @InjectMocks
    private TaskListDone taskListDone;

    AutoCloseable mocks;
    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRun_CallsPrintDoneTasks() {
        taskListDone.run();
        verify(service, times(1)).printDoneTasks();
    }

    @Test
    void testRun_LogsStartingAndEndingMessages() {
        taskListDone.run();
        verify(log, times(1)).info("Starting Task List Done");
        verify(log, times(1)).info("Ending Task List Done");
    }

    @Test
    void testRun_ServiceThrowsException() {
        doThrow(new RuntimeException("Service failed")).when(service).printDoneTasks();
        assertThrows(RuntimeException.class, () -> taskListDone.run());
        verify(service, times(1)).printDoneTasks();
    }
}
