package com.osrs.game.content.minigames.wintertodt;

import com.osrs.game.content.combat.CombatType;
import com.osrs.game.content.combat.hit.PendingHit;
import com.osrs.game.content.combat.method.CombatMethod;
import com.osrs.game.entity.impl.Character;
import com.osrs.game.model.Animation;

public class WintertodtPyroMancer implements CombatMethod {

    public static final int[] REJUVENATION_POTIONS = new int[] { 20699, 20700, 20701, 20702 };
    public static final int ATTACK_ANIM = 4432;
    public static final int SIT_DOWN_ANIM = 6295;

    public static final String[][] CHAT_STRINGS = new String[][] {
            {
                    "Light this brazier!"
            },
            {
                    "Fix this brazier!"
            },
            {
                    "Yemalo shi cardito!"
            },
            {
                    "Arg, it got me!",
                    "Mummy!",
                    "My flame burns low.",
                    "We are doomed.",
                    "I think I'm dying.",
                    "Ugh, help me!"
            },
            {
                    "We can rest for a time."
            }
    };

    @Override
    public boolean canAttack(Character character, Character target) {
        return false;
    }

    @Override
    public boolean canMove() {
        return true;
    }

    @Override
    public void startAnimation(Character character) {
        character.getAsNpc().performAnimation(new Animation(ATTACK_ANIM));
        if(character.getAsNpc().getHitpoints() < 0) {
            character.performAnimation(new Animation(SIT_DOWN_ANIM));
        }
    }

    @Override
    public void prepareAttack(Character character, Character target) {

    }

    @Override
    public int getAttackSpeed(Character character) {
        return 0;
    }

    @Override
    public int getAttackDistance(Character character) {
        return 0;
    }

    @Override
    public CombatType getCombatType() {
        return null;
    }

    @Override
    public PendingHit[] getHits(Character character, Character target) {
        return new PendingHit[0];
    }

    @Override
    public void finished(Character character, Character target) {

    }

    @Override
    public void handleAfterHitEffects(PendingHit hit) {

    }
}
