package com.osrs.net.packet.impl.commands.developer;

import com.osrs.game.content.combat.CombatFactory;
import com.osrs.game.entity.impl.player.Player;
import com.osrs.game.task.impl.CombatPoisonEffect.PoisonType;
import com.osrs.net.packet.impl.commands.Command;

public class Poisonme extends Command {

	@Override
	public void execute(Player player, String input) {
		CombatFactory.poisonEntity(player, PoisonType.valueOf(input.toUpperCase()));
	}

}
