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
package me.MiniDigger.CraftCore.Socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Lang.LogLevel;
import me.MiniDigger.Core.Packet.Packet;
import me.MiniDigger.Core.Socket.Session;
import me.MiniDigger.Core.Socket.SocketClient;
import me.MiniDigger.Core.Socket.SocketHandler;
import me.MiniDigger.Core.Socket.SocketServer;
import me.MiniDigger.CraftCore.Lang.MSG;
import me.MiniDigger.CraftCore.Packet.Packets.ChatPacket;
import me.MiniDigger.CraftCore.Packet.Packets.CommandPacket;
import me.MiniDigger.CraftCore.Packet.Packets.IdentificationPacket;
import me.MiniDigger.CraftCore.Packet.Packets.LogRecordPacket;
import me.MiniDigger.CraftCore.Packet.Packets.ServerCommandPacket;
import me.MiniDigger.CraftCore.Packet.Packets.ServerPacket;

public class CoreSocketHandler implements SocketHandler {

	private final List<Session>	sessions	= new ArrayList<>();
	private SocketServer		server;
	private SocketClient		client;

	@Override
	public void startServer() {
		server = new CoreSocketServer(new InetSocketAddress(33333));
		((CoreSocketServer) server).start();
	}

	@Override
	public void startClient() {
		try {
			client = new CoreSocketClient(new URI("ws://localhost:33333"));
			((CoreSocketClient) client).connect();

			Bukkit.getScheduler().runTaskLater(Core.getCore().getInstance(), new Runnable() {

				@Override
				public void run() {
					final IdentificationPacket packet = new IdentificationPacket();
					packet.setClientName((Core.getCore().getInstance()).getConfig().getString("ws-user"));
					packet.setPass((Core.getCore().getInstance()).getConfig().getString("ws-pass"));
					Core.getCore().getPacketHandler().sendPacket(packet);
				}
			}, 10);
		}
		catch (final URISyntaxException e) {
			MSG.stacktrace(LogLevel.DEBUG, e, true);
		}
	}

	@Override
	public void stopClient() {
		((CoreSocketClient) client).close();
	}

	@Override
	public void stopServer() {
		final ServerCommandPacket packet = new ServerCommandPacket();
		packet.setCommand("CloseClient");
		Core.getCore().getPacketHandler().sendBroadcast(packet);

		try {
			((CoreSocketServer) server).stop();
		}
		catch (IOException | InterruptedException e) {
			MSG.stacktrace(LogLevel.DEBUG, e, true);
		}
	}

	@Override
	public void registerPackets() {
		final List<Class<? extends Packet>> classes = new ArrayList<Class<? extends Packet>>();
		classes.add(ChatPacket.class);
		classes.add(IdentificationPacket.class);
		classes.add(ServerPacket.class);
		classes.add(LogRecordPacket.class);
		classes.add(CommandPacket.class);
		classes.add(ServerCommandPacket.class);

		for (final Class<? extends Packet> c : classes) {
			try {
				Core.getCore().getPacketHandler().registerPacket(c.newInstance().getName(), c);
			}
			catch (final Exception e) {
				MSG.stacktrace(LogLevel.DEBUG, e, true);
			}
		}
	}

	@Override
	public void openSession(final InetSocketAddress address) {
		final Session session = new CoreSession(address);
		sessions.add(session);
	}

	@Override
	public void closeSession(final InetSocketAddress address) {
		while (getSession(address) != null && sessions.contains(getSession(address))) {
			sessions.remove(getSession(address));
		}
	}

	@Override
	public void reciveName(final String name, final InetSocketAddress address) {
		getSession(address).setName(name);
	}

	@Override
	public Session getSession(final String name) {
		for (final Session session : sessions) {
			if (session.getName() == null) {
				continue;
			}
			if (session.getName().equals(name)) {
				return session;
			}
		}
		return null;
	}

	@Override
	public Session getSession(final InetSocketAddress address) {
		for (final Session session : sessions) {
			if (session == null) {
				continue;
			}
			if (session.getAddress() == null) {
				continue;
			}
			if (session.getAddress().equals(address)) {
				return session;
			}
		}
		return null;
	}

	@Override
	public SocketServer getServer() {
		return server;
	}

	@Override
	public SocketClient getClient() {
		return client;
	}
}
