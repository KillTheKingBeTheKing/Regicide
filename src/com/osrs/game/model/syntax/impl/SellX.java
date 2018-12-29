package com.osrs.game.model.syntax.impl;

import com.osrs.game.entity.impl.player.Player;
import com.osrs.game.model.PlayerStatus;
import com.osrs.game.model.container.shop.ShopManager;
import com.osrs.game.model.syntax.EnterSyntax;

public class SellX implements EnterSyntax {

    private final int slot, itemId;

    public SellX(int itemId, int slot) {
        this.itemId = itemId;
        this.slot = slot;
    }

    @Override
    public void handleSyntax(Player player, String input) {

    }

    @Override
    public void handleSyntax(Player player, int input) {
        if (player.getStatus() == PlayerStatus.SHOPPING) {
            ShopManager.sellItem(player, slot, itemId, input);
        }
    }

}
