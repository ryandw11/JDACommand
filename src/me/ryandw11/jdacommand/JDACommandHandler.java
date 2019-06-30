package me.ryandw11.jdacommand;

import java.util.HashMap;
import java.util.Map;

import me.ryandw11.jdacommand.backend.CommandFire;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class JDACommandHandler extends ListenerAdapter {
	
	private JDA jda;
	private String prefix;
	private Map<CommandExecutor, String[]> cmds;
	
	public JDACommandHandler(JDA jda, String prefix) {
		this.jda = jda;
		this.prefix = prefix;
		cmds = new HashMap<CommandExecutor, String[]>();
	}
	
	public void register(CommandExecutor cmd, String[] names) {
		cmds.put(cmd, names);
	}
	
	public void finalize() {
		jda.addEventListener(new CommandFire(prefix, cmds));
		System.out.println("Listener added.");
	}
	
	
	
}
