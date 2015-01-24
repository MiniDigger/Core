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

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.Stats.Stats;
import me.MiniDigger.Core.Stats.StatsType;
import me.MiniDigger.Core.User.User;

public class StatsCommands {
	
	@Command(name = "stats", description = "Macht alles mit Stats", usage = "", permission = "stats")
	public void stats(final CommandArgs args) {
		if (args.isUser()) {
			args.getUser().sendMessage(Prefix.STATS.getPrefix().then("Macht alles mit Stats"));
			
			args.getUser().sendMessage(Prefix.STATS.getPrefix().then("Sub-Commands: add,remove,set,get,top"));
		} else {
			args.getSender().sendMessage("[Stats] Macht alles mit Stats:");
			args.getSender().sendMessage("[Stats] Sub-Commands: add,remove,set,get,top");
		}
	}
	
	@Command(name = "stats.add", description = "Fügt Stats hinzu", usage = "stats add <spieler> <type> <anzahl>", permission = "stats.add")
	public void add(final CommandArgs args) {
		User other;
		try {
			other = Core.getCore().getUserHandler().get(Bukkit.getPlayer(args.getArgs()[0]).getUniqueId());
		} catch (final Exception ex) {
			if (args.isUser()) {
				args.getUser().sendMessage(Prefix.STATS.getPrefix().then("Unbekannter Spieler: " + args.getArgs()[0]).color(ChatColor.RED));
				args.getUser().sendMessage(Prefix.STATS.getPrefix().then("Richtige Benutzung: stats add <spieler> <type> <anzahl>"));
			} else {
				args.getSender().sendMessage("[Stats] Unbekannter Spieler: " + args.getArgs()[0]);
				args.getSender().sendMessage("[Stats] Richtige Benutzung: stats add <spieler> <type> <anzahl>");
			}
			return;
		}
		
		StatsType type;
		try {
			type = StatsType.valueOf(args.getArgs()[1]);
		} catch (final Exception ex) {
			if (args.isUser()) {
				args.getUser().sendMessage(Prefix.STATS.getPrefix().then("Unbekannter StatsType: " + args.getArgs()[1]).color(ChatColor.RED));
				args.getUser().sendMessage(Prefix.STATS.getPrefix().then("Richtige Benutzung: stats add <spieler> <type> <anzahl>"));
			} else {
				args.getSender().sendMessage("[Stats] Unbekannter StatsType: " + args.getArgs()[1]);
				args.getSender().sendMessage("[Stats] Richtige Benutzung: stats add <spieler> <type> <anzahl>");
			}
			return;
		}
		
		int anzahl;
		try {
			anzahl = Integer.parseInt(args.getArgs()[2]);
		} catch (final Exception ex) {
			if (args.isUser()) {
				args.getUser().sendMessage(Prefix.STATS.getPrefix().then("Unbekannter Anzahl: " + args.getArgs()[2]).color(ChatColor.RED));
				args.getUser().sendMessage(Prefix.STATS.getPrefix().then("Richtige Benutzung: stats add <spieler> <type> <anzahl>"));
			} else {
				args.getSender().sendMessage("[Stats] Unbekannter Anzahl: " + args.getArgs()[2]);
				args.getSender().sendMessage("[Stats] Richtige Benutzung: stats add <spieler> <type> <anzahl>");
			}
			return;
		}
		
		final Stats stats = Core.getCore().getStatsHandler().get(other.getUUID());
		stats.add(type, anzahl);
		stats.save();
		stats.load();
		
		if (args.isUser()) {
			args.getUser().sendMessage(Prefix.STATS.getPrefix().then("Der User hat nun " + stats.get(type) + " " + type.getGame() + "." + type.getStats()));
		} else {
			args.getSender().sendMessage("[Stats] Der User hat nun " + stats.get(type) + " " + type.getGame() + "." + type.getStats());
		}
	}
	
