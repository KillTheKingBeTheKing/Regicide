package com.osrs.game.content;

import com.osrs.game.model.Skill;

public class Requirement {
	private Skill skill;
	private int level;
	
	public Requirement(Skill skill, int level) {
		this.skill = skill;
		this.level = level;
	}
	
	public Skill getSkill() {
		return skill;
	}
	
	public int getLevel() {
		return level;
	}
}
