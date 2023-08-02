package org.plugSys.core.events;

import java.util.LinkedList;
import java.util.List;

public class TimeGetEvent {
    private static final List<TimeGetEventHandler> handlers = new LinkedList<>();

    public static void register(TimeGetEventHandler eventHandler) {
        handlers.add(eventHandler);
    }

    public static void notifyHandlers() {
        for (TimeGetEventHandler eventHandler : handlers) {
            eventHandler.getTime();
        }
    }
}
