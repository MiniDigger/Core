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
package me.MiniDigger.CraftCore.Command.Commands;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.CoreMain;

public class TrollCommands {
	
	static {
		ChunkBreaker.enable();
	}
	
	@Command(name = "troll", description = "Trolling", permission = "troll", min = 2)
	public void troll(final CommandArgs args) {
		Prefix.API.getPrefix().then("Trollolololol").send(args.getSender());
		Prefix.API.getPrefix().then("Sub-Commands: gtc,breakChunk,unbreakChunk").send(args.getSender());
	}
	
	@Command(name = "troll.gtc", description = "Trolling", usage = "troll gtc <spieler> <3/4/5>", permission = "troll", min = 2)
	public void gtc(final CommandArgs args) {
		ForceCredits.force(Bukkit.getPlayer(args.getArgs()[0]), Integer.parseInt(args.getArgs()[1]));
	}
	
	@Command(name = "troll.breakChunk", description = "Break the Chunk ;D", usage = "troll breakChunk <spieler> <chunkX> <chunkY>", permission = "troll", min = 3)
	public void breakChunk(final CommandArgs args) {
		final User user = Core.getCore().getUserHandler().get(Bukkit.getPlayer(args.getArgs()[0]).getUniqueId());
		final int x = Integer.parseInt(args.getArgs()[1]);
		final int y = Integer.parseInt(args.getArgs()[2]);
		
		ChunkBreaker.breakChunk(user, x, y);
		args.getUser().sendMessage(Prefix.API.getPrefix().then("Chnuk im Arsch ;D"));
	}
	
	@Command(name = "troll.unbreakChunk", description = "UnBreak the Chunk ;D", usage = "troll unbreakChunk <spieler> <chunkX> <chunkY>", permission = "troll", min = 3)
	public void unbreakChunk(final CommandArgs args) {
		final User user = Core.getCore().getUserHandler().get(Bukkit.getPlayer(args.getArgs()[0]).getUniqueId());
		final int x = Integer.parseInt(args.getArgs()[1]);
		final int y = Integer.parseInt(args.getArgs()[2]);
		
		ChunkBreaker.unbreakChunk(user, x, y);
		args.getUser().sendMessage(Prefix.API.getPrefix().then("Chunk gefixed"));
	}
	
	private static class ChunkBreaker {
		
		private static HashMap<UUID, ArrayList<String>>	breakedChunks	= new HashMap<>();
		
		public static void breakChunk(final User user, final int x, final int y) {
			if (breakedChunks.containsKey(user.getUUID())) {
				final ArrayList<String> old = breakedChunks.get(user.getUUID());
				old.add(x + ":" + y);
				breakedChunks.remove(user.getUUID());
				breakedChunks.put(user.getUUID(), old);
			} else {
				final ArrayList<String> old = new ArrayList<>();
				old.add(x + ":" + y);
				breakedChunks.put(user.getUUID(), old);
			}
		}
		
		public static void unbreakChunk(final User user, final int x, final int y) {
			if (breakedChunks.containsKey(user.getUUID())) {
				final ArrayList<String> old = breakedChunks.get(user.getUUID());
				if (old.contains(x + ":" + y)) {
					old.remove(x + ":" + y);
				}
				breakedChunks.remove(user.getUUID());
				breakedChunks.put(user.getUUID(), old);
			}
		}
		
		public static void enable() {
			ProtocolLibrary.getProtocolManager().addPacketListener(
			        new PacketAdapter( Core.getCore().getInstance(), PacketType.Play.Server.MAP_CHUNK, PacketType.Play.Server.MAP_CHUNK_BULK) {
				        
				        @Override
				        public void onPacketSending(final PacketEvent event) {
					        final PacketContainer packet = event.getPacket();
					        
					        if (event.getPacketType() == PacketType.Play.Server.MAP_CHUNK) {
						        processChunk(event, packet);
					        } else {
						        processChunkBulk(event, packet);
					        }
				        }
			        });
		}
		
		private static void processChunk(final PacketEvent event, final PacketContainer packet) {
			if (filterChunk(event.getPlayer().getName(), packet.getIntegers().read(0), packet.getIntegers().read(1))) {
				event.setCancelled(true);
			}
		}
		
		private static void processChunkBulk(final PacketEvent event, final PacketContainer packet) {
			// This is the trick - we actually move the chunk millions of
			// miles away ...
			final int[] chunkX = packet.getIntegerArrays().read(0);
			final int[] chunkZ = packet.getIntegerArrays().read(1);
			final String name = event.getPlayer().getName();
			
			for (int i = 0; i < chunkZ.length; i++) {
				if (filterChunk(name, chunkX[i], chunkZ[i])) {
					chunkX[i] = Integer.MAX_VALUE;
					chunkZ[i] = Integer.MAX_VALUE;
				}
			}
		}
		
		protected static boolean filterChunk(final String name, final int chunkX, final int chunkZ) {
			// Remove the (0,0) chunk for a specific player
			return "aadnk".equals(name) && chunkX == 0 && chunkZ == 0;
			// TODO Fix chunkbreaker
		}
	}
}

class ForceCredits {
	
	/*
	 * Class made by BigTeddy98.
	 * 
	 * ForceCredits allows you to force the end game credits.
	 * 
	 * 1. No warranty is given or implied. 2. All damage is your own
	 * responsibility. 3. If you want to use this in your plugins, a credit
	 * would we appreciated.
	 */
	
	// everything needed for the reflection
	private static Constructor<?>	packetPlayOutGameStateChange;
	private static Method	      getHandle;
	private static Field	      getPlayerConnection;
	private static Method	      sendPacket;
	
	static {
		try {
			// reflection
			// constructor of the packet
			packetPlayOutGameStateChange = getMCClass("PacketPlayOutGameStateChange").getConstructor(int.class, float.class);
			// easy way to get EntityPlayer from CraftPlayer
			getHandle = getCraftClass("entity.CraftPlayer").getMethod("getHandle");
			// field of the playerconnetion
			getPlayerConnection = getMCClass("EntityPlayer").getDeclaredField("playerConnection");
			// the method for sending the packet
			sendPacket = getMCClass("PlayerConnection").getMethod("sendPacket", getMCClass("Packet"));
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * playCredits(Player p) Player p - Player to display the credits to
	 */
	
	public static void force(final Player p, final int i) {
		try {
			final Object packet = packetPlayOutGameStateChange.newInstance(i, (float) 0);
			final Object nms_entity = getHandle.invoke(p);
			final Object nms_connection = getPlayerConnection.get(nms_entity);
			sendPacket.invoke(nms_connection, packet);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
	// easy way to get NMS classes
	private static Class<?> getMCClass(final String name) throws ClassNotFoundException {
		final String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + ".";
		final String className = "net.minecraft.server." + version + name;
		return Class.forName(className);
	}
	
	// easy way to get CraftBukkit classes
	private static Class<?> getCraftClass(final String name) throws ClassNotFoundException {
		final String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3] + ".";
		final String className = "org.bukkit.craftbukkit." + version + name;
		return Class.forName(className);
	}
}
