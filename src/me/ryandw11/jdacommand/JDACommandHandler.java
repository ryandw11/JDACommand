package me.ryandw11.jdacommand;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.ryandw11.jdacommand.backend.CmdHolder;
import me.ryandw11.jdacommand.backend.CommandFire;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class JDACommandHandler extends ListenerAdapter {
	
	private JDA jda;
	private String prefix;
	private Map<CmdHolder, String[]> cmds;
	
	/**
	 * The command handler.
	 * @param jda Your instance of jda.
	 * @param prefix Your command prefix.
	 */
	public JDACommandHandler(JDA jda, String prefix) {
		this.jda = jda;
		this.prefix = prefix;
		cmds = new HashMap<CmdHolder, String[]>();
	}
	
	/**
	 * Register a command class. A command class can contain multiple commands. The register method only needs to be called once per class.
	 * @param clazz the class object.
	 */
	public void register(Object clazz) {
		List<Method> mtd = new ArrayList<Method>(Arrays.asList(clazz.getClass().getDeclaredMethods()));
		for(Method msd : mtd) {
			if(msd.isAnnotationPresent(Command.class)) {
				if(msd.getParameters()[0].getType() == JDACommand.class) {
					Command cmd = msd.getAnnotation(Command.class);
					cmds.put(new CmdHolder(msd, clazz), cmd.alias());
				}else {
					System.out.println("[JDACommandHandler] Notice: Can not find the JDACommand parameter on a command method!");
				}
			}
		}
		
	}
	
	/**
	 * Finalize the commands after they have all been registered.
	 */
	public void finalize() {
		jda.addEventListener(new CommandFire(prefix, cmds));
	}
	
	
	
	
}
