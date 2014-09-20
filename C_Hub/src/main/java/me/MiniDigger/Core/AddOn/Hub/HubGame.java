package me.MiniDigger.Core.AddOn.Hub;

import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Game.GameType;
import me.MiniDigger.CraftCore.Feature.Features.MapFeature;
import me.MiniDigger.CraftCore.Game.CoreGame;

import org.bukkit.ChatColor;


public class HubGame extends CoreGame {
	
	@Override
	public GameType getType() {
		return GameType.LOBBY;
	}
	
	@Override
	public void init() {
		setGameData("Lobby", "Spawn");
		
		HubPhase hub = new HubPhase(this, null, null);
		hub.setBarMsg(ChatColor.RED + "" + ChatColor.MAGIC + "abc" + ChatColor.RESET + ChatColor.GOLD + " Hub " + ChatColor.RESET + ChatColor.RED + ChatColor.MAGIC
		        + "abc");
		((MapFeature) hub.getFeature(FeatureType.MAP)).setMap("Spawn");
		
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
