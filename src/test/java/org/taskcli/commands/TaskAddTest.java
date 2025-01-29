package org.taskcli.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.taskcli.service.TaskService;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

class TaskAddTest {

    @Mock
    private TaskService service;

    @InjectMocks
    private TaskAdd taskAdd;

    AutoCloseable mocks;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddTaskWithDescription() {
        taskAdd.desc = "Test Task";
        taskAdd.run();
        verify(service, times(1)).addTask("Test Task");
    }

    @Test
    void testAddTaskWithEmptyDescription() {
        taskAdd.desc = "";
        taskAdd.run();
        verify(service, times(1)).addTask("");
    }

    @Test
    void testAddTaskWithNullDescription() {
        taskAdd.desc = null;
        taskAdd.run();
        verify(service, times(1)).addTask(null);
    }
}
