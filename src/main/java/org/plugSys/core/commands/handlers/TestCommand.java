package org.plugSys.core.commands.handlers;


import org.plugSys.core.commands.handlers.annotation.Command;

@Command("test")
public class TestCommand implements BaseCommand{

    private TestCommand(){}

    public void handle(String[] args) {
        System.out.println("test");
    }

    @Override
    public void help() {
        System.out.println("This is test command");
    }
}
