package me.MiniDigger.CraftCore.Feature.Features;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Material;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.User.User;
import me.MiniDigger.CraftCore.Feature.CoreFeature;

public class BuildFeature extends CoreFeature {
	
	private Material[]	allowed;
	
	public BuildFeature(Phase phase, Material... allowed) {
		super(phase);
		this.allowed = allowed;
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.BUILD;
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
		for (UUID id : getPhase().getGame().getPlayers()) {
			User user = Core.getCore().getUserHandler().get(id);
			Core.getCore().getBuildHandler().setBuilder(user, true);
			Core.getCore().getBuildHandler().allow(user, allowed);
		}
	}
	
	@Override
	public void end() {
		for (UUID id : getPhase().getGame().getPlayers()) {
			User user = Core.getCore().getUserHandler().get(id);
			Core.getCore().getBuildHandler().setBuilder(user, false);
			Core.getCore().getBuildHandler().disallow(user, (Material) null);
		}
	}
	
}
