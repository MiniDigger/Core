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
package me.MiniDigger.Core.AddOn.TicTacToe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.inventory.Inventory;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Command.Completer;
import me.MiniDigger.Core.Event.Events.UserJoinGameEvent;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Game.Game;
import me.MiniDigger.Core.Game.GameType;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.AddOn.CoreAddOn;
import me.MiniDigger.CraftCore.Event.Events.CoreUserJoinGameEvent;
import me.MiniDigger.CraftCore.Feature.Features.TwoPlayerFeature;

public class TicTacToeAddOn extends CoreAddOn {

	private static HashMap<UUID, Inventory> savedGame = new HashMap<>();

	public static void saveGame(final UUID user, final Inventory inv) {
		if (savedGame.containsKey(user)) {
			savedGame.remove(savedGame.get(user));
		}
		savedGame.put(user, inv);
	}

	@Override
	public void enable() {
		GameType.TICTACTOE.setClass(TicTacToeGame.class);
		Core.getCore().getCommandHandler().registerCommands(this);
		super.enable();
	}

	@Override
	public void disable() {
		Core.getCore().getCommandHandler().unregisterCommands(this);
		super.disable();
	}

	@Command(name = "tictactoe", permission = "tictactoe", min = 0, max = 1, consol = false, description = "Macht alles mit TicTacToe", usage = "tictactoe [doStep,showLastGame,bot,(player)]")
	public void tictactoe(final CommandArgs args) {
		if (args.getArgs().length == 0) {
			for (final Game game : Core.getCore().getGameHandler().getGames(args.getUser())) {
				if (game instanceof TicTacToeGame) {
					final TicTacToeGame g = (TicTacToeGame) game;
					g.openInv(((TwoPlayerFeature) g.getPhase().getFeature(FeatureType.TWOPLAYER)).isOne(args.getUser().getUUID()) ? 1 : 2);
				}
			}
			return;
		}
		if (args.getArgs()[0].equalsIgnoreCase("doStep")) {
			args.getUser().sendMessage(Prefix.TICTACTOE.getPrefix().then("Du kannst gerade keinen Zug machen!"));
			return;
		} else if (args.getArgs()[0].equalsIgnoreCase("showLastGame")) {
			args.getPlayer().openInventory(savedGame.get(args.getPlayer().getUniqueId()));
			return;
		}
		final TicTacToeGame game = new TicTacToeGame();
		Core.getCore().getGameHandler().addGame(game);
		game.init();
		final UserJoinGameEvent e1 = new CoreUserJoinGameEvent(game, args.getUser());
		Bukkit.getPluginManager().callEvent((Event) e1);
		game.join(args.getUser()).name();

		if (args.getArgs()[0].equalsIgnoreCase("bot")) {
			final User bot = Core.getCore().getUserHandler().getBot();
			final UserJoinGameEvent e2 = new CoreUserJoinGameEvent(game, bot);
			Bukkit.getPluginManager().callEvent((Event) e2);
			game.join(bot);
		} else {
			final User user = Core.getCore().getUserHandler().get(Bukkit.getPlayer(args.getArgs()[0]).getUniqueId());
			final UserJoinGameEvent e2 = new CoreUserJoinGameEvent(game, user);
			Bukkit.getPluginManager().callEvent((Event) e2);
			game.join(user);
			user.sendMessage(Prefix.TICTACTOE.getPrefix().then(args.getUser().getDisplayName() + " hat dich herausgefordert!"));
		}
		args.getUser().sendMessage(Prefix.TICTACTOE.getPrefix().then("Das Spiel startet jetzt..."));
		game.start();
	}

	@Completer(name = "tictactoe")
	public List<String> tictactoeC(final CommandArgs args) {
		final List<String> result = new ArrayList<>();

		if (args.getArgs().length == 1) {
			result.add("doStep");
			result.add("showLastGame");
			result.add("bot");

			for (final User u : Core.getCore().getUserHandler().getOnlineUsers()) {
				result.add(u.getDisplayName());
			}
		}

		return result;
	}
}
