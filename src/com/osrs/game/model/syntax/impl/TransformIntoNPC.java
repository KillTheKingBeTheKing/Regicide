package com.osrs.game.model.syntax.impl;

import com.osrs.game.entity.impl.player.Player;
import com.osrs.game.model.syntax.EnterSyntax;

public class TransformIntoNPC implements EnterSyntax {

    @Override
    public void handleSyntax(Player player, String input) {
    	int id = Integer.parseInt(input);
		player.setNpcTransformationId(id);
		player.getPacketSender().sendInterfaceRemoval();
    }

    @Override
    public void handleSyntax(Player player, int input) {
		player.setNpcTransformationId(input);
		player.getPacketSender().sendInterfaceRemoval();
    }
}
