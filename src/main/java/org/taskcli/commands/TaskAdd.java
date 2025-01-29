package org.taskcli.commands;

import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.taskcli.service.TaskService;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.HelpCommand;

@Command(name = "add", description = "Adds a task to the task list", subcommands = HelpCommand.class)
@Slf4j
public class TaskAdd implements Runnable{

    @Inject
    TaskService service;

    @Parameters(paramLabel = "<task>", defaultValue = "Hello",
            description = "Description of the task to be added to the list")
    public String desc;

    @Override
    public void run() {
        log.info("Starting Task Add: Description is {}", desc);
        service.addTask(desc);
        log.info("Task Added Successfully");
    }
}
