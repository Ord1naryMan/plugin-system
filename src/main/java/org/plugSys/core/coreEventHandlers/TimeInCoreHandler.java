package org.plugSys.core.coreEventHandlers;

import org.plugSys.core.events.TimeInEventHandler;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class TimeInCoreHandler implements TimeInEventHandler {
    @Override
    public void getTimeIn(long amount, ChronoUnit unit) {
        System.out.println(LocalTime.now().plus(amount, unit));
    }
}
