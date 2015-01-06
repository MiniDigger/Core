package me.MiniDigger.AddOn.UltraSpleef;

import java.util.ArrayList;
import java.util.List;

import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;

import me.MiniDigger.CraftCore.Feature.CoreFeature;

public class UltraSpleefFeature extends CoreFeature {
	
	public UltraSpleefFeature(Phase phase) {
		super(phase);
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.ULTRASPLEEF;
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
