package com.osrs.game.content.combat.method.impl.npcs;

import com.osrs.game.content.combat.CombatType;
import com.osrs.game.content.combat.hit.PendingHit;
import com.osrs.game.content.combat.method.CombatMethod;
import com.osrs.game.entity.impl.Character;
import com.osrs.game.entity.impl.npc.NPC;
import com.osrs.game.model.Animation;
import com.osrs.game.model.Projectile;;

public class SupremeCombatMethod implements CombatMethod {

    private CombatType currentAttackType = CombatType.RANGED;

    @Override
    public void startAnimation(Character character) {
    }

    @Override
    public boolean canMove() {
        return true;
    }

    @Override
    public void prepareAttack(Character character, Character target) {
        NPC npc = character.getAsNpc();

        currentAttackType = CombatType.RANGED;

        if (currentAttackType == CombatType.RANGED) {
            npc.performAnimation(new Animation(2855));
            character.executeProjectile(new Projectile(character, target, 298, 45, 75, 70, 35, 1));
        }
    }


    @Override
    public void finished(Character character, Character target) {

    }

    @Override
    public PendingHit[] getHits(Character character, Character target) {
        return new PendingHit[]{new PendingHit(character, target, this, true, 1)};
    }

    @Override
    public int getAttackSpeed(Character character) {
        return character.getBaseAttackSpeed();
    }

    @Override
    public int getAttackDistance(Character character) {
        return 10;
    }

    @Override
    public CombatType getCombatType() {
        return currentAttackType;
    }

    @Override
    public boolean canAttack(Character character, Character target) {
        return target.isPlayer();
    }

    @Override
    public void handleAfterHitEffects(PendingHit hit) {

    }
}