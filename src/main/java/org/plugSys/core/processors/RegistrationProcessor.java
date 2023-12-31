package org.plugSys.core.processors;

import org.plugSys.core.commands.handlers.BaseCommand;
import org.plugSys.core.commands.handlers.annotation.Command;
import org.plugSys.core.coreEventHandlers.CoreHandlersInit;
import org.plugSys.utils.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.util.List;

public class RegistrationProcessor implements BaseProcessor{

    @Override
    public void process() {
        String packageName = BaseCommand.class.getPackageName();
        List<Class<BaseCommand>> classes =  ReflectionUtils.getAllClassesFrom(packageName, BaseCommand.class);

        for (Class<BaseCommand> clazz : classes) {
            Command command = clazz.getAnnotation(Command.class);
            if (command != null) {

                String commandName = command.value();
                CommandProcessor.addCommand(commandName, getInstance(clazz));
            }
        }

        //add core handlers, do it here 'cause we want them to run before plugin handlers
        CoreHandlersInit.init();
    }

    private BaseCommand getInstance(Class<BaseCommand> clazz) {
        try {
            Constructor<BaseCommand> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);

            return constructor.newInstance();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
