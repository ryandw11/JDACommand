package me.ryandw11.jdacommand;

import java.util.List;

import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;

public class JDACommand {
	
	private String name;
	private List<String> args;
	private User author;
	private ChannelType getType;
	private Message message;
	
	public JDACommand(String name, List<String> args, User author, ChannelType type, Message message) {
		this.name = name;
		this.args = args;
		this.author = author;
		this.getType = type;
		this.message = message;
	}
	
	/**
	 * Get which of the aliases were used.
	 * @return The name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get the arguments of the command
	 * @return
	 */
	public List<String> getArgs(){
		return args;
	}
	
	/**
	 * Get the author of the message.
	 * @return the author
	 */
	public User getAuthor() {
		return author;
	}
	
	/**
	 * Get the channel type it was sent in.
	 * @return Get the channel type.
	 */
	public ChannelType getChannelType() {
		return getType;
	}
	
	/**
	 * Get the message that was sent.
	 * @return
	 */
	public Message getMessage() {
		return message;
	}

}
