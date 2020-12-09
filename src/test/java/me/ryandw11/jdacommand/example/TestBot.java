package me.ryandw11.jdacommand.example;

import javax.security.auth.login.LoginException;

import me.ryandw11.jdacommand.JDACommandHandler;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import java.io.InputStream;
import java.util.Scanner;

public class TestBot {
	
	public static void main(String[] args) throws LoginException {
		InputStream inputStream = TestBot.class.getResourceAsStream("/code.txt");
		Scanner scanner = new Scanner(inputStream);

		JDA jda = JDABuilder.createDefault(scanner.nextLine())
				.build();
		JDACommandHandler jdacmd = new JDACommandHandler(jda, "!");
		jdacmd.register(new ExampleCommand());
		jdacmd.finalizeCommands();
	}
	
	
	
	
}
