/*
 *     Copyright 2017 MarioCMFlys
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */

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
