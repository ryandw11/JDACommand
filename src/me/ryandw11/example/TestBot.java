package me.ryandw11.example;

import javax.security.auth.login.LoginException;

import me.ryandw11.jdacommand.JDACommandHandler;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

public class TestBot {
	
	public static void main(String[] args) throws LoginException {
		JDA jda = new JDABuilder("Your bot id.")
				.build();
		JDACommandHandler jdacmd = new JDACommandHandler(jda, "!");
		jdacmd.register(new ExampleCommand());
		jdacmd.finalize();
	}
	
	
	
	
}
