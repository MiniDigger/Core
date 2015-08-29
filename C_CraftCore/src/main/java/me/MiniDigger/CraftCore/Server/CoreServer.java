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
package me.MiniDigger.CraftCore.Server;

import java.util.regex.Pattern;

import org.bukkit.Bukkit;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Game.Game;
import me.MiniDigger.Core.Game.GameType;
import me.MiniDigger.Core.Server.Server;

public class CoreServer implements Server {

	private GameType	primaryGameType;
	private String		name;
	private int			numPlayers;
	private int			maxPlayers;
	private String		phase;
	private boolean		join;
	private boolean		spectate;
	private boolean		online;
	private boolean		external;

	public static Server fromString(String data) {
		if (data.charAt(0) == '|') {
			data = data.replaceFirst(Pattern.quote("|"), "");
		}
		final String[] d = data.split(Pattern.quote("|"));
		final Server server = new CoreServer();

		server.setName(d[0]);
		if (d[1] != null && !d[1].equals("null")) {
			server.setPrimaryGameType(GameType.valueOf(d[1]));
		}
		server.setNumPlayers(Integer.parseInt(d[2]));
		server.setMaxPlayers(Integer.parseInt(d[3]));
		server.setPhase(d[4]);
		server.setJoin(Boolean.parseBoolean(d[5]));
		server.setSpectate(Boolean.parseBoolean(d[6]));
		server.setOnline(Boolean.parseBoolean(d[7]));

		return server;
	}

	@Override
	public String toString() {
		return name + "|" + primaryGameType.name() + "|" + numPlayers + "|" + maxPlayers + "|" + phase + "|" + join + "|" + spectate + "|" + online;
	}

	public static Server getForThis(final boolean online) {
		final Server server = new CoreServer();

		try {
			final Game game = Core.getCore().getGameHandler().getMainGame();
			server.setPrimaryGameType(game.getType());
			server.setPhase(game.getPhase().getName());
			server.setJoin(game.allowJoin());
			server.setSpectate(game.allowSpectate());
		}
		catch (final Exception ex) {
			final String game = Core.getCore().getInstance().getConfig().getString("server-type");
			if (game != null) {
				try {
					final GameType type = GameType.valueOf(game);
					if (type != null) {
						server.setPrimaryGameType(type);
					}
				}
				catch (final Exception ex2) {}
			} else {
				server.setPrimaryGameType(GameType.NOTHING);
			}
			server.setPhase("WAITING");
			server.setJoin(true);
			server.setSpectate(false);
		}

		server.setName((Core.getCore().getInstance()).getConfig().getString("server-name"));
		server.setMaxPlayers(Bukkit.getMaxPlayers());
		server.setNumPlayers(Core.getCore().getUserHandler().getOnlinePlayers().size());

		server.setOnline(online);

		return server;
	}

	@Override
	public boolean isFull() {
		return numPlayers >= maxPlayers ? true : false;
	}

	@Override
	public GameType getPrimaryGameType() {
		return primaryGameType;
	}

	@Override
	public void setPrimaryGameType(final GameType primaryGameType) {
		this.primaryGameType = primaryGameType;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public int getNumPlayers() {
		return numPlayers;
	}

	@Override
	public void setNumPlayers(final int numPlayers) {
		this.numPlayers = numPlayers;
	}

	@Override
	public int getMaxPlayers() {
		return maxPlayers;
	}

	@Override
	public void setMaxPlayers(final int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	@Override
	public String getPhase() {
		return phase;
	}

	@Override
	public void setPhase(final String phase) {
		this.phase = phase;
	}

	@Override
	public boolean isJoin() {
		return join;
	}

	@Override
	public void setJoin(final boolean join) {
		this.join = join;
	}

	@Override
	public boolean isSpectate() {
		return spectate;
	}

	@Override
	public void setSpectate(final boolean spectate) {
		this.spectate = spectate;
	}

	@Override
	public boolean isOnline() {
		return online;
	}

	@Override
	public void setOnline(final boolean online) {
		this.online = online;
	}

	@Override
	public boolean isExternal() {
		return external;
	}
}
