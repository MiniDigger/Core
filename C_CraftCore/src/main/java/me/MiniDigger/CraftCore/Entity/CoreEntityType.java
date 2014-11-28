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
package me.MiniDigger.CraftCore.Entity;

import java.util.Map;

import org.bukkit.craftbukkit.v1_8_R1.CraftWorld;

import net.minecraft.server.v1_8_R1.Entity;
import net.minecraft.server.v1_8_R1.EntityTypes;

import org.bukkit.Location;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import me.MiniDigger.Core.Core;

public enum CoreEntityType {
	ZOMBIE("CoreZombie", 54, CoreZombie.class);
	
	private String	                name;
	private int	                    id;
	private Class<? extends Entity>	clazz;
	
	private CoreEntityType(final String name, final int id, final Class<? extends Entity> clazz) {
		this.name = name;
		this.id = id;
		this.clazz = clazz;
		addToMaps();
	}
	
	public void spawnEntity(final Location loc) {
		Entity entity;
		try {
			entity = clazz.getConstructor(org.bukkit.World.class).newInstance(loc.getWorld());
		} catch (final Exception e) {
			e.printStackTrace();
			return;
		}
		entity.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
		((CraftWorld) loc.getWorld()).getHandle().addEntity(entity, SpawnReason.CUSTOM);
	}
	
	@SuppressWarnings("unchecked")
	private void addToMaps() {
		final Map<String, Class<? extends Entity>> c = (Map<String, Class<? extends Entity>>) Core.getCore().getReflectionUtil()
		        .getPrivateField("c", EntityTypes.class, null);
		final Map<Class<? extends Entity>, String> d = (Map<Class<? extends Entity>, String>) Core.getCore().getReflectionUtil()
		        .getPrivateField("d", EntityTypes.class, null);
		final Map<Integer, Class<? extends Entity>> e = (Map<Integer, Class<? extends Entity>>) Core.getCore().getReflectionUtil()
		        .getPrivateField("e", EntityTypes.class, null);
		final Map<Class<? extends Entity>, Integer> f = (Map<Class<? extends Entity>, Integer>) Core.getCore().getReflectionUtil()
		        .getPrivateField("f", EntityTypes.class, null);
		final Map<String, Integer> g = (Map<String, Integer>) Core.getCore().getReflectionUtil().getPrivateField("g", EntityTypes.class, null);
		
		if (c.containsKey(name)) {
			c.remove(name);
		}
		if (d.containsKey(clazz)) {
			d.remove(clazz);
		}
		if (e.containsKey(Integer.valueOf(id))) {
			e.remove(Integer.valueOf(id));
		}
		if (f.containsKey(clazz)) {
			f.remove(clazz);
		}
		if (g.containsKey(name)) {
			g.remove(name);
		}
		
		c.put(name, clazz);
		d.put(clazz, name);
		e.put(Integer.valueOf(id), clazz);
		f.put(clazz, Integer.valueOf(id));
		g.put(name, Integer.valueOf(id));
	}
}
