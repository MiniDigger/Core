package me.MiniDigger.Core.AddOn.Crank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Feature.CoreFeature;

public class CrankFeature extends CoreFeature {
	
	private int	                          crankTime;
	private HashMap<UUID, BukkitRunnable>	timers	= new HashMap<UUID, BukkitRunnable>();
	
	public CrankFeature(Phase phase, int crankTime) {
		super(phase);
		this.crankTime = crankTime;
	}
	
	public int getCrankTime() {
		return crankTime;
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.CRANK;
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
		for (final UUID id : getPhase().getGame().getPlayers()) {
			reset(id);
		}
	}
	
	public void reset(final UUID id) {
		try {
			timers.get(id).cancel();
		} catch (Exception ex) {}
		BukkitRunnable timer = new BukkitRunnable() {
			
			private int	time	= crankTime;
			
			@Override
			public void run() {
				time--;
				User user = Core.getCore().getUserHandler().get(id);
				if (time == 0) {
					explode(id);
					cancel();
				} else {
					user.getPlayer().setLevel(time);
					user.getPlayer().setExp(crankTime / time);
				}
			}
		};
		timer.runTaskTimer((Plugin) Core.getCore().getInstance(), 1, 1);
		timers.put(id, timer);
	}
	
	public void explode(UUID id) {
		System.out.println("boom");
	}
	
	@Override
	public void end() {
		timers = null;
	}
}