	@Command(name = "stats.remove", description = "Nimmt Stats weg", usage = "stats remove <spieler> <type> <anzahl>", permission = "stats.remove")
	public void remove(final CommandArgs args) {
		User other;
		try {
			other = Core.getCore().getUserHandler().get(Bukkit.getPlayer(args.getArgs()[0]).getUniqueId());
		} catch (final Exception ex) {
			if (args.isUser()) {
				args.getUser().sendMessage(Prefix.STATS.getPrefix().then("Unbekannter Spieler: " + args.getArgs()[0]).color(ChatColor.RED));
				args.getUser().sendMessage(Prefix.STATS.getPrefix().then("Richtige Benutzung: stats remove <spieler> <type> <anzahl>"));
			} else {
				args.getSender().sendMessage("[Stats] Unbekannter Spieler: " + args.getArgs()[0]);
				args.getSender().sendMessage("[Stats] Richtige Benutzung: stats remove <spieler> <type> <anzahl>");
			}
			return;
		}
		
		StatsType type;
		try {
			type = StatsType.valueOf(args.getArgs()[1]);
		} catch (final Exception ex) {
			if (args.isUser()) {
				args.getUser().sendMessage(Prefix.STATS.getPrefix().then("Unbekannter StatsType: " + args.getArgs()[1]).color(ChatColor.RED));
				args.getUser().sendMessage(Prefix.STATS.getPrefix().then("Richtige Benutzung: stats remove <spieler> <type> <anzahl>"));
			} else {
				args.getSender().sendMessage("[Stats] Unbekannter StatsType: " + args.getArgs()[1]);
				args.getSender().sendMessage("[Stats] Richtige Benutzung: stats remove <spieler> <type> <anzahl>");
			}
			return;
		}
		
		int anzahl;
		try {
			anzahl = Integer.parseInt(args.getArgs()[2]);
		} catch (final Exception ex) {
			if (args.isUser()) {
				args.getUser().sendMessage(Prefix.STATS.getPrefix().then("Unbekannter Anzahl: " + args.getArgs()[2]).color(ChatColor.RED));
				args.getUser().sendMessage(Prefix.STATS.getPrefix().then("Richtige Benutzung: stats remove <spieler> <type> <anzahl>"));
			} else {
				args.getSender().sendMessage("[Stats] Unbekannter Anzahl: " + args.getArgs()[2]);
				args.getSender().sendMessage("[Stats] Richtige Benutzung: stats remove <spieler> <type> <anzahl>");
			}
			return;
		}
		
		final Stats stats = Core.getCore().getStatsHandler().get(other.getUUID());
		stats.remove(type, anzahl);
		stats.save();
		stats.load();
		
		if (args.isUser()) {
			args.getUser().sendMessage(Prefix.STATS.getPrefix().then("Der User hat nun " + stats.get(type) + " " + type.getGame() + "." + type.getStats()));
		} else {
			args.getSender().sendMessage("[Stats] Der User hat nun " + stats.get(type) + " " + type.getGame() + "." + type.getStats());
		}
	}
	
	@Command(name = "stats.set", description = "Sets Stats", usage = "stats set <spieler> <type> <anzahl>", permission = "stats.set")
	public void set(final CommandArgs args) {
		User other;
		try {
			other = Core.getCore().getUserHandler().get(Bukkit.getPlayer(args.getArgs()[0]).getUniqueId());
		} catch (final Exception ex) {
			if (args.isUser()) {
				args.getUser().sendMessage(Prefix.STATS.getPrefix().then("Unbekannter Spieler: " + args.getArgs()[0]).color(ChatColor.RED));
				args.getUser().sendMessage(Prefix.STATS.getPrefix().then("Richtige Benutzung: stats set <spieler> <type> <anzahl>"));
			} else {
				args.getSender().sendMessage("[Stats] Unbekannter Spieler: " + args.getArgs()[0]);
				args.getSender().sendMessage("[Stats] Richtige Benutzung: stats set <spieler> <type> <anzahl>");
			}
			return;
		}
		
		StatsType type;
		try {
			type = StatsType.valueOf(args.getArgs()[1]);
		} catch (final Exception ex) {
			if (args.isUser()) {
				args.getUser().sendMessage(Prefix.STATS.getPrefix().then("Unbekannter StatsType: " + args.getArgs()[1]).color(ChatColor.RED));
				args.getUser().sendMessage(Prefix.STATS.getPrefix().then("Richtige Benutzung: stats set <spieler> <type> <anzahl>"));
			} else {
				args.getSender().sendMessage("[Stats] Unbekannter StatsType: " + args.getArgs()[1]);
				args.getSender().sendMessage("[Stats] Richtige Benutzung: stats set <spieler> <type> <anzahl>");
			}
			return;
		}
		
		int anzahl;
		try {
			anzahl = Integer.parseInt(args.getArgs()[2]);
		} catch (final Exception ex) {
			if (args.isUser()) {
				args.getUser().sendMessage(Prefix.STATS.getPrefix().then("Unbekannter Anzahl: " + args.getArgs()[2]).color(ChatColor.RED));
				args.getUser().sendMessage(Prefix.STATS.getPrefix().then("Richtige Benutzung: stats set <spieler> <type> <anzahl>"));
			} else {
				args.getSender().sendMessage("[Stats] Unbekannter Anzahl: " + args.getArgs()[2]);
				args.getSender().sendMessage("[Stats] Richtige Benutzung: stats set <spieler> <type> <anzahl>");
			}
			return;
		}
		
		final Stats stats = Core.getCore().getStatsHandler().get(other.getUUID());
		stats.set(type, anzahl);
		stats.save();
		stats.load();
		
		if (args.isUser()) {
			args.getUser().sendMessage(Prefix.STATS.getPrefix().then("Der User hat nun " + stats.get(type) + " " + type.getGame() + "." + type.getStats()));
		} else {
			args.getSender().sendMessage("[Stats] Der User hat nun " + stats.get(type) + " " + type.getGame() + "." + type.getStats());
		}
	}
	
