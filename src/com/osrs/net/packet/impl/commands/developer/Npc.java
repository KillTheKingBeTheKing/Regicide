package com.osrs.net.packet.impl.commands.developer;

import com.osrs.game.World;
import com.osrs.game.entity.impl.npc.NPC;
import com.osrs.game.entity.impl.player.Player;
import com.osrs.net.packet.impl.commands.Command;

public class Npc extends Command {

	@Override
	public void execute(Player player, String input) {
		if (input.contains(" ")) {
			String[] args = input.split(" ");
			for (int i = 0; i < Integer.parseInt(args[1]); i++) {
				NPC npc = new NPC(Integer.parseInt(args[0]), player.getPosition().clone());
				World.getAddNPCQueue().add(npc);
			}
		} else {
			NPC npc = new NPC(Integer.parseInt(input), player.getPosition().clone());
			World.getAddNPCQueue().add(npc);
		}
	}

}
