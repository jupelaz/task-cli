package org.taskcli;

import lombok.val;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;
import picocli.CommandLine.Help.ColorScheme;
import picocli.CommandLine.Help.Ansi.Style;

import static org.junit.jupiter.api.Assertions.*;

class TasksCliTest {

    @Test
    void testMainWithValidArguments() {
        String[] args = {"--help"};
        int exitCode = new CommandLine(new TasksCli()).execute(args);
        assertEquals(0, exitCode);
    }

    @Test
    void testMainWithInvalidArguments() {
        String[] args = {"--invalid-arg"};
        int exitCode = new CommandLine(new TasksCli()).execute(args);
        assertNotEquals(0, exitCode);
    }

    @Test
    void testMainWithNoArguments() {
        String[] args = {};
        int exitCode = new CommandLine(new TasksCli()).execute(args);
        assertEquals(0, exitCode);
    }

    @Test
    void testCallMethod() {
        TasksCli tasksCli = new TasksCli();
        int result = tasksCli.call();
        assertEquals(0, result);
    }

    @Test
    void testGetColorScheme() {
        ColorScheme colorScheme = TasksCli.getColorScheme();
        assertNotNull(colorScheme);
        val commandStyles = colorScheme.commandStyles();
        assertTrue(commandStyles.contains(Style.bold));
        assertTrue(commandStyles.contains(Style.underline));
        val optionStyles = colorScheme.optionStyles();
        assertTrue(optionStyles.contains(Style.fg_yellow));
        val parameterStyles = colorScheme.parameterStyles();
        assertTrue(parameterStyles.contains(Style.fg_yellow));
        val optionParams = colorScheme.optionParamStyles();
        assertTrue(optionParams.contains(Style.italic));
        val errors = colorScheme.errorStyles();
        assertTrue(errors.contains(Style.fg_red));
        assertTrue(errors.contains(Style.bold));
        val stackTraces = colorScheme.stackTraceStyles();
        assertTrue(stackTraces.contains(Style.italic));
    }
}