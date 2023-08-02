package org.plugSys.core.commands.handlers;

public interface BaseCommand {
    void handle(String[] args);
    void help();
}
