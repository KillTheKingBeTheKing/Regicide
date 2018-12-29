package com.osrs.net.packet.impl.commands.developer;

import com.osrs.game.content.combat.CombatSpecial;
import com.osrs.game.entity.impl.player.Player;
import com.osrs.net.packet.impl.commands.Command;

public class Spec extends Command {

	@Override
	public void execute(Player player, String input) {
		int amt = Integer.parseInt(input);
		player.setSpecialPercentage(amt);
		CombatSpecial.updateBar(player);
	}

}
