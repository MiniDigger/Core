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

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Game.Game;
import me.MiniDigger.Core.Game.GameHandler;
import me.MiniDigger.Core.Game.GameType;
import me.MiniDigger.Core.User.User;

public class CoreGameHandler implements GameHandler {
	
	private boolean	              mainGameStarted	= false;
	private Game	              mainGame;
	private final ArrayList<Game>	games	      = new ArrayList<>();
	
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
		final String game = (Core.getCore().getInstance()).getConfig().getString("server-type");
		if (game != null) {
			try {
				final GameType type = GameType.valueOf(game);
				mainGame = (Game) type.getGame().newInstance();
				addGame(mainGame);
				Core.getCore().getInstance().info("Game " + type.getName() + " gefunden.");
				Core.getCore().getInstance().info("Warte auf ersten Spieler...");
			} catch (final Exception ex) {
				Core.getCore().getInstance().error("Game " + game + " isn't installed!");
				ex.printStackTrace();
			}
		} else {
			Core.getCore().getInstance().info("No main game found.");
		}
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
		for (final Game g : getGames(user)) {
			if (g.getType() != GameType.TICTACTOE) {
				g.leave(user);
			}
		}
		game.join(user);
	}
	
	@Override
	public void leaveGame(final User user, final Game game) {
		game.leave(user);
	}
}
