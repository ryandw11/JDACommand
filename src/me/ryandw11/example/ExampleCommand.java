package me.ryandw11.example;

import me.ryandw11.jdacommand.Command;
import me.ryandw11.jdacommand.CommandExecutor;
import me.ryandw11.jdacommand.JDACommand;
import net.dv8tion.jda.core.entities.MessageChannel;

public class ExampleCommand implements CommandExecutor {
	@Command
	public void hi(JDACommand cmd) {
		MessageChannel chan = cmd.getMessage().getChannel();
		chan.sendMessage("pong!").queue();
	}
}
