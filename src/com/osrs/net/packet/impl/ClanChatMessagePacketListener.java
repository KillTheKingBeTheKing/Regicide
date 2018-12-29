package com.osrs.net.packet.impl;

import com.osrs.game.content.clan.ClanChatManager;
import com.osrs.game.entity.impl.player.Player;
import com.osrs.net.packet.Packet;
import com.osrs.net.packet.PacketListener;

public class ClanChatMessagePacketListener implements PacketListener {

    @SuppressWarnings("unused")
	@Override
    public void handleMessage(Player player, Packet packet) {
        String crowns = packet.readString();
        String message = packet.readString();

        ClanChatManager.sendMessage(player, message);
    }

}
