package com.osrs.net.packet.impl.commands.developer;

import com.osrs.game.entity.impl.player.Player;
import com.osrs.net.packet.impl.commands.Command;

public class Config extends Command {

	@Override
	public void execute(Player player, String input) {
		String[] parts = input.split(" ");
		player.getPacketSender().sendConfigByte(Integer.parseInt(parts[0]), (byte)Integer.parseInt(parts[1]));
	}

}
