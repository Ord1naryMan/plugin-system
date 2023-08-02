package org.plugSys.core.commands.handlers;

import org.plugSys.core.commands.handlers.annotation.Command;
import org.plugSys.core.events.TimeGetEvent;
import org.plugSys.core.events.TimeInEvent;

import java.util.Arrays;

import static org.plugSys.utils.ConsoleUtils.ANSI_RED;
import static org.plugSys.utils.ConsoleUtils.ANSI_RESET;

@Command("time")
public class TimeCommand implements BaseCommand{

    public void handle(String[] args) {
        if (args.length == 0) {
            System.out.println(ANSI_RED + "Illegal args. " + ANSI_RESET);
            help();
            return;
        }
        if (args[0].equals("get")) {
            TimeGetEvent.notifyHandlers();
        } else if (args[0].equals("in")) {
            args = Arrays.copyOfRange(args, 1, args.length);
            if (TimeInEvent.isValid(args)) {
                TimeInEvent.notifyHandlers(args);
            } else {
                help();
            }
        }
    }

    @Override
    public void help() {
        System.out.println("get - show current time" + System.lineSeparator() +
                "in {amount} {units} - show time after entered amount of time");
    }
}