	@Command(name = "stats.get", description = "Zeigt Stats an", usage = "stats get <type> [spieler]", permission = "stats.get", min = 1, max = 2)
	public void get(final CommandArgs args) {
		User other;
		if (args.getArgs().length == 2) {
			if (!args.getUser().hasPermission("stats.get.other")) {
				args.getUser().sendMessage(Prefix.STATS.getPrefix().then("Du darfst dir nicht die Stats von anderen Spielern angucken!").color(ChatColor.RED));
				return;
			}
			
			try {
				other = Core.getCore().getUserHandler().get(Bukkit.getPlayer(args.getArgs()[1]).getUniqueId());
			} catch (final Exception ex) {
				if (args.isUser()) {
					args.getUser().sendMessage(Prefix.STATS.getPrefix().then("Unbekannter Spieler: " + args.getArgs()[1]).color(ChatColor.RED));
					args.getUser().sendMessage(Prefix.STATS.getPrefix().then("Richtige Benutzung: stats get <spieler> <type>"));
				} else {
					args.getSender().sendMessage("[Stats] Unbekannter Spieler: " + args.getArgs()[1]);
					args.getSender().sendMessage("[Stats] Richtige Benutzung: stats get <spieler> <type>");
				}
				return;
			}
		} else {
			other = args.getUser();
		}
		
		StatsType type;
		try {
			type = StatsType.valueOf(args.getArgs()[0]);
		} catch (final Exception ex) {
			if (args.isUser()) {
				args.getUser().sendMessage(Prefix.STATS.getPrefix().then("Unbekannter StatsType: " + args.getArgs()[0]).color(ChatColor.RED));
				args.getUser().sendMessage(Prefix.STATS.getPrefix().then("Richtige Benutzung: stats get <spieler> <type>"));
			} else {
				args.getSender().sendMessage("[Stats] Unbekannter StatsType: " + args.getArgs()[1]);
				args.getSender().sendMessage("[Stats] Richtige Benutzung: stats get <spieler> <type>");
			}
			return;
		}
		
		final Stats stats = Core.getCore().getStatsHandler().get(other.getUUID());
		stats.load();
		
		if (args.isUser()) {
			args.getUser().sendMessage(Prefix.STATS.getPrefix().then("Der User hat " + stats.get(type) + " " + type.getGame() + "." + type.getStats()));
		} else {
			args.getSender().sendMessage("[Stats] Der User hat " + stats.get(type) + " " + type.getGame() + "." + type.getStats());
		}
	}
	
	@Command(name = "stats.top", description = "Zeigt Top Stats an", usage = "stats top <type> [top was?]", permission = "stats.top")
	public void getTop(final CommandArgs args) {
		StatsType type;
		try {
			type = StatsType.valueOf(args.getArgs()[0]);
		} catch (final Exception ex) {
			if (args.isUser()) {
				args.getUser().sendMessage(Prefix.STATS.getPrefix().then("Unbekannter StatsType: " + args.getArgs()[0]).color(ChatColor.RED));
				args.getUser().sendMessage(Prefix.STATS.getPrefix().then("Richtige Benutzung: stats top <type> [top was?]"));
			} else {
				args.getSender().sendMessage("[Stats] Unbekannter StatsType: " + args.getArgs()[0]);
				args.getSender().sendMessage("[Stats] Richtige Benutzung: stats top <type> [top was?]");
			}
			return;
		}
		
		int top = 5;
		try {
			top = Integer.parseInt(args.getArgs()[1]);
			if (top >= 10) {
				top = 10;
			}
		} catch (final Exception ex) {
			if (args.isUser()) {
				args.getUser().sendMessage(Prefix.STATS.getPrefix().then("Unbekannte Zahl: " + args.getArgs()[1]).color(ChatColor.RED));
				args.getUser().sendMessage(Prefix.STATS.getPrefix().then("Richtige Benutzung: stats top <type> [top was?]"));
			} else {
				args.getSender().sendMessage("[Stats] Unbekannte Zahl: " + args.getArgs()[1]);
				args.getSender().sendMessage("[Stats] Richtige Benutzung: stats top <type> [top was?]");
			}
			return;
		}
		
		final List<String> tops = Core.getCore().getStatsHandler().getTop(type, top);
		
		if (args.isUser()) {
			args.getUser().sendMessage(Prefix.STATS.getPrefix().then("Top " + top + " in der Kategorie " + type.getGame() + "." + type.getStats()));
			for (int i = 0; i < top; i++) {
				args.getUser().sendMessage(
				        Prefix.STATS.getPrefix().then("#" + i).color(ChatColor.GOLD).then(" " + tops.get(i).split(":")[0]).color(ChatColor.GRAY)
				                .then(": " + tops.get(i).split(":")[1]));
			}
		} else {
			args.getSender().sendMessage("[Stats] Top " + top + " in der Kategorie " + type.getGame() + "." + type.getStats());
			for (int i = 0; i < top; i++) {
				args.getSender().sendMessage("[Stats] #" + i + " " + tops.get(i).split(":")[0] + ": " + tops.get(i).split(":")[1]);
			}
		}
	}
}
