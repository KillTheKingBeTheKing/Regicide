package com.osrs.net.packet.impl.commands.developer;

import com.osrs.game.entity.impl.player.Player;
import com.osrs.game.model.Graphic;
import com.osrs.net.packet.impl.commands.Command;

public class Gfx extends Command {

	@Override
	public void execute(Player player, String input) {
		int gfx = Integer.parseInt(input);
		player.performGraphic(new Graphic(gfx));
	}

}
