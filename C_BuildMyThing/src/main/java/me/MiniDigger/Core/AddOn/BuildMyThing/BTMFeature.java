package me.MiniDigger.Core.AddOn.BuildMyThing;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;

import me.MiniDigger.CraftCore.Feature.CoreFeature;

public class BTMFeature extends CoreFeature {
	
	public BTMFeature(Phase phase) {
		super(phase);
	}
	
	private String[]	words	= new String[] { "banane", "apfel" };
	private UUID	   builder;
	private List<UUID>	builded	= new ArrayList<UUID>();
	
	@Override
	public FeatureType getType() {
		return FeatureType.BTM;
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
		
	}
	
	@Override
	public void end() {
		
	}
	
}
