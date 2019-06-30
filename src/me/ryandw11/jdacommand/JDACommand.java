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
	
	public String getName() {
		return name;
	}
	
	public List<String> getArgs(){
		return args;
	}
	
	public User getAuthor() {
		return author;
	}
	
	public ChannelType getChannelType() {
		return getType;
	}
	
	public Message getMessage() {
		return message;
	}

}
