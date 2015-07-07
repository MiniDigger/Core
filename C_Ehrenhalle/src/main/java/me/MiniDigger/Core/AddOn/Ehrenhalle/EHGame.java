package me.MiniDigger.Core.AddOn.Ehrenhalle;


import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Game.GameType;

import me.MiniDigger.CraftCore.Feature.Features.MapFeature;
import me.MiniDigger.CraftCore.Game.CoreGame;

public class EHGame extends CoreGame {
	
	@Override
	public GameType getType() {
		return GameType.EH;
	}
	
	@Override
	public void init() {
		super.maxplayers = 1000;
		
		setGameData("Lobby", "Ehrenhalle");
		
		final EHPhase hub = new EHPhase();
		((MapFeature) hub.getFeature(FeatureType.MAP)).setMap("Ehrenhalle");
		
		setPhase(hub);
		
		super.init();
	}
	
	@Override
	public void start() {
		super.start();
		getPhase().init();
		getPhase().startPhase();
	}
}
