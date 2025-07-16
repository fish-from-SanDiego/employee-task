package org.fishfromsandiego.employeetask.presentation.command;

import org.fishfromsandiego.employeetask.presentation.command.util.CommandType;
import org.springframework.stereotype.Component;

@Component(CommandType.HELP_)

public class HelpCommand implements SimpleCommand {
    @Override
    public String execute(String[] args) {
        var sb = new StringBuilder("Список команд:\n");
        for (String arg : args) {
            sb.append(arg).append("\n");
        }
        sb.delete(sb.length() - 1, sb.length());

        return sb.toString();
    }

    @Override
    public String helpDescription() {
        return "";
    }

    @Override
    public CommandType getType() {
        return CommandType.HELP;
    }
}
