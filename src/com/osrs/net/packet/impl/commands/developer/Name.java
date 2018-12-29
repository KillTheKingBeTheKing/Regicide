package com.osrs.net.packet.impl.commands.developer;

import com.osrs.game.World;
import com.osrs.game.definition.NpcDefinition;
import com.osrs.game.entity.impl.npc.NPC;
import com.osrs.game.entity.impl.player.Player;
import com.osrs.net.packet.impl.commands.Command;

public class Name extends Command {
    /**
     * The command which is to be executed when it's called.
     *
     * @param player The player to whom the command should be applied.
     * @param input  Any additional parameters.
     */
    @Override
    public void execute(Player player, String input) {
        String name = input.split(" ")[0];
        for (NpcDefinition def : NpcDefinition.definitions.values()) {
            if (def.getName().equalsIgnoreCase(name.replaceAll("_", " "))) {
                NPC npc = new NPC(def.getId(), player.getPosition().clone());
                World.getAddNPCQueue().add(npc);
            }
        }
    }
}
