package com.osrs.net.packet.impl.commands.moderator;

import java.util.Optional;

import com.osrs.game.World;
import com.osrs.game.entity.impl.player.Player;
import com.osrs.game.entity.impl.player.PlayerSaving;
import com.osrs.net.packet.impl.commands.Command;
import com.osrs.util.PlayerPunishment;

public class Unmute extends Command {

	@Override
	public void execute(Player player, String input) {
		String player2 = input;
		Optional<Player> plr = World.getPlayerByName(player2);
		if(!PlayerSaving.playerExists(player2) && !plr.isPresent()) {
			player.getPacketSender().sendMessage("Player "+player2+" does not exist.");
			return;
		}
		if(!PlayerPunishment.muted(player2)) {
			player.getPacketSender().sendMessage("Player "+player2+" does not have an active mute.");
			return;
		}
		PlayerPunishment.unmute(player2);
		player.getPacketSender().sendMessage("Player "+player2+" was successfully unmuted.");
		if(plr.isPresent()) {
			plr.get().getPacketSender().sendMessage("You have been unmuted by "+player.getUsername()+".");
		}
	}

}
