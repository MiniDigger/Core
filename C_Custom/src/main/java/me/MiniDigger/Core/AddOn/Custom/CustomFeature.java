package me.MiniDigger.Core.AddOn.Custom;

import java.util.ArrayList;
import java.util.List;

import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.CraftCore.Feature.CoreFeature;

public class CustomFeature extends CoreFeature {

	public CustomFeature(Phase phase) {
		super(phase);
	}

	@Override
	public FeatureType getType() {
		return FeatureType.CUSTOM;
	}

	@Override
	public List<FeatureType> getDependencies() {
		return new ArrayList<>();
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

}
