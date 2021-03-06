package com.osrs.net.packet.impl;

import com.osrs.game.entity.impl.player.Player;
import com.osrs.game.model.teleportation.TeleportHandler;
import com.osrs.game.model.teleportation.TeleportType;
import com.osrs.game.model.teleportation.chatbox.BossTeleport;
import com.osrs.game.model.teleportation.chatbox.CityTeleport;
import com.osrs.game.model.teleportation.chatbox.DungeonTeleport;
import com.osrs.game.model.teleportation.chatbox.MinigameTeleport;
import com.osrs.game.model.teleportation.chatbox.PvPTeleport;
import com.osrs.game.model.teleportation.chatbox.SkillingTeleport;
import com.osrs.game.model.teleportation.chatbox.Teleport;
import com.osrs.game.model.teleportation.chatbox.TrainingTeleport;
import com.osrs.net.packet.Packet;
import com.osrs.net.packet.PacketListener;

public class TeleportRequestGamePacket implements PacketListener {

	@Override
	public void handleMessage(Player player,  Packet packet) {
		final int parentHierarchy = packet.readByte();
		final int childHierarchy = packet.readByte();
		
		if (parentHierarchy < HIERARCHY_OPTIONS.length && childHierarchy < HIERARCHY_OPTIONS[parentHierarchy].length) {
			final Teleport teleport = HIERARCHY_OPTIONS[parentHierarchy][childHierarchy];
			if (teleport.getPosition() == null) {
				player.sendMessage("Coordinates are needed for this teleport: " + teleport.toString());
				return;
			}
			if (teleport.getWarning() != null) {
					player.getPacketSender().sendInterfaceRemoval();
					TeleportHandler.teleport(player, teleport.getPosition(), TeleportType.NORMAL, true);
			} else {
				player.getPacketSender().sendInterfaceRemoval();
				TeleportHandler.teleport(player, teleport.getPosition(), TeleportType.NORMAL, false);
			}
		} else {
			player.sendMessage("Could not teleport! Please contact an administrator with this message [" + parentHierarchy + ", " + childHierarchy + "]");
		}
	}
	
	private static final Teleport[][] HIERARCHY_OPTIONS = {
		TrainingTeleport.values(),
		DungeonTeleport.values(),
		BossTeleport.values(),
		PvPTeleport.values(),
		CityTeleport.values(),
		MinigameTeleport.values(),
		
		SkillingTeleport.AGILITY.getTeleports(),
		SkillingTeleport.COOKING.getTeleports(),
		SkillingTeleport.CRAFTING.getTeleports(),
		SkillingTeleport.FISHING.getTeleports(),
		SkillingTeleport.HUNTER.getTeleports(),
		SkillingTeleport.MINING.getTeleports(),
		SkillingTeleport.SLAYER.getTeleports(),
		SkillingTeleport.SMITHING.getTeleports(),
		SkillingTeleport.THIEVING.getTeleports(),
		SkillingTeleport.WOODCUTTING.getTeleports(),
		SkillingTeleport.FARMING.getTeleports(),
	};
	
}
