package com.osrs.game.content.combat.method;

import com.osrs.game.content.combat.CombatType;
import com.osrs.game.content.combat.hit.PendingHit;
import com.osrs.game.entity.impl.Character;

/**
 * Represents a combat method.
 */
public interface CombatMethod {
    abstract boolean canAttack(Character character, Character target);
    abstract boolean canMove();
    abstract void startAnimation(Character character);
    abstract void prepareAttack(Character character, Character target);
    abstract int getAttackSpeed(Character character);
    abstract int getAttackDistance(Character character);
    abstract CombatType getCombatType();
    abstract PendingHit[] getHits(Character character, Character target);
    abstract void finished(Character character, Character target);
    abstract void handleAfterHitEffects(PendingHit hit);
}