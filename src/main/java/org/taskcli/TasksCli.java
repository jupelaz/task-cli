
package org.taskcli;

import java.util.concurrent.Callable;

import lombok.extern.slf4j.Slf4j;
import org.taskcli.commands.TaskAdd;
import org.taskcli.commands.TaskDelete;
import org.taskcli.commands.TaskList;
import org.taskcli.commands.TaskUpdate;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.HelpCommand;
import picocli.CommandLine.Help.Ansi.Style;
import picocli.CommandLine.Help.ColorScheme;


/**
 * @author gan
 * @version 0.0.1
 * @since 2024-Jan-18
 */

@Command(name = "tasks-cli", mixinStandardHelpOptions = true, version = "tasks-cli 0.0.1", description = "operate a task list", subcommands = {
        HelpCommand.class, TaskAdd.class, TaskUpdate.class, TaskDelete.class, TaskList.class
})
@Slf4j
public class TasksCli implements Callable<Integer> {
    /**
     * Main function to invoke the picocli framework.
     *
     * @param args the command line arguments.
     *             The first argument ...
     *             The second argument ...
     */
    public static void main(String[] args) {
        CommandLine taskCommandLine = new CommandLine(new TasksCli());
        int exitCode = taskCommandLine
                .setColorScheme(TasksCli.getColorScheme())
                .execute(args);
        System.exit(exitCode);
    }

    /**
     * Function invoked by picocli to operate with the task list.
     *
     */
    @Override
    public Integer call() {
        CommandLine.usage(this, System.out);
        return 0;
    }

    public static CommandLine.Help.ColorScheme getColorScheme() {
        // see also CommandLine.Help.defaultColorScheme()
        return new ColorScheme.Builder()
                .commands(Style.bold, Style.underline) // combine multiple styles
                .options(Style.fg_yellow) // yellow foreground color
                .parameters(Style.fg_yellow)
                .optionParams(Style.italic)
                .errors(Style.fg_red, Style.bold)
                .stackTraces(Style.italic)
                .build();
    }

}