package com.osrs.net.packet.impl.commands.developer;

import com.osrs.game.entity.impl.player.Player;
import com.osrs.game.model.container.shop.ShopManager;
import com.osrs.net.packet.impl.commands.Command;

public class Shop extends Command {

	@Override
	public void execute(Player player, String input) {
		ShopManager.open(player, Integer.parseInt(input));
		player.getPacketSender().sendMessage("Opening Shop: " + Integer.parseInt(input));
	}

}
