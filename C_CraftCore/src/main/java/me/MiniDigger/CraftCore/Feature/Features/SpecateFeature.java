package me.MiniDigger.CraftCore.Feature.Features;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.event.EventHandler;

import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;

import me.MiniDigger.CraftCore.Event.Events.CoreUserJoinGameEvent;
import me.MiniDigger.CraftCore.Event.Events.CoreUserLeaveGameEvent;
import me.MiniDigger.CraftCore.Feature.CoreFeature;

public class SpecateFeature extends CoreFeature {
	
	public SpecateFeature(final Phase phase) {
		super(phase);
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.SPEC;
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
	
	public boolean isSpec(final UUID id) {
		return getPhase().getGame().getSpecs().contains(id);
	}
	
	public void spec(final UUID id) {
		getPhase().getGame().addSpec(id);
	}
	
	@EventHandler
	public void onJoin(final CoreUserJoinGameEvent e) {
		
	}
	
	@EventHandler
	public void onLeave(final CoreUserLeaveGameEvent e) {
		if (isSpec(e.getUser().getUUID())) {
			getPhase().getGame().remSpec(e.getUser().getUUID());
		}
	}
}
