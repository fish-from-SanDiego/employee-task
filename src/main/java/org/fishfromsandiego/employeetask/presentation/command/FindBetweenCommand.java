package org.fishfromsandiego.employeetask.presentation.command;

import lombok.RequiredArgsConstructor;
import org.fishfromsandiego.employeetask.data.entity.Employee;
import org.fishfromsandiego.employeetask.presentation.command.util.CommandException;
import org.fishfromsandiego.employeetask.presentation.command.util.CommandHelper;
import org.fishfromsandiego.employeetask.presentation.command.util.CommandType;
import org.fishfromsandiego.employeetask.service.EmployeeService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component(CommandType.FIND_ALL_BETWEEN_YEARS_)
@RequiredArgsConstructor
public class FindBetweenCommand implements SimpleCommand {

    private final EmployeeService employeeService;

    @Override
    public String execute(String[] args) {
        if (args.length != 2) {
            throw CommandException.wrongFormat();
        }
        int fromYear = CommandHelper.parseIntOrThrowWrongFormat(args[0]);
        int toYear = CommandHelper.parseIntOrThrowWrongFormat(args[1]);

        if (fromYear > toYear) {
            throw new CommandException("Год начала интервала не может быть больше года его конца");
        }
        var from = LocalDate.ofYearDay(fromYear, 1);
        var to = LocalDate.of(toYear, 12, 31);
        List<Employee> employees = employeeService.findByBirthDateBetween(from, to);

        if (employees.isEmpty())
            return "Сотрудников с такими датами рождения нет";

        var sb = new StringBuilder("Список найденных сотрудников:\n");
        employees.forEach(employee -> sb.append(employee.toString()).append("\n"));
        sb.delete(sb.length() - 1, sb.length());

        return sb.toString();
    }

    @Override
    public String helpDescription() {
        return CommandType.FIND_ALL_BETWEEN_YEARS.toString() + " <from> <to> - найти всех сотрудников с " +
                "датами рождения с from по to год";
    }

    @Override
    public CommandType getType() {
        return CommandType.FIND_ALL_BETWEEN_YEARS;
    }
}
