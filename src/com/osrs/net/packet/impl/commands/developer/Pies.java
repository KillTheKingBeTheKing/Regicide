package com.osrs.net.packet.impl.commands.developer;

import com.osrs.game.entity.impl.player.Player;
import com.osrs.game.model.Item;
import com.osrs.net.packet.impl.commands.Command;
import com.osrs.util.ItemIdentifiers;

public class Pies extends Command {

	@Override
	public void execute(Player player, String input) {
		player.getInventory().add(new Item(ItemIdentifiers.UNCOOKED_BERRY_PIE))
							 .add(new Item(ItemIdentifiers.UNCOOKED_MEAT_PIE))
							 .add(new Item(ItemIdentifiers.RAW_MUD_PIE))
							 .add(new Item(ItemIdentifiers.UNCOOKED_APPLE_PIE))
							 .add(new Item(ItemIdentifiers.RAW_GARDEN_PIE))
							 .add(new Item(ItemIdentifiers.RAW_FISH_PIE))
							 .add(new Item(ItemIdentifiers.RAW_ADMIRAL_PIE))
							 .add(new Item(ItemIdentifiers.RAW_WILD_PIE))
							 .add(new Item(ItemIdentifiers.RAW_SUMMER_PIE));
	}

}
