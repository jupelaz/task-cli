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

class TaskMarkDoneTest {

    @Mock
    private TaskService service;

    @Mock
    private Logger log;

    @InjectMocks
    private TaskMarkDone taskMarkDone;

    AutoCloseable mocks;
    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRun_CallsPrintDoneTasks() {
        taskMarkDone.run();
        verify(service, times(1)).printDoneTasks();
    }

    @Test
    void testRun_LogsStartingAndCompletedMessages() {
        taskMarkDone.run();
        verify(log, times(1)).info("Starting Task Mark Done: Id is {}", taskMarkDone.id);
        verify(log, times(1)).info("Task Mark Done Completed");
    }

    @Test
    void testRun_ServiceThrowsException() {
        doThrow(new RuntimeException("Service failed")).when(service).printDoneTasks();
        assertThrows(RuntimeException.class, () -> taskMarkDone.run());
        verify(service, times(1)).printDoneTasks();
    }

    @Test
    void testRun_IdParameterIsLogged() {
        taskMarkDone.id = 5; // Set a specific ID
        taskMarkDone.run();
        verify(log, times(1)).info("Starting Task Mark Done: Id is {}", 5);
    }
}
