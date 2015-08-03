package me.MiniDigger.Core.AddOn.Ehrenhalle;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.MiniDigger.Core.Lang.LogLevel;
import me.MiniDigger.CraftCore.Lang.MSG;

public class EHScanner {

	private final int RANGE = 100;

	private final World w;
	private final Location spawn;

	private List<Location> tower1 = new ArrayList<>();
	private List<Location> tower2 = new ArrayList<>();
	private List<Location> tower3 = new ArrayList<>();

	private List<Location> wall1 = new ArrayList<>();
	private List<Location> wall2 = new ArrayList<>();
	private List<Location> wall3 = new ArrayList<>();

	private List<Location> fireworksI = new ArrayList<>();
	private List<Location> fireworksO = new ArrayList<>();

	public EHScanner(World w) {
		this.w = w;
		this.spawn = new Location(w, 18.0, 5, 24.0);
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

		for (int x = minX; x <= maxX; x++) {
			for (int y = minY; y <= maxY; y++) {
				for (int z = minZ; z <= maxZ; z++) {
					final Location loc = new Location(spawn.getWorld(), x, y, z);
					if (loc.getBlock().getState() instanceof Sign) {
						final Sign s = (Sign) loc.getBlock().getState();
						if (s.getLine(0).equals("[EH]")) {
							switch (s.getLine(1)) {
							case "T":
								if (s.getLine(2).equals("1")) {
									tower1.add(loc);
								} else if (s.getLine(2).equals("2")) {
									tower2.add(loc);
								} else if (s.getLine(2).equals("3")) {
									tower3.add(loc);
								} else {
									System.out.println("failed scan for sign t " + s.toString());
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
									System.out.println("failed scan for sign w " + s.toString());
								}
								break;
							case "F":
								if (s.getLine(2).equals("I")) {
									fireworksI.add(loc);
								} else if (s.getLine(2).equals("F")) {
									fireworksO.add(loc);
								} else {
									System.out.println("failed scan for sign " + s.toString());
								}
								break;
							default:
								break;
							}
						}
					}
				}
			}
		}

		System.out.println("Scan ended, Results: ");
		System.out.println("Wall: Row 1:" + wall1.size() + ", Row 2: " + wall2.size() + ", Row 3: " + wall3.size()
				+ ", Combined: " + (wall1.size() + wall2.size() + wall3.size()));
		System.out.println("Tower: Row 1:" + tower1.size() + ", Row 2: " + tower2.size() + ", Row 3: " + tower3.size()
				+ ", Combined: " + (tower1.size() + tower2.size() + tower3.size()));
		System.out.println("Fireworks: Inner:" + fireworksI.size() + ", Outer: " + fireworksO.size() + ", Combined: "
				+ (fireworksI.size() + fireworksO.size()));
		System.out.println("All combined: " + (wall1.size() + wall2.size() + wall3.size() + tower1.size()
				+ tower2.size() + tower3.size() + fireworksI.size() + fireworksO.size()));
	}

	public int loadChunks() {
		int i = 0;
		int a = 0;
		List<Location> combined = new ArrayList<>();
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
		System.out.println("Didn't load " + a + " Chunks");
		return i;
	}

	public void save(File file) {
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		config.set("wall1", wall1);
		config.set("wall2", wall2);
		config.set("wall3", wall3);

		config.set("tower1", tower1);
		config.set("tower2", tower2);
		config.set("tower3", tower3);

		config.set("fireworkI", fireworksI);
		config.set("fireworkO", fireworksO);

		try {
			config.save(file);
		} catch (IOException e) {
			System.out.println("Error while saving config");
			MSG.stacktrace(LogLevel.DEBUG, e);
		}
	}

	@SuppressWarnings("unchecked")
	public void load(File file) {
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		wall1 = (List<Location>) config.get("wall1");
		wall2 = (List<Location>) config.get("wall2");
		wall3 = (List<Location>) config.get("wall3");

		tower1 = (List<Location>) config.get("tower1");
		tower2 = (List<Location>) config.get("tower2");
		tower3 = (List<Location>) config.get("tower3");
		
		config.set("fireworkI", fireworksI);
		config.set("fireworkO", fireworksO);
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
