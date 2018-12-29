package com.osrs.net.packet.impl.commands.developer;

import java.util.Optional;

import com.osrs.game.collision.Region;
import com.osrs.game.collision.RegionManager;
import com.osrs.game.entity.impl.player.Player;
import com.osrs.net.packet.impl.commands.Command;

public class Getregion extends Command {
    /**
     * The command which is to be executed when it's called.
     *
     * @param player The player to whom the command should be applied.
     * @param input  Any additional parameters.
     */
    @Override
    public void execute(Player player, String input) {
        Optional<Region> region = RegionManager.getRegion(player.getX(), player.getY());
        player.sendMessage(
                "Region ID: " + (region.isPresent() ? region.get().getRegionId() : "UNKNOWN_REGION")
        );
    }
}
