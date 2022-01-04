package me.ryandw11.jdacommand;

import me.ryandw11.jdacommand.backend.CmdHolder;
import me.ryandw11.jdacommand.backend.CommandListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.internal.utils.JDALogger;

import java.lang.reflect.Method;
import java.util.*;

public class JDACommandHandler extends ListenerAdapter {

    private final JDA jda;
    private final String prefix;
    private final Map<CmdHolder, String[]> commandMap;

    /**
     * Construct the command handler with your instance of JDA and the prefix you would like to use.
     *
     * @param jda    Your instance of jda.
     * @param prefix The prefix you would like to use for your command.
     */
    public JDACommandHandler(JDA jda, String prefix) {
        this.jda = jda;
        this.prefix = prefix;
        commandMap = new HashMap<>();
    }

    /**
     * Register a command class. A command class can contain multiple commands. The register method only needs to be called once per class.
     *
     * @param clazz the class object.
     */
    public void register(Object clazz) {
        List<Method> mtd = new ArrayList<>(Arrays.asList(clazz.getClass().getDeclaredMethods()));
        for (Method msd : mtd) {
            if (msd.isAnnotationPresent(Command.class)) {
                if (msd.getParameters()[0].getType() == JDACommand.class) {
                    Command cmd = msd.getAnnotation(Command.class);
                    commandMap.put(new CmdHolder(msd, clazz), cmd.alias());
                } else {
                    JDALogger.getLog(JDACommandHandler.class).error("Cannot find the JDACommand parameter on a command method!");
                }
            }
        }
    }

    /**
     * Finalize the commands after they have all been registered.
     */
    public void finalizeCommands() {
        jda.addEventListener(new CommandListener(prefix, commandMap));
        JDALogger.getLog(JDACommandHandler.class).info("Finalized JDA Commands and added event listener.");
    }


}
