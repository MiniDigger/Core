package me.MiniDigger.Core.AddOn.Ehrenhalle;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Event.Events.CoreUserJoinGameEvent;
import me.MiniDigger.CraftCore.Event.Events.CoreUserLeaveGameEvent;
import me.MiniDigger.CraftCore.Feature.CoreFeature;

public class EHFeature extends CoreFeature {

	public EHFeature(final Phase phase) {
		super(phase);
	}

	@Override
	public FeatureType getType() {
		return FeatureType.EH;
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
		for (final UUID id : getPhase().getGame().getPlayers()) {
			final User u = Core.getCore().getUserHandler().get(id);
			u.getPlayer().setGameMode(GameMode.SPECTATOR);
			u.getPlayer().teleport(((EHPhase) getPhase()).getS().getSpawn());
		}
	}

	@Override
	public void end() {
		for (final UUID id : getPhase().getGame().getPlayers()) {
			final User u = Core.getCore().getUserHandler().get(id);
			u.getPlayer().setGameMode(GameMode.SURVIVAL);
		}
	}

	@EventHandler
	public void onJoin(final CoreUserJoinGameEvent e) {
		if (e.getGame().getIdentifier() == getPhase().getGame().getIdentifier()) {
			e.getUser().getPlayer().setGameMode(GameMode.SPECTATOR);
			e.getUser().getPlayer().teleport(((EHPhase) getPhase()).getS().getSpawn());
		}
	}

	@EventHandler
	public void onQuit(final CoreUserLeaveGameEvent e) {
		if (e.getGame().getIdentifier() == getPhase().getGame().getIdentifier()) {
			e.getUser().getPlayer().setGameMode(GameMode.SURVIVAL);
			e.getUser().getPlayer().teleport(((EHPhase) getPhase()).getS().getSpawn());
		}
	}
}
