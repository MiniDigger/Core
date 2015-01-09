package me.MiniDigger.AddOn.UltraSpleef;

import org.bukkit.ChatColor;

import me.MiniDigger.Core.Game.Game;
import me.MiniDigger.Core.Phase.Phase;

import me.MiniDigger.CraftCore.Feature.Features.NoDropFeature;
import me.MiniDigger.CraftCore.Feature.Features.NoPickupFeature;
import me.MiniDigger.CraftCore.Phase.CorePhase;

public class UltraSpleefPhase extends CorePhase {
	
	public UltraSpleefPhase(Game game, Phase next) {
		super(game, next);
	}
	
	@Override
	public String getName() {
		return "UltraSpleef";
	}
	
	@Override
	public String getBarMessage() {
		return ChatColor.RED + "" + ChatColor.MAGIC + "||" + ChatColor.RESET + "" + ChatColor.GOLD + getName() + ChatColor.RED + "" + ChatColor.MAGIC + "||";
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

		addFeature(new NoPickupFeature(this));
		addFeature(new NoDropFeature(this));
	}	
}
