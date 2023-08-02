package org.plugSys.core.events;

import java.util.LinkedList;
import java.util.List;

public class ShowHelpMenuEvent {

    private static final List<ShowHelpMenuEventHandler> handlers = new LinkedList<>();

    public static void register(ShowHelpMenuEventHandler handler) {
        handlers.add(handler);
    }

    public static void notifyHandlers() {
        for (var handler : handlers) {
            handler.showHelpMenu();
        }
    }

}
