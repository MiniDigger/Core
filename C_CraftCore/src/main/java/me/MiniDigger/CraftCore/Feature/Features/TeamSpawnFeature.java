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
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerRespawnEvent;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Map.MapData;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Team.Team;
import me.MiniDigger.Core.User.User;
import me.MiniDigger.CraftCore.Event.Events.CoreUserDeathEvent;
import me.MiniDigger.CraftCore.Event.Events.CoreUserJoinGameEvent;
import me.MiniDigger.CraftCore.Feature.CoreFeature;

public class TeamSpawnFeature extends CoreFeature {

	private DyeColor						locKey;
	private boolean							spawn;
	private HashMap<String, List<Location>>	usedSpawns;
	private HashMap<String, List<Location>>	spawns;

	public TeamSpawnFeature(final Phase phase, final boolean spawn) {
		super(phase);
		this.spawn = spawn;
		locKey = DyeColor.RED;
	}

	public TeamSpawnFeature(final Phase phase, final boolean spawn, final DyeColor locKey) {
		this(phase, spawn);
		this.locKey = locKey;
	}

	public boolean spawn() {
		return spawn;
	}

	public void setSpawn(final boolean spawn) {
		this.spawn = spawn;
	}

	@Override
	public FeatureType getType() {
		return FeatureType.TEAM_SPAWN;
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
		spawns = new HashMap<String, List<Location>>();
		usedSpawns = new HashMap<String, List<Location>>();
		final MapData data = ((MapFeature) getPhase().getFeature(FeatureType.MAP)).getMap();
		final HashMap<String, Location> spawns = data.getLocs(locKey);

		for (final Location loc : spawns.values()) {
			final Block b = loc.getBlock().getRelative(BlockFace.DOWN, 2);
			if (b.getType() == Material.SIGN || b.getType() == Material.SIGN_POST || b.getType() == Material.WALL_SIGN) {
				final Sign s = (Sign) b.getState();
				if (s.getLine(0).equalsIgnoreCase("Team")) {
					final String name = s.getLine(1);
					List<Location> spawn = this.spawns.remove(name);
					if (spawn == null) {
						spawn = new ArrayList<Location>();
					}
					spawn.add(loc);
					this.spawns.put(name, spawn);
					Core.getCore().getInstance().debug("Found spawn for Team: " + name);
				}
			}
		}

		if (spawn) {
			for (final UUID id : getPhase().getGame().getPlayers()) {
				spawn(Core.getCore().getUserHandler().get(id));
			}
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
	}

	public Location getSpawn(final Team t) {
		List<Location> usedSpawns = this.usedSpawns.remove(t.getName());
		final List<Location> spawns = this.spawns.remove(t.getName());

		if (usedSpawns == null) {
			usedSpawns = new ArrayList<Location>();
		}

		if (spawns == null) {
			Core.getCore().getInstance().debug("SPAWNS FOR TEAM " + t.getName() + " = NULL");
		}

		if (usedSpawns.size() >= spawns.size()) {
			usedSpawns = new ArrayList<>();
		}
		int counter = 0;// To prevent invinitiv loop
		while (true) {
			final Location loc = spawns.get(Core.getCore().getRandomUtil().nextInt(spawns.size()));
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
			this.usedSpawns.put(t.getName(), usedSpawns);
			this.spawns.put(t.getName(), spawns);

			return loc;
		}
	}

	public Location spawn(final User user) {
		final TeamFeature tf = (TeamFeature) getPhase().getFeature(FeatureType.TEAM);
		final Team t = tf.getTeam(user);

		final Location loc = getSpawn(t);

		if (user == null) {
			Core.getCore().getInstance().debug("wait, user null?!");
		}

		if (loc == null) {
			Core.getCore().getInstance().debug("wait, loc null?! " + t.getName());
		}

		if (user.getPlayer() == null) {
			Core.getCore().getInstance().debug("wait, player null?! " + user.getDisplayName() + " " + user.getUUID());
		}

		user.getPlayer().teleport(loc);

		return loc;
	}

	public Location spawn(final Player p) {
		final User user = Core.getCore().getUserHandler().get(p.getUniqueId());
		final TeamFeature tf = (TeamFeature) getPhase().getFeature(FeatureType.TEAM);
		final Team t = tf.getTeam(user);

		final Location loc = getSpawn(t);

		p.teleport(loc);

		return loc;
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
			e.setRespawnLocation(spawn(e.getPlayer()));
		}
	}
}
