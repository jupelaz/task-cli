package org.taskcli.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.taskcli.model.Status;
import org.taskcli.model.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.taskcli.utils.Constants.TASKS_FILE;

class FileUtilsImplTest {

    private FileUtilsImpl fileUtils;

    @BeforeEach
    void setUp() throws IOException {
        fileUtils = new FileUtilsImpl();
        Files.deleteIfExists(Path.of(TASKS_FILE));
    }

    @Test
    void testReadFile_Success() throws IOException {
        Task task = Task.builder().id(1).description("Test").createdAt(LocalDateTime.now()).status(Status.READY).updatedAt(LocalDateTime.now()).build();
        fileUtils.writeListToFile(Collections.singletonList(task));
        List<Task> tasks = fileUtils.readFile();
        assertNotNull(tasks);
        assertEquals(1, tasks.size());
    }

    @Test
    void testReadFile_FileNotFound() {
        assertThrows(FileNotFoundException.class, () -> fileUtils.readFile());
    }

    @Test
    void testCheckIfFileExists_FileCreated() throws IOException {
        assertFalse(Files.exists(Path.of(TASKS_FILE)));
        fileUtils.checkIfFileExists();
        assertTrue(Files.exists(Path.of(TASKS_FILE)));
    }

    @Test
    void testCheckIfFileExists_FileAlreadyExists() throws IOException {
        assertFalse(Files.exists(Path.of(TASKS_FILE)));
        fileUtils.checkIfFileExists();
        assertTrue(Files.exists(Path.of(TASKS_FILE)));
        fileUtils.checkIfFileExists();
        assertTrue(Files.exists(Path.of(TASKS_FILE)));
    }

    @Test
    void testWriteListToFile_Success() throws IOException {
        Task task = Task.builder().id(1).description("Test").createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).status(Status.READY).build();
        fileUtils.writeListToFile(List.of(task));
        assertEquals(1, fileUtils.readFile().size());
    }

    @Test
    void testWriteListToFile_EmptyList() throws IOException {
        List<Task> tasks = Collections.emptyList();
        fileUtils.writeListToFile(tasks);
        assertEquals(0, fileUtils.readFile().size());
    }

}