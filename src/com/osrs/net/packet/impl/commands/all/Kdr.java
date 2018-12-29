package com.osrs.net.packet.impl.commands.all;

import com.osrs.game.entity.impl.player.Player;
import com.osrs.net.packet.impl.commands.Command;

public class Kdr extends Command {

	@Override
	public void execute(Player player, String input) {
		player.forceChat("I currently have "+player.getKillDeathRatio()+" kdr!");
	}
}
