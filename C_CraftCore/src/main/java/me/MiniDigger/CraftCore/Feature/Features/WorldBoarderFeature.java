package me.MiniDigger.CraftCore.Feature.Features;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.WorldBorder;

import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Map.MapData;
import me.MiniDigger.Core.Phase.Phase;

import me.MiniDigger.CraftCore.Feature.CoreFeature;

public class WorldBoarderFeature extends CoreFeature {
	
	private WorldBorder	b;
	private boolean	    reset;
	
	public WorldBoarderFeature(Phase phase, boolean reset) {
		super(phase);
		this.reset = reset;
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.WORLDBOARDER;
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
	
	public void fakeStart(Location center, long time, double size) {
		final MapData m = ((MapFeature) getPhase().getFeature(FeatureType.MAP)).getMap();
		b = Bukkit.getWorld(m.getName()).getWorldBorder();
		b.setCenter(center);
		b.setSize(size, time);
		b.setDamageBuffer(5.0);
		b.setWarningTime(20);
		b.setWarningDistance(5);
	}
	
	@Override
	public void start() {
		
	}
	
	@Override
	public void end() {
		if (reset) {
			b.reset();
		}
	}
	
	public WorldBorder getWorldBoarder() {
		return b;
	}
}
