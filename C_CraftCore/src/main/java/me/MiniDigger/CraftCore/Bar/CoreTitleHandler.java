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

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.craftbukkit.v1_8_R3.util.CraftChatMessage;
import org.bukkit.entity.Player;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Bar.TitleHandler;
import me.MiniDigger.Core.Lang.LogLevel;

import me.MiniDigger.CraftCore.Lang.MSG;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PlayerConnection;

public class CoreTitleHandler implements TitleHandler {

	private boolean		initialized;
	private Class<?>	nmsChatSerializer;
	private Class<?>	nmsPacketTitle;
	private Class<?>	nmsTitleAction;
	private Class<?>	nmsPlayerConnection;
	private Class<?>	nmsEntityPlayer;
	private Class<?>	nmsChatBaseComponent;
	private Class<?>	ioNettyChannel;
	private Method		nmsSendPacket;
	@SuppressWarnings("unused")
	private Method		nmsChatSerializerA;
	private Method		nmsNetworkGetVersion;
	private Field		nmsFieldPlayerConnection;
	private Field		nmsFieldNetworkManager;
	private Field		nmsFieldNetworkManagerI;
	private Field		nmsFieldNetworkManagerM;
	private double		serverVersion;
	private final int	VERSION;

	public CoreTitleHandler() {
		serverVersion = 1.8;
		VERSION = 47;
		if (!initialized) {
			final String ver = Core.getCore().getReflectionUtil().getVersion();
			if (ver.contains("1_7")) {
				serverVersion = 1.7;
			}
			if (ver.contains("1_8")) {
				serverVersion = 1.8;
			}
			if (ver.contains("1_8_R2")) {
				serverVersion = 1.83;
			}
			if (ver.contains("1_8_R3")) {
				serverVersion = 1.85;
			}
			try {
				nmsChatBaseComponent = Core.getCore().getReflectionUtil().getNMSClass("IChatBaseComponent");
				nmsChatSerializer = Core.getCore().getReflectionUtil().getNMSClass(
						(Core.getCore().getReflectionUtil().getVersion().contains("1_7") || Core.getCore().getReflectionUtil().getVersion().contains("1_8_R1")) ? "ChatSerializer" : "IChatBaseComponent$ChatSerializer");
				if (Core.getCore().getReflectionUtil().getVersion().contains("1_8")) {
					nmsPacketTitle = Core.getCore().getReflectionUtil().getNMSClass("PacketPlayOutTitle");
					if (serverVersion >= 1.83) {
						nmsTitleAction = Core.getCore().getReflectionUtil().getNMSClass("PacketPlayOutTitle$EnumTitleAction");
					} else {
						nmsTitleAction = Core.getCore().getReflectionUtil().getNMSClass("EnumTitleAction");
					}
				}
				nmsPlayerConnection = Core.getCore().getReflectionUtil().getNMSClass("PlayerConnection");
				nmsEntityPlayer = Core.getCore().getReflectionUtil().getNMSClass("EntityPlayer");
				ioNettyChannel = ((serverVersion == 1.7) ? Class.forName("net.minecraft.util.io.netty.channel.Channel") : Class.forName("io.netty.channel.Channel"));
				nmsFieldPlayerConnection = Core.getCore().getReflectionUtil().getField(nmsEntityPlayer, "playerConnection");
				nmsFieldNetworkManager = Core.getCore().getReflectionUtil().getField(nmsPlayerConnection, "networkManager");
				nmsFieldNetworkManagerI = Core.getCore().getReflectionUtil().getField(nmsFieldNetworkManager.getType(), "i");
				nmsFieldNetworkManagerM = Core.getCore().getReflectionUtil().getField(nmsFieldNetworkManager.getType(), "m");
				nmsSendPacket = Core.getCore().getReflectionUtil().getMethod(nmsPlayerConnection, "sendPacket", new Class[0]);
				if (nmsSendPacket == null) {
					// Core.getCore().getInstance().debug("null....");
					nmsSendPacket = PlayerConnection.class.getMethod("sendPacket", Packet.class);
				}
				nmsChatSerializerA = Core.getCore().getReflectionUtil().getMethod(nmsChatSerializer, "a", String.class);
				nmsNetworkGetVersion = Core.getCore().getReflectionUtil().getMethod(nmsFieldNetworkManager.getType(), "getVersion", ioNettyChannel);
				initialized = true;
			}
			catch (final Exception e) {
				MSG.stacktrace(LogLevel.DEBUG, e);
			}
		}
	}

	@Override
	public void sendTitle(final Player p, final String title) {
		if (p == null || title == null) {
			throw new NullPointerException();
		}
		if (getVersion(p) < VERSION) {
			return;
		}
		// if (!title.startsWith("{") || !title.endsWith("}")) {
		// title = Core.getCore().getChatColorUtil().convertToJSON(title);
		// }
		try {
			final Object handle = Core.getCore().getReflectionUtil().getHandle(p);
			final Object connection = nmsFieldPlayerConnection.get(handle);
			// final Object serialized = nmsChatSerializerA.invoke(null, title);
			// Core.getCore().getInstance().debug("title " + title);
			final Object serialized = CraftChatMessage.fromString(title)[0];
			final Object packet = nmsPacketTitle.getConstructor(nmsTitleAction, nmsChatBaseComponent).newInstance(nmsTitleAction.getEnumConstants()[0], serialized);
			nmsSendPacket.invoke(connection, packet);
		}
		catch (final Exception e) {
			MSG.stacktrace(LogLevel.DEBUG, e);
		}
	}

