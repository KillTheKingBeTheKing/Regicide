package com.osrs.net.packet.impl.commands.all;

import com.osrs.game.content.presets.Presetables;
import com.osrs.game.entity.impl.player.Player;
import com.osrs.net.packet.impl.commands.Command;

public class Preset extends Command {

	@Override
	public void execute(Player player, String input) {
		Presetables.open(player);
	}

}
