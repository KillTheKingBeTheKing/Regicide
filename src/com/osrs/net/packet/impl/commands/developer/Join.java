package com.osrs.net.packet.impl.commands.developer;

import java.util.Optional;

import com.osrs.game.World;
import com.osrs.game.entity.impl.player.Player;
import com.osrs.net.packet.impl.commands.Command;

public class Join extends Command {

	@Override
	public void execute(Player player, String input) {
		Optional<Player> joined = World.getPlayerByName(input);
		if (joined.isPresent()) {
			if (joined.get().getInstancedRegion().isPresent()) {
				player.sendMessage("Joining " + joined.get().getUsername() + "'s Instance.");
				joined.get().getInstancedRegion().get().addEntity(player);
			}
		}
	}

}