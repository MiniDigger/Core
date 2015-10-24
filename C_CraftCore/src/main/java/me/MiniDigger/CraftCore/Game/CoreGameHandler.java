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
package me.MiniDigger.CraftCore.Game;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Game.Game;
import me.MiniDigger.Core.Game.GameHandler;
import me.MiniDigger.Core.Game.GameType;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Event.Events.CoreUserJoinGameEvent;
import me.MiniDigger.CraftCore.Event.Events.CoreUserLeaveGameEvent;

public class CoreGameHandler implements GameHandler {

	private boolean					mainGameStarted	= false;
	private Game					mainGame;
	private final ArrayList<Game>	games			= new ArrayList<>();

	private List<GameType> disabled = new ArrayList<GameType>();

	@Override
	public boolean isMainGameStarted() {
		return mainGameStarted;
	}

	@Override
	public void setMainGameStarted(final boolean started) {
		mainGameStarted = started;
	}

	@Override
	public Game getMainGame() {
		return mainGame;
	}

	@Override
	public void searchMainGame() {
		final String game = Core.getCore().getInstance().getConfig().getString("server-type");
		if (game != null) {
			try {
				final GameType type = GameType.valueOf(game);
				mainGame = (Game) type.getGame().newInstance();
				addGame(mainGame);
				Core.getCore().getInstance().info("Game " + type.getName() + " gefunden.");
				Core.getCore().getInstance().info("Warte auf ersten Spieler...");
			}
			catch (final Exception ex) {
				Core.getCore().getInstance().error("Game " + game + " isn't installed!");
				ex.printStackTrace();
			}
		} else {
			Core.getCore().getInstance().info("No main game found.");
		}

		reloadDisabled();
	}

	@Override
	public void addGame(final Game game) {
		games.add(game);
	}

	@Override
	public void removeGame(final Game game) {
		games.remove(game);
	}

	@Override
	public List<Game> getGames(final User user) {
		final List<Game> g = new ArrayList<>();
		for (final Game game : games) {
			if (game.getPlayers().contains(user.getUUID())) {
				g.add(game);
			}
		}
		return g;
	}

	@Override
	public void joinGame(final User user, final Game game) {
		if (game.isFull()) {
			Prefix.API.getPrefix().then("Das Spiel ist voll!").color(ChatColor.RED).send(user.getPlayer());
			return;
		}

		for (final Game g : getGames(user)) {
			if (g.getType() != GameType.TICTACTOE) {
				leave(user, g);
			}
		}
		game.join(user);

		Core.getCore().getInstance().debug("join " + game.getChatChannel().getName());
		user.setPrimaryChannel(game.getChatChannel());

		Core.getCore().getPlayerUtil().prepare(user.getPlayer());
		Core.getCore().getScoreboardHandler().getBoard(user.getUUID()).clear();
		Core.getCore().getBarHandler().removeBar(user.getPlayer());
		Core.getCore().getMenuHandler().closeMenu(user);

		final CoreUserJoinGameEvent event = new CoreUserJoinGameEvent(game, user);
		Bukkit.getPluginManager().callEvent(event);
	}

	private void leave(final User user, final Game game) {
		final CoreUserLeaveGameEvent event = new CoreUserLeaveGameEvent(game, user);
		Bukkit.getPluginManager().callEvent(event);

		game.leave(user);

		Core.getCore().getInstance().debug("leave " + game.getChatChannel().getName());
		user.leaveChannel(game.getChatChannel());
		user.setPrimaryChannel(Core.getCore().getChatHandler().getChannel("Default"));

		Core.getCore().getPlayerUtil().prepare(user.getPlayer());
		Core.getCore().getScoreboardHandler().getBoard(user.getUUID()).clear();
		Core.getCore().getBarHandler().removeBar(user.getPlayer());
		Core.getCore().getMenuHandler().closeMenu(user);
	}

	@Override
	public void leaveGame(final User user, final Game game) {
		leave(user, game);

		try {
			user.getPlayer().teleport(Core.getCore().getWorldHandler().getFallbackLoc());
		}
		catch (final Exception ex) {}

		if (getMainGame().getType() == GameType.LOBBY) {
			joinGame(user, getMainGame());
		}
	}

	@Override
	public List<Game> getGames() {
		return games;
	}

	@Override
	public boolean isDisabled(final GameType type) {
		return disabled.contains(type);
	}

	@Override
	public void reloadDisabled() {
		disabled.clear();
		disabled = new ArrayList<GameType>();

		Core.getCore().getInstance().reloadConfig();
		final List<String> list = Core.getCore().getInstance().getConfig().getStringList("disabled-gametypes");
		for (final String s : list) {
			try {
				disabled.add(GameType.valueOf(s.toUpperCase()));
			}
			catch (final Exception ex) {
				Core.getCore().getInstance().debug("Unknown gametype " + s);
			}
		}
		Core.getCore().getInstance().info(disabled.size() + " Spielmodi wurden deaktiviert");
	}
}
