package com.osrs.net.packet.impl.commands.developer;

import com.osrs.game.entity.impl.player.Player;
import com.osrs.game.model.Position;
import com.osrs.net.packet.impl.commands.Command;

public class Tele extends Command {

	@Override
	public void execute(Player player, String input) {
		String[] parts = input.split(" ");
		int x = Integer.parseInt(parts[0]);
		int y = Integer.parseInt(parts[1]);
		int z = 0;
		if(parts.length == 3) {
			z = Integer.parseInt(parts[2]);
		}
		player.moveTo(new Position(x, y, z));
	}

}