	@Override
	public void sendTitle(final Player p, final int fadeIn, final int stay, final int fadeOut, final String title) {
		sendTimings(p, fadeIn, stay, fadeOut);
		sendTitle(p, title);
	}

	@Override
	public void sendSubTitle(final Player p, final String subtitle) {
		if (p == null || subtitle == null) {
			throw new NullPointerException();
		}
		if (getVersion(p) < VERSION) {
			return;
		}
		// if (!subtitle.startsWith("{") || !subtitle.endsWith("}")) {
		// subtitle = Core.getCore().getChatColorUtil().convertToJSON(subtitle);
		// }
		try {
			final Object handle = Core.getCore().getReflectionUtil().getHandle(p);
			final Object connection = nmsFieldPlayerConnection.get(handle);
			// final Object serialized = nmsChatSerializerA.invoke(null,
			// subtitle);
			// final Object serialized = ChatSerializer.a(subtitle);
			final Object serialized = CraftChatMessage.fromString(subtitle)[0];
			final Object packet = nmsPacketTitle.getConstructor(nmsTitleAction, nmsChatBaseComponent).newInstance(nmsTitleAction.getEnumConstants()[1], serialized);
			nmsSendPacket.invoke(connection, packet);
		}
		catch (final Exception e) {
			MSG.stacktrace(LogLevel.DEBUG, e);
		}
	}

	@Override
	public void sendSubTitle(final Player p, final int fadeIn, final int stay, final int fadeOut, final String subtitle) {
		sendTimings(p, fadeIn, stay, fadeOut);
		sendSubTitle(p, subtitle);
	}

	@Override
	public void sendTimings(final Player p, final int fadeIn, final int stay, final int fadeOut) {
		if (p == null) {
			throw new NullPointerException();
		}
		if (getVersion(p) < VERSION) {
			return;
		}
		try {
			final Object handle = Core.getCore().getReflectionUtil().getHandle(p);
			final Object connection = nmsFieldPlayerConnection.get(handle);
			final Object packet = nmsPacketTitle.getConstructor(Integer.TYPE, Integer.TYPE, Integer.TYPE).newInstance(fadeIn, stay, fadeOut);
			nmsSendPacket.invoke(connection, packet);
		}
		catch (final Exception e) {
			MSG.stacktrace(LogLevel.DEBUG, e);
		}
	}

	@Override
	public void reset(final Player p) {
		if (p == null) {
			throw new NullPointerException();
		}
		if (getVersion(p) < VERSION) {
			return;
		}
		try {
			final Object handle = Core.getCore().getReflectionUtil().getHandle(p);
			final Object connection = nmsFieldPlayerConnection.get(handle);
			final Object packet = nmsPacketTitle.getConstructor(nmsTitleAction, nmsChatBaseComponent).newInstance(nmsTitleAction.getEnumConstants()[4], null);
			nmsSendPacket.invoke(connection, packet);
		}
		catch (final Exception e) {
			MSG.stacktrace(LogLevel.DEBUG, e);
		}
	}

	@Override
	public void clear(final Player p) {
		if (p == null) {
			throw new NullPointerException();
		}
		if (getVersion(p) < VERSION) {
			return;
		}
		try {
			final Object handle = Core.getCore().getReflectionUtil().getHandle(p);
			final Object connection = nmsFieldPlayerConnection.get(handle);
			final Object packet = nmsPacketTitle.getConstructor(nmsTitleAction, nmsChatBaseComponent).newInstance(nmsTitleAction.getEnumConstants()[3], null);
			nmsSendPacket.invoke(connection, packet);
		}
		catch (final Exception e) {
			MSG.stacktrace(LogLevel.DEBUG, e);
		}
	}

	@Override
	public int getVersion(final Player p) {
		try {
			final Object handle = Core.getCore().getReflectionUtil().getHandle(p);
			final Object connection = nmsFieldPlayerConnection.get(handle);
			final Object network = nmsFieldNetworkManager.get(connection);
			Object channel;
			if (serverVersion == 1.7) {
				nmsFieldNetworkManagerM.setAccessible(true);
				channel = nmsFieldNetworkManagerM.get(network);
			} else {
				nmsFieldNetworkManagerI.setAccessible(true);
				channel = nmsFieldNetworkManagerI.get(network);
			}
			final Object version = (serverVersion == 1.7) ? nmsNetworkGetVersion.invoke(network, channel) : 47;
			// Core.getCore().getInstance().debug("version " + version);
			return (int) version;
		}
		catch (final Exception e) {
			MSG.stacktrace(LogLevel.DEBUG, e);
			return 180;
		}
	}
}
