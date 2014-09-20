package me.MiniDigger.CraftCore.Feature.Features;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Event.Events.UserDeathEvent;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Map.MapData;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.User.User;
import me.MiniDigger.CraftCore.CoreMain;
import me.MiniDigger.CraftCore.Feature.CoreFeature;

public class LastManStandingFeature extends CoreFeature {
	
	public LastManStandingFeature(Phase phase) {
		super(phase);
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.LASTMANSTANDING;
	}
	
	@Override
	public List<FeatureType> getDependencies() {
		List<FeatureType> result = new ArrayList<>();
		result.add(FeatureType.PVP);
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
	public void onDeath(UserDeathEvent e) {
		if (e.getGame() != null && e.getGame().getIdentifier().equals(getPhase().getGame().getIdentifier())) {
			e.setShouldRespawn(false);
			MapData map = Core.getCore().getMapHandler().getMap(getPhase().getGame().getGameData("Lobby"));
			HashMap<String, Location> locs = map.getLocs(DyeColor.RED);
			Location loc = locs.get(locs.keySet().iterator().next());
			e.getUser().getPlayer().teleport(loc);
			
			getPhase().getGame().leave(e.getUser());
			
			if (getPhase().getGame().getPlayers().size() < 2) {
				Bukkit.getScheduler().runTaskLater((CoreMain) Core.getCore().getInstance(), new Runnable() {
					
					@Override
					public void run() {
						try {
							getPhase().getGame().end(Core.getCore().getUserHandler().get(getPhase().getGame().getPlayers().get(0)));
						} catch (Exception ex) {
							getPhase().getGame().end((User) null);
						}
					}
				}, 10);// till respawn is finished
			} else {
				getPhase().getGame().broadCastMessage(
				        Prefix.getByGameType(getPhase().getGame().getType()).getPrefix().then("Es sind noch ").color(ChatColor.AQUA)
				                .then(getPhase().getGame().getPlayers().size() + "").color(ChatColor.BLUE).then(" Spieler am leben!").color(ChatColor.AQUA));
			}
		}
	}
}
