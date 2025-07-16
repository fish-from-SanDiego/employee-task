package org.fishfromsandiego.employeetask.presentation.command;

import lombok.RequiredArgsConstructor;
import org.fishfromsandiego.employeetask.presentation.command.util.CommandException;
import org.fishfromsandiego.employeetask.presentation.command.util.CommandHelper;
import org.fishfromsandiego.employeetask.presentation.command.util.CommandType;
import org.fishfromsandiego.employeetask.service.EmployeeService;
import org.springframework.stereotype.Component;

@Component(CommandType.FIND_BY_ID_)
@RequiredArgsConstructor
public class FindByIdCommand implements SimpleCommand {
    private final EmployeeService employeeService;

    @Override
    public String execute(String[] args) {
        if (args.length != 1) {
            throw CommandException.wrongFormat();
        }

        long id = CommandHelper.parseLongOrThrowWrongFormat(args[0]);

        return employeeService.findById(id)
                .map(emp -> "Сотрудник найден: " + emp.toString())
                .orElse("Сотрудника с таким id нет");
    }

    @Override
    public String helpDescription() {
        return CommandType.FIND_BY_ID.toString() + " <id> - найти сотрудника по id";
    }

    @Override
    public CommandType getType() {
        return CommandType.FIND_BY_ID;
    }
}
