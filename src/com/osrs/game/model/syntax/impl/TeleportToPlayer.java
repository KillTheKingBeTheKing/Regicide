package com.osrs.game.model.syntax.impl;

import java.util.Optional;

import com.osrs.game.World;
import com.osrs.game.entity.impl.player.Player;
import com.osrs.game.model.syntax.EnterSyntax;

public class TeleportToPlayer implements EnterSyntax {

    @Override
    public void handleSyntax(Player player, String input) {
    	Optional<Player> optionalPlayer = World.getPlayerByName(input);
    	if (optionalPlayer.isPresent()) {
    		player.moveTo(optionalPlayer.get().getPosition());
    	}
		player.getPacketSender().sendInterfaceRemoval();
    }

    @Override
    public void handleSyntax(Player player, int input) {

    }
}
