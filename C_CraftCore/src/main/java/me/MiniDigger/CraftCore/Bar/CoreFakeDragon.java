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
package me.MiniDigger.CraftCore.Bar;

import java.lang.reflect.Method;

import org.bukkit.Location;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Bar.FakeDragon;

public class CoreFakeDragon implements FakeDragon {

	private static final int	MAX_HEALTH	= 200;
	private int					id;
	private final int			x;
	private final int			y;
	private final int			z;
	private final int			pitch		= 0;
	private final int			yaw			= 0;
	private final byte			xvel		= 0;
	private final byte			yvel		= 0;
	private final byte			zvel		= 0;
	private float				health;
	private final boolean		visible		= false;
	private String				name;
	private final Object		world;

	private Object dragon;

	public CoreFakeDragon(final Location loc, final String name, final float percent) {
		this.name = name;
		x = loc.getBlockX();
		y = loc.getBlockY();
		z = loc.getBlockZ();
		health = percent * MAX_HEALTH;
		world = Core.getCore().getReflectionUtil().getHandle(loc.getWorld());
	}

	@Override
	public void setHealth(final float percent) {
		health = (percent / MAX_HEALTH);
	}

	@Override
	public void setHealth(final double health) {
		this.health = (float) health;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public Object getSpawnPacket() {
		final Class<?> Entity = Core.getCore().getReflectionUtil().getCraftClass("Entity");
		final Class<?> EntityLiving = Core.getCore().getReflectionUtil().getCraftClass("EntityLiving");
		final Class<?> EntityEnderDragon = Core.getCore().getReflectionUtil().getCraftClass("EntityEnderDragon");

		try {
			dragon = EntityEnderDragon.getConstructor(Core.getCore().getReflectionUtil().getCraftClass("World")).newInstance(world);

			Core.getCore().getReflectionUtil().getMethod(EntityEnderDragon, "setLocation", double.class, double.class, double.class, float.class, float.class).invoke(dragon, x, y, z, pitch, yaw);
			Core.getCore().getReflectionUtil().getMethod(EntityEnderDragon, "setInvisible", boolean.class).invoke(dragon, visible);
			Core.getCore().getReflectionUtil().getMethod(EntityEnderDragon, "setCustomName", String.class).invoke(dragon, name);
			Core.getCore().getReflectionUtil().getMethod(EntityEnderDragon, "setHealth", float.class).invoke(dragon, health);

			Core.getCore().getReflectionUtil().getField(Entity, "motX").set(dragon, xvel);
			Core.getCore().getReflectionUtil().getField(Entity, "motY").set(dragon, yvel);
			Core.getCore().getReflectionUtil().getField(Entity, "motZ").set(dragon, zvel);

			id = (Integer) Core.getCore().getReflectionUtil().getMethod(EntityEnderDragon, "getId").invoke(dragon);

			final Class<?> packetClass = Core.getCore().getReflectionUtil().getCraftClass("PacketPlayOutSpawnEntityLiving");
			return packetClass.getConstructor(new Class<?>[] { EntityLiving }).newInstance(dragon);
		}
		catch (final Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Object getDestroyPacket() {
		try {
			final Class<?> packetClass = Core.getCore().getReflectionUtil().getCraftClass("PacketPlayOutEntityDestroy");
			return packetClass.getConstructor(new Class<?>[] { int[].class }).newInstance(new int[] { id });
		}
		catch (final Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Object getMetaPacket(final Object watcher) {
		try {
			final Class<?> watcherClass = Core.getCore().getReflectionUtil().getCraftClass("DataWatcher");
			final Class<?> packetClass = Core.getCore().getReflectionUtil().getCraftClass("PacketPlayOutEntityMetadata");
			return packetClass.getConstructor(new Class<?>[] { int.class, watcherClass, boolean.class }).newInstance(id, watcher, true);
		}
		catch (final Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Object getTeleportPacket(final Location loc) {
		try {
			final Class<?> packetClass = Core.getCore().getReflectionUtil().getCraftClass("PacketPlayOutEntityTeleport");
			return packetClass.getConstructor(new Class<?>[] { int.class, int.class, int.class, int.class, byte.class, byte.class, boolean.class }).newInstance(id, loc.getBlockX() * 32, loc.getBlockY() * 32,
					loc.getBlockZ() * 32, (byte) ((int) loc.getYaw() * 256 / 360), (byte) ((int) loc.getPitch() * 256 / 360), false);
		}
		catch (final Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Object getWatcher() {
		final Class<?> Entity = Core.getCore().getReflectionUtil().getCraftClass("Entity");
		final Class<?> DataWatcher = Core.getCore().getReflectionUtil().getCraftClass("DataWatcher");

		try {
			final Object watcher = DataWatcher.getConstructor(new Class<?>[] { Entity }).newInstance(dragon);
			final Method a = Core.getCore().getReflectionUtil().getMethod(DataWatcher, "a", new Class<?>[] { int.class, Object.class });

			a.invoke(watcher, 0, visible ? (byte) 0 : (byte) 0x20);
			a.invoke(watcher, 6, health);
			a.invoke(watcher, 7, 0);
			a.invoke(watcher, 8, (byte) 0);
			a.invoke(watcher, 10, name);
			a.invoke(watcher, 11, (byte) 1);
			return watcher;
		}
		catch (final Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
