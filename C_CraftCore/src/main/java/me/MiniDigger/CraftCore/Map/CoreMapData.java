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
package me.MiniDigger.CraftCore.Map;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.material.Wool;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Map.MapData;

public class CoreMapData implements MapData {
	
	private String	                                     name;
	private HashMap<DyeColor, HashMap<String, Location>>	locs;
	
	public CoreMapData(final String name) {
		this.name = name;
		locs = new HashMap<>();
	}
	
	public CoreMapData(final FileConfiguration con) {
		load(con);
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public HashMap<String, Location> getLocs(final DyeColor type) {
		return locs.get(type);
	}
	
	@Override
	public void createConfig() {
		final File file = new File(Bukkit.getWorld(name).getWorldFolder(), "map.yml");
		try {
			file.createNewFile();
		} catch (final IOException e1) {
			e1.printStackTrace();
		}
		final FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		config.set("name", name);
		
		final ArrayList<String> types = new ArrayList<>();
		for (final DyeColor type : locs.keySet()) {
			types.add(type.name());
		}
		config.set("locTypes", types);
		
		for (final DyeColor type : locs.keySet()) {
			final ArrayList<String> keys = new ArrayList<>();
			for (final String key : locs.get(type).keySet()) {
				final Location loc = locs.get(type).get(key);
				final String d = key + ";" + loc.getX() + ";" + loc.getY() + ";" + loc.getZ() + ";" + loc.getPitch() + ";" + loc.getYaw();
				keys.add(d);
			}
			config.set(type.name(), keys);
		}
		
		try {
			config.save(file);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void load(final FileConfiguration con) {
		name = con.getString("name");
		
		locs = new HashMap<>();
		
		final ArrayList<DyeColor> types = new ArrayList<>();
		for (final String s : con.getStringList("locTypes")) {
			DyeColor t;
			try {
				t = DyeColor.valueOf(s);
			} catch (final Exception ex) {
				Core.getCore().getInstance().error("Could not read LocType " + s + "!");
				continue;
			}
			types.add(t);
		}
		
		for (final DyeColor t : types) {
			final HashMap<String, Location> locs = new HashMap<>();
			for (final String s : con.getStringList(t.name())) {
				try {
					final String[] split = s.split(";");
					
					final String key = split[0];
					final double x = Double.parseDouble(split[1]);
					final double y = Double.parseDouble(split[2]);
					final double z = Double.parseDouble(split[3]);
					final float pitch = Float.parseFloat(split[4]);
					final float yaw = Float.parseFloat(split[5]);
					final World w = Bukkit.getWorld(name);
					final Location loc = new Location(w, x, y, z, pitch, yaw);
					
					locs.put(key, loc);
				} catch (final Exception ex) {
					Core.getCore().getInstance().error("Could not read Location " + t.name() + "." + s + "!");
					continue;
				}
			}
			this.locs.put(t, locs);
		}
	}
	
	@Override
	public void scanMap(final Location start, final int range, final List<DyeColor> searchFor, final Runnable finished) {
		// final Thread thread = new Thread(new Runnable() {
		//
		// @Override
		// public void run() {
		final HashMap<DyeColor, Integer> ints = new HashMap<>();
		
		final int startX = start.getBlockX();
		final int startY = start.getBlockZ();
		
		final int minX = startX - range;
		final int minY = 0;
		final int minZ = startY - range;
		
		final int maxX = startX + range;
		final int maxY = start.getWorld().getMaxHeight();
		final int maxZ = startY + range;
		
		for (int x = minX; x <= maxX; x++) {
			for (int y = minY; y <= maxY; y++) {
				for (int z = minZ; z <= maxZ; z++) {
					final Location loc = new Location(start.getWorld(), x, y, z);
					if (loc.getBlock().getType() == Material.WOOL) {
						@SuppressWarnings("deprecation") final DyeColor color = DyeColor.getByWoolData(loc.getBlock().getData());
						HashMap<String, Location> wLocs = CoreMapData.this.locs.get(color);
						if (!searchFor.contains(color)) {
							continue;
						}
						if (wLocs == null) {
							wLocs = new HashMap<String, Location>();
						}
						
						if (loc.getBlock().getRelative(BlockFace.DOWN, 2).getState() instanceof Sign) {
							final Sign sign = (Sign) loc.getBlock().getRelative(BlockFace.DOWN, 2).getState();
							wLocs.put(sign.getLine(0), loc);
							} else {
							Integer i = ints.remove(color);
							if (i == null) {
								i = 0;
							}
							wLocs.put(i + "", loc);
							i++;
							ints.put(color, i);
						}
						
						// TODO Use blockface of sign for pitch and yaw
						
						CoreMapData.this.locs.remove(color);
						CoreMapData.this.locs.put(color, wLocs);
						// TODO We will need some kind of pitch and ywa
						// for the spawns
					}
				}
			}
		}
		// for (final DyeColor color : locs.keySet()) {
		// final ArrayList<Location> loc = locs.get(color);
		// final HashMap<String, Location> l = new HashMap<>();
		// for (int i = 0; i < loc.size(); i++) {
		// l.put(i + "", loc.get(i));
		// }
		// CoreMapData.this.locs.put(color, l);
		// }
		
		System.out.println("Size: " + CoreMapData.this.locs.keySet().size());
		Bukkit.getScheduler().runTask(Core.getCore().getInstance(), finished);
		
		// }
		// });
		//
		// thread.setName("MapScanner #1");
		// thread.start();
	}
	
	@Override
	public int clearLocs() {
		int i = 0;
		for (final HashMap<String, Location> map : locs.values()) {
			for (final Location l : map.values()) {
				try {
					if (l == null || l.getBlock() == null) {
						continue;
					}
				} catch (final Exception ex) {
					continue;
				}
				l.getBlock().setType(Material.AIR);
				i++;
			}
		}
		return i;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public int setLocs() {
		int i = 0;
		for (final DyeColor color : locs.keySet()) {
			final HashMap<String, Location> map = locs.get(color);
			for (final Location l : map.values()) {
				l.setWorld(Bukkit.getWorld(name));
				try {
					if (l == null || l.getBlock() == null) {
						System.out.println("Failed i");
						continue;
					}
				} catch (final Exception ex) {
					System.out.println("Failed !");
					// ex.printStackTrace(); Always NPE at
					// org.bukkit.Location.getBlock(Location.java:82)
					continue;
				}
				final Wool wool = new Wool(color);
				l.getBlock().setType(Material.WOOL);
				l.getBlock().setData(wool.getData());
				i++;
			}
		}
		return i;
	}
	
	@Override
	public int loadChunks() {
		int i = 0;
		for (final HashMap<String, Location> map : locs.values()) {
			for (final Location l : map.values()) {
				l.setWorld(Bukkit.getWorld(name));
				if (!l.getChunk().isLoaded() || l.getWorld().isChunkLoaded(l.getChunk())) {
					l.getChunk().load();
					l.getWorld().loadChunk(l.getChunk());
					i++;
				}
			}
		}
		return i;
	}
	
}
