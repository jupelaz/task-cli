package org.taskcli.commands;

import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.taskcli.service.TaskService;
import picocli.CommandLine.Command;
import picocli.CommandLine.HelpCommand;
import picocli.CommandLine.Parameters;

@Slf4j
@Command(name = "mark-done", description = "Updates an task status to done from the task list", subcommands = HelpCommand.class)
public class TaskMarkDone implements Runnable {
    @Inject
    public TaskService service;

    @Parameters(paramLabel = "<id>", defaultValue = "1",
            description = "Id of the task to be marked as done in the list")
    public int id;

    @Override
    public void run() {
        log.info("Starting Task Mark Done: Id is {}", id);
        service.printDoneTasks();
        log.info("Task Mark Done Completed");
    }
}
