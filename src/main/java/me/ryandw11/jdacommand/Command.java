package me.ryandw11.jdacommand;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Declare a command in a class using this. Use alias = {} to denote the command name and its aliases.<br>
 * <b>The command must have a JDACommand parameter</b>
 *
 * @author Ryandw11
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Command {
    String[] alias();
}
