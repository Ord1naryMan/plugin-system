package org.plugSys.core.processors;

import org.plugSys.utils.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class MainProcessor {

    private MainProcessor() {}

    public static void discoverAndStartProcessors() {
        List<Class> classes = getAllProcessorClasses();
        for (Class clazz : classes) {
            try {
                Constructor constructor = clazz.getDeclaredConstructor();
                constructor.setAccessible(true);
                BaseProcessor processor = (BaseProcessor) constructor.newInstance();
                clazz.getMethod("process").invoke(processor);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("Your processor " + clazz.getSimpleName() +
                        " class must have 'process' method!!");
            } catch (InvocationTargetException e) {
                throw new RuntimeException("Some Processor " + clazz.getSimpleName() +
                        " throw an exception:" +
                        e.getMessage() +
                        " at " + Arrays.toString(e.getStackTrace()));
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Method 'process' must be public at " +
                        clazz.getSimpleName());
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static String getCurrentPackageName() {
        return MainProcessor.class.getPackageName();
    }

    private static List<Class> getAllProcessorClasses() {
        List<Class> classes = ReflectionUtils.getAllClassesFrom(getCurrentPackageName());
        return classes.stream().filter(clazz -> Arrays.asList(clazz.getInterfaces())
                .contains(BaseProcessor.class))
                .collect(Collectors.toList());
    }
}
