package com.osrs.game.model.syntax.impl;


import com.osrs.game.content.dialogues.DialogueHandler;
import com.osrs.game.entity.impl.player.Player;
import com.osrs.game.model.syntax.EnterSyntax;

public class SetEmail implements EnterSyntax {
	
	@Override
	public void handleSyntax(Player player, String input) {
		if(input.contains("@")) {
			DialogueHandler.sendStatement(player, "Email has Been Set!");
			player.setEmail(input);
		}
		
	}

	@Override
	public void handleSyntax(Player player, int input) {

	}

}
