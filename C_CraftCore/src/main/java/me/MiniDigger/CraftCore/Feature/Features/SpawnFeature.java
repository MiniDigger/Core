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
 * Written by Martin Benndorf <admin@minidigger.me>, 2013-2014 and others
 */
package me.MiniDigger.CraftCore.Feature.Features;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Map.MapData;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Event.Events.CoreUserDeathEvent;
import me.MiniDigger.CraftCore.Event.Events.CoreUserJoinGameEvent;
import me.MiniDigger.CraftCore.Feature.CoreFeature;

public class SpawnFeature extends CoreFeature {
	
	private DyeColor	locKey;
	private boolean	 spawn;
	
	public SpawnFeature(final Phase phase, final boolean spawn) {
		super(phase);
		this.spawn = spawn;
		locKey = DyeColor.RED;
	}
	
	public SpawnFeature(final Phase phase, final boolean spawn, final DyeColor locKey) {
		this(phase, spawn);
		this.locKey = locKey;
	}
	
	private List<Location>	usedSpawns;
	private Location[]	   spawns;
	
	public boolean spawn() {
		return spawn;
	}
	
	public void setSpawn(final boolean spawn) {
		this.spawn = spawn;
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.SPAWN;
	}
	
	@Override
	public List<FeatureType> getDependencies() {
		final List<FeatureType> result = new ArrayList<>();
		result.add(FeatureType.MAP);
		return result;
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
		usedSpawns = new ArrayList<>();
		final MapData data = ((MapFeature) getPhase().getFeature(FeatureType.MAP)).getMap();
		final HashMap<String, Location> spawns = data.getLocs(locKey);
		this.spawns = spawns.values().toArray(new Location[spawns.values().size()]);
		if (spawn) {
			for (final UUID id : getPhase().getGame().getPlayers()) {
				spawn(Core.getCore().getUserHandler().get(id));
			}
		}
	}
	
	@Override
	public void end() {
		usedSpawns = null;
		spawns = null;
	}
	
	public Location spawn(final User user) {
		if (usedSpawns.size() >= spawns.length) {
			usedSpawns = new ArrayList<>();
		}
		int counter = 0;// To prevent invinitiv loop
		while (true) {
			final Location loc = spawns[Core.getCore().getRandomUtil().nextInt(spawns.length)];
			if (loc == null) {
				counter++;
				continue;
			}
			if (usedSpawns.contains(loc)) {
				if (counter >= 100) {
					usedSpawns = new ArrayList<>();
					counter = 0;
				} else {
					counter++;
				}
				continue;
			}
			usedSpawns.add(loc);
			try {
				user.getPlayer().teleport(loc);
			} catch (final Exception ex) {
				new BukkitRunnable() {
					
					@Override
					public void run() {
						try {
							user.getPlayer().teleport(loc);
						} catch (final Exception ex) {
							System.out.println("respawn failed after second try");
						}
					}
				}.runTaskLater((Plugin) Core.getCore().getInstance(), 20 * 2);
			}
			return loc;
		}
	}
	
	@EventHandler
	public void onJoin(final CoreUserJoinGameEvent e) {
		if (e.getGame().getIdentifier().equals(getPhase().getGame().getIdentifier())) {
			spawn(e.getUser());
		}
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onDeath(final CoreUserDeathEvent e) {
		if (!e.shouldRespawn()) {
			return;
		}
		if (e.getGame() != null && e.getGame().getIdentifier().equals(getPhase().getGame().getIdentifier())) {
			spawn(e.getUser());
		}
	}
	
	@EventHandler
	public void onRespawn(final PlayerRespawnEvent e) {
		final User user = Core.getCore().getUserHandler().get(e.getPlayer().getUniqueId());
		if (getPhase().getGame().getPlayers().contains(user.getUUID())) {
			e.setRespawnLocation(spawn(user));
		}
	}
}
