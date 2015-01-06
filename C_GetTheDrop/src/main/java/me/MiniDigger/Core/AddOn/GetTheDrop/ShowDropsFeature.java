package me.MiniDigger.Core.AddOn.GetTheDrop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.DisplaySlot;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Scoreboard.Scoreboard;

import me.MiniDigger.CraftCore.Feature.CoreFeature;
import me.MiniDigger.CraftCore.Scoreboard.CoreScoreboard;
import me.MiniDigger.CraftCore.Scoreboard.CoreScoreboardLine;

public class ShowDropsFeature extends CoreFeature {
	
	private BukkitTask	       task;
	private Map<UUID, Integer>	drops	= new HashMap<UUID, Integer>();
	
	public ShowDropsFeature(Phase phase) {
		super(phase);
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.SHOWDROPS;
	}
	
	@Override
	public List<FeatureType> getDependencies() {
		return new ArrayList<FeatureType>();
	}
	
	@Override
	public List<FeatureType> getSoftDependencies() {
		return new ArrayList<FeatureType>();
	}
	
	@Override
	public List<FeatureType> getIncompabilities() {
		return new ArrayList<FeatureType>();
	}
	
	@Override
	public void start() {
		scoreboard();
	}
	
	@Override
	public void end() {
		if (task != null) {
			task.cancel();
			task = null;
		}
		
		Core.getCore().getScoreboardHandler().clearAll();
	}
	
	@EventHandler
	public void onPickup(PlayerPickupItemEvent e) {
		if (getPhase().getGame().getPlayers().contains(e.getPlayer().getUniqueId())) {
			int i = 0;
			if (drops.containsKey(e.getPlayer().getUniqueId())) {
				i = drops.remove(e.getPlayer().getUniqueId());
			}
			i++;
			drops.put(e.getPlayer().getUniqueId(), i);
		}
	}
	
	public void scoreboard() {
		if (task != null) {
			task.cancel();
			task = null;
		}
		final Scoreboard b = new CoreScoreboard(ChatColor.GOLD + "Items");
		
		task = new BukkitRunnable() {
			
			@Override
			public void run() {
				for (UUID id : drops.keySet()) {
					try {
						b.addLine(new CoreScoreboardLine(drops.get(id).intValue(), Core.getCore().getUserHandler().get(id).getDisplayName(), DisplaySlot.SIDEBAR));
					} catch (Exception ex) {
						
					}
				}
				for (UUID id : getPhase().getGame().getPlayers()) {
					try {
						Core.getCore().getScoreboardHandler().addToPlayer(b, Bukkit.getPlayer(id));
					} catch (Exception ex) {
						
					}
				}
			}
		}.runTaskTimer(Core.getCore().getInstance(), 20, 20);
		
	}
	
}
