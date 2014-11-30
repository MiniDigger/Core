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

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.comphenix.protocol.wrappers.WrappedChatComponent;

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
		task = Bukkit.getScheduler().runTaskTimer( Core.getCore().getInstance(), new Runnable() {
			
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
	public WrappedChatComponent[] getServerInfo(final WrappedChatComponent[] lines) {
		FancyMessage msg;
		String name = null;
		try {
			JSONObject obj = (JSONObject) new JSONParser().parse(lines[1].getJson());
			if (obj.containsKey("text")) {
				name = (String) obj.get("text");
			}
			if (name == null) {
				System.out.println("No name in line 1? " + lines[1].getJson());
			}
		} catch (Exception ex) {
			System.out.println("could not read name from " + lines[1].getJson());
			return lines;
		}
		
		final Server server = getServerInfo(name);
		if (server == null || !server.isOnline()) {
			msg = new FancyMessage("██████████").color(ChatColor.DARK_RED);
			lines[0].setJson(msg.toJSONString());
			msg = new FancyMessage("RESTART").color(ChatColor.RED).style(ChatColor.BOLD, ChatColor.UNDERLINE);
			lines[1].setJson(msg.toJSONString());
			msg = new FancyMessage("Bitte warten!").color(ChatColor.RED);
			lines[2].setJson(msg.toJSONString());
			msg = new FancyMessage("██████████").color(ChatColor.DARK_RED);
			lines[3].setJson(msg.toJSONString());
		} else if (server.getName().contains("lobby")) {
			msg = new FancyMessage("██████████").color(ChatColor.DARK_GREEN);
			lines[0].setJson(msg.toJSONString());
			msg = new FancyMessage("Back to").color(ChatColor.GREEN);
			lines[1].setJson(msg.toJSONString());
			msg = new FancyMessage("Hub!").color(ChatColor.GREEN);
			lines[2].setJson(msg.toJSONString());
			msg = new FancyMessage("██████████").color(ChatColor.DARK_GREEN);
			lines[3].setJson(msg.toJSONString());
		} else if (server.isFull() && !server.isJoin()) {
			msg = new FancyMessage("[Full]").color(ChatColor.DARK_RED);
			lines[0].setJson(msg.toJSONString());
			msg = new FancyMessage(server.getPhase()).color(ChatColor.DARK_BLUE);
			lines[1].setJson(msg.toJSONString());
			msg = new FancyMessage(server.getNumPlayers() + "/" + server.getMaxPlayers()).color(ChatColor.DARK_BLUE);
			lines[2].setJson(msg.toJSONString());
			msg = new FancyMessage(server.getName()).color(ChatColor.DARK_BLUE);
			lines[3].setJson(msg.toJSONString());
		} else if (server.isFull()) {
			msg = new FancyMessage("[Full]").color(ChatColor.DARK_RED);
			lines[0].setJson(msg.toJSONString());
			msg = new FancyMessage(server.getPhase()).color(ChatColor.DARK_BLUE);
			lines[1].setJson(msg.toJSONString());
			msg = new FancyMessage(server.getNumPlayers() + "/" + server.getMaxPlayers()).color(ChatColor.DARK_BLUE);
			lines[2].setJson(msg.toJSONString());
			msg = new FancyMessage(server.getName()).color(ChatColor.DARK_BLUE);
			lines[3].setJson(msg.toJSONString());
		} else if (server.isJoin()) {
			msg = new FancyMessage("[Join]").color(ChatColor.AQUA);
			lines[0].setJson(msg.toJSONString());
			msg = new FancyMessage(server.getPhase()).color(ChatColor.DARK_GREEN);
			lines[1].setJson(msg.toJSONString());
			msg = new FancyMessage(server.getNumPlayers() + "/" + server.getMaxPlayers()).color(ChatColor.DARK_GREEN);
			lines[2].setJson(msg.toJSONString());
			msg = new FancyMessage(server.getName()).color(ChatColor.DARK_GREEN);
			lines[3].setJson(msg.toJSONString());
		} else if (server.isSpectate()) {
			msg = new FancyMessage("[Specatete]").color(ChatColor.YELLOW);
			lines[0].setJson(msg.toJSONString());
			msg = new FancyMessage(server.getPhase()).color(ChatColor.GOLD);
			lines[1].setJson(msg.toJSONString());
			msg = new FancyMessage(server.getNumPlayers() + "/" + server.getMaxPlayers()).color(ChatColor.GOLD);
			lines[2].setJson(msg.toJSONString());
			msg = new FancyMessage(server.getName()).color(ChatColor.GOLD);
			lines[3].setJson(msg.toJSONString());
		}
		
		return lines;
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
