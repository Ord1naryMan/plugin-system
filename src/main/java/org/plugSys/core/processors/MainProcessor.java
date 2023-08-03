package org.plugSys.core.processors;

import java.util.LinkedList;
import java.util.List;


public class MainProcessor {

    private MainProcessor() {}

    private static final LinkedList<BaseProcessor> processors = new LinkedList<>(
            List.of(
                    new RegistrationProcessor(),
                    new PluginProcessor()
            )
    );

    public static void discoverAndStartProcessors() {
        for (BaseProcessor processor : processors) {
            processor.process();
        }
    }
}
