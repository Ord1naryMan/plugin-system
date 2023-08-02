package org.plugSys;

import org.plugSys.core.coreEventHandlers.CoreHandlersInit;
import org.plugSys.core.processors.CommandProcessor;
import org.plugSys.core.processors.MainProcessor;

public class ApplicationRunner {

    public static boolean isRunning;

    public static void main(String[] args) {
        isRunning = true;
        MainProcessor.discoverAndStartProcessors();
        CoreHandlersInit.init();
        CommandProcessor.process();
    }
}
