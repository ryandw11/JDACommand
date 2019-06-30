package me.ryandw11.jdacommand.backend;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import me.ryandw11.jdacommand.JDACommand;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

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
		if(message.startsWith(prefix)) {
			String[] args = message.split(" ");
			getCommandsWithname(args[0], event);
		}
	}
	
	public void getCommandsWithname(String name, MessageReceivedEvent event) {
		String cmdname = name.replace(prefix, "");
		final Map<CmdHolder, String[]> kcmd = cmd;
		@SuppressWarnings("rawtypes")
		Iterator it = kcmd.entrySet().iterator();
		while(it.hasNext()) {
			@SuppressWarnings("unchecked")
			Map.Entry<CmdHolder, String[]> pair = (Map.Entry<CmdHolder, String[]>) it.next();
			String[] command = pair.getValue();
			for(int i = 0; i < command.length; i++) {
				if(command[i].equalsIgnoreCase(cmdname)) {
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

