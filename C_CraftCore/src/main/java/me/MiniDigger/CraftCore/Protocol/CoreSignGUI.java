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
package me.MiniDigger.CraftCore.Protocol;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.BlockPosition;
import com.comphenix.protocol.wrappers.WrappedChatComponent;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Protocol.SignGUI;

public class CoreSignGUI implements SignGUI, Listener {

	protected ProtocolManager				protocolManager;
	protected PacketAdapter					packetListener;
	protected Map<String, SignGUIListener>	listeners;
	protected Map<String, Vector>			signLocations;
	protected Plugin						plugin;

	public CoreSignGUI() {
		plugin = Core.getCore().getInstance();
		init();
	}

	@Override
	public void open(final Player player, final String[] defaultText, final SignGUIListener response) {
		final List<PacketContainer> packets = new ArrayList<PacketContainer>();

		int x = 0;
		final int y = 0;
		int z = 0;
		WrappedChatComponent[] lines;
		if (defaultText != null) {
			x = player.getLocation().getBlockX();
			z = player.getLocation().getBlockZ();

			lines = new WrappedChatComponent[defaultText.length];
			for (int i = 0; i < defaultText.length; i++) {
				lines[i] = WrappedChatComponent.fromText(defaultText[i]);
			}

			final PacketContainer packet53 = protocolManager.createPacket(PacketType.Play.Server.BLOCK_CHANGE);
			// packet53.getIntegers().write(0, x).write(1, y).write(2, z);
			packet53.getBlockPositionModifier().write(0, new BlockPosition(x, y, z));
			// packet53.getIntegers().write(0,
			// org.bukkit.Material.SIGN_POST.getId());

			Core.getCore().getInstance().debug("==========" + packet53.getType().name() + "=========");
			for (final String s : Core.getCore().getProtocolHandler().packetcontainerToString(packet53)) {
				// if (s.endsWith("0")) {
				// continue;
				// }
				Core.getCore().getInstance().debug(s);
			}
			Core.getCore().getInstance().debug("=========================================");

			// packet53.getBlocks().write(0, Material.SIGN_POST);
			// packet53.getIntegers().write(0, Material.SIGN_POST.getId() << 4 |
			// 0x0);
			packets.add(packet53);

			final PacketContainer packet130 = protocolManager.createPacket(PacketType.Play.Server.UPDATE_SIGN);
			// packet130.getIntegers().write(0, x).write(1, y).write(2, z);
			packet130.getBlockPositionModifier().write(0, new BlockPosition(x, y, z));
			// packet130.getStringArrays().write(0, defaultText);
			packet130.getChatComponentArrays().write(0, lines);
			packets.add(packet130);
		}

		final PacketContainer packet133 = protocolManager.createPacket(PacketType.Play.Server.OPEN_SIGN_ENTITY);
		// packet133.getIntegers().write(0, x).write(2, z);
		packet133.getBlockPositionModifier().write(0, new BlockPosition(x, y, z));
		packets.add(packet133);

		if (defaultText != null) {
			final PacketContainer packet53 = protocolManager.createPacket(PacketType.Play.Server.BLOCK_CHANGE);
			packet53.getBlockPositionModifier().write(0, new BlockPosition(x, y, z));
			// packet53.getBlocks().write(0, org.bukkit.Material.BEDROCK);
			// packet53.getIntegers().write(0, Material.BEDROCK.getId() << 4 |
			// 0x0);
			packets.add(packet53);
		}

		try {
			for (final PacketContainer packet : packets) {
				protocolManager.sendServerPacket(player, packet);
			}
			signLocations.put(player.getName(), new Vector(x, y, z));
			listeners.put(player.getName(), response);
		}
		catch (final InvocationTargetException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void destroy() {
		protocolManager.removePacketListener(packetListener);
		listeners.clear();
		signLocations.clear();
	}

	@Override
	public void init() {
		protocolManager = ProtocolLibrary.getProtocolManager();
		listeners = new ConcurrentHashMap<String, SignGUIListener>();
		signLocations = new ConcurrentHashMap<String, Vector>();

		Bukkit.getPluginManager().registerEvents(this, plugin);

		ProtocolLibrary.getProtocolManager().addPacketListener(packetListener = new PacketAdapter(plugin, PacketType.Play.Client.UPDATE_SIGN) {

			@Override
			public void onPacketReceiving(final PacketEvent event) {
				final Player player = event.getPlayer();

				final Vector v = signLocations.remove(player.getName());
				if (v == null) {
					return;
				}

				final BlockPosition pos = event.getPacket().getBlockPositionModifier().read(0);
				if (!pos.equals(new BlockPosition(v))) {
					return;
				}

				// final String[] lines =
				// event.getPacket().getStringArrays().getValues().get(0);
				final WrappedChatComponent[] chatLines = event.getPacket().getChatComponentArrays().read(0);
				final String[] lines = new String[chatLines.length];
				for (int i = 0; i < chatLines.length; i++) {
					try {
						lines[i] = chatLines[i].getJson().replaceAll("^\"|\"$", "");
						// ChatColor color =
						// Core.getCore().getChatColorUtil().toChatColor(lines[i]);
						// lines[i] = "{\"text\":\"" + lines[i] +
						// "\",\"color\":\"" + color + "\"}";
						lines[i] = ChatColor.translateAlternateColorCodes('&', lines[i]);
					}
					catch (final Exception ex) {
						Core.getCore().getInstance().debug("error while reading default text?");
						ex.printStackTrace();
						lines[i] = "fail";
					}
				}

				final SignGUIListener response = listeners.remove(event.getPlayer().getName());
				if (response != null) {
					event.setCancelled(true);
					Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

						@Override
						public void run() {
							response.onSignDone(player, lines);
						}
					});
				}
			}
		});
	}

	@Override
	public void open(final Player player, final SignGUIListener response) {
		open(player, null, response);
	}

	@Override
	public void cleanupPlayer(final Player player) {
		listeners.remove(player.getUniqueId());
		signLocations.remove(player.getUniqueId());
	}

	@Override
	public void onQuit(final PlayerQuitEvent event) {
		cleanupPlayer(event.getPlayer());
	}

}
