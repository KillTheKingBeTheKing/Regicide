package com.osrs.net.packet.impl.commands.all;

import com.osrs.game.entity.impl.player.Player;
import com.osrs.net.packet.impl.commands.Command;

public class Changepass extends Command {

	@Override
	public void execute(Player player, String input) {
		String pass = input;
		if(pass.length() > 0 && pass.length() < 15) {
			player.setPassword(pass);
			player.getPacketSender().sendMessage("Your password is now: "+pass);
		} else {
			player.getPacketSender().sendMessage("Invalid password input.");
		}}

}
