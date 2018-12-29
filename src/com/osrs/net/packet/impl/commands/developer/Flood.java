package com.osrs.net.packet.impl.commands.developer;

import com.osrs.Server;
import com.osrs.game.entity.impl.player.Player;
import com.osrs.net.packet.impl.commands.Command;

public class Flood extends Command {

	@Override
	public void execute(Player player, String input) {
		int amt = Integer.parseInt(input);
		Server.getFlooder().login(amt);
	}
}
