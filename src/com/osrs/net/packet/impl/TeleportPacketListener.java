package com.osrs.net.packet.impl;

import com.osrs.game.entity.impl.player.Player;
import com.osrs.game.model.Position;
import com.osrs.game.model.teleportation.TeleportHandler;
import com.osrs.game.model.teleportation.Teleportable;
import com.osrs.net.packet.Packet;
import com.osrs.net.packet.PacketListener;

/**
 * This packet listener handles the action when pressing a teleport menu in the
 * chatbox teleport interface.
 *
 * @author Professor Oak
 */

public class TeleportPacketListener implements PacketListener {

	@Override
	public void handleMessage(Player player, Packet packet) {
		if (player.getHitpoints() <= 0)
			return;
		int type = packet.readByte();
		int index = packet.readByte();

		if (!player.isTeleportInterfaceOpen()) {
			player.getPacketSender().sendInterfaceRemoval();
			return;
		}


		if (player.isDebug()) {
			player.getPacketSender().sendMessage("Selected a teleport. Type: " + Integer.toString(type) + ", index: " + Integer.toString(index) + ".");
		}

		for (Teleportable teleport : Teleportable.values()) {
			if (teleport.getType() == type && teleport.getIndex() == index) {
				Position teleportPosition = teleport.getPosition();
				if (TeleportHandler.checkReqs(player, teleportPosition)) {
					player.getPreviousTeleports().put(teleport.getTeleportButton(), teleportPosition);
					TeleportHandler.teleport(player, teleportPosition, player.getSpellbook().getTeleportType(), true);
				}
				break;
			}
		}
	}
}
