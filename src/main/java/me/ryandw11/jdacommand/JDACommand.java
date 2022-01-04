package me.ryandw11.jdacommand;

import net.dv8tion.jda.api.entities.*;

import java.util.List;

/**
 * This class represents a command that was sent.
 */
public class JDACommand {

    private final String name;
    private final List<String> args;
    private final User author;
    private final ChannelType getType;
    private final Message message;

    /**
     * This is done internally by the JDACommand library.
     *
     * @param name    The name of the command.
     * @param args    The arguments.
     * @param author  The author.
     * @param type    The type.
     * @param message The message sent.
     */
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
     * @return The author that sent the command.
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

    /**
     * Get the message channel where the command was sent.
     *
     * @return The message channel where the message was sent.
     */
    public MessageChannel getChannel() {
        return message.getChannel();
    }

    /**
     * Get the guild where the command was sent.
     *
     * @return The guild where the command was sent.
     */
    public Guild getGuild() {
        return message.getGuild();
    }

}
