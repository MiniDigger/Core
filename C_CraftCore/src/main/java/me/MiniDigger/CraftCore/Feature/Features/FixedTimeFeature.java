package me.MiniDigger.CraftCore.Feature.Features;

import java.util.ArrayList;
import java.util.List;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.CraftCore.Feature.CoreFeature;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

public class FixedTimeFeature extends CoreFeature {
	
	private final long	time;
	private String	   world;
	private BukkitTask	task;
	
	public FixedTimeFeature(final Phase phase, final long time) {
		super(phase);
		this.time = time;
		
	}
	
	public BukkitTask getTask() {
		return task;
	}
	
	public void setTask(final BukkitTask task) {
		this.task = task;
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.FIXEDTIME;
	}
	
	@Override
	public List<FeatureType> getDependencies() {
		final List<FeatureType> result = new ArrayList<>();
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
		final MapFeature m = (MapFeature) getPhase().getFeature(FeatureType.MAP);
		world = m.getMap().getName();
		
		final World w = Bukkit.getWorld(world);
		task = Bukkit.getScheduler().runTaskTimer((Plugin) Core.getCore().getInstance(), new Runnable() {
			
			@Override
			public void run() {
				w.setTime(time);
			}
		}, 2, 2);
		
	}
	
	@Override
	public void end() {
		task.cancel();
	}
	
}
