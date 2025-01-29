package org.taskcli.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void testTaskCreation() {
        LocalDateTime now = LocalDateTime.now();
        Task task = Task.builder()
                .id(1)
                .description("Sample Task")
                .status(Status.READY)
                .createdAt(now)
                .updatedAt(now)
                .build();

        assertNotNull(task);
        assertEquals(1, task.getId());
        assertEquals("Sample Task", task.getDescription());
        assertEquals(Status.READY, task.getStatus());
        assertEquals(now, task.getCreatedAt());
        assertEquals(now, task.getUpdatedAt());
    }

    @Test
    void testTaskWithNullValues() {
        Task task = Task.builder()
                .id(2)
                .description(null)
                .status(Status.READY)
                .createdAt(null)
                .updatedAt(null)
                .build();

        assertNotNull(task);
        assertEquals(2, task.getId());
        assertNull(task.getDescription());
        assertNull(task.getCreatedAt());
        assertNull(task.getUpdatedAt());
    }

    @Test
    void testTaskWithInvalidId() {
        assertThrows(IllegalArgumentException.class, () -> Task.builder()
                .id(-1)
                .description("Invalid Task")
                .status(Status.DONE)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build());
    }

    @Test
    void testTaskWithInvalidStatus() {
        assertThrows(NullPointerException.class, () -> Task.builder()
                .id(3)
                .description("Task with Invalid Status")
                .status(null)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build());
    }
}