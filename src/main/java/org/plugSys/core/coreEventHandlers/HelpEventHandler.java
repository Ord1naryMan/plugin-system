package org.plugSys.core.coreEventHandlers;

import org.plugSys.core.events.ShowHelpMenuEventHandler;
import org.plugSys.core.processors.CommandProcessor;
import org.plugSys.utils.ConsoleUtils;

import java.util.Set;

public class HelpEventHandler implements ShowHelpMenuEventHandler {


    @Override
    public void showHelpMenu() {
        Set<String> availableCommands = CommandProcessor.getAllCommandNames();
        for (String command : availableCommands) {
            System.out.println(ConsoleUtils.ANSI_YELLOW + command + ":" + ConsoleUtils.ANSI_RESET);
            CommandProcessor.showHelpMenu(command);
        }
    }
}
