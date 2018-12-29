package com.osrs.net.discord.listeners;

import com.osrs.net.discord.CommandParser;
import com.osrs.net.discord.DiscordConnection;
import com.osrs.net.discord.DiscordConstants;
import com.osrs.net.discord.commands.CommandHandler;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {

	public void onMessageReceived(MessageReceivedEvent event) {
		DiscordConnection.channel = event.getChannel();
		if (event.getMessage().getContentRaw().startsWith(DiscordConstants.getPrefex()) && event.getMessage().getAuthor().getId() != event.getJDA() .getSelfUser().getId()) {
			CommandHandler.handleCommand(CommandParser.parser(event.getMessage().getContentRaw(), event));
			event.getTextChannel().deleteMessageById(event.getMessageId()).queue();
		}
	}
	
}
