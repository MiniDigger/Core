package me.MiniDigger.CraftCore.Feature.Features;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Event.Events.UserJoinGameEvent;
import me.MiniDigger.Core.Event.Events.UserLeaveGameEvent;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.CraftCore.Feature.CoreFeature;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;

public class NoNameTagFeature extends CoreFeature {
	
	public NoNameTagFeature(final Phase phase) {
		super(phase);
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.NONAMETAG;
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
		for (final UUID id : getPhase().getGame().getPlayers()) {
			Core.getCore().getNametagHandler().hideTag(Bukkit.getPlayer(id));
		}
	}
	
	@Override
	public void end() {
		for (final UUID id : getPhase().getGame().getPlayers()) {
			Core.getCore().getNametagHandler().showNametag(Bukkit.getPlayer(id));
		}
	}
	
	@EventHandler
	public void onJoin(final UserJoinGameEvent e) {
		if (e.getGame().getIdentifier().equals(getPhase().getGame().getIdentifier())) {
			Core.getCore().getNametagHandler().hideTag(Bukkit.getPlayer(e.getUser().getUUID()));
		}
	}
	
	@EventHandler
	public void onLeave(final UserLeaveGameEvent e) {
		if (e.getGame().getIdentifier().equals(getPhase().getGame().getIdentifier())) {
			Core.getCore().getNametagHandler().showNametag(Bukkit.getPlayer(e.getUser().getUUID()));
		}
	}
	
}
