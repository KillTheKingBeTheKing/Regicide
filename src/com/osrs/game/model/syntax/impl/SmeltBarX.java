package com.osrs.game.model.syntax.impl;

import com.osrs.game.content.skill.smithing.SmithingBarData;
import com.osrs.game.content.skill.smithing.Smithing.Smelting;
import com.osrs.game.entity.impl.player.Player;
import com.osrs.game.model.syntax.EnterSyntax;

public class SmeltBarX implements EnterSyntax {

    private final SmithingBarData.Bar bar;

    public SmeltBarX(SmithingBarData.Bar bar) {
        this.bar = bar;
    }

    @Override
    public void handleSyntax(Player player, String input) {
    }

    @Override
    public void handleSyntax(Player player, int input) {
        if (input <= 0 || input > Integer.MAX_VALUE) {
            return;
        }
        player.getSkillManager().startSkillable(new Smelting(bar, input));
    }
}
