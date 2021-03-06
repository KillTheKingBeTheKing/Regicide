package com.osrs.net.packet.impl.commands.admin;

import java.util.Optional;

import com.osrs.game.World;
import com.osrs.game.entity.impl.player.Player;
import com.osrs.game.entity.impl.player.PlayerSaving;
import com.osrs.net.packet.impl.commands.Command;
import com.osrs.util.PlayerPunishment;

public class Ban extends Command {

	@Override
	public void execute(Player player, String input) {
		String player2 = input;
		Optional<Player> plr = World.getPlayerByName(player2);
		if(!PlayerSaving.playerExists(player2) && !plr.isPresent()) {
			player.getPacketSender().sendMessage("Player "+player2+" is not a valid online player.");
			return;
		}
		if(PlayerPunishment.banned(player2)) {
			player.getPacketSender().sendMessage("Player "+player2+" already has an active ban.");
			if(plr.isPresent()) {
				plr.get().requestLogout();
			}
			return;
		}
		PlayerPunishment.ban(player2);
		player.getPacketSender().sendMessage("Player "+player2+" was successfully banned. Command logs written.");
		if(plr.isPresent()) {
			plr.get().requestLogout();
		}
	}

}
