package com.osrs.game.model.syntax.impl;

import com.osrs.game.content.clan.ClanChatManager;
import com.osrs.game.entity.impl.player.Player;
import com.osrs.game.model.syntax.EnterSyntax;

public class NameClanChat implements EnterSyntax {
	
	@Override
	public void handleSyntax(Player player, String input) {
		if (ClanChatManager.getClanChatChannel(player) != null)
			ClanChatManager.setName(player, input);
		else
			ClanChatManager.createClan(player, input);
	}

	@Override
	public void handleSyntax(Player player, int input) {
		
	}

}
