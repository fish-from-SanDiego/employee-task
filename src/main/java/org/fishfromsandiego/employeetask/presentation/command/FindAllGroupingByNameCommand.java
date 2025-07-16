package org.fishfromsandiego.employeetask.presentation.command;

import lombok.RequiredArgsConstructor;
import org.fishfromsandiego.employeetask.data.entity.Employee;
import org.fishfromsandiego.employeetask.presentation.command.util.CommandException;
import org.fishfromsandiego.employeetask.presentation.command.util.CommandType;
import org.fishfromsandiego.employeetask.service.EmployeeService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component(CommandType.FIND_ALL_GROUPED_BY_NAME_)
@RequiredArgsConstructor
public class FindAllGroupingByNameCommand implements SimpleCommand {
    private final EmployeeService employeeService;

    @Override
    public String execute(String[] args) {
        if (args.length != 0) {
            throw CommandException.wrongFormat();
        }
        Map<String, List<Employee>> employeesByName = employeeService.findAllGroupingByNameSorted();

        if (employeesByName.isEmpty())
            return "Не найден ни один сотрудник";

        var sb = new StringBuilder();
        employeesByName.forEach((name, employees) -> {
            sb.append("Сотрудники с именем ").append(name).append(":\n");
            employees.forEach(employee -> sb.append(employee.toString()).append("\n"));
        });
        sb.delete(sb.length() - 1, sb.length());

        return sb.toString();
    }

    @Override
    public String helpDescription() {
        return CommandType.FIND_ALL_GROUPED_BY_NAME.toString() + " - найти всех сотрудников и сгруппировать по именам";
    }

    @Override
    public CommandType getType() {
        return CommandType.FIND_ALL_GROUPED_BY_NAME;
    }
}
