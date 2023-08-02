package org.plugSys.core.events;

import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;

import static org.plugSys.utils.ConsoleUtils.ANSI_RED;
import static org.plugSys.utils.ConsoleUtils.ANSI_RESET;

public class TimeInEvent {

    private static final List<TimeInEventHandler> handlers = new LinkedList<>();

    public static void register(TimeInEventHandler handler) {
        handlers.add(handler);
    }

    public static void notifyHandlers(String[] args) {
        for (TimeInEventHandler handler : handlers) {
            handler.getTimeIn(Long.parseLong(args[0]), ChronoUnit.valueOf(args[1].toUpperCase()));
        }
    }

    public static boolean isValid(String[] args) {
        if (args.length > 2) {
            System.out.println("A lot of args");
            return false;
        } else if (args.length < 2) {
            System.out.println("Not enough args");
            return false;
        } else if (!args[0].matches("\\d+") ||
                (!args[1].equalsIgnoreCase("seconds") &&
                !args[1].equalsIgnoreCase("minutes") &&
                !args[1].equalsIgnoreCase("hours"))) {
            System.out.println(ANSI_RED + "Illegal args. " + ANSI_RESET +
                    "Example 'time in 2 Minutes', 'time in 1 hours', 'time in 11 SeCoNdS'. " +
                    System.lineSeparator() +
                    "Case does not matter");
            return false;
        }
        return true;
    }
}
