package me.mariocmflys.corgii;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

public class Run {
	public static void main(String[] args){
		System.out.println(C.BOT_NAME+" "+C.BOT_VERSION+" by "+C.BOT_AUTHOR);
		if(args.length >= 1){
			try {
				JDA jda = new JDABuilder(AccountType.BOT).setToken(args[0]).buildBlocking();
				jda.addEventListener(new EventListener());
			} catch (LoginException | IllegalArgumentException
					| InterruptedException | RateLimitedException e) {
				System.err.println("Error: Failed to start bot");
				e.printStackTrace();
			}
		}
		else{
			System.err.println("Error: no token supplied.");
		}
	}
}
