package com.osrs.net.packet.impl;

import com.osrs.game.definition.NpcDefinition;
import com.osrs.game.entity.impl.player.Player;
import com.osrs.net.packet.Packet;
import com.osrs.net.packet.PacketListener;

public class ExamineNpcPacketListener implements PacketListener {

    @Override
    public void handleMessage(Player player, Packet packet) {
        int npcId = packet.readShort();
        
        if (npcId <= 0) {
            return;
        }

        NpcDefinition npcDef = NpcDefinition.forId(npcId);
        if (npcDef != null) {
            player.getPacketSender().sendMessage(npcDef.getExamine());
        }
    }

}
