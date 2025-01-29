package org.taskcli.commands;

import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.taskcli.service.TaskService;
import picocli.CommandLine.Command;
import picocli.CommandLine.HelpCommand;

@Command(name = "list", description = "Shows the to do task list", subcommands = HelpCommand.class)
@Slf4j
public class TaskListDone implements Runnable{

    @Inject
    private TaskService service;

    @Override
    public void run() {
        log.info("Starting Task List Done");
        service.printDoneTasks();
        log.info("Ending Task List Done");

    }
}
