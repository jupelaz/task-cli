package org.taskcli.commands;

import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.taskcli.service.TaskService;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;
import picocli.CommandLine.HelpCommand;

@Slf4j
@Command(name = "delete", description = "Delete an item from the task list", subcommands = HelpCommand.class)
public class TaskDelete implements Runnable {

    @Parameters(paramLabel = "<id>", defaultValue = "1",
            description = "Id of the task to be deleted in the list")
    public int id;

    @Inject
    TaskService service;

    @Override
    public void run() {
        log.info("Starting Task Delete: Id is {}", id);
        service.deleteTask(id);
        log.info("Ending Task Delete");
    }
}
