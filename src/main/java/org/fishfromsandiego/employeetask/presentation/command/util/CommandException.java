package org.fishfromsandiego.employeetask.presentation.command.util;

public class CommandException extends RuntimeException {
    public CommandException(String message) {
        super(message);
    }

    public CommandException() {
        super();
    }

    public static CommandException wrongFormat() {
        return new CommandException("Неправильный формат команды");
    }
}
