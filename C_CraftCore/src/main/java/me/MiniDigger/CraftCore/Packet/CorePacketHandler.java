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
package me.MiniDigger.CraftCore.Packet;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.regex.Pattern;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.server.WebSocketServer;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Packet.Packet;
import me.MiniDigger.Core.Packet.PacketHandler;
import me.MiniDigger.Core.Socket.Session;

import me.MiniDigger.CraftCore.Packet.Packets.IdentificationPacket;

public class CorePacketHandler implements PacketHandler {
	
	private final HashMap<String, Class<? extends Packet>>	packets	= new HashMap<>();
	
	@Override
	public void registerPacket(final String name, final Class<? extends Packet> packet) {
		if (!packets.containsKey(name)) {
			packets.put(name, packet);
		}
	}
	
	@Override
	public Class<? extends Packet> getPacket(final String name) {
		return packets.get(name);
	}
	
	@Override
	public void sendPacket(final Packet packet) {
		try {
			((WebSocketClient) Core.getCore().getSocketHandler().getClient()).send(packet.toString());
		} catch (final Exception ex) {
			
		}
	}
	
	@Override
	public void sendPacket(final Packet packet, final Session session) {
		for (final WebSocket socket : ((WebSocketServer) Core.getCore().getSocketHandler().getServer()).connections()) {
			if (socket.getRemoteSocketAddress().equals(session.getAddress())) {
				socket.send(packet.toString());
			}
		}
	}
	
	@Override
	public void sendBroadcast(final Packet packet) {
		for (final WebSocket socket : ((WebSocketServer) Core.getCore().getSocketHandler().getServer()).connections()) {
			socket.send(packet.toString());
		}
	}
	
	@Override
	public boolean handleIncome(final String msg, final WebSocket con) {
		Class<? extends Packet> packet;
		try {
			final String[] s = msg.split(Pattern.quote("|"));
			packet = getPacket(s[0]);
			if (packet == null) {
				Core.getCore().getInstance().error("[Server] Could not parse msg = " + msg + ". Packet " + s[0] + " not found!");
				return false;
			}
		} catch (final Exception ex) {
			Core.getCore().getInstance().error("[Server] Could not parse msg = " + msg + ". Error: " + ex.getMessage());
			return false;
		}
		try {
			final Packet pa = packet.getConstructor().newInstance();
			pa.fromString(msg);
			final Session s = Core.getCore().getSocketHandler().getSession(con.getRemoteSocketAddress());
			if (s.isIdentified() || pa instanceof IdentificationPacket) {
				pa.handle(con);
				return true;
			} else {
				Core.getCore().getInstance().error("[Server] " + con.getRemoteSocketAddress().toString() + " tryed to send packet without beeing identified!");
				return false;
			}
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public void handleIncome(final String msg) {
		Class<? extends Packet> packet;
		try {
			final String[] s = msg.split(Pattern.quote("|"));
			packet = getPacket(s[0]);
			if (packet == null) {
				Core.getCore().getInstance().error("[Client] Could not parse msg = " + msg + ". Packet " + s[0] + " not found!");
				return;
			}
		} catch (final Exception ex) {
			Core.getCore().getInstance().error("[Client] Could not parse msg = " + msg + ". Error: " + ex.getMessage());
			return;
		}
		try {
			final Packet pa = packet.getConstructor().newInstance();
			pa.fromString(msg);
			pa.handle();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}
}
