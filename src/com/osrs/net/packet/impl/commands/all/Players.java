package com.osrs.net.packet.impl.commands.all;

import com.osrs.game.World;
import com.osrs.game.content.combat.bountyhunter.BountyHunter;
import com.osrs.game.entity.impl.player.Player;
import com.osrs.net.packet.impl.commands.Command;

public class Players extends Command {

	@Override
	public void execute(Player player, String input) {
		player.getPacketSender().sendConsoleMessage("There are currently "+World.getPlayers().size()+" players online and "+BountyHunter.PLAYERS_IN_WILD.size()+" players in the Wilderness.");
	}

}
