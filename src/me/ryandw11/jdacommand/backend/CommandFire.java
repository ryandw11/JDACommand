package me.ryandw11.jdacommand.backend;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import me.ryandw11.jdacommand.Command;
import me.ryandw11.jdacommand.CommandExecutor;
import me.ryandw11.jdacommand.JDACommand;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class CommandFire extends ListenerAdapter {
	
	private String prefix;
	private Map<CommandExecutor, String[]> cmd;
	public CommandFire(String prefix, Map<CommandExecutor, String[]> cmd) {
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
		final Map<CommandExecutor, String[]> kcmd = cmd;
		@SuppressWarnings("rawtypes")
		Iterator it = kcmd.entrySet().iterator();
		while(it.hasNext()) {
			@SuppressWarnings("unchecked")
			Map.Entry<CommandExecutor, String[]> pair = (Map.Entry<CommandExecutor, String[]>) it.next();
			String[] command = pair.getValue();
			for(int i = 0; i < command.length; i++) {
				if(command[i].equalsIgnoreCase(cmdname)) {
					fireCommand(pair.getKey(), event, cmdname);
				}
			}
		}
	}
	
	public void fireCommand(CommandExecutor commandexe, MessageReceivedEvent event, String name) {
		Class<?> clz = commandexe.getClass();
		List<Method> mtd = new ArrayList<Method>(Arrays.asList(clz.getDeclaredMethods()));
		for(Method mth : mtd) {
			if(mth.isAnnotationPresent(Command.class)) {
				List<Parameter> par = new ArrayList<>(Arrays.asList(mth.getParameters()));
				if(par.size() == 1 && par.get(0).getType() == JDACommand.class) {
					List<String> s = new ArrayList<>(Arrays.asList(event.getMessage().getContentRaw().split(" ")));
					s.remove(0);
					try {
						mth.invoke(commandexe, new JDACommand(name, s, event.getAuthor(), event.getChannelType(), event.getMessage()));
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}

