package org.plugSys.core.processors;

import org.plugSys.core.commands.handlers.BaseCommand;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import static org.plugSys.ApplicationRunner.isRunning;

public class CommandProcessor {

    private CommandProcessor() {}

    private static final Map<String, BaseCommand> commands = new HashMap<>();

    static void addCommand(String name, BaseCommand command) {
            commands.put(name, command);
    }

    static public void runCommand(String name, String[] args) {
        commands.get(name).handle(args);
    }

    public static void process() {
        isRunning = true;
        System.out.println("Enter your command: ");

        try (Scanner input = new Scanner(System.in)) {
            while (isRunning) {
                System.out.print(">");
                String[] userInput = input.nextLine().split(" ");

                if (userInput[0].equals("quit")) {
                    isRunning = false;
                    continue;
                }

                if (!commands.containsKey(userInput[0])) {
                    System.out.println("No such command " + userInput[0]);
                    continue;
                }
                runCommand(userInput[0], Arrays.copyOfRange(userInput, 1, userInput.length));
            }
        }
    }

    public static Set<String> getAllCommandNames() {
        return commands.keySet();
    }

    public static void showHelpMenu(String command) {
        commands.get(command).help();
    }
}
