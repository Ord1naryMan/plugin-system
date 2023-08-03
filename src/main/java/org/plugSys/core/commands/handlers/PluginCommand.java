package org.plugSys.core.commands.handlers;

import org.plugSys.core.commands.handlers.annotation.Command;
import org.plugSys.core.processors.PluginProcessor;

import java.util.Set;

@Command("plugins")
public class PluginCommand implements BaseCommand{
    @Override
    public void handle(String[] args) {
        System.out.println("Plugins:");
        Set<String> loadedPlugins = PluginProcessor.getLoadedPlugins();
        for (String plugin : loadedPlugins) {
            System.out.println("- " + plugin);
        }
    }

    @Override
    public void help() {
        System.out.println("Show all loaded plugins");
    }
}
