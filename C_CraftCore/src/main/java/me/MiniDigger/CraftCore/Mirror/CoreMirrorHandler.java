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
package me.MiniDigger.CraftCore.Mirror;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketAdapter.AdapterParameteters;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.injector.GamePhase;
import com.comphenix.protocol.reflect.EquivalentConverter;
import com.comphenix.protocol.reflect.PrettyPrinter;
import com.comphenix.protocol.reflect.PrettyPrinter.ObjectPrinter;
import com.comphenix.protocol.utility.MinecraftReflection;
import com.comphenix.protocol.wrappers.BukkitConverters;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Mirror.MirrorHandler;
import me.MiniDigger.Core.Mirror.MirrorReviever;
import me.MiniDigger.Core.Mirror.MirrorSender;

import net.sf.cglib.proxy.Factory;

public class CoreMirrorHandler implements MirrorHandler {

	private static final String	IP		= "localhost";
	private static final int	PORT	= 1337;

	private MirrorReviever	reviever;
	private MirrorSender	sender;

	@SuppressWarnings("unused")
	@Override
	public void init() {
		if (true) {
			return;// TODO Mirror?!
		}
		try {
			if (System.getProperty("mirrorserver") != null && System.getProperty("mirrorserver").equalsIgnoreCase("true")) {
				sender = new CoreMirrorSender();
				sender.init(PORT);
			} else if (System.getProperty("mirrorclient") != null && System.getProperty("mirrorclient").equalsIgnoreCase("true")) {
				reviever = new CoreMirrorReciever();
				reviever.init(IP, PORT);
				return;
			} else {
				return;
			}
		}
		catch (final Exception ex) {
			Core.getCore().getInstance().debug("ex" + ex.getMessage());
			return;
		}

		new CoreEventHandler().init();
		if (true) {
			return;
		}

		final AdapterParameteters params = new AdapterParameteters();
		final Set<PacketType> types = new HashSet<PacketType>();
		for (final PacketType type : PacketType.values()) {
			types.add(type);
		}
		params.gamePhase(GamePhase.PLAYING).optionAsync().plugin(Core.getCore().getInstance()).serverSide().types(types);
		ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(params) {

			@Override
			public void onPacketSending(final PacketEvent event) {
				try {
					// printInformation(event);
					Core.getCore().getInstance().debug("got " + event.getPacketType().name());
					sender.send(event);
				}
				catch (final Exception e) {

				}
			}

			@Override
			public void onPacketReceiving(final PacketEvent event) {
				try {
					// printInformation(event);
				}
				catch (final Exception e) {

				}
			}
		});
	}

	@Override
	public MirrorReviever getReceiver() {
		return reviever;
	}

	@Override
	public MirrorSender getSender() {
		return sender;
	}

	@SuppressWarnings("unused")
	private void printInformation(final PacketEvent event) {
		final String verb = event.isServerPacket() ? "Sent" : "Received";
		final String format = event.isServerPacket() ? "%s %s to %s" : "%s %s from %s";

		final String shortDescription = String.format(format, event.isCancelled() ? "Cancelled" : verb, event.getPacketType(), event.getPlayer().getName());

		try {
			Core.getCore().getInstance().info(shortDescription + ":\n" + getPacketDescription(event.getPacket()));
		}
		catch (final IllegalAccessException e) {
			Core.getCore().getInstance().error("Unable to use reflection.");
			e.printStackTrace();
		}
	}

	public String getPacketDescription(final PacketContainer packetContainer) throws IllegalAccessException {
		final Object packet = packetContainer.getHandle();
		Class<?> clazz = packet.getClass();

		// Get the first Minecraft super class
		while (clazz != null && clazz != Object.class && (!MinecraftReflection.isMinecraftClass(clazz) || Factory.class.isAssignableFrom(clazz))) {
			clazz = clazz.getSuperclass();
		}

		return PrettyPrinter.printObject(packet, clazz, MinecraftReflection.getPacketClass(), PrettyPrinter.RECURSE_DEPTH, new ObjectPrinter() {

			@Override
			public boolean print(final StringBuilder output, final Object value) {
				final EquivalentConverter<Object> converter = findConverter(value.getClass());
				if (converter != null) {
					output.append(converter.getSpecific(value));
					return true;
				}
				return false;
			}
		});
	}

	private EquivalentConverter<Object> findConverter(Class<?> clazz) {
		final Map<Class<?>, EquivalentConverter<Object>> converters = BukkitConverters.getConvertersForGeneric();

		while (clazz != null) {
			final EquivalentConverter<Object> result = converters.get(clazz);

			if (result != null) {
				return result;
			} else {
				clazz = clazz.getSuperclass();
			}
		}
		return null;
	}
}
