package me.MiniDigger.CraftCore.Feature.Features;

import java.util.ArrayList;
import java.util.List;

import me.MiniDigger.Core.Event.Events.UserDamageEvent;
import me.MiniDigger.Core.Event.Events.UserDeathEvent;


import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.CraftCore.Feature.CoreFeature;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;

public class PvPFeature extends CoreFeature {
	
	private boolean	pvpEnabled;
	
	public PvPFeature(Phase phase, boolean pvpEnabled) {
		super(phase);
		this.pvpEnabled = pvpEnabled;
	}
	
	public boolean isPvPEnabled(){
		return pvpEnabled;
	}
	
	public void setPvPEnabled(boolean pvpenabled){
		this.pvpEnabled = pvpenabled;
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.PVP;
	}
	
	@Override
	public List<FeatureType> getDependencies() {
		List<FeatureType> result = new ArrayList<>();
		result.add(FeatureType.MAP);
		return result;
	}
	
	@Override
	public List<FeatureType> getSoftDependencies() {
		return new ArrayList<>();
	}
	
	@Override
	public List<FeatureType> getIncompabilities() {
		return new ArrayList<>();
	}
	
	@Override
	public void start() {
		
	}
	
	@Override
	public void end() {
		
	}
	
	@EventHandler
	public void onDmg(UserDamageEvent e) {
		if (e.getGame() != null && e.getGame().equals(getPhase().getGame())) {
			if (!pvpEnabled) {
				if (e.getDamager() != null) {
					e.setCancelled(true);
					return;
				}
			}
		}
	}
	
	@EventHandler
	public void onDeath(UserDeathEvent e) {
		if (e.getGame() != null && e.getGame().getIdentifier().equals(getPhase().getGame().getIdentifier())) {
			if (e.getKiller() != null) {
				getPhase().getGame().broadCastMessage(
				        Prefix.getByGameType(getPhase().getGame().getType()).getPrefix().then("Der Spieler ").color(ChatColor.AQUA).then(e.getUser().getDisplayName())
				                .color(ChatColor.DARK_BLUE).then(" wurde von ").color(ChatColor.AQUA).then(e.getKiller().getDisplayName()).color(ChatColor.DARK_BLUE)
				                .then(" getötet!").color(ChatColor.AQUA));
			} else {
				getPhase().getGame().broadCastMessage(
				        Prefix.getByGameType(getPhase().getGame().getType()).getPrefix().then("Der Spieler ").color(ChatColor.AQUA).then(e.getUser().getDisplayName())
				                .color(ChatColor.DARK_BLUE).then(" ist gestorben!").color(ChatColor.AQUA));
			}
		}
	}
	
}