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
package me.MiniDigger.CraftCore.Server;

import java.util.ArrayList;

import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.IChatBaseComponent;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitTask;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Server.Server;
import me.MiniDigger.Core.Server.ServerHandler;

import me.MiniDigger.CraftCore.CoreMain;
import me.MiniDigger.CraftCore.Packet.Packets.ServerPacket;

import mkremins.fanciful.FancyMessage;

public class CoreServerHandler implements ServerHandler {
	
	private final ArrayList<Server>	servers	= new ArrayList<>();
	private BukkitTask	            task;
	
	@Override
	public void startTask() {
		task = Bukkit.getScheduler().runTaskTimer((CoreMain) Core.getCore().getInstance(), new Runnable() {
			
			@Override
			public void run() {
				Core.getCore().getPacketHandler().sendPacket(new ServerPacket(CoreServer.getForThis(true)));
			}
		}, 5 * 20, 1 * 20);
	}
	
	@Override
	public void stopTask() {
		task.cancel();
	}
	
	@Override
	public IChatBaseComponent[] getServerInfo(final IChatBaseComponent[] lines) {
		final IChatBaseComponent[] newLines = new IChatBaseComponent[4];
		
		FancyMessage msg;
		final Server server = getServerInfo(lines[1].getText());
		if (server == null || !server.isOnline()) {
			msg = new FancyMessage("██████████").color(ChatColor.DARK_RED);
			newLines[0] = ChatSerializer.a(msg.toJSONString());
			msg = new FancyMessage("RESTART").color(ChatColor.RED).style(ChatColor.BOLD, ChatColor.UNDERLINE);
			newLines[1] = ChatSerializer.a(msg.toJSONString());
			msg = new FancyMessage("Bitte warten!").color(ChatColor.RED);
			newLines[2] = ChatSerializer.a(msg.toJSONString());
			msg = new FancyMessage("██████████").color(ChatColor.DARK_RED);
			newLines[3] = ChatSerializer.a(msg.toJSONString());
		} else if (server.getName().contains("lobby")) {
			msg = new FancyMessage("██████████").color(ChatColor.DARK_GREEN);
			newLines[0] = ChatSerializer.a(msg.toJSONString());
			msg = new FancyMessage("Back to").color(ChatColor.GREEN);
			newLines[1] = ChatSerializer.a(msg.toJSONString());
			msg = new FancyMessage("Hub!").color(ChatColor.GREEN);
			newLines[2] = ChatSerializer.a(msg.toJSONString());
			msg = new FancyMessage("██████████").color(ChatColor.DARK_GREEN);
			newLines[3] = ChatSerializer.a(msg.toJSONString());
		} else if (server.isFull() && !server.isJoin()) {
			msg = new FancyMessage("[Full]").color(ChatColor.DARK_RED);
			newLines[0] = ChatSerializer.a(msg.toJSONString());
			msg = new FancyMessage(server.getPhase()).color(ChatColor.DARK_BLUE);
			newLines[1] = ChatSerializer.a(msg.toJSONString());
			msg = new FancyMessage(server.getNumPlayers() + "/" + server.getMaxPlayers()).color(ChatColor.DARK_BLUE);
			newLines[2] = ChatSerializer.a(msg.toJSONString());
			msg = new FancyMessage(server.getName()).color(ChatColor.DARK_BLUE);
			newLines[3] = ChatSerializer.a(msg.toJSONString());
		} else if (server.isFull()) {
			msg = new FancyMessage("[Full]").color(ChatColor.DARK_RED);
			newLines[0] = ChatSerializer.a(msg.toJSONString());
			msg = new FancyMessage(server.getPhase()).color(ChatColor.DARK_BLUE);
			newLines[1] = ChatSerializer.a(msg.toJSONString());
			msg = new FancyMessage(server.getNumPlayers() + "/" + server.getMaxPlayers()).color(ChatColor.DARK_BLUE);
			newLines[2] = ChatSerializer.a(msg.toJSONString());
			msg = new FancyMessage(server.getName()).color(ChatColor.DARK_BLUE);
			newLines[3] = ChatSerializer.a(msg.toJSONString());
		} else if (server.isJoin()) {
			msg = new FancyMessage("[Join]").color(ChatColor.AQUA);
			newLines[0] = ChatSerializer.a(msg.toJSONString());
			msg = new FancyMessage(server.getPhase()).color(ChatColor.DARK_GREEN);
			newLines[1] = ChatSerializer.a(msg.toJSONString());
			msg = new FancyMessage(server.getNumPlayers() + "/" + server.getMaxPlayers()).color(ChatColor.DARK_GREEN);
			newLines[2] = ChatSerializer.a(msg.toJSONString());
			msg = new FancyMessage(server.getName()).color(ChatColor.DARK_GREEN);
			newLines[3] = ChatSerializer.a(msg.toJSONString());
		} else if (server.isSpectate()) {
			msg = new FancyMessage("[Specatete]").color(ChatColor.YELLOW);
			newLines[0] = ChatSerializer.a(msg.toJSONString());
			msg = new FancyMessage(server.getPhase()).color(ChatColor.GOLD);
			newLines[1] = ChatSerializer.a(msg.toJSONString());
			msg = new FancyMessage(server.getNumPlayers() + "/" + server.getMaxPlayers()).color(ChatColor.GOLD);
			newLines[2] = ChatSerializer.a(msg.toJSONString());
			msg = new FancyMessage(server.getName()).color(ChatColor.GOLD);
			newLines[3] = ChatSerializer.a(msg.toJSONString());
		}
		
		return newLines;
	}
	
	@Override
	public Server getServerInfo(final String name) {
		synchronized (servers) {
			for (final Server s : servers) {
				if (s.getName().equalsIgnoreCase(name)) {
					return s;
				}
			}
		}
		return null;
	}
	
	@Override
	public void gotServerInfo(final Server server) {
		if (getServerInfo(server.getName()) == null) {
			servers.add(server);
		} else {
			servers.remove(server);
			servers.add(server);
		}
	}
	
}
