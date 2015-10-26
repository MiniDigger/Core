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
package me.MiniDigger.CraftCore.WorldNew;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Lang.LangKeyType;
import me.MiniDigger.Core.Lang.LogLevel;
import me.MiniDigger.Core.Map.MapData;
import me.MiniDigger.Core.World.WorldHandler;

import me.MiniDigger.CraftCore.Generator.CoreCleanroomChunkGenerator;
import me.MiniDigger.CraftCore.Lang.MSG;
import me.MiniDigger.CraftCore.Map.CoreMapData;

public class CoreWorldHandler implements WorldHandler {

	public CoreWorldHandler() {
		cleanup();
	}

	@Override
	public Location getFallbackLoc() {
		// return new Location(Bukkit.getWorld("Spawn"), 969, 108, 85);
		if (Bukkit.getWorld("Spawn") != null) {
			Core.getCore().getInstance().debug("spawn");
			final MapData map = Core.getCore().getMapHandler().getMap("Spawn");
			if (map != null) {
				return (Location) map.getLocs(DyeColor.RED).values().toArray()[0];
			}
		} else {
			Core.getCore().getInstance().debug("lobby");
			if (Bukkit.getWorld(Core.getCore().getGameHandler().getMainGame().getType().getAbk() + "_Lobby") != null) {
				return new Location(Bukkit.getWorld("Spawn"), -38, 4, -41);
			}
		}
		Core.getCore().getInstance().debug("fallback");
		return new Location(Bukkit.getWorld("Spawn"), -937.5, 38.0, -768.5);
	}

	@Override
	public void unloadWorld(final String world, final Location fallBackLoc) {
		Core.getCore().getInstance().debug("unload " + world);

//		MultiverseCore.//TODO MULTIVERSE
		
		Core.getCore().getMapHandler().unload(Core.getCore().getMapHandler().getMap(world));

		final World w = Bukkit.getWorld(world);
		if (w == null) {
			return;
		}

		try {
			w.save();
		}
		catch (final Exception ex) {
			Core.getCore().getInstance().debug("No save for you, bitch");
		}

		try {
			for (final LivingEntity e : w.getLivingEntities()) {
				if (e.getType() == EntityType.PLAYER) {
					e.teleport(fallBackLoc);
				} else {
					e.remove();
				}
			}
		}
		catch (final Exception ex) {
			Core.getCore().getInstance().debug("Ok, you can live some seconds longer...");
		}

		try {
			for (final Chunk c : w.getLoadedChunks()) {
				c.unload();
				w.unloadChunk(c);
			}
		}
		catch (final Exception ex) {
			Core.getCore().getInstance().debug("I will unload you later...");
		}
		try {
			Bukkit.unloadWorld(w, true);
		}
		catch (final Exception ex) {
			Core.getCore().getInstance().debug("Will, that sucks");
		}
	}

	@Override
	public World loadWorld(final String name) {
		return loadWorld(name, name);
	}

	@Override
	public World loadWorld(final String name, final String newName) {
		Core.getCore().getInstance().debug("load " + name + " as " + newName);
		final WorldCreator wc = WorldCreator.name(newName);
		wc.environment(Environment.NORMAL);
		wc.generateStructures(false);
		wc.type(WorldType.FLAT);
		wc.generator(new CoreCleanroomChunkGenerator("."));

		fixSession(new File(Core.getCore().getStringUtil().replaceLast(Bukkit.getWorldContainer().getAbsolutePath(), ",", ""), newName));

		final World w = new CoreWorldLoader().loadWorld(wc);
		w.setAutoSave(false);

		MSG.log(LogLevel.INFO, LangKeyType.World.LOADING_CHUNKS);
		try {
			MapData data = Core.getCore().getMapHandler().getMap(name);
			if (data == null) {
				Core.getCore().getInstance().debug("try other name: " + newName);
				data = Core.getCore().getMapHandler().getMap(newName);
			}
			final int i = data.loadChunks();
			MSG.log(LogLevel.INFO, LangKeyType.World.CHUNKS_LOADED, i + "");
		}
		catch (final Exception ex) {
			MSG.log(LogLevel.ERROR, LangKeyType.World.CHUNK_LOAD_ERROR, ex.getMessage());
			MSG.stacktrace(LogLevel.DEBUG, ex);
		}

		return w;
	}

