package com.osrs.net.packet.impl;

import com.osrs.game.collision.RegionManager;
import com.osrs.game.content.minigames.barrows.Barrows;
import com.osrs.game.content.skill.farming.Farming;
import com.osrs.game.entity.impl.grounditem.ItemOnGroundManager;
import com.osrs.game.entity.impl.npc.NpcAggression;
import com.osrs.game.entity.impl.object.ObjectManager;
import com.osrs.game.entity.impl.player.Player;
import com.osrs.net.packet.Packet;
import com.osrs.net.packet.PacketListener;


public class RegionChangePacketListener implements PacketListener {

    @Override
    public void handleMessage(Player player, Packet packet) {       
        if (player.isAllowRegionChangePacket()) {
            RegionManager.loadMapFiles(player.getPosition().getX(), player.getPosition().getY());
            player.getPacketSender().deleteRegionalSpawns();
            ItemOnGroundManager.onRegionChange(player);
            ObjectManager.onRegionChange(player);
            Barrows.brotherDespawn(player);
            Farming.onRegionChange(player);
            player.getAggressionTolerance().start(NpcAggression.NPC_TOLERANCE_SECONDS); //Every 4 minutes, reset aggression for npcs in the region.
            player.setAllowRegionChangePacket(false);
        }
    }
}
