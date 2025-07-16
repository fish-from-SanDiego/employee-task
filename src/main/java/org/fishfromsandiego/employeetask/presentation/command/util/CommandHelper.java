package org.fishfromsandiego.employeetask.presentation.command.util;

public class CommandHelper {
    public static long parseLongOrThrowWrongFormat(String arg) {
        try {
            return Long.parseLong(arg);
        } catch (NumberFormatException e) {
            throw CommandException.wrongFormat();
        }
    }

    public static int parseIntOrThrowWrongFormat(String arg) {
        try {
            return Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            throw CommandException.wrongFormat();
        }
    }
}
