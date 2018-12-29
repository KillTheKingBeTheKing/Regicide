package com.osrs.game.content.combat.method.impl.specials;

import com.osrs.game.content.combat.CombatFactory;
import com.osrs.game.content.combat.CombatSpecial;
import com.osrs.game.content.combat.CombatType;
import com.osrs.game.content.combat.hit.PendingHit;
import com.osrs.game.content.combat.method.CombatMethod;
import com.osrs.game.entity.impl.Character;
import com.osrs.game.model.Animation;
import com.osrs.game.model.Graphic;
import com.osrs.game.model.GraphicHeight;
import com.osrs.game.model.Priority;
import com.osrs.game.task.impl.CombatPoisonEffect.PoisonType;
import com.osrs.util.Misc;

public class AbyssalTentacleCombatMethod implements CombatMethod {

    private static final Animation ANIMATION = new Animation(1658, Priority.HIGH);
    private static final Graphic GRAPHIC = new Graphic(181, GraphicHeight.HIGH, Priority.HIGH);

    @Override
    public CombatType getCombatType() {
        return CombatType.MELEE;
    }

    @Override
    public boolean canMove() {
        return true;
    }

    @Override
    public PendingHit[] getHits(Character character, Character target) {
        return new PendingHit[]{new PendingHit(character, target, this, true, 0)};
    }

    @Override
    public boolean canAttack(Character character, Character target) {
        return true;
    }

    @Override
    public void prepareAttack(Character character, Character target) {
        CombatSpecial.drain(character, CombatSpecial.ABYSSAL_TENTACLE.getDrainAmount());
    }

    @Override
    public int getAttackSpeed(Character character) {
        return character.getBaseAttackSpeed();
    }

    @Override
    public int getAttackDistance(Character character) {
        return 1;
    }

    @Override
    public void startAnimation(Character character) {
        character.performAnimation(ANIMATION);
    }

    @Override
    public void finished(Character character, Character target) {

    }

    @Override
    public void handleAfterHitEffects(PendingHit hit) {
        Character target = hit.getTarget();

        if (target.getHitpoints() <= 0) {
            return;
        }
        target.performGraphic(GRAPHIC);
        CombatFactory.freeze(target, 10);
        if (Misc.getRandom(100) < 50) {
            CombatFactory.poisonEntity(target, PoisonType.EXTRA);
        }
    }
}