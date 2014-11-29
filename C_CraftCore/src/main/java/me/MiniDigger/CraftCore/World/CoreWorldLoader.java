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
package me.MiniDigger.CraftCore.World;

import java.io.File;

import org.bukkit.craftbukkit.v1_8_R1.CraftServer;

import net.minecraft.server.v1_8_R1.ConvertProgressUpdater;
import net.minecraft.server.v1_8_R1.Convertable;
import net.minecraft.server.v1_8_R1.EntityTracker;
import net.minecraft.server.v1_8_R1.EnumGamemode;
import net.minecraft.server.v1_8_R1.ServerNBTManager;
import net.minecraft.server.v1_8_R1.WorldData;
import net.minecraft.server.v1_8_R1.WorldManager;
import net.minecraft.server.v1_8_R1.WorldServer;
import net.minecraft.server.v1_8_R1.WorldSettings;

import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Entity;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.generator.ChunkGenerator;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.World.WorldLoader;

public class CoreWorldLoader implements WorldLoader {
	
	@Override
	public World loadWorld(final WorldCreator creator) {
		final CraftServer server = (CraftServer) org.bukkit.Bukkit.getServer();
		if (creator == null) {
			throw new IllegalArgumentException("Creator may not be null");
		}
		
		final String name = creator.name();
		Core.getCore().getInstance().info("Loading world '" + name + "'");
		ChunkGenerator generator = creator.generator();
		final File folder = new File(server.getWorldContainer(), name);
		final World world = server.getWorld(name);
		final net.minecraft.server.v1_8_R1.WorldType type = net.minecraft.server.v1_8_R1.WorldType.getType(creator.type().getName());
		final boolean generateStructures = creator.generateStructures();
		
		if (world != null) {
			return world;
		}
		
		if ((folder.exists()) && (!folder.isDirectory())) {
			throw new IllegalArgumentException("File exists with the name '" + name + "' and isn't a folder");
		}
		
		if (generator == null) {
			generator = server.getGenerator(name);
		}
		
		final Convertable converter = new net.minecraft.server.v1_8_R1.WorldLoaderServer(server.getWorldContainer());
		if (converter.isConvertable(name)) {
			Core.getCore().getInstance().info("Converting world '" + name + "'");
			converter.convert(name, new ConvertProgressUpdater(server.getServer()));
		}
		
		int dimension = 3 + server.getServer().worlds.size() + 1;
		boolean used = false;
		do {
			for (final WorldServer worldServer : server.getServer().worlds) {
				used = worldServer.dimension == dimension;
				if (used) {
					dimension++;
					break;
				}
			}
		} while (used);
		final boolean hardcore = false;
		dimension = dimension + 3;
		Core.getCore().getInstance().info("Created world with dimension : " + dimension);
		
		// final WorldServer internal = new WorldServer(server.getServer(), new
		// net.minecraft.server.v1_8_R1.ServerNBTManager(server.getWorldContainer(),
		// name, true), name,
		// dimension, new WorldSettings(creator.seed(), EnumGamemode.SURVIVAL,
		// generateStructures, hardcore, type),
		// server.getServer().methodProfiler,
		// creator.environment(), generator);
		
		final WorldServer internal = new WorldServer(server.getServer(), new ServerNBTManager(server.getWorldContainer(), name, true), new WorldData(new WorldSettings(
		        creator.seed(), EnumGamemode.SURVIVAL, generateStructures, hardcore, type), name), dimension, server.getServer().methodProfiler, creator.environment(),
		        generator);
		
		boolean containsWorld = false;
		for (final World otherWorld : server.getWorlds()) {
			if (otherWorld.getName().equalsIgnoreCase(name.toLowerCase())) {
				containsWorld = true;
				break;
			}
		}
		
		if (!containsWorld) {
			return null;
		}
		
		internal.scoreboard = server.getScoreboardManager().getMainScoreboard().getHandle();
		internal.worldMaps = server.getServer().worlds.get(0).worldMaps;
		internal.tracker = new EntityTracker(internal);
		internal.addIWorldAccess(new WorldManager(server.getServer(), internal));
		// internal.difficulty = EnumDifficulty.HARD;
		internal.setSpawnFlags(true, true);
		internal.savingDisabled = true;
		internal.b();
		server.getServer().worlds.add(internal);
		
		if (generator != null) {
			internal.getWorld().getPopulators().addAll(generator.getDefaultPopulators(internal.getWorld()));
		}
		
		server.getPluginManager().callEvent(new WorldInitEvent(internal.getWorld()));
		server.getPluginManager().callEvent(new WorldLoadEvent(internal.getWorld()));
		
		for (final Entity e : internal.getWorld().getEntities()) {
			e.remove();
			System.out.println("remove " + e.getType());
		}
		
		return internal.getWorld();
	}
}
