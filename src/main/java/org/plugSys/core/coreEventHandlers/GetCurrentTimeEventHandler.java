package org.plugSys.core.coreEventHandlers;

import org.plugSys.core.events.TimeGetEventHandler;

import java.time.LocalTime;

public class GetCurrentTimeEventHandler implements TimeGetEventHandler {
    @Override
    public void getTime() {
        System.out.println(LocalTime.now());
    }
}
