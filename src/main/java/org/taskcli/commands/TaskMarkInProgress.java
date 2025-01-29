package org.taskcli.commands;

import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.taskcli.service.TaskService;
import picocli.CommandLine.Command;
import picocli.CommandLine.HelpCommand;
import picocli.CommandLine.Parameters;

@Slf4j
@Command(name = "mark-in-progress", description = "Updates an task status to in progress from the task list", subcommands = HelpCommand.class)
public class TaskMarkInProgress implements Runnable {

    @Inject
    private TaskService service;

    @Parameters(paramLabel = "<id>", defaultValue = "1",
            description = "Id of the task to be marked as in progress in the list")
    public int id;

    @Override
    public void run() {
        log.info("Starting Task Mark In Progress: Id is {}", id);
        service.printInProgressTasks();
        log.info("Task Mark In Progress Completed");
    }
}
