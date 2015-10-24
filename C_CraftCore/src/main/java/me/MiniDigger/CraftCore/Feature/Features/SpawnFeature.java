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

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

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
	private boolean		spawn;

	public SpawnFeature(final Phase phase, final boolean spawn) {
		super(phase);
		this.spawn = spawn;
		locKey = DyeColor.RED;
	}

	public SpawnFeature(final Phase phase, final boolean spawn, final DyeColor locKey) {
		this(phase, spawn);
		this.locKey = locKey;
	}

	private List<Vector>	usedSpawns;
	private Location[]		spawns;

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
		if (data == null) {
			Core.getCore().getInstance().debug("no map to spawn!");
			return;
		}
		final HashMap<String, Location> spawns = data.getLocs(locKey);
		if (spawns == null) {
			Core.getCore().getInstance().debug("spawns null in spawnfeature (lockey: " + locKey + ", map " + data.getName() + "{" + data.getOldName() + "}");
			return;
		}
		this.spawns = spawns.values().toArray(new Location[spawns.values().size()]);
		if (this.spawns != null || this.spawns.length == 0) {
			if (spawn) {
				for (final UUID id : getPhase().getGame().getPlayers()) {
					spawn(Core.getCore().getUserHandler().get(id));
				}
			}
		} else {
			Core.getCore().getInstance().debug("spawns null on start");
		}
	}

	@EventHandler
	public void onUserJoin(final CoreUserJoinGameEvent e) {
		if (e.getGame().getIdentifier() == getPhase().getGame().getIdentifier()) {
			spawn(e.getUser());
		}
	}

	@Override
	public void end() {
		usedSpawns = null;
		spawns = null;
		Core.getCore().getInstance().debug("end spawnfeature");
	}

	public Location spawn(final User user) {
		if (spawns == null) {
			usedSpawns = new ArrayList<>();
			MapData data = ((MapFeature) getPhase().getFeature(FeatureType.MAP)).getMap();
			if (data == null) {
				Core.getCore().getInstance().debug("no map to spawn2!");

				((MapFeature) getPhase().getFeature(FeatureType.MAP)).setMap(getPhase().getGame().getGameData("VoteWinner"));
				data = ((MapFeature) getPhase().getFeature(FeatureType.MAP)).getMap();
			}

			final HashMap<String, Location> spawns = data.getLocs(locKey);
			if (spawns == null) {
				Core.getCore().getInstance().debug("spawns2 null in spawnfeature (lockey: " + locKey + ", map " + data.getName() + "{" + data.getOldName() + "}");
				return null;
			}
			this.spawns = spawns.values().toArray(new Location[spawns.values().size()]);
		}

		if (usedSpawns.size() >= spawns.length) {
			usedSpawns = new ArrayList<>();
		}

		int counter = 0;// To prevent invinitiv loop
		while (true) {
			final Location loc = spawns[Core.getCore().getRandomUtil().nextInt(spawns.length)].clone();
			if (loc == null) {
				counter++;
				continue;
			}
			if (usedSpawns.contains(loc.toVector())) {
				if (counter >= 100) {
					usedSpawns = new ArrayList<>();
					counter = 0;
				} else {
					counter++;
				}
				continue;
			}
			usedSpawns.add(loc.toVector());
			loc.add(0.5, 0.5, 0.5);

			final Block b = loc.getBlock().getRelative(BlockFace.DOWN, 1);
			if (b.getType() == Material.AIR) {
				b.setType(Material.GLASS);
				Bukkit.getScheduler().runTaskLater(Core.getCore().getInstance(), new Runnable() {

					@Override
					public void run() {
						b.setType(Material.AIR);
					}
				}, 20 * 5);
			}

			try {
				Core.getCore().getInstance().debug("respawn2");
				Core.getCore().getInstance().debug("loc: " + loc);
				user.getPlayer().teleport(loc);
				if (user.getPlayer().getLocation().distance(loc) > 5) {
					Bukkit.getScheduler().runTaskLater(Core.getCore().getInstance(), new Runnable() {

						@Override
						public void run() {
							user.getPlayer().teleport(loc);
						}
					}, 5);
				}
			}
			catch (final Exception ex) {
				Core.getCore().getTaskHandler().runTaskLater(new BukkitRunnable() {

					@Override
					public void run() {
						try {
							user.getPlayer().teleport(loc);
						}
						catch (final Exception ex) {
							Core.getCore().getInstance().debug("respawn failed after second try");
						}
					}
				}, 20 * 2, getPhase());
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

	@EventHandler(ignoreCancelled = true)
	public void onRespawn(final PlayerRespawnEvent e) {
		final User user = Core.getCore().getUserHandler().get(e.getPlayer().getUniqueId());
		if (getPhase().getGame().getPlayers().contains(user.getUUID())) {
			Core.getCore().getInstance().debug("respawn");
			e.setRespawnLocation(spawn(user));
		}
	}
}
