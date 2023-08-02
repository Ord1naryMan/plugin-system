package org.plugSys.core.coreEventHandlers;

import org.plugSys.core.events.ShowHelpMenuEvent;
import org.plugSys.core.events.TimeGetEvent;
import org.plugSys.core.events.TimeInEvent;

public class CoreHandlersInit {

    public static void init() {
        TimeGetEvent.register(new GetCurrentTimeEventHandler());
        TimeInEvent.register(new TimeInCoreHandler());
        ShowHelpMenuEvent.register(new HelpEventHandler());
    }
}
