package me.MiniDigger.Core.AddOn.Kistenkrieg;

import me.MiniDigger.Core.Game.Game;

import me.MiniDigger.CraftCore.Phase.CorePhase;

public class KKPhase extends CorePhase {
	
	public KKPhase(Game game) {
		super(game, null);
	}
	
	@Override
	public String getName() {
		return "KistenKrieg";
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
