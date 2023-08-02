package org.plugSys.core.commands.handlers;

import org.plugSys.core.commands.handlers.annotation.Command;
import org.plugSys.core.events.ShowHelpMenuEvent;

@Command("help")
public class HelpCommand implements BaseCommand{
    @Override
    public void handle(String[] args) {
        ShowHelpMenuEvent.notifyHandlers();
    }

    @Override
    public void help() {
        System.out.println("Show this menu");
    }
}
