package org.plugSys.core.processors;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PluginProcessor implements BaseProcessor{

    private static final Set<String> loadedPlugins = new HashSet<>();
    @Override
    public void process() {

        String fullPluginsFolderPath = Path.of(
                System.getProperty("user.dir"),
                "src","main","java","org","plugSys","plugins"
        ).toString();

        List<String> pluginNames = Arrays.stream(
                Objects.requireNonNull(
                        new File(fullPluginsFolderPath)
                .list((dir, name) -> name.endsWith(".jar"))
                )
        ).toList();

        for (String name : pluginNames) {
            loadJar(name);
        }
    }


    private void loadJar(String pluginPath){
        JarFile jarFile = null;
        URLClassLoader cl = null;

        pluginPath = Path.of("src","main","java","org","plugSys","plugins", pluginPath).toString();
        try {
            jarFile = new JarFile(pluginPath);
            Enumeration<JarEntry> enumeration = jarFile.entries();

            URL[] urls = {new URL("jar:file:" + pluginPath + "!/")};
            cl = URLClassLoader.newInstance(urls);

            while (enumeration.hasMoreElements()) {
                JarEntry je = enumeration.nextElement();
                if (je.isDirectory() || !je.getName().endsWith("Initialize.class")) {
                    continue;
                }

                String className = je.getName()
                        .substring(0, je.getName().length() - ".class".length());
                className = className.replace('/', '.');
                Class c = cl.loadClass(className);
                Object o = c.getDeclaredConstructor().newInstance();
                String pluginName = (String) c.getField("pluginName").get(o);
                if (loadedPlugins.contains(pluginName)) {
                    return;
                }
                c.getDeclaredMethod("init").invoke(o);
                System.out.println(pluginName + " has been initialized!");
                loadedPlugins.add(pluginName);
            }
        } catch (IOException | ClassNotFoundException | InvocationTargetException | IllegalAccessException |
                 NoSuchMethodException | InstantiationException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException ignored) {
        } finally {
            try {
                if (jarFile != null) {
                    jarFile.close();
                }
                if (cl != null) {
                    cl.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Set<String> getLoadedPlugins() {
        return new HashSet<>(loadedPlugins);
    }
}
