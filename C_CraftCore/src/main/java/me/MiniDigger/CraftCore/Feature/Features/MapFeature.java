package me.MiniDigger.CraftCore.Feature.Features;

import java.util.ArrayList;
import java.util.List;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Map.MapData;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.CraftCore.Feature.CoreFeature;

public class MapFeature extends CoreFeature {
	
	private MapData	map;
	
	public MapFeature(Phase phase, String map) {
		super(phase);
		setMap(map);
	}
	
	public MapData getMap(){
		return map;
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.MAP;
	}
	
	@Override
	public List<FeatureType> getDependencies() {
		return new ArrayList<>();
	}
	
	@Override
	public List<FeatureType> getSoftDependencies() {
		List<FeatureType> result = new ArrayList<>();
		result.add(FeatureType.VOTE);
		return result;
	}
	
	@Override
	public List<FeatureType> getIncompabilities() {
		return new ArrayList<>();
	}
	
	@Override
	public void start() {
		if (map != null) {
			map.clearLocs();
		}
	}
	
	@Override
	public void end() {
		map = null;
	}
	
	public void setMap(String map) {
		if (map == null) {
			return;
		}
		if (Core.getCore().getMapHandler().getMap(map) == null) {
			Core.getCore().getWorldHandler().copyWorld(map);
			Core.getCore().getWorldHandler().loadWorld(map);
		}
		
		this.map = Core.getCore().getMapHandler().getMap(map);
	}
}