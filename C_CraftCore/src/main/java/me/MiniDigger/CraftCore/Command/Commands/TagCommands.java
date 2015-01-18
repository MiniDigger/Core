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
import org.bukkit.entity.Player;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Prefix.Prefix;

import ca.wacos.nametagedit.NametagAPI;

public class TagCommands {
	
	@Command(name = "tag", description = "Macht alles mit Nametags", permission = "tag")
	public void tag(final CommandArgs args) {
		Prefix.TAG.getPrefix().then("Klicke hier ").color(ChatColor.YELLOW).command("/tag show").then("um dein Tag wieder anzuzeigen").color(ChatColor.GOLD)
		        .send(args.getSender());
		Prefix.TAG.getPrefix().then("Klicke hier ").color(ChatColor.YELLOW).command("/tag hide").then("um dein Tag zu verstecken").color(ChatColor.GOLD)
		        .send(args.getSender());
		Prefix.TAG.getPrefix().then("Klicke hier ").color(ChatColor.YELLOW).suggest("/tag suffix ").then("um Suffixe zu ändern").color(ChatColor.GOLD)
		        .send(args.getSender());
		Prefix.TAG.getPrefix().then("Klicke hier ").color(ChatColor.YELLOW).suggest("/tag prefix ").then("um Prefixe zu ändern").color(ChatColor.GOLD)
		        .send(args.getSender());
		Prefix.TAG.getPrefix().then("Klicke hier ").color(ChatColor.YELLOW).suggest("/addon hide ").then("um das Tag von einem Spieler zu verstecken")
		        .color(ChatColor.GOLD).send(args.getSender());
		Prefix.TAG.getPrefix().then("Klicke hier ").color(ChatColor.YELLOW).suggest("/addon show  ").then("um das Tag von einem Spieler wieder anzuzeigen")
		        .color(ChatColor.GOLD).send(args.getSender());
	}
	
	@Command(name = "tag.show", description = "Zeigt den Nametag von einem Spieler oder dir wieder", permission = "tag.show", max = 1, sync = true)
	public void show(final CommandArgs args) {
		if (args.getArgs().length == 0) {
			Core.getCore().getNametagHandler().showTag(args.getPlayer());
			// Core.getCore().getNametagTeamHandler().update(args.getUser().getUUID(),
			// null, null, true);
			Prefix.TAG.getPrefix().then("Dein Tag ist nun wieder sichtbar!").color(ChatColor.GREEN).send(args.getSender());
		} else {
			final Player p = Bukkit.getPlayer(args.getArgs()[0]);
			if (p == null) {
				Prefix.TAG.getPrefix().then("Diesen Spieler gibt es nicht!").color(ChatColor.RED).send(args.getSender());
				return;
			}
			Core.getCore().getNametagHandler().showTag(p);
			// Core.getCore().getNametagTeamHandler().update(p.getUniqueId(),
			// null, null, true);
			Prefix.TAG.getPrefix().then("Der Tag von " + p.getDisplayName() + " ist nun wieder sichtbar!").color(ChatColor.GREEN).send(args.getSender());
		}
	}
	
	@Command(name = "tag.hide", description = "Versteckt den Nametag von einem Spieler oder dir", permission = "tag.show", max = 1, sync = true)
	public void hide(final CommandArgs args) {
		if (args.getArgs().length == 0) {
			
			// Core.getCore().getNametagTeamHandler().update(args.getPlayer().getUniqueId(),
			// null, null, false);
			Prefix.TAG.getPrefix().then("Dein Tag ist nun unsichtbar!").color(ChatColor.GREEN).send(args.getSender());
		} else {
			final Player p = Bukkit.getPlayer(args.getArgs()[0]);
			if (p == null) {
				Prefix.TAG.getPrefix().then("Diesen Spieler gibt es nicht!").color(ChatColor.RED).send(args.getSender());
				return;
			}
			Core.getCore().getNametagHandler().hideTag(p);
			// Core.getCore().getNametagTeamHandler().update(p.getUniqueId(),
			// null, null, false);
			Prefix.TAG.getPrefix().then("Der Tag von " + p.getDisplayName() + " ist nun unsichtbar!").color(ChatColor.GREEN).send(args.getSender());
		}
	}
	
	@Command(name = "tag.prefix", description = "Verändert das Prefix von einem Spieler", permission = "tag.prefix", min = 1, max = 2, string = 1)
	public void prefix(final CommandArgs args) {
		if (args.getArgs().length == 2) {
			NametagAPI.setPrefix(args.getPlayer().getName(), args.getArgs()[0]);
			// Core.getCore().getNametagTeamHandler().update(args.getPlayer().getUniqueId(),
			// args.getArgs()[0], null, true);
			Prefix.TAG.getPrefix().then("Prefix geändert!").color(ChatColor.GREEN).send(args.getSender());
		} else {
			final Player p = Bukkit.getPlayer(args.getArgs()[0]);
			if (p == null) {
				Prefix.TAG.getPrefix().then("Diesen Spieler gibt es nicht!").color(ChatColor.RED).send(args.getSender());
				return;
			}
			NametagAPI.setPrefix(p.getName(), args.getArgs()[1]);
			// Core.getCore().getNametagTeamHandler().update(p.getUniqueId(),
			// args.getArgs()[1], null, true);
			Prefix.TAG.getPrefix().then("Prefix geändert!").color(ChatColor.GREEN).send(args.getSender());
		}
	}
	
	@Command(name = "tag.suffix", description = "Verändert das Suffix von einem Spieler", permission = "tag.suffix", min = 1, max = 2, string = 1)
	public void suffix(final CommandArgs args) {
		if (args.getArgs().length == 2) {
			NametagAPI.setSuffix(args.getPlayer().getName(), args.getArgs()[0]);
			// Core.getCore().getNametagTeamHandler().update(args.getPlayer().getUniqueId(),
			// null, args.getArgs()[0], true);
			Prefix.TAG.getPrefix().then("Suffix geändert!").color(ChatColor.GREEN).send(args.getSender());
		} else {
			final Player p = Bukkit.getPlayer(args.getArgs()[0]);
			if (p == null) {
				Prefix.TAG.getPrefix().then("Diesen Spieler gibt es nicht!").color(ChatColor.RED).send(args.getSender());
				return;
			}
			NametagAPI.setSuffix(p.getName(), args.getArgs()[1]);
			// Core.getCore().getNametagTeamHandler().update(p.getUniqueId(),
			// null, args.getArgs()[1], true);
			Prefix.TAG.getPrefix().then("Suffix geändert!").color(ChatColor.GREEN).send(args.getSender());
		}
	}
}
