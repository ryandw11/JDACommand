package me.ryandw11.jdacommand;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

import java.util.List;

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
     *
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the arguments of the command
     *
     * @return The arguments of the command.
     */
    public List<String> getArgs() {
        return args;
    }

    /**
     * Get the author of the message.
     *
     * @return the author
     */
    public User getAuthor() {
        return author;
    }

    /**
     * Get the channel type it was sent in.
     *
     * @return Get the channel type.
     */
    public ChannelType getChannelType() {
        return getType;
    }

    /**
     * Get the message that was sent.
     *
     * @return The message that was sent.
     */
    public Message getMessage() {
        return message;
    }

}
