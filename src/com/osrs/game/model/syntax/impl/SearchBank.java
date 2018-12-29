package com.osrs.game.model.syntax.impl;

import com.osrs.game.entity.impl.player.Player;
import com.osrs.game.model.container.impl.Bank;
import com.osrs.game.model.syntax.EnterSyntax;

public class SearchBank implements EnterSyntax {

    @Override
    public void handleSyntax(Player player, String input) {
        Bank.search(player, input);
    }

    @Override
    public void handleSyntax(Player player, int input) {

    }

}
