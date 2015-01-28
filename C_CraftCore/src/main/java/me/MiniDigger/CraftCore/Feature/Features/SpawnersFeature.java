package me.MiniDigger.CraftCore.Feature.Features;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;

import me.MiniDigger.CraftCore.Feature.CoreFeature;

public class SpawnersFeature extends CoreFeature {
	
	private List<SpawnerFeature>	f	= new ArrayList<SpawnerFeature>();
	
	public SpawnersFeature(Phase phase) {
		super(phase);
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.SPAWNERS;
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
		for (SpawnerFeature f : this.f) {
			Bukkit.getPluginManager().registerEvents(f, Core.getCore().getInstance());
			f.start();
		}
	}
	
	@Override
	public void end() {
		for (SpawnerFeature f : this.f) {
			HandlerList.unregisterAll(f);
			f.end();
		}
	}
	
	public void add(SpawnerFeature e) {
		f.add(e);
	}
}
