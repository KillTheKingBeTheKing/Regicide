package com.osrs.net.packet.impl.commands.admin;

import com.osrs.game.entity.impl.player.Player;
import com.osrs.net.packet.impl.commands.Command;

public class Unipmute extends Command {

	@Override
	public void execute(Player player, String input) {
		player.getPacketSender().sendMessage("Unipmutes can only be handled manually.");
	}

}
