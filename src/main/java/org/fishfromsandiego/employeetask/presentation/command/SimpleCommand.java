package org.fishfromsandiego.employeetask.presentation.command;

import org.fishfromsandiego.employeetask.presentation.command.util.CommandType;

public interface SimpleCommand {
    String execute(String[] args);

    String helpDescription();

    CommandType getType();
}
