package com.osrs.game.content.skill.crafting;

import java.util.Arrays;
import java.util.Optional;

import com.osrs.game.content.skill.skillable.impl.ItemCreationSkillable;
import com.osrs.game.entity.impl.player.Player;
import com.osrs.game.model.*;
import com.osrs.game.model.menu.CreationMenu;
import com.osrs.game.model.menu.impl.SingleItemCreationMenu;
import com.osrs.util.ItemIdentifiers;

public class Crafting extends ItemIdentifiers {

    public static boolean craftGem(Player player, int itemUsed, int itemUsedWith) {
        if (itemUsed == CHISEL || itemUsedWith == CHISEL) {
            GemData.CraftableGem gem = GemData.CraftableGem.map.get(itemUsed == CHISEL ? itemUsedWith : itemUsed);
            if (gem != null) {
                player.setCreationMenu(Optional.of(new SingleItemCreationMenu(player, gem.getCut().getId(), "How many would you like to cut?", new CreationMenu.CreationMenuAction() {
                    @Override
                    public void execute(int item, int amount) {
                        player.getSkillManager().startSkillable(new ItemCreationSkillable(Arrays.asList(new RequiredItem(new Item(CHISEL), false), new RequiredItem(gem.getUncut(), true)), gem.getCut(), amount, Optional.of(gem.getAnimationLoop()), gem.getLevel(), gem.getExp(), Skill.CRAFTING));
                    }
                }).open()));
                return true;
            }
        }
        return false;
    }

    public static boolean craftBolt(Player player, int itemUsed, int itemUsedWith) {
        if (itemUsed == CHISEL || itemUsedWith == CHISEL) {
            BoltTips.BoltTip gem = BoltTips.BoltTip.map.get(itemUsed == CHISEL ? itemUsedWith : itemUsed);
            if (gem != null) {
                player.setCreationMenu(Optional.of(new SingleItemCreationMenu(player, gem.getCut().getId(), "How many would you like to make?", new CreationMenu.CreationMenuAction() {
                    @Override
                    public void execute(int item, int amount) {
                        player.getSkillManager().startSkillable(new ItemCreationSkillable(Arrays.asList(new RequiredItem(new Item(CHISEL), false), new RequiredItem(gem.getUncut(), true)), gem.getCut(), amount, Optional.of(gem.getAnimationLoop()), gem.getLevel(), gem.getExp(), Skill.FLETCHING));
                    }
                }).open()));
                return true;
            }
        }
        return false;
    }
}