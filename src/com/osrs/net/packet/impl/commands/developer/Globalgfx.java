package com.osrs.net.packet.impl.commands.developer;

import com.osrs.game.entity.impl.player.Player;
import com.osrs.game.model.Graphic;
import com.osrs.game.model.Position;
import com.osrs.net.packet.impl.commands.Command;

public class Globalgfx extends Command {

    @Override
    public void execute(Player player, String input) {
        String[] args = input.split(" ");
        player.getPacketSender().sendGlobalGraphic(
                new Graphic(Integer.parseInt(args[0])),
                new Position(
                        Integer.parseInt(args[1]),
                        Integer.parseInt(args[2]),
                        player.getZ()
                )
        );
    }

}