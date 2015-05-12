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
			System.out.println("1");
			final MapData map = Core.getCore().getMapHandler().getMap("Spawn");
			if (map != null) {
				System.out.println("2");
				return (Location) map.getLocs(DyeColor.RED).values().toArray()[0];
			}
		}
		System.out.println("wtf");
		return new Location(Bukkit.getWorld("Spawn"), -937.5, 38.0, -768.5);
	}
	
	@Override
	public void unloadWorld(final String world, final Location fallBackLoc) {
		final World w = Bukkit.getWorld(world);
		w.save();
		
		for (final LivingEntity e : w.getLivingEntities()) {
			if (e.getType() == EntityType.PLAYER) {
				e.teleport(fallBackLoc);
			} else {
				e.remove();
			}
		}
		
		for (final Chunk c : w.getLoadedChunks()) {
			c.unload();
			w.unloadChunk(c);
		}
		
		Bukkit.unloadWorld(w, true);
	}
	
	@Override
	public World loadWorld(final String name) {
		final WorldCreator wc = WorldCreator.name(name);
		wc.environment(Environment.NORMAL);
		wc.generateStructures(false);
		wc.type(WorldType.FLAT);
		wc.generator(new CoreCleanroomChunkGenerator("."));
		
		final File session = new File(Core.getCore().getStringUtil().replaceLast(Bukkit.getWorldContainer().getAbsolutePath(), ",", ""), name + "/session.lock");
		session.delete();
		try {
			session.createNewFile();
			final DataOutputStream dataoutputstream = new DataOutputStream(new FileOutputStream(session));
			
			try {
				dataoutputstream.writeLong(System.currentTimeMillis());
			} finally {
				dataoutputstream.close();
			}
		} catch (final IOException ioexception) {
			Core.getCore().getInstance().error("ERROR: " + ioexception.getMessage());
		}
		
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
		final File out = new File(Core.getCore().getStringUtil().replaceLast(Bukkit.getWorldContainer().getAbsolutePath(), ".", ""));
		final File oldMap = new File(out, name);
		
		if (Bukkit.getWorld(name) != null) {
			unloadWorld(name, Core.getCore().getWorldHandler().getFallbackLoc());
		}
		
		if (oldMap.exists() && oldMap.isDirectory()) {
			_.log(LogLevel.DEBUG, LangKeyType.World.DELETE_OLD);
			Core.getCore().getFileUtil().deleteDirectory(oldMap);
		}
	}
	
	@Override
	public void copyWorld(final String name) {
		final File mapFolder = new File((Core.getCore().getInstance()).getConfig().getString("mapFolder"));
		File map = new File(mapFolder, name + ".zip");
		final File out = new File(Core.getCore().getStringUtil().replaceLast(Bukkit.getWorldContainer().getAbsolutePath(), ".", ""));
		final File oldMap = new File(out, name);
		
		deleteWorld(name);
		
		_.log(LogLevel.DEBUG, LangKeyType.World.UNZIP, map.getAbsolutePath(), out.getAbsolutePath());
		
		Core.getCore().getDeZipUtil().extract(new File(map.getAbsolutePath()), out);
		
		final File session = new File(oldMap, "session.lock");
		session.delete();
		try {
			session.createNewFile();
			final DataOutputStream dataoutputstream = new DataOutputStream(new FileOutputStream(session));
			
			try {
				dataoutputstream.writeLong(System.currentTimeMillis());
			} finally {
				dataoutputstream.close();
			}
		} catch (final IOException ioexception) {
			_.log(LogLevel.ERROR, LangKeyType.Main.ERROR, ioexception.getMessage());
			_.stacktrace(LogLevel.DEBUG, ioexception);
		}
		
		map = new File(Bukkit.getWorldContainer(), name);
		final File mapDataFile = new File(map, "map.yml");
		final FileConfiguration con = YamlConfiguration.loadConfiguration(mapDataFile);
		
		final MapData data = new CoreMapData(con);
		Core.getCore().getMapHandler().addMap(data);
	}
}
