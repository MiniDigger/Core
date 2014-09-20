package me.MiniDigger.CraftCore.Feature.Features;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.User.User;
import me.MiniDigger.CraftCore.Feature.CoreFeature;

public class TwoPlayerFeature extends CoreFeature {
	
	private UUID	one;
	private UUID	two;
	
	public TwoPlayerFeature(Phase phase) {
		super(phase);
		try {
			one = getPhase().getGame().getPlayers().get(0);
		} catch (Exception ex) {
			Core.getCore().getInstance().error("Well, play alone.");
		}
		try {
			two = getPhase().getGame().getPlayers().get(1);
		} catch (Exception ex) {
			Core.getCore().getInstance().error("Well, play alone.");
			two = one;
		}
	}
	
	public UUID getOne(){
		return one;
	}
	
	public UUID getTwo(){
		return two;
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.TWOPLAYER;
	}
	
	public UUID getOther(UUID id) {
		if (id.equals(one)) {
			return two;
		} else {
			return one;
		}
	}
	
	public User getOther(User user) {
		return Core.getCore().getUserHandler().get(getOther(user.getUUID()));
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
	
	public boolean isOne(UUID uuid) {
		return one.equals(uuid);
	}
	
	public boolean isTwo(UUID uuid) {
		return two.equals(uuid);
	}
}
