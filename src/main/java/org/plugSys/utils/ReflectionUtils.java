package org.plugSys.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class ReflectionUtils {

    public static List<Class> getAllClassesFrom(String packageName) {
        try {
            InputStream stream = ClassLoader.getSystemClassLoader()
                    .getResourceAsStream(packageName.replaceAll("[.]", "/"));

            assert stream != null;

            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            stream.close();
            return reader.lines()
                    .filter(line -> line.endsWith(".class"))
                    .map(line -> getClass(line, packageName))
                    .toList();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static <T> List<Class<T>> getAllClassesFrom(String packageName, Class<T> requiredInterface) {
        List<Class> rawClasses = getAllClassesFrom(packageName);

        return rawClasses.stream()
                .filter(
                        clazz -> Arrays.asList(clazz.getInterfaces()).contains(requiredInterface)
                )
                .map(clazz -> (Class<T>) clazz)
                .toList();
    }
    private static Class getClass(String className, String packageName) {
        className = className.substring(0, className.indexOf("."));
        try {
            return Class.forName(packageName + "." + className);
        } catch(ClassNotFoundException e) {
            throw new RuntimeException("ClassNotFound exception occurred, " + e.getMessage());
        }
    }
}
