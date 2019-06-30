package me.ryandw11.example;

import me.ryandw11.jdacommand.Command;
import me.ryandw11.jdacommand.JDACommand;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.MessageHistory;

public class ExampleCommand {
	/*
	 * Multiple commands can exist in a single class.
	 * Each command can have its own set of aliases.
	 * The delete command tests arguments.
	 * 
	 */
	
	@Command(alias = { "ping", "par", "works" })
	public void hi(JDACommand cmd) {
		MessageChannel chan = cmd.getMessage().getChannel();
		chan.sendMessage("pong!").queue();
	}
	
	@Command(alias = {"deleteme"})
	public void deleteMsg(JDACommand cmd) {
		MessageChannel chan = cmd.getMessage().getChannel();
		cmd.getMessage().delete().queue();
		chan.sendMessage("Your message has been deleted!").queue();
	}
	
	@SuppressWarnings("static-access")
	@Command(alias = {"delete"})
	public void delete(JDACommand cmd) {
		if(cmd.getArgs().size() != 1) {
			cmd.getMessage().getChannel().sendMessage("Your command has too many args!").queue();
		}else {
			if(cmd.getAuthor().getIdLong() != 0) { //Replace with your user id.
				cmd.getMessage().getChannel().sendMessage("You are not Ryandw11!").queue();
				return;
			}
			MessageChannel chan = cmd.getMessage().getChannel();
			MessageHistory msgh;
			try {
				msgh = chan.getHistory().getHistoryAround(chan, cmd.getArgs().get(0)).limit(10).complete();
			}
			catch(IllegalArgumentException ex) {
				chan.sendMessage("That is not a valid message id!").queue();
				return;
			}
			Message msg = msgh.getMessageById(Long.parseLong(cmd.getArgs().get(0)));
			if(msg == null) {
				chan.sendMessage("The message requested does not exist!").queue();
				return;
			}
			msg.delete().queue();
			chan.sendMessage("Your message has been deleted!").queue();
		}
	}
	
}
