package com.osrs.net.packet.impl;

import com.osrs.game.entity.impl.player.Player;
import com.osrs.net.packet.Packet;
import com.osrs.net.packet.PacketListener;

public class PlayerInactivePacketListener implements PacketListener {

    //CALLED EVERY 3 MINUTES OF INACTIVITY

    @Override
    public void handleMessage(Player player, Packet packet) {
    }
}
