package com.osrs.game.model.syntax.impl;

import com.osrs.game.content.clan.ClanChatManager;
import com.osrs.game.entity.impl.player.Player;
import com.osrs.game.model.syntax.EnterSyntax;

public class JoinClanChat implements EnterSyntax {

    @Override
    public void handleSyntax(Player player, String input) {
        ClanChatManager.join(player, input);
    }

    @Override
    public void handleSyntax(Player player, int input) {

    }
}
