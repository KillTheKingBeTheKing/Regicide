package com.osrs.game.model.areas.impl;

import java.util.Arrays;
import java.util.Optional;

import com.osrs.game.World;
import com.osrs.game.entity.impl.Character;
import com.osrs.game.entity.impl.npc.NPC;
import com.osrs.game.entity.impl.player.Player;
import com.osrs.game.model.Animation;
import com.osrs.game.model.AnimationLoop;
import com.osrs.game.model.Boundary;
import com.osrs.game.model.Position;
import com.osrs.game.model.areas.impl.PrivateArea;
import com.osrs.game.task.Task;
import com.osrs.game.task.TaskManager;
import com.osrs.util.NpcIdentifiers;

public class VorkathArea extends PrivateArea {

    public VorkathArea() {
        super(Arrays.asList(new Boundary(2259, 4052, 2285, 4078, true)));
    }

    @Override
    public void enter(Character character) {
        super.add(character);
        if (character.isPlayer()) {
            NPC npc = new NPC(8059, new Position(2269, 4062, character.getAsPlayer().getIndex() * 4));
            super.add(npc);
            World.getAddNPCQueue().add(npc);
            TaskManager.submit(new Task(8, npc, true) {
                @Override
                protected void execute() {
                    npc.performAnimation(new Animation(7946));
                }
            });
        }
    }

    @Override
    public void leave(Character character) {
    	super.leave(character);
        if (character.isPlayer())
        	super.destroy();
}

    @Override
    public void process(Character character) {
        if (character.isNpc()) {
            if (character.getAsNpc().getId() == NpcIdentifiers.VORKATH_ASLEEP) {
                character.getAsNpc().performAnimation(7946);
            }
        }
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
    public void defeated(Player player, Character character) { }

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
        return false;
    }

    @Override
    public boolean dropItemsOnDeath(Player player, Optional<Player> killer) {
        return true;
    }

    @Override
    public boolean handleDeath(Player player, Optional<Player> killer) {
    	player.getVorkathLostItems().store();
        leave(player);
        return false;
    }

    @Override
    public void onPlayerRightClick(Player player, Player rightClicked, int option) {

    }

    @Override
    public boolean handleObjectClick(Player player, int objectId, int type) {
        switch (objectId) {
            default:
                return false;
        }
    }

    @Override
    public boolean isWilderness() {
        return false;
    }

    @Override
    public Position getLootDropPosition() { return new Position(2272, 4059); }

}
