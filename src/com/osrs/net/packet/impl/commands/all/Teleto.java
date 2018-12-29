package com.osrs.net.packet.impl.commands.all;

import java.util.Optional;

import com.osrs.game.World;
import com.osrs.game.entity.impl.player.Player;
import com.osrs.game.model.Position;
import com.osrs.net.packet.impl.commands.Command;

public class Teleto extends Command {
	
	public void execute(Player player, String input) {
		String[] parts = input.split(" ");
		Optional<Player> player_ = World.getPlayerByName(parts[0]);
		player.moveTo(new Position(player_.get().getX(),player_.get().getY(),player_.get().getZ()));
	}

}