	@Override
	public void deleteWorld(final String name) {
		if (name == null) {
			return;
		}

		Core.getCore().getInstance().debug("delete " + name);

		final File out = new File(Core.getCore().getStringUtil().replaceLast(Bukkit.getWorldContainer().getAbsolutePath(), ".", ""));
		final File oldMap = new File(out, name);

		if (Bukkit.getWorld(name) != null) {
			unloadWorld(name, Core.getCore().getWorldHandler().getFallbackLoc());
		}

		if (oldMap.exists() && oldMap.isDirectory()) {
			try {
				MSG.log(LogLevel.DEBUG, LangKeyType.World.DELETE_OLD, name);
				Core.getCore().getFileUtil().deleteDirectory(oldMap);
			}
			catch (final Exception ex) {
				Core.getCore().getInstance().debug("err");
				fixSession(oldMap);
				try {
					MSG.log(LogLevel.DEBUG, LangKeyType.World.DELETE_OLD, name);
					Core.getCore().getFileUtil().deleteDirectory(oldMap);
				}
				catch (final Exception exs) {
					Core.getCore().getInstance().debug("err124");
				}
			}
		}
	}

	private void fixSession(final File oldMap) {
		Core.getCore().getInstance().debug("fix session");
		final File session = new File(oldMap, "session.lock");
		session.delete();
		Core.getCore().getInstance().debug("deleted " + session.getAbsolutePath());

		final File uid = new File(oldMap, "uid.dat");
		uid.delete();

		// try {
		// session.createNewFile();
		// final DataOutputStream dataoutputstream = new DataOutputStream(new
		// FileOutputStream(session));
		//
		// try {
		// dataoutputstream.writeLong(System.currentTimeMillis());
		// } finally {
		// dataoutputstream.close();
		// }
		// } catch (final IOException ioexception) {
		// MSG.log(LogLevel.ERROR, LangKeyType.Main.ERROR,
		// ioexception.getMessage());
		// MSG.stacktrace(LogLevel.DEBUG, ioexception);
		// }
	}

	@Override
	public void copyWorld(final String name) {
		copyWorld(name, name);
	}

	@Override
	public void copyWorld(final String name, final String newName) {
		final File mapFolder = new File((Core.getCore().getInstance()).getConfig().getString("mapFolder"));
		File map = new File(mapFolder, name + ".zip");
		final File out = new File(Core.getCore().getStringUtil().replaceLast(Bukkit.getWorldContainer().getAbsolutePath(), ".", ""));

		deleteWorld(name);

		MSG.log(LogLevel.DEBUG, LangKeyType.World.UNZIP, map.getAbsolutePath(), out.getAbsolutePath());

		Core.getCore().getDeZipUtil().extract(new File(map.getAbsolutePath()), out);

		new File(out, name).renameTo(new File(out, newName));

		map = new File(Bukkit.getWorldContainer(), newName);
		fixSession(map);

		final File mapDataFile = new File(map, "map.yml");
		Core.getCore().getInstance().debug("load config " + mapDataFile.getAbsolutePath());
		final FileConfiguration con = YamlConfiguration.loadConfiguration(mapDataFile);

		final MapData data = new CoreMapData(con);
		data.setNewName(newName);
		Core.getCore().getMapHandler().addMap(data);
	}

	@Override
	public void cleanup() {
		Core.getCore().getInstance().debug("cleanup");
		final File mapFolder = Bukkit.getWorldContainer();
		String[] ignore = new String[] { "logs", "plugins", "world", "world_the_end" };
		for (final World w : Bukkit.getWorlds()) {
			ignore = (String[]) ArrayUtils.add(ignore, w.getName());
		}
		final List<String> i = Arrays.asList(ignore);

		for (final File f : mapFolder.listFiles()) {
			if (f.isDirectory()) {
				if (!i.contains(f.getName())) {
					if (Core.getCore().getFileUtil().deleteDirectory(f)) {
						Core.getCore().getInstance().debug("Could not delete " + f.getName());
					}
				}
			}
		}
	}
}
