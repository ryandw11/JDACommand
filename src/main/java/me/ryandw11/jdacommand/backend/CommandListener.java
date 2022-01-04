package me.ryandw11.jdacommand.backend;

import me.ryandw11.jdacommand.JDACommand;
import me.ryandw11.jdacommand.JDACommandHandler;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.internal.utils.JDALogger;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * The listener that is registered with JDA.
 *
 * <p>For internal use only.</p>
 */
public class CommandListener extends ListenerAdapter {

    private final String prefix;
    private final Map<CmdHolder, String[]> commandMap;

    /**
     * Construct the command listener.
     *
     * <p>For internal use only.</p>
     *
     * @param prefix     The command prefix.
     * @param commandMap The command map.
     */
    public CommandListener(String prefix, Map<CmdHolder, String[]> commandMap) {
        this.prefix = prefix;
        this.commandMap = commandMap;
    }


    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        if (message.startsWith(prefix)) {
            String[] args = message.split(" ");
            getCommandsWithName(args[0], event);
        }
    }

    /**
     * Get the specific command based upon the name.
     *
     * @param name  The name of the command.
     * @param event The message event.
     */
    private void getCommandsWithName(String name, MessageReceivedEvent event) {
        String commandName = name.replace(prefix, "");
        for (Map.Entry<CmdHolder, String[]> pair : commandMap.entrySet()) {
            String[] command = pair.getValue();
            for (String s : command) {
                if (s.equalsIgnoreCase(commandName)) {
                    fireCommand(pair.getKey(), event, commandName);
                }
            }
        }
    }

    /**
     * Trigger a specific command.
     *
     * @param cmdh  The command holder to trigger.
     * @param event The message event from JDA.
     * @param name  The name of the command.
     */
    private void fireCommand(CmdHolder cmdh, MessageReceivedEvent event, String name) {
        List<String> s = new ArrayList<>(Arrays.asList(event.getMessage().getContentRaw().split(" ")));
        s.remove(0);
        try {
            cmdh.getMethod().invoke(cmdh.getObject(), new JDACommand(name, s, event.getAuthor(), event.getChannelType(), event.getMessage()));
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            JDALogger.getLog(JDACommandHandler.class).error("Failed to fire command, unable to use reflection to invoke command.", e);
        }
    }
}

