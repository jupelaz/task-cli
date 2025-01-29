package org.taskcli.commands;

import jakarta.inject.Inject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.taskcli.repository.TaskRepository;
import org.taskcli.repository.FileUtils;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;
import picocli.CommandLine.HelpCommand;

@Slf4j
@Command(name = "update", description = "Updates an item from the task list", subcommands = HelpCommand.class)
public class TaskUpdate implements Runnable {

    @Inject
    private FileUtils fileUtils;
    @Inject
    private TaskRepository taskService;

    @Parameters(paramLabel = "<id>", defaultValue = "1",
            description = "Id of the task to be updated in the list")
    public int id;

    @Parameters(paramLabel = "<task>", defaultValue = "Hello",
            description = "Description of the task to be updated in the list")
    public String desc;

    @SneakyThrows
    @Override
    public void run() {
        log.info("Starting Task Update: Id is {} and description is {}", id, desc);

        fileUtils.checkIfFileExists();
        val taskList = fileUtils.readFile();
        if(taskService.updateTaskInList(id,desc,taskList)){
            System.out.println("Task couldn't be updated");
        }
        fileUtils.writeListToFile(taskList);
    }
}
