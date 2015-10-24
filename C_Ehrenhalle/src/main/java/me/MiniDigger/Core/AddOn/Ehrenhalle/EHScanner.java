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
package me.MiniDigger.Core.AddOn.Ehrenhalle;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Lang.LogLevel;

import me.MiniDigger.CraftCore.Lang.MSG;

public class EHScanner {

	private final int RANGE = 100;

	private final World		w;
	private final Location	spawn;

	private List<Location>	tower1	= new ArrayList<>();
	private List<Location>	tower2	= new ArrayList<>();
	private List<Location>	tower3	= new ArrayList<>();

	private List<Location>	wall1	= new ArrayList<>();
	private List<Location>	wall2	= new ArrayList<>();
	private List<Location>	wall3	= new ArrayList<>();

	private List<Location>	fireworksI	= new ArrayList<>();
	private List<Location>	fireworksO	= new ArrayList<>();

	public EHScanner(final World w) {
		this.w = w;
		this.spawn = new Location(w, 18.0, 5, 124.0);
	}

	public void clear() {
		final List<Location> locs = new ArrayList<>();
		locs.addAll(tower1);
		locs.addAll(tower2);
		locs.addAll(tower3);
		locs.addAll(wall1);
		locs.addAll(wall2);
		locs.addAll(wall3);
		locs.addAll(fireworksI);
		locs.addAll(fireworksO);

		Core.getCore().getInstance().debug("clear " + locs.size());

		for (final Location loc : locs) {
			if (loc.getBlock().getState() instanceof Sign) {
				loc.getBlock().setType(Material.AIR);
			} else {
				Core.getCore().getInstance().debug("no sign at " + loc.toString());
			}
		}
	}

	public void scan() {
		final int startX = spawn.getBlockX();
		final int startY = spawn.getBlockZ();

		final int minX = startX - RANGE;
		final int minY = 0;
		final int minZ = startY - RANGE;

		final int maxX = startX + RANGE;
		final int maxY = spawn.getWorld().getMaxHeight();
		final int maxZ = startY + RANGE;

		Core.getCore().getInstance().debug("minx " + minX);
		Core.getCore().getInstance().debug("maxx " + maxX);
		Core.getCore().getInstance().debug("minz " + minZ);
		Core.getCore().getInstance().debug("maxz " + maxZ);

		for (int x = minX; x <= maxX; x++) {
			for (int y = minY; y <= maxY; y++) {
				for (int z = minZ; z <= maxZ; z++) {
					final Location loc = new Location(spawn.getWorld(), x, y, z);
					if (loc.getBlock().getState() instanceof Sign) {
						final Sign s = (Sign) loc.getBlock().getState();
						if (s.getLine(0).equals("[EH]")) {
							final org.bukkit.material.Sign sign = (org.bukkit.material.Sign) s.getData();
							loc.setYaw(Core.getCore().getFaceUtil().faceToYaw(sign.getFacing().getOppositeFace()));

							switch (s.getLine(1)) {
								case "T":
									if (s.getLine(2).equals("1")) {
										tower1.add(loc);
									} else if (s.getLine(2).equals("2")) {
										tower2.add(loc);
									} else if (s.getLine(2).equals("3")) {
										tower3.add(loc);
									} else {
										Core.getCore().getInstance().debug("failed scan for sign t " + s.toString());
									}
									break;
								case "W":
									if (s.getLine(2).equals("1")) {
										wall1.add(loc);
									} else if (s.getLine(2).equals("2")) {
										wall2.add(loc);
									} else if (s.getLine(2).equals("3")) {
										wall3.add(loc);
									} else {
										Core.getCore().getInstance().debug("failed scan for sign w " + s.toString());
									}
									break;
								case "F":
									if (s.getLine(2).equals("I")) {
										fireworksI.add(loc);
									} else if (s.getLine(2).equals("O")) {
										fireworksO.add(loc);
									} else {
										Core.getCore().getInstance().debug("failed scan for sign f " + s.toString());
									}
									break;
								default:
									Core.getCore().getInstance().debug("default");
									break;
							}
						}
					}
				}
			}
		}

		Core.getCore().getInstance().debug("Scan ended, Results: ");
		Core.getCore().getInstance().debug("Wall: Row 1:" + wall1.size() + ", Row 2: " + wall2.size() + ", Row 3: " + wall3.size() + ", Combined: " + (wall1.size() + wall2.size() + wall3.size()));
		Core.getCore().getInstance().debug("Tower: Row 1:" + tower1.size() + ", Row 2: " + tower2.size() + ", Row 3: " + tower3.size() + ", Combined: " + (tower1.size() + tower2.size() + tower3.size()));
		Core.getCore().getInstance().debug("Fireworks: Inner:" + fireworksI.size() + ", Outer: " + fireworksO.size() + ", Combined: " + (fireworksI.size() + fireworksO.size()));
		Core.getCore().getInstance().debug("All combined: " + (wall1.size() + wall2.size() + wall3.size() + tower1.size() + tower2.size() + tower3.size() + fireworksI.size() + fireworksO.size()));
	}

	public int loadChunks() {
		int i = 0;
		int a = 0;
		final List<Location> combined = new ArrayList<>();
		combined.addAll(wall1);
		combined.addAll(tower1);
		combined.addAll(fireworksI);
		for (final Location l : combined) {
			l.setWorld(w);
			if (!l.getChunk().isLoaded() || !l.getWorld().isChunkLoaded(l.getChunk())) {
				l.getChunk().load();
				l.getWorld().loadChunk(l.getChunk());
				i++;
			} else {
				a++;
			}
		}
		Core.getCore().getInstance().debug("Didn't load " + a + " Chunks");
		return i;
	}

	public void save(final File file) {
		final FileConfiguration config = YamlConfiguration.loadConfiguration(new File(file, "locs"));
		config.set("wall1", wall1);
		config.set("wall2", wall2);
		config.set("wall3", wall3);

		config.set("tower1", tower1);
		config.set("tower2", tower2);
		config.set("tower3", tower3);

		config.set("fireworkI", fireworksI);
		config.set("fireworkO", fireworksO);

		try {
			config.save(new File(file, "locs"));
		}
		catch (final IOException e) {
			Core.getCore().getInstance().debug("Error while saving config");
			MSG.stacktrace(LogLevel.DEBUG, e);
		}
	}

	@SuppressWarnings("unchecked")
	public void load(final File file) {
		final FileConfiguration config = YamlConfiguration.loadConfiguration(new File(file, "locs"));
		wall1 = (List<Location>) config.get("wall1");
		wall2 = (List<Location>) config.get("wall2");
		wall3 = (List<Location>) config.get("wall3");

		tower1 = (List<Location>) config.get("tower1");
		tower2 = (List<Location>) config.get("tower2");
		tower3 = (List<Location>) config.get("tower3");

		fireworksI = (List<Location>) config.get("fireworkI");
		fireworksO = (List<Location>) config.get("fireworkO");
	}

	public Location getSpawn() {
		return spawn;
	}

	public List<Location> getTower1() {
		return tower1;
	}

	public List<Location> getTower2() {
		return tower2;
	}

	public List<Location> getTower3() {
		return tower3;
	}

	public List<Location> getWall1() {
		return wall1;
	}

	public List<Location> getWall2() {
		return wall2;
	}

	public List<Location> getWall3() {
		return wall3;
	}

	public List<Location> getFireworksI() {
		return fireworksI;
	}

	public List<Location> getFireworksO() {
		return fireworksO;
	}
}
