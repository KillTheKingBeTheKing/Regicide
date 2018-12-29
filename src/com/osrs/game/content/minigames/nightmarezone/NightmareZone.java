package com.osrs.game.content.minigames.nightmarezone;

import com.osrs.game.entity.impl.player.Player;

public class NightmareZone {

	Player player;
	private int cofferValue = 0;
	
	public NightmareZone(Player player) {
		this.player = player;
	}

	public boolean hasCustomRumble() {
		return false;
	}

	public String getCustomDifficulty() {
		return "normal";
	}

	public int getCoffer() {
		return cofferValue;
	}
	public void setCoffer(int value) {
		this.cofferValue = value;
	}

	
}
