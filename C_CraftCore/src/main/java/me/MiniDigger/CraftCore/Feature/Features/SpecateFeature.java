/**
 * █████████████████████████████████████████████████████████████████████████████████████████████████████████████████
 * █░░░░░░░░░░░░░░█░░░░░░██░░░░░░█░░░░░░░░░░░░░░████░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░░░███░░░░░░░░░░░░░░█
 * █░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █░░░░░░▄▀░░░░░░█░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░████████████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░████░░▄▀░░███░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░▄▀░░░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░████████████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░██░░▄▀░░█████░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░██░░▄▀░░░░░░█░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░░░░░█████░░░░░░██░░░░░░█░░░░░░░░░░░░░░████░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░██░░░░░░░░░░█░░░░░░░░░░░░░░█
 * █████████████████████████████████████████████████████████████████████████████████████████████████████████████████
 *
 * Copyright © MiniDigger and others - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Martin Benndorf <admin@minidigger.me>, 2013-2015 and others
 */
package me.MiniDigger.CraftCore.Feature.Features;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.DyeColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Map.MapData;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Event.Events.CoreUserDeathEvent;
import me.MiniDigger.CraftCore.Event.Events.CoreUserJoinGameEvent;
import me.MiniDigger.CraftCore.Event.Events.CoreUserLeaveGameEvent;
import me.MiniDigger.CraftCore.Feature.CoreFeature;

public class SpecateFeature extends CoreFeature {

	private Location loc;

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
		final MapData map = Core.getCore().getMapHandler().getMap(getPhase().getGame().getGameData("VoteWinner"));
		final HashMap<String, Location> locs = map.getLocs(DyeColor.RED);
		if (locs != null) {
			loc = locs.get(locs.keySet().iterator().next());
		}
	}

	@Override
	public void end() {

	}

	public boolean isSpec(final UUID id) {
		return getPhase().getGame().getSpecs().contains(id);
	}

	public void spec(final User user) {
		if (!getPhase().getGame().getSpecs().contains(user.getUUID())) {
			getPhase().getGame().leave(user);
			getPhase().getGame().addSpec(user.getUUID());
			user.getPlayer().setGameMode(GameMode.SPECTATOR);

			Core.getCore().getScoreboardHandler().getBoard(user.getUUID()).clear();

			Core.getCore().getTaskHandler().runTaskLater(new BukkitRunnable() {

				@Override
				public void run() {
					if (loc != null) {
						user.getPlayer().teleport(loc);
					}
				}
			}, 10, getPhase());// Wait for respawn

			Prefix.SPEC.getPrefix().then("Du bist jetzt Zuschauer!").send(user.getPlayer());
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onDeath(final CoreUserDeathEvent e) {
		if (!e.shouldRespawn()) {
			spec(e.getUser());
		}
	}

	@EventHandler
	public void respawn(final PlayerRespawnEvent e) {
		if (getPhase().getGame().getSpecs().contains(e.getPlayer().getUniqueId())) {
			if (loc != null) {
				e.setRespawnLocation(loc);
				e.getPlayer().teleport(loc);
				Core.getCore().getInstance().debug("set respawn loc");
			} else {
				Core.getCore().getInstance().debug("respawn loc = null");
			}
		}
	}

	public void remSpec(final User user) {
		getPhase().getGame().remSpec(user.getUUID());
		user.getPlayer().setGameMode(GameMode.SURVIVAL);
	}

	@EventHandler
	public void onJoin(final CoreUserJoinGameEvent e) {
		if (e.getGame().getIdentifier() == getPhase().getGame().getIdentifier()) {
			if (!getPhase().getGame().allowJoin()) {
				spec(e.getUser());
			} else {
				remSpec(e.getUser());
			}
		}
	}

	@EventHandler
	public void onLeave(final CoreUserLeaveGameEvent e) {
		if (isSpec(e.getUser().getUUID())) {
			remSpec(e.getUser());
		}
	}
}
