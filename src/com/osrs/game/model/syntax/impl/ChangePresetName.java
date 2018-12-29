package com.osrs.game.model.syntax.impl;

import com.osrs.game.content.presets.Presetables;
import com.osrs.game.entity.impl.player.Player;
import com.osrs.game.model.syntax.EnterSyntax;
import com.osrs.util.Misc;

public class ChangePresetName implements EnterSyntax {
	
	private int presetIndex;
	
	public ChangePresetName(final int presetIndex) {
		this.presetIndex = presetIndex;
	}

	@Override
	public void handleSyntax(Player player, String input) {
		
		player.getPacketSender().sendInterfaceRemoval();
		
		input = Misc.formatText(input);
		
		if(!Misc.isValidName(input)) {
			player.getPacketSender().sendMessage("Invalid name for preset. Please enter characters only.");
			player.setCurrentPreset(null);
			Presetables.open(player);
			return;
		}
		
		if(player.getPresets()[presetIndex] != null) {
			
			player.getPresets()[presetIndex].setName(input);
			player.getPacketSender().sendMessage("The preset's name has been updated.");
			
			Presetables.open(player);
		}
	}

	@Override
	public void handleSyntax(Player player, int input) {
	}

}
