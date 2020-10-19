package me.ryandw11.jdacommand.backend;

import me.ryandw11.jdacommand.JDACommand;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CommandFire extends ListenerAdapter {

    private String prefix;
    private Map<CmdHolder, String[]> cmd;

    public CommandFire(String prefix, Map<CmdHolder, String[]> cmd) {
        this.prefix = prefix;
        this.cmd = cmd;
    }


    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        if (message.startsWith(prefix)) {
            String[] args = message.split(" ");
            getCommandsWithName(args[0], event);
        }
    }

    public void getCommandsWithName(String name, MessageReceivedEvent event) {
        String cmdname = name.replace(prefix, "");
        final Map<CmdHolder, String[]> kcmd = cmd;
        for (Map.Entry<CmdHolder, String[]> pair : kcmd.entrySet()) {
            String[] command = pair.getValue();
            for (String s : command) {
                if (s.equalsIgnoreCase(cmdname)) {
                    fireCommand(pair.getKey(), event, cmdname);
                }
            }
        }
    }

    public void fireCommand(CmdHolder cmdh, MessageReceivedEvent event, String name) {
        List<String> s = new ArrayList<>(Arrays.asList(event.getMessage().getContentRaw().split(" ")));
        s.remove(0);
        try {
            cmdh.getMethod().invoke(cmdh.getObject(), new JDACommand(name, s, event.getAuthor(), event.getChannelType(), event.getMessage()));
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

