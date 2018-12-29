package com.osrs.game.content.dialogues.impl;

import com.osrs.game.entity.impl.npc.NPC;
import com.osrs.game.entity.impl.player.Player;

public abstract class Dialogue {
    public abstract void execute(Player player, NPC npc);
    public abstract boolean handleActions(Player player, NPC npc);
    
    public abstract boolean handleTwo(Player player, NPC npc);
    public abstract boolean handleThree(Player player, NPC npc);
    public abstract boolean handleFour(Player player, NPC npc);
    public abstract boolean handleFive(Player player, NPC npc);
}
