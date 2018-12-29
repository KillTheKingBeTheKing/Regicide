package com.osrs.net.packet.impl.commands.developer;

import com.osrs.game.definition.loader.impl.ItemDefinitionLoader;
import com.osrs.game.definition.loader.impl.NpcDefinitionLoader;
import com.osrs.game.definition.loader.impl.NpcDropDefinitionLoader;
import com.osrs.game.definition.loader.impl.ShopDefinitionLoader;
import com.osrs.game.entity.impl.player.Player;
import com.osrs.net.packet.impl.commands.Command;

public class Reload extends Command {

	@Override
	public void execute(Player player, String input) {
		switch (input.toLowerCase()) {
			case "items":
				try {
					ItemDefinitionLoader.reload();
				} catch (Throwable e) {
					e.printStackTrace();
				}
				break;
			case "shops":
				try {
					ShopDefinitionLoader.reload();
				} catch (Throwable e) {
					e.printStackTrace();
				}
				break;
			case "drops":
				try {
					NpcDropDefinitionLoader.reload();
				} catch (Throwable e) {
					e.printStackTrace();
				}
				break;
			case "npcs":
				try {
					NpcDefinitionLoader.reload();
				} catch (Throwable e) {
					e.printStackTrace();
				}
				break;
		}
	}

}
