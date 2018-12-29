package com.osrs.game.model.syntax.impl;

import com.osrs.game.World;
import com.osrs.game.entity.impl.npc.NPC;
import com.osrs.game.entity.impl.player.Player;
import com.osrs.game.model.syntax.EnterSyntax;

public class SpawnAggressiveNPC implements EnterSyntax {

    @Override
    public void handleSyntax(Player player, String input) {
    	int id = Integer.parseInt(input);
		NPC npc = new NPC(id, player.getPosition().clone());
		npc.getDefinition().setAggressive(true);
		World.getAddNPCQueue().add(npc);
		player.getPacketSender().sendInterfaceRemoval();
    }

    @Override
    public void handleSyntax(Player player, int input) {
		NPC npc = new NPC(input, player.getPosition().clone());
		npc.getDefinition().setAggressive(true);
		World.getAddNPCQueue().add(npc);
		player.getPacketSender().sendInterfaceRemoval();
    }
}
