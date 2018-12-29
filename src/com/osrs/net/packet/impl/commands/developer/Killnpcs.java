package com.osrs.net.packet.impl.commands.developer;

import com.osrs.game.World;
import com.osrs.game.entity.impl.npc.NPC;
import com.osrs.game.entity.impl.player.Player;
import com.osrs.net.packet.impl.commands.Command;

public class Killnpcs extends Command {

	@Override
	public void execute(Player player, String input) {
		String[] args = input.split(" ");
		int id = Integer.parseInt(args[0]);
		int radius = Integer.parseInt(args[1]);
		for (NPC npc : World.getNpcs()) {
			if (npc != null) {
				if (npc.getId() == id) {
		            final int distanceToPlayer = npc.getSpawnPosition().getDistance(player.getPosition());
		            if (distanceToPlayer <= radius) {
		            	npc.appendDeath();
		            }
				}
            }
		}
	}

}
