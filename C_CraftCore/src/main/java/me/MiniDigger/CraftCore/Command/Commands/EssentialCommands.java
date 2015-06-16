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

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Game.GameType;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.User.User;
import me.MiniDigger.Core.Util.EntityUtil.Type;

import mkremins.fanciful.FancyMessage;

public class EssentialCommands {
	
	@Command(name = "spawnmob", usage = "spawnmob <type> [count]", min = 1, max = 2, consol = false, permission = "spawnmob", description = "Spawn Mobs")
	public void spawnmob(final CommandArgs args) {
		int c = 1;
		final EntityType type;
		try {
			type = EntityType.valueOf(args.getArgs()[0]);
		} catch (final Exception ex) {
			Prefix.API.getPrefix().then("Unbekanntes Entity!").color(ChatColor.RED).send(args.getPlayer());;
			return;
		}
		
		try {
			c = Integer.parseInt(args.getArgs()[1]);
		} catch (final Exception ex) {
			c = 1;
		}
		
		final int fC = c;
		Bukkit.getScheduler().runTask(Core.getCore().getInstance(), new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < fC; i++) {
					args.getPlayer().getWorld().spawn(args.getPlayer().getLocation(), type.getEntityClass());
				}
				Prefix.API.getPrefix().then("Es wurde" + (fC > 1 ? "n " : " ") + fC + " " + type.name() + " gespawnt!").color(ChatColor.GREEN).send(args.getPlayer());
			}
			
		});
	}
	
	@Command(name = "killall", usage = "", min = 0, max = 0, consol = true, permission = "killall", description = "Tötet Mobs")
	public void killall(final CommandArgs args) {
		int i = 0;
		for (final World world : Bukkit.getWorlds()) {
			for (final Entity e : world.getEntities()) {
				boolean b = false;
				for (final EntityType t : Core.getCore().getEntityUtil().getAll(Type.ANGRY, Type.FRIENDLY, Type.PASSIV, Type.PROJECTILE)) {
					if (t == e.getType()) {
						b = true;
					}
				}
				if (b) {
					i++;
					e.remove();
				}
			}
		}
		Prefix.API.getPrefix().then("Es wurde" + (i > 1 ? "n " : " ") + i + " Entities gelöscht!").color(ChatColor.GREEN).send(args.getSender());
	}
	
	@Command(name = "hub", aliases = { "spawn" }, usage = "", min = 0, max = 0, consol = false, permission = "hub", description = "Teleportiert den Spieler zum Hub", sync = true)
	public void hub(final CommandArgs args) {
		if (Core.getCore().getGameHandler().getMainGame().getType() == GameType.LOBBY) {
			Core.getCore().getGameHandler().joinGame(args.getUser(), Core.getCore().getGameHandler().getMainGame());
			Prefix.API.getPrefix().then("Wuuschh").send(args.getPlayer());
		} else {
			Prefix.API.getPrefix().then("Nur auf dem Hub Server!").color(ChatColor.RED).send(args.getPlayer());
		}
	}
	
	@Command(name = "ban", usage = "<user> <reason>", min = 2, consol = true, permission = "ban", description = "Ban a user", sync = true, string = 2)
	public void ban(final CommandArgs args) {
		Bukkit.getBanList(BanList.Type.NAME).addBan(args.getArgs()[0], args.getArgs()[1], null, args.getSender().getName());
		Prefix.API.getPrefix().then("Der Spieler " + args.getArgs()[0] + " wurde gebannt!").send(args.getSender());
		
		Player p = Bukkit.getPlayer(args.getArgs()[0]);
		if (p != null) {
			p.kickPlayer("Du wurdest von " + args.getSender().getName() + " gebannt: " + args.getArgs()[1]);
		}
	}
	
	@Command(name = "unban", usage = "<user>", min = 1, max = 1, consol = true, permission = "unban", description = "UnBan a user", sync = true)
	public void unban(final CommandArgs args) {
		Bukkit.getBanList(BanList.Type.NAME).pardon(args.getArgs()[0]);
		Prefix.API.getPrefix().then("Der Spieler " + args.getArgs()[0] + " kann jetzt wieder Spielen!").send(args.getSender());
	}
}
