package com.osrs.net.packet.impl.commands.developer;

import java.util.Optional;

import com.osrs.game.World;
import com.osrs.game.entity.impl.player.Player;
import com.osrs.game.model.rights.Right;
import com.osrs.net.packet.impl.commands.Command;

public class Rights extends Command {

	@Override
	public void execute(Player player, String input) {
		String[] parts = input.split(" ");
		String other = parts[0];
		String right = parts[1];
		
		Optional<Player> player_ = World.getPlayerByName(other);
		if(!player_.isPresent()) {
			player.getPacketSender().sendMessage("Could not find player: "+other);
			return;
		}
		
		player_.get().getRights().add(Right.valueOf(right));
		player_.get().getPacketSender().sendRights();
		player_.get().getPacketSender().sendMessage("You're now a "+right);
		player.getPacketSender().sendMessage("Gave "+other+" rank: "+right);
	}

}
