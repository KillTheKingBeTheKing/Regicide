package com.osrs.net.packet.impl;

import com.osrs.game.entity.impl.player.Player;
import com.osrs.game.model.PlayerRelations.PrivateChatStatus;
import com.osrs.net.packet.Packet;
import com.osrs.net.packet.PacketListener;

public class ChatSettingsPacketListener implements PacketListener {


    @Override
    public void handleMessage(Player player, Packet packet) {
        @SuppressWarnings("unused")
        int publicMode = packet.readByte();

        int privateMode = packet.readByte();

        @SuppressWarnings("unused")
        int tradeMode = packet.readByte();

		/*
		 * Did the player change their private chat status? 
		 * If yes, update status for all friends.
		 */

        if (privateMode > PrivateChatStatus.values().length) {
            return;
        }

        PrivateChatStatus privateChatStatus = PrivateChatStatus.values()[privateMode];
        if (player.getRelations().getStatus() != privateChatStatus) {
            player.getRelations().setStatus(privateChatStatus, true);
        }
    }
}
