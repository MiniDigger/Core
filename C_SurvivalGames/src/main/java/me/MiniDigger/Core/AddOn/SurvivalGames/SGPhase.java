package me.MiniDigger.Core.AddOn.SurvivalGames;

import me.MiniDigger.Core.Game.Game;

import me.MiniDigger.CraftCore.Phase.CorePhase;

public class SGPhase extends CorePhase {
	
	public SGPhase(final Game game) {
		super(game, null);
	}
	
	@Override
	public String getName() {
		return "SG";
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
