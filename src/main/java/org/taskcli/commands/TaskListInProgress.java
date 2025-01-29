package org.taskcli.commands;

import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.taskcli.service.TaskService;
import picocli.CommandLine.Command;
import picocli.CommandLine.HelpCommand;

@Command(name = "list", description = "Shows the in progress task list", subcommands = HelpCommand.class)
@Slf4j
public class TaskListInProgress implements Runnable{

    @Inject
    private TaskService service;

    @Override
    public void run() {
        log.info("Starting Task List In Progress");
        service.printInProgressTasks();
        log.info("Ending Task List In Progress");
    }
}
