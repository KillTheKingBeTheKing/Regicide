package com.osrs.net.packet.impl.commands.all;

import com.osrs.game.entity.impl.player.Player;
import com.osrs.game.model.Item;
import com.osrs.net.packet.impl.commands.Command;

public class Spawn extends Command {

	@Override
	public void execute(Player player, String input) {
		String[] parts = input.split(" ");
		int amount = 1;
		if(parts.length > 1) {
			amount = Integer.parseInt(parts[1]);
		}
		player.getInventory().add(new Item(Integer.parseInt(parts[0]), amount));
	}

}
