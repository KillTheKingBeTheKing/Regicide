package com.osrs.game.model.areas.impl;

import java.util.Arrays;
import java.util.Optional;

import com.osrs.game.entity.impl.Character;
import com.osrs.game.entity.impl.player.Player;
import com.osrs.game.model.Boundary;

public class ArmaArea extends PrivateArea {

    public static final Boundary BOUNDARY = new Boundary(2824, 5296, 2842, 5308, true);

    public ArmaArea() {
        super(Arrays.asList(BOUNDARY));
    }

    @Override
    public void process(Character character) {

    }

    @Override
    public boolean canTeleport(Player player) {
        return true;
    }

    @Override
    public boolean canAttack(Character attacker, Character target) {
        return true;
    }

    @Override
    public void defeated(Player player, Character character) {

    }

    @Override
    public boolean canTrade(Player player, Player target) {
        return false;
    }

    @Override
    public boolean isMulti(Character character) {
        return true;
    }

    @Override
    public boolean canEat(Player player, int itemId) {
        return true;
    }

    @Override
    public boolean canDrink(Player player, int itemId) {
        return true;
    }

    @Override
    public boolean dropItemsOnDeath(Player player, Optional<Player> killer) {
        return true;
    }

    @Override
    public boolean handleDeath(Player player, Optional<Player> killer) {
        return false;
    }

    @Override
    public void onPlayerRightClick(Player player, Player rightClicked, int option) {

    }

    @Override
    public boolean handleObjectClick(Player player, int objectId, int type) {
        return false;
    }

    @Override
    public boolean isWilderness() {
        return false;
    }
}
