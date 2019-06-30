package me.ryandw11.example;

import javax.security.auth.login.LoginException;

import me.ryandw11.jdacommand.CommandExecutor;
import me.ryandw11.jdacommand.JDACommandHandler;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

public class TestBot implements CommandExecutor {
	
	public static void main(String[] args) throws LoginException {
		JDA jda = new JDABuilder("NTkzOTUzNzAzMzU1MDg4OTAz.XRVYiw.5nhfz7sxGc8Ns-REQAE1sg0xFuc")
				.build();
		JDACommandHandler jdacmd = new JDACommandHandler(jda, "!");
		String[] ales = {"ping", "pop"};
		jdacmd.register(new ExampleCommand(), ales);
		jdacmd.finalize();
	}
	
	
	
	
}
