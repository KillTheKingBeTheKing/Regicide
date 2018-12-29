package com.osrs.net.packet.impl.commands.developer;

import com.osrs.game.definition.NpcDefinition;
import com.osrs.game.entity.impl.player.Player;
import com.osrs.net.packet.impl.commands.Command;

public class Pnpc extends Command {

	@Override
	public void execute(Player player, String input) {
	    int id = Integer.parseInt(input);
	    player.setSize(NpcDefinition.forId(id).getSize());
		player.setNpcTransformationId(id);
	}

}
