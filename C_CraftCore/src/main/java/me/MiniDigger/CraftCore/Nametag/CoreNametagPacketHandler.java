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
package me.MiniDigger.CraftCore.Nametag;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CoreNametagPacketHandler {
	
	private final Object	packet;
	
	private static Class<?>	packetType;
	
	private static Method	getHandle;
	private static Method	sendPacket;
	private static Field	playerConnection;
	
	private static String	version	         = "";
	private static String	fieldPrefix	     = "";
	private static String	fieldSuffix	     = "";
	private static String	fieldTag	     = "";
	private static String	fieldPlayers	 = "";
	private static String	fieldTeamName	 = "";
	private static String	fieldParamInt	 = "";
	private static String	fieldPackOption	 = "";
	private static String	fieldDisplayName	= "";
	
	static {
		try {
			version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
			
			final Class<?> typeNMSPlayer = Class.forName("net.minecraft.server." + version + ".EntityPlayer");
			final Class<?> typeCraftPlayer = Class.forName("org.bukkit.craftbukkit." + version + ".entity.CraftPlayer");
			final Class<?> typePlayerConnection = Class.forName("net.minecraft.server." + version + ".PlayerConnection");
			
			getHandle = typeCraftPlayer.getMethod("getHandle");
			playerConnection = typeNMSPlayer.getField("playerConnection");
			sendPacket = typePlayerConnection.getMethod("sendPacket", Class.forName("net.minecraft.server." + version + ".Packet"));
			
			if (version.startsWith("v1_8")) {
				fieldPrefix = "c";
				fieldSuffix = "d";
				fieldTag = "e";
				fieldPlayers = "g";
				fieldTeamName = "a";
				fieldParamInt = "h";
				fieldPackOption = "i";
				fieldDisplayName = "b";
				
			} else {
				fieldPrefix = "c";
				fieldSuffix = "d";
				fieldPlayers = "e";
				fieldTeamName = "a";
				fieldParamInt = "f";
				fieldPackOption = "g";
				fieldDisplayName = "b";
				
			}
			
			if (version.startsWith("v1_5")) {
				packetType = Class.forName("net.minecraft.server." + version + ".Packet209SetScoreboardTeam");
			} else {
				packetType = Class.forName("net.minecraft.server." + version + ".PacketPlayOutScoreboardTeam");
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
	public CoreNametagPacketHandler(final String name, final String prefix, final String suffix, final Collection<String> players, final int paramInteger,
	        final boolean tag) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, NoSuchFieldException,
	        InvocationTargetException {
		
		packet = packetType.newInstance();
		setField(fieldTeamName, name);
		setField(fieldParamInt, paramInteger);
		
		if (paramInteger == 0 || paramInteger == 2) {
			setField(fieldDisplayName, name);
			setField(fieldPrefix, prefix);
			setField(fieldSuffix, suffix);
			setField(fieldPackOption, 1);
			setField(fieldTag, tag ? "always" : "never");
		}
		
		if (paramInteger == 0) {
			addAll(players);
		}
	}
	
	public CoreNametagPacketHandler(final String name, Collection<String> players, final int paramInt) throws ClassNotFoundException, IllegalAccessException,
	        InstantiationException, NoSuchMethodException, NoSuchFieldException, InvocationTargetException {
		packet = packetType.newInstance();
		
		if (paramInt != 3 && paramInt != 4) {
			throw new IllegalArgumentException("Method must be join or leave for player constructor");
		}
		
		if (players == null || players.isEmpty()) {
			players = new ArrayList<String>();
		}
		
		setField(fieldTeamName, name);
		setField(fieldParamInt, paramInt);
		addAll(players);
	}
	
	public void sendToPlayer(final Player bukkitPlayer) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException,
	        NoSuchMethodException, NoSuchFieldException {
		final Object player = getHandle.invoke(bukkitPlayer);
		
		final Object connection = playerConnection.get(player);
		
		sendPacket.invoke(connection, packet);
	}
	
	private void setField(final String field, final Object value) throws NoSuchFieldException, IllegalAccessException {
		final Field f = packet.getClass().getDeclaredField(field);
		f.setAccessible(true);
		f.set(packet, value);
	}
	
	@SuppressWarnings("all")
	private void addAll(final Collection<String> col) throws NoSuchFieldException, IllegalAccessException {
		final Field f = packet.getClass().getDeclaredField(fieldPlayers);
		f.setAccessible(true);
		((Collection) f.get(packet)).addAll(col);
	}
}