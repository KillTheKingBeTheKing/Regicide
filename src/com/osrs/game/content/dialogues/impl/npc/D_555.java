package com.osrs.game.content.dialogues.impl.npc;

import com.osrs.game.content.dialogues.impl.Dialogue;
import com.osrs.game.entity.impl.npc.NPC;
import com.osrs.game.entity.impl.player.Player;

//555 = the npc's id so the monk from earlier
public class D_555 extends Dialogue {
    @Override
    public void execute(Player player, NPC npc) {
        if (npc == null)
            return;

        if (player.getNpcTalking() == npc.getId()) {
            switch (player.getDialogueState()) {
                default://Temporary
                    player.getPacketSender().sendMessage("This NPC has no interest in speaking to you.");
                    break;
            }
        }
    }
    public boolean handleActions(Player player, NPC npc) {
        return false;
    }
	@Override
	public boolean handleTwo(Player player, NPC npc) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean handleThree(Player player, NPC npc) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean handleFour(Player player, NPC npc) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean handleFive(Player player, NPC npc) {
		// TODO Auto-generated method stub
		return false;
	}
}
