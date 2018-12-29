package com.osrs.net.packet.impl.commands.developer;

import com.osrs.game.content.combat.WeaponInterfaces;
import com.osrs.game.content.skill.SkillManager;
import com.osrs.game.entity.impl.player.Player;
import com.osrs.game.model.Flag;
import com.osrs.game.model.Skill;
import com.osrs.net.packet.impl.commands.Command;

public class Master extends Command {

	@Override
	public void execute(Player player, String input) {
		for(Skill skill : Skill.values()) {
			int level = SkillManager.getMaxAchievingLevel(skill);
			player.getSkillManager().setCurrentLevel(skill, level).setMaxLevel(skill, level).setExperience(skill, SkillManager.getExperienceForLevel(level));
		}
		WeaponInterfaces.assign(player);
		player.getUpdateFlag().flag(Flag.APPEARANCE);
	}

}
