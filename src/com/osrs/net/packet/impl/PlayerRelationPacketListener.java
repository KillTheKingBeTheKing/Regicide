package com.osrs.net.packet.impl;

import java.util.Optional;

import com.osrs.game.World;
import com.osrs.game.entity.impl.player.Player;
import com.osrs.net.packet.Packet;
import com.osrs.net.packet.PacketConstants;
import com.osrs.net.packet.PacketListener;
import com.osrs.util.Misc;

/**
 * This packet listener is called when a player is doing something relative to
 * their friends or ignore list, such as adding or deleting a player from said
 * list.
 *
 * @author relex lawl
 */

public class PlayerRelationPacketListener implements PacketListener {

	@Override
	public void handleMessage(Player player, Packet packet) {
		try {

			long username = packet.readLong();
			if (username < 0) {
				return;
			}

			switch (packet.getOpcode()) {
			case PacketConstants.ADD_FRIEND_OPCODE:
				player.getRelations().addFriend(username);
				break;
			case PacketConstants.ADD_IGNORE_OPCODE:
				player.getRelations().addIgnore(username);
				break;
			case PacketConstants.REMOVE_FRIEND_OPCODE:
				player.getRelations().deleteFriend(username);
				break;
			case PacketConstants.REMOVE_IGNORE_OPCODE:
				player.getRelations().deleteIgnore(username);
				break;
			case PacketConstants.SEND_PM_OPCODE:
				String message = packet.readString();
				if (message.isEmpty()) {
					return;
				}
				Optional<Player> friend = World
						.getPlayerByName(Misc.formatText(Misc.longToString(username)).replaceAll("_", " "));
				if (friend.isPresent()) {
					player.getRelations().message(friend.get(), message);
				} else {
					player.getPacketSender().sendMessage("That player is offline.");
				}
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
