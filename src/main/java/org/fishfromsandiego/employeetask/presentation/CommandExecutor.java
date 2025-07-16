package org.fishfromsandiego.employeetask.presentation;

import lombok.RequiredArgsConstructor;
import org.fishfromsandiego.employeetask.presentation.command.HelpCommand;
import org.fishfromsandiego.employeetask.presentation.command.SimpleCommand;
import org.fishfromsandiego.employeetask.presentation.command.util.CommandException;
import org.fishfromsandiego.employeetask.presentation.command.util.CommandType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CommandExecutor implements CommandLineRunner {
    private final Map<String, SimpleCommand> commandsByName;

    private final HelpCommand helpCommand;

    @Override
    public void run(String... args) throws Exception {
        var reader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        System.out.println(
                "\nНебольшое консольное приложение, напишите "
                        + helpCommand.getType().toString() +
                        " для получения списка команд\n");

        while (true) {
            try {
                input = reader.readLine();
                String[] tokens = input.split("\\s+");
                if (tokens.length == 0)
                    continue;
                CommandType commandType = CommandType.ofName(tokens[0]);
                String[] commandArgs = Arrays.copyOfRange(tokens, 1, tokens.length);

                if (commandType.equals(helpCommand.getType())) {
                    System.out.println(helpCommand.execute(getHelpDescriptions()));
                    continue;
                }

                System.out.println(commandsByName.get(commandType.toString()).execute(commandArgs));

            } catch (CommandException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Произошла неизвестная ошибка");
            } finally {
                System.out.println();
            }
        }
    }

    private String[] getHelpDescriptions() {
        return commandsByName.values().stream()
                .filter(comamnd -> comamnd.getType() != helpCommand.getType())
                .map(SimpleCommand::helpDescription)
                .toArray(String[]::new);
    }

}
