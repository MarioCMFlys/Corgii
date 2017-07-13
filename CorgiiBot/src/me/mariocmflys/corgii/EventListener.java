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
