package me.MiniDigger.Core.AddOn.KitPvP;

import me.MiniDigger.Core.Game.Game;

import me.MiniDigger.CraftCore.Phase.CorePhase;

public class KPPhase extends CorePhase {
	
	public KPPhase(Game game) {
		super(game, null);
	}
	
	@Override
	public String getName() {
		return "KitPvP";
	}
	
	@Override
	public boolean displayBar() {
		return true;
	}
	
	@Override
	public boolean displayLevel() {
		return false;
	}
	
	@Override
	public void init() {
		
	}
	
}
