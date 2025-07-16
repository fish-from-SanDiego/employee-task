package org.fishfromsandiego.employeetask.presentation.command.util;

public enum CommandType {
    FIND_BY_ID(CommandType.FIND_BY_ID_),
    FIND_ALL_GROUPED_BY_NAME(CommandType.FIND_ALL_GROUPED_BY_NAME_),
    FIND_ALL_BETWEEN_YEARS(CommandType.FIND_ALL_BETWEEN_YEARS_),
    HELP(CommandType.HELP_);

    private String name;

    public static final String FIND_BY_ID_ = "findById";
    public static final String FIND_ALL_GROUPED_BY_NAME_ = "groupByName";
    public static final String FIND_ALL_BETWEEN_YEARS_ = "findBetween";
    public static final String HELP_ = "help";

    @Override
    public String toString() {
        return this.name;
    }

    CommandType(String name) {
        this.name = name;
    }

    public static CommandType ofName(String name) {
        for (CommandType type : CommandType.values()) {
            if (type.name.equals(name)) {
                return type;
            }
        }
        throw new CommandException("Неизвестная команда: " + name);
    }
}
