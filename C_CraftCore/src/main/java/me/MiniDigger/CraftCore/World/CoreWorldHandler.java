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
package me.MiniDigger.CraftCore.World;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.tools.ant.types.CommandlineJava.SysProperties;

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

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Lang.LangKeyType;
import me.MiniDigger.Core.Lang.LogLevel;
import me.MiniDigger.Core.Map.MapData;
import me.MiniDigger.Core.World.WorldHandler;

import me.MiniDigger.CraftCore.Generator.CoreCleanroomChunkGenerator;
import me.MiniDigger.CraftCore.Lang._;
import me.MiniDigger.CraftCore.Map.CoreMapData;

public class CoreWorldHandler implements WorldHandler {
	
	@Override
	public Location getFallbackLoc() {
		// return new Location(Bukkit.getWorld("Spawn"), 969, 108, 85);
		if (Bukkit.getWorld("Spawn") != null) {
			final MapData map = Core.getCore().getMapHandler().getMap("Spawn");
			if (map != null) {
				return (Location) map.getLocs(DyeColor.RED).values().toArray()[0];
			}
		}
		System.out.println("wtf");
		return new Location(Bukkit.getWorld("Spawn"), -937.5, 38.0, -768.5);
	}
	
	@Override
	public void unloadWorld(final String world, final Location fallBackLoc) {
		System.out.println("unload " + world);
		final World w = Bukkit.getWorld(world);
		if (w == null) {
			return;
		}
		
		try {
			w.save();
		} catch (Exception ex) {
			System.out.println("No save for you, bitch");
		}
		
		try {
			for (final LivingEntity e : w.getLivingEntities()) {
				if (e.getType() == EntityType.PLAYER) {
					e.teleport(fallBackLoc);
				} else {
					e.remove();
				}
			}
		} catch (Exception ex) {
			System.out.println("Ok, you can live some seconds longer...");
		}
		
		try {
			for (final Chunk c : w.getLoadedChunks()) {
				c.unload();
				w.unloadChunk(c);
			}
		} catch (Exception ex) {
			System.out.println("I will unload you later...");
		}
		try {
			Bukkit.unloadWorld(w, true);
		} catch (Exception ex) {
			System.out.println("Will, that sucks");
		}
		System.out.println("?");
	}
	
	@Override
	public World loadWorld(final String name) {
		System.out.println("load " + name);
		final WorldCreator wc = WorldCreator.name(name);
		wc.environment(Environment.NORMAL);
		wc.generateStructures(false);
		wc.type(WorldType.FLAT);
		wc.generator(new CoreCleanroomChunkGenerator("."));
		
		fixSession(new File(Core.getCore().getStringUtil().replaceLast(Bukkit.getWorldContainer().getAbsolutePath(), ",", ""), name));
		
		final World w = new CoreWorldLoader().loadWorld(wc);
		w.setAutoSave(false);
		
		_.log(LogLevel.INFO, LangKeyType.World.LOADING_CHUNKS);
		try {
			final MapData data = Core.getCore().getMapHandler().getMap(name);
			final int i = data.loadChunks();
			_.log(LogLevel.INFO, LangKeyType.World.CHUNKS_LOADED, i + "");
		} catch (final Exception ex) {
			_.log(LogLevel.ERROR, LangKeyType.World.CHUNK_LOAD_ERROR, ex.getMessage());
			_.stacktrace(LogLevel.DEBUG, ex);
		}
		
		return w;
	}
	
	@Override
	public void deleteWorld(final String name) {
		if (name == null) {
			return;
		}
		
		System.out.println("delete " + name);
		
		final File out = new File(Core.getCore().getStringUtil().replaceLast(Bukkit.getWorldContainer().getAbsolutePath(), ".", ""));
		final File oldMap = new File(out, name);
		
		if (Bukkit.getWorld(name) != null) {
			unloadWorld(name, Core.getCore().getWorldHandler().getFallbackLoc());
		}
		
		if (oldMap.exists() && oldMap.isDirectory()) {
			try {
				_.log(LogLevel.DEBUG, LangKeyType.World.DELETE_OLD, name);
				Core.getCore().getFileUtil().deleteDirectory(oldMap);
			} catch (Exception ex) {
				System.out.println("err");
				fixSession(oldMap);
				try {
					_.log(LogLevel.DEBUG, LangKeyType.World.DELETE_OLD, name);
					Core.getCore().getFileUtil().deleteDirectory(oldMap);
				} catch (Exception exs) {
					System.out.println("err124");
				}
			}
		}
	}
	
	private void fixSession(File oldMap) {
		System.out.println("fix session");
		final File session = new File(oldMap, "session.lock");
		session.delete();
		System.out.println("deleted " + session.getAbsolutePath());
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
		// _.log(LogLevel.ERROR, LangKeyType.Main.ERROR,
		// ioexception.getMessage());
		// _.stacktrace(LogLevel.DEBUG, ioexception);
		// }
	}
	
	@Override
	public void copyWorld(final String name) {
		System.out.println("copy " + name);
		final File mapFolder = new File((Core.getCore().getInstance()).getConfig().getString("mapFolder"));
		File map = new File(mapFolder, name + ".zip");
		final File out = new File(Core.getCore().getStringUtil().replaceLast(Bukkit.getWorldContainer().getAbsolutePath(), ".", ""));
		final File oldMap = new File(out, name);
		
		deleteWorld(name);
		
		_.log(LogLevel.DEBUG, LangKeyType.World.UNZIP, map.getAbsolutePath(), out.getAbsolutePath());
		
		Core.getCore().getDeZipUtil().extract(new File(map.getAbsolutePath()), out);
		
		fixSession(oldMap);
		
		map = new File(Bukkit.getWorldContainer(), name);
		final File mapDataFile = new File(map, "map.yml");
		final FileConfiguration con = YamlConfiguration.loadConfiguration(mapDataFile);
		
		final MapData data = new CoreMapData(con);
		Core.getCore().getMapHandler().addMap(data);
	}
}
