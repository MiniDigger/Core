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
package me.MiniDigger.CraftCore.Protocol.SignGUI;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.NumberConversions;

public abstract class NMSUtils implements NMSCore {
	
	private static String	 NMSVersion	= Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
	
	protected static NMSCore	impl	= getImpl();
	
	private static NMSCore getImpl() {
		try {
			int version = NumberConversions.toInt(NMSVersion.replace("_", "").substring(1, 3));
			if (version >= 18) {
				version = 18;
			}
			if (version <= 17) {
				version = 17;
			}
			
			return (NMSCore) Class.forName(NMSUtils.class.getPackage().getName() + ".NMSImpl" + version).newInstance();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected static Class<?> getClass(String name) {
		try {
			name = name.replaceAll("(obc|nms)", "$1\\." + NMSVersion);
			name = name.replace("obc", "org.bukkit.craftbukkit");
			name = name.replace("nms", "net.minecraft.server");
			return Class.forName(name);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected static Constructor<?> getConstructor(final Class<?> clazz, final Class<?>... classes) {
		try {
			return clazz.getConstructor(classes);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected static Method getMethod(final Class<?> clazz, final String method, final Class<?>... classes) {
		try {
			return Reflect.getMethod(clazz, method, classes);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void sendPacket(final Player p, final Object packet) {
		try {
			if (!packetClass.isAssignableFrom(packet.getClass())) {
				throw new IllegalArgumentException(packet.getClass().getName() + " is not a " + packetClass.getName());
			}
			
			sendPacket.invoke(Reflect.get(getHandlePlayer.invoke(p), "playerConnection"), packet);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Object getPacket(final String name) {
		try {
			final Class<?> packet = getClass("nms." + name);
			
			if (!packetClass.isAssignableFrom(packet)) {
				throw new IllegalArgumentException(packet.getCanonicalName() + " is not a " + packetClass.getCanonicalName());
			}
			return packet.newInstance();
		} catch (final Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Object getNMSWorld(final World world) {
		try {
			return nmsWorld.cast(getHandleWorld.invoke(world));
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Object getSignEditPacket(final int x, final int y, final int z) {
		return impl.getSignEdit(x, y, z);
	}
	
	public static Object getSignChangePacket(final Block sign, final String... lines) {
		return impl.getSignChange(sign, lines);
	}
	
	public static void sendEditor(final Player p, final Block sign) {
		impl.sendSignEditor(p, sign);
	}
	
	public static Object getTile(final Block sign) {
		return impl.getTileEntity(sign);
	}
	
	public static String getVersion() {
		try {
			return Reflect.invoke(getHandleServer.invoke(Bukkit.getServer()), "getVersion");
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
