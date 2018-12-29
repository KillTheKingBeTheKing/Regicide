package com.osrs.game.content.combat.method.impl.specials;

import com.osrs.game.content.combat.CombatSpecial;
import com.osrs.game.content.combat.CombatType;
import com.osrs.game.content.combat.hit.PendingHit;
import com.osrs.game.content.combat.method.CombatMethod;
import com.osrs.game.entity.impl.Character;
import com.osrs.game.model.Animation;
import com.osrs.game.model.Graphic;
import com.osrs.game.model.GraphicHeight;
import com.osrs.game.model.Priority;

public class DragonHalberdCombatMethod implements CombatMethod {

    private static final Animation ANIMATION = new Animation(1203, Priority.HIGH);
    private static final Graphic GRAPHIC = new Graphic(282, GraphicHeight.HIGH, Priority.HIGH);

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
        return new PendingHit[]{new PendingHit(character, target, this, true, 1), new PendingHit(character, target, this, true, 0)};
    }

    @Override
    public boolean canAttack(Character character, Character target) {
        return true;
    }

    @Override
    public void prepareAttack(Character character, Character target) {
        CombatSpecial.drain(character, CombatSpecial.DRAGON_HALBERD.getDrainAmount());
    }

    @Override
    public int getAttackSpeed(Character character) {
        return character.getBaseAttackSpeed();
    }

    @Override
    public int getAttackDistance(Character character) {
        return 2;
    }

    @Override
    public void startAnimation(Character character) {
        character.performAnimation(ANIMATION);
        character.performGraphic(GRAPHIC);
    }

    @Override
    public void finished(Character character, Character target) {

    }

    @Override
    public void handleAfterHitEffects(PendingHit hit) {
    }
}