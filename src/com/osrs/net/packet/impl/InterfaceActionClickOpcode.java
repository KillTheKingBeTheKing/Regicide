package com.osrs.net.packet.impl;

import com.osrs.game.content.clan.ClanChatManager;
import com.osrs.game.content.presets.Presetables;
import com.osrs.game.entity.impl.player.Player;
import com.osrs.game.model.container.impl.Bank;
import com.osrs.game.model.teleportation.TeleportHandler;
import com.osrs.net.packet.Packet;
import com.osrs.net.packet.PacketListener;

public class InterfaceActionClickOpcode implements PacketListener {

	@Override
	public void handleMessage(Player player, Packet packet) {
		int interfaceId = packet.readInt();
		int action = packet.readByte();

		if (player == null || player.getHitpoints() <= 0 || player.isTeleporting()) {
			return;
		}

		if (Bank.handleButton(player, interfaceId, action)) {
			return;
		}
		
		if (ClanChatManager.handleButton(player, interfaceId, action)) {
			return;
		}
		
		if (Presetables.handleButton(player, interfaceId)) {
			return;
		}
		
		if (TeleportHandler.handleButton(player, interfaceId, action)) {
			return;
		}
	}
}
