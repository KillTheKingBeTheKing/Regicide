package com.osrs.net.packet.impl;

import com.osrs.game.content.combat.CombatSpecial;
import com.osrs.game.entity.impl.player.Player;
import com.osrs.net.packet.Packet;
import com.osrs.net.packet.PacketListener;

/**
 * This packet listener handles the action when pressing
 * a special attack bar.
 *
 * @author Professor Oak
 */

public class SpecialAttackPacketListener implements PacketListener {

    @Override
    public void handleMessage(Player player, Packet packet) {
        @SuppressWarnings("unused")
        int specialBarButton = packet.readInt();

        if (player.getHitpoints() <= 0) {
            return;
        }

        CombatSpecial.activate(player);
    }
}
