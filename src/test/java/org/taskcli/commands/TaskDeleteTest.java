package org.taskcli.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.taskcli.service.TaskService;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

class TaskDeleteTest {

    @Mock
    private TaskService service;

    @InjectMocks
    private TaskDelete taskDelete;

    AutoCloseable mocks;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteTask() {
        taskDelete.id = 1;
        taskDelete.run();
        verify(service, times(1)).deleteTask(1);
    }

    @Test
    void testDeleteTaskWithLargeId() {
        taskDelete.id = Integer.MAX_VALUE;
        taskDelete.run();
        verify(service, times(1)).deleteTask(Integer.MAX_VALUE);
    }

    @Test
    void testDeleteTaskWithInvalidId() {
        taskDelete.id = 0;
        taskDelete.run();
        verify(service, times(1)).deleteTask(0);
    }
}
