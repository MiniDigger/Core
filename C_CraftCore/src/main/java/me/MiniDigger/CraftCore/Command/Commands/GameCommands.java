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
package me.MiniDigger.CraftCore.Command.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Game.Game;
import me.MiniDigger.Core.Game.GameType;
import me.MiniDigger.Core.Prefix.Prefix;

public class GameCommands {

	@Command(name = "game", usage = "", consol = false, permission = "game", description = "Macht alles mit Games")
	public void game(final CommandArgs args) {
		Prefix.API.getPrefix().then("Subcommands: ").then("game join <spielname>, ").suggest("join ").then("game leave, ").command("game leave").then("game info <spielname>, ").suggest("game info ").then(" game list")
				.command("game list").send(args.getPlayer());
	}

	@Command(name = "game.join", usage = "", consol = false, permission = "game.join", description = "Tritt einem Spiel bei", min = 1, max = 1, sync = true)
	public void join(final CommandArgs args) {
		for (final Game game : Core.getCore().getGameHandler().getGames()) {
			if (game.getType().name().equalsIgnoreCase(args.getArgs()[0])) {
				if (Core.getCore().getGameHandler().isDisabled(game.getType())) {
					args.getUser().sendMessage(Prefix.API.getPrefix().then("Dieser Spielmodi ist temporär deaktiviert! Sorry!").color(ChatColor.RED));
					return;
				}
				if (game.getPlayers().contains(args.getUser().getUUID()) || game.getSpecs().contains(args.getUser().getUUID())) {
					args.getUser().sendMessage(Prefix.API.getPrefix().then("Du bist bereits in diesem Spiel!").color(ChatColor.RED));
					return;
				}
				Core.getCore().getGameHandler().joinGame(args.getUser(), game);
				args.getUser().sendMessage(Prefix.API.getPrefix().then("Spiel beigetreten").color(ChatColor.GREEN));
				return;
			}
		}

		for (final GameType type : GameType.values()) {
			if (type.name().equalsIgnoreCase(args.getArgs()[0])) {
				if (Core.getCore().getGameHandler().isDisabled(type)) {
					args.getUser().sendMessage(Prefix.API.getPrefix().then("Dieser Spielmodi ist temporär deaktiviert! Sorry!").color(ChatColor.RED));
					return;
				}
				Bukkit.dispatchCommand(args.getPlayer(), type.getCommand());
				args.getUser().sendMessage(Prefix.API.getPrefix().then("Neues Spiel gestartet!").color(ChatColor.GREEN));

				final Game g = Core.getCore().getGameHandler().getMainGame();
				if (g.getType() == GameType.LOBBY) {
					g.broadCastMessage(Prefix.API.getPrefix().then("Der Spieler " + args.getUser().getDisplayName() + " hat ein neus Spiel " + type.getName() + " gestartet!").color(ChatColor.GOLD));
					g.broadCastMessage(Prefix.API.getPrefix().then("Klicke hier um beizutreten").command("/game join " + type.name()).color(ChatColor.GOLD));
				}
			}
		}
		// args.getUser().sendMessage(Prefix.API.getPrefix().then("Unbekanntes
		// Spiel!").color(ChatColor.RED));
	}

	@Command(name = "game.leave", usage = "", consol = false, permission = "game.leave", description = "Verlässt ein Spiel", max = 0, sync = true)
	public void leave(final CommandArgs args) {
		for (final Game game : Core.getCore().getGameHandler().getGames(args.getUser())) {
			if (Core.getCore().getGameHandler().getMainGame().getType().equals(game.getType()) && game.getType() != GameType.LOBBY) {
				Core.getCore().getServerHandler().connect(args.getUser(), "lobby");
			}

			if (game.getType() != GameType.TICTACTOE) {
				Core.getCore().getGameHandler().leaveGame(args.getUser(), game);
				args.getUser().sendMessage(Prefix.API.getPrefix().then("Spiel verlassen").color(ChatColor.GREEN));
				return;
			}
		}
		args.getUser().sendMessage(Prefix.API.getPrefix().then("Du bist in keinem Spiel!").color(ChatColor.RED));
	}

	@Command(name = "game.info", usage = "", consol = false, permission = "game.info", description = "Zeigt Infos zu einem aktuellen Spiel bei", min = 1, max = 1)
	public void info(final CommandArgs args) {

	}

	@Command(name = "game.list", usage = "", consol = false, permission = "game.list", description = "Zeigt eine Liste mit allen aktiven Spielen", max = 0)
	public void list(final CommandArgs args) {
		Prefix.API.getPrefix().then("********** Games *********").color(ChatColor.GOLD).send(args.getPlayer());
		for (final Game game : Core.getCore().getGameHandler().getGames()) {
			Prefix.API.getPrefix().then("Game#" + game.getIdentifier()).send(args.getPlayer());
			Prefix.API.getPrefix().then(" * Typ: " + game.getType().name() + ", Spieler: " + game.getPlayers().size() + "(" + game.getSpecs().size() + "), Phase: " + game.getPhase().getName()).send(args.getPlayer());
		}
		Prefix.API.getPrefix().then("**************************").color(ChatColor.GOLD).send(args.getPlayer());
	}
}
