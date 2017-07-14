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

import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class EventListener extends ListenerAdapter {
	@Override
    public void onMessageReceived(MessageReceivedEvent event){
		
	}
	
	@Override
	public void onGuildVoiceJoin(GuildVoiceJoinEvent event){
		if(event.getChannelJoined().getName().equalsIgnoreCase(C.CHANNEL_CREATE_NAME)){
			event.getGuild().getController().createVoiceChannel(C.CHANNEL_PREFIX+System.currentTimeMillis()).queue(c -> {
				event.getGuild().getController().moveVoiceMember(event.getMember(), (VoiceChannel) c).queue();
			});
		}
	}
	
	@Override
	public void onGuildVoiceMove(GuildVoiceMoveEvent event){
		if(event.getChannelLeft().getName().startsWith(C.CHANNEL_PREFIX)){
			if(event.getChannelLeft().getMembers().size() == 0){
				event.getChannelLeft().delete().queue();
			}
		}
		if(event.getChannelJoined().getName().equalsIgnoreCase(C.CHANNEL_CREATE_NAME)){
			event.getGuild().getController().createVoiceChannel(C.CHANNEL_PREFIX+System.currentTimeMillis()).queue(c -> {
				event.getGuild().getController().moveVoiceMember(event.getMember(), (VoiceChannel) c).queue();
			});
		}
	}
	
	@Override
	public void onGuildVoiceLeave(GuildVoiceLeaveEvent event){
		if(event.getChannelLeft().getName().startsWith(C.CHANNEL_PREFIX)){
			if(event.getChannelLeft().getMembers().size() == 0){
				event.getChannelLeft().delete().queue();
			}
		}
	}
}
