package com.osrs.game.model.syntax.impl;

import com.osrs.game.content.clan.ClanChatManager;
import com.osrs.game.entity.impl.player.Player;
import com.osrs.game.model.syntax.EnterSyntax;
import com.osrs.util.Misc;

public class ChangeClanChatName implements EnterSyntax {

	@Override
	public void handleSyntax(Player player, String input) {
		if (input.length() > 12) {
			input = input.substring(0, 11);
		}
		if (!Misc.isValidName(input)) {
			player.getPacketSender().sendMessage("Invalid syntax entered. Please set a valid name.");
			return;
		}
		ClanChatManager.setName(player, input);
	}

	@Override
	public void handleSyntax(Player player, int input) {
	}

}
