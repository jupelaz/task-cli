package org.taskcli.repository;

import org.taskcli.model.Task;

import java.io.IOException;
import java.util.List;


public interface FileUtils {
    List<Task> readFile() throws IOException;
    void checkIfFileExists() throws IOException;
    void writeListToFile(List<Task> data) throws IOException;
}
