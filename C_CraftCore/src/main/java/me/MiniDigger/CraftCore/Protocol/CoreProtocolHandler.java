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
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.reflect.StructureModifier;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.comphenix.protocol.wrappers.WrappedServerPing;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Protocol.ProtocolHandler;
import me.MiniDigger.Core.Protocol.SignChangers;
import me.MiniDigger.Core.Protocol.SignListeners;
import me.MiniDigger.Core.Protocol.SkullChangers;
import me.MiniDigger.Core.User.User;

public class CoreProtocolHandler implements ProtocolHandler {
	
	private String	                   fame;
	private final ProtocolManager	   manager	         = ProtocolLibrary.getProtocolManager();
	
	private SignChangers	           signChangers;
	private SkullChangers	           skullChangers;
	private SignListeners	           signListeners;
	
	private final Map<String, Integer>	protocolVersions	= new HashMap<>();
	
	private List<UUID>	               noUpdates	     = new ArrayList<UUID>();
	
	@Override
	public void init() {
		signChangers = new CoreSignChangers();
		skullChangers = new CoreSkullChangers();
		signListeners = new CoreSignListeners();
		
		signChangers.init();
		skullChangers.init();
		signListeners.init();
		
		Bukkit.getScheduler().runTaskTimer(Core.getCore().getInstance(), new Runnable() {
			
			@Override
			public void run() {
				final String[] names = new String[] { "MiniDigger", "Notch", "jeb_", "Dinnerbone", };
				
				try {
					fame = Core.getCore().getUserHandler().getOnlinePlayers()
					        .get(Core.getCore().getRandomUtil().nextInt(Core.getCore().getUserHandler().getOnlinePlayers().size())).getName();
				} catch (final Throwable e) {
					fame = names[Core.getCore().getRandomUtil().nextInt(names.length)];
				}
			}
		}, 2 * 20, 2 * 20);
		
		Bukkit.getScheduler().runTaskTimerAsynchronously(Core.getCore().getInstance(), new Runnable() {
			
			@Override
			public void run() {
				signChangers.update(noUpdates);
				skullChangers.update(noUpdates);
			}
		}, 2 * 20, 2 * 20);
		
		manager.addPacketListener(new PacketAdapter(Core.getCore().getInstance(), PacketType.Play.Server.UPDATE_SIGN) {
			
			@Override
			public void onPacketSending(final PacketEvent event) {
				signChangers.handlePacket(event);
			}
		});
		// Skulls
		manager.addPacketListener(new PacketAdapter(Core.getCore().getInstance(), PacketType.Play.Server.TILE_ENTITY_DATA) {
			
			@Override
			public void onPacketSending(final PacketEvent event) {
				skullChangers.handlePacket(event);
			}
		});
		// Ping
		manager.addPacketListener(new PacketAdapter(Core.getCore().getInstance(), ListenerPriority.NORMAL, Arrays.asList(PacketType.Status.Server.OUT_SERVER_INFO)) {
			
			@Override
			public void onPacketSending(final PacketEvent event) {
				handlePing(event.getPacket().getServerPings().read(0));
			}
		});
		// Zensor
		manager.addPacketListener(new PacketAdapter(Core.getCore().getInstance(), PacketType.Play.Server.CHAT) {
			
			@Override
			public void onPacketSending(final PacketEvent event) {
				final WrappedChatComponent chat = event.getPacket().getChatComponents().read(0);
				
				if (chat.getJson().contains("%name%")) {
					chat.setJson(chat.getJson().replaceAll("%name%", event.getPlayer().getDisplayName() + " du Boss"));
					event.getPacket().getChatComponents().write(0, chat);
				}
			}
		});
		// TabBlocker
		manager.addPacketListener(new PacketAdapter(Core.getCore().getInstance(), PacketType.Play.Client.TAB_COMPLETE) {
			
			@Override
			@SuppressWarnings("unchecked")
			public void onPacketReceiving(final PacketEvent event) {
				if (event.getPacketType() == PacketType.Play.Client.TAB_COMPLETE) {
					final User user = Core.getCore().getUserHandler().get(event.getPlayer().getUniqueId());
					if (!user.hasPermission("tabcomplete") && event.getPacket().getStrings().read(0).startsWith("/")) {
						event.setCancelled(true);
						List<String> list = new ArrayList<String>();
						final String start = event.getPacket().getStrings().read(0);
						
						try {
							list.addAll((List<? extends String>) Bukkit.getServer().getClass().getMethod("tabCompleteCommand", Player.class, String.class)
							        .invoke(Bukkit.getServer(), event.getPlayer(), start));
						} catch (final Exception e) {
							e.printStackTrace();
						}
						
						final List<String> allowed = new ArrayList<>();
						// TODO Fix tabblocker
						// for (Command cmd :
						// Core.getCore().getCommandHandler().getCommands()) {
						// if (user.hasPermission(cmd.permission())) {
						// allowed.add(cmd.getMainCommand());
						// }
						// }
						
						final List<String> newList = new ArrayList<>();
						for (final String s : list) {
							if (allowed.contains(s.replaceFirst("/", ""))) {
								newList.add(s);
							}
						}
						
						list = newList;
						
						final String[] tabList = new String[list.size()];
						
						for (int index = 0; index < list.size(); index++) {
							tabList[index] = list.get(index);
						}
						
						final PacketContainer tabComplete = manager.createPacket(PacketType.Play.Server.TAB_COMPLETE);
						tabComplete.getStringArrays().write(0, tabList);
						try {
							manager.sendServerPacket(event.getPlayer(), tabComplete);
						} catch (final InvocationTargetException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
		
		// Protocol verison
		manager.addPacketListener(new PacketAdapter(Core.getCore().getInstance(), PacketType.Handshake.Client.SET_PROTOCOL) {
			
			@Override
			public void onPacketReceiving(final PacketEvent event) {
				protocolVersions.put(event.getPlayer().getName(), event.getPacket().getIntegers().read(0));
			}
		});
	}
	
	@Override
	@EventHandler
	public void onLogin(final PlayerLoginEvent e) {
		int protocol = 0;
		final String name = e.getPlayer().getName();
		String key = "";
		for (final String s : protocolVersions.keySet()) {
			if (s.startsWith("UNKNOWN[/")) {
				String newS = "";
				newS = s.replaceFirst(Pattern.quote("UNKNOWN[/"), "");
				newS = newS.replaceFirst("]", "");
				
				if (e.getAddress().getHostAddress().equalsIgnoreCase(newS.split(":")[0])) {
					key = s;
					protocol = protocolVersions.get(s);
					break;
				}
			}
		}
		
		protocolVersions.remove(key);
		protocolVersions.put(name, protocol);
		
		System.out.println(name + "'s protocol version is " + protocol);
	}
	
	@Override
	@EventHandler
	public void onQuit(final PlayerQuitEvent e) {
		if (protocolVersions.containsKey(e.getPlayer().getName())) {
			protocolVersions.remove(e.getPlayer().getName());
		}
	}
	
	@Override
	public int getProtocolVersion(final String name) {
		return protocolVersions.get(name);
	}
	
	@SuppressWarnings("deprecation")
	private void handlePing(final WrappedServerPing ping) {
		ping.setPlayers(Arrays.asList(new WrappedGameProfile("1", ChatColor.RED + "Hallo und willkommen"), new WrappedGameProfile("2", "auf Zone-Games.eu!"),
		        new WrappedGameProfile("3", "Hier findest du vieles.")));
		ping.setVersionName("Please use 1.7.10");
		ping.setPlayersOnline(100);
		ping.setPlayersMaximum(-1);
	}
	
	@Override
	public ProtocolManager getManager() {
		return manager;
	}
	
	@Override
	public String getFame() {
		return fame;
	}
	
	@Override
	public SignChangers getSignChangers() {
		return signChangers;
	}
	
	@Override
	public SkullChangers getSkullSChangers() {
		return skullChangers;
	}
	
	@Override
	public SignListeners getSignListeners() {
		return signListeners;
	}
	
	@Override
	public void noUpdates(UUID id) {
		if (!noUpdates.contains(id)) {
			noUpdates.add(id);
		}
	}
	
	@Override
	public void updates(UUID id) {
		if (noUpdates.contains(id)) {
			noUpdates.remove(id);
		}
	}
	
	@Override
	public boolean toggleUpdates(UUID id) {
		if (noUpdates.contains(id)) {
			noUpdates.remove(id);
			return false;
		} else {
			noUpdates.add(id);
			return true;
		}
	}
	
	@Override
	public List<String> packetcontainerToString(PacketContainer c) {
		List<String> result = new ArrayList<String>();
		
		for (Method m : c.getClass().getMethods()) {
			if (m.getName().startsWith("get")) {
				if (m.getReturnType().getName().contains("StructureModifier")) {
					try {
						StructureModifier<?> mod = (StructureModifier<?>) m.invoke(c);
						result.add(m.getName() + ": " + mod.size());
					} catch (Exception e) {
//						e.printStackTrace();
					}
				}
			}
		}
		
		return result;
	}
}
