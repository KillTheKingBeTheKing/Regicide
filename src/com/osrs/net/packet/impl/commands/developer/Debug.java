package com.osrs.net.packet.impl.commands.developer;

import com.osrs.game.entity.impl.player.Player;
import com.osrs.net.packet.impl.commands.Command;

public class Debug extends Command {

	@Override
	public void execute(Player player, String input) {
		player.toggleDebug();
		player.getPacketSender().sendMessage("@dre@Debug Mode: " + (player.isDebug() ? "Enabled" : "Disabled"));
	}

}
