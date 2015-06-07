package me.MiniDigger.Core.AddOn.Infected;

import me.MiniDigger.Core.Game.Game;

import me.MiniDigger.CraftCore.Phase.CorePhase;

public class IFPhase extends CorePhase {
	
	public IFPhase(Game game) {
		super(game, null);
	}
	
	@Override
	public String getName() {
		return "Infected";
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
