package com.osrs.game.model.areas.impl;

import java.util.Arrays;
import java.util.Optional;

import com.osrs.game.content.minigames.pestcontrol.PestControl;
import com.osrs.game.entity.impl.Character;
import com.osrs.game.entity.impl.player.Player;
import com.osrs.game.model.Boundary;
import com.osrs.game.model.areas.Area;

public class PestControlBoatArea extends Area {
/*
        2659, 2636 },
        { 2664, 2644
 */
    public PestControlBoatArea() {
        super(Arrays.asList(new Boundary(2659, 2664, 2636,  2644)));
    }

    @Override
    public void enter(Character character) {//Show interface if player is in area
        character.getAsPlayer().getPacketSender().sendWalkableInterface(21119);
    }

    @Override
    public void leave(Character character) {//Remove interface if player leaves area
        character.getAsPlayer().getPacketSender().sendInterfaceRemoval();
    }

    @Override
    public void process(Character character) {//On every game tick if player is in area
        PestControl.sequence();
    }

    @Override
    public boolean canTeleport(Player player) {//you get the idea... ok so
        player.getPacketSender().sendMessage("Please leave the boat first");
        return false;
    }

    @Override
    public boolean canAttack(Character attacker, Character target) {
        return false;
    }

    @Override
    public void defeated(Player player, Character character) {

    }

    @Override
    public boolean canTrade(Player player, Player target) {
        player.getPacketSender().sendMessage("Please leave the boat first");
        return false;
    }

    @Override
    public boolean isMulti(Character character) {
        return false;
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
        return false;
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
        switch (objectId) {
            case 14314: //Gang plank
                PestControl.leave(player, true);
                return true;
        }
        return false;
    }

	@Override
	public boolean isWilderness() {
		return false;
	}
}
