package org.plugSys.core.events;

import java.time.temporal.ChronoUnit;

public interface TimeInEventHandler {

    void getTimeIn(long amount, ChronoUnit unit);
}
