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
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Kit.Kit;
import me.MiniDigger.Core.Prefix.Prefix;

public class KitCommands {
	
	@Command(name = "kit", permission = "kit", usage = "", description = "Macht alles mit Kits")
	public void kit(final CommandArgs args) {
		Prefix.KIT.getPrefix().then("Klicke hier ").color(ChatColor.YELLOW).suggest("/kit create ").then("um ein neues Kit zu erzeugen").color(ChatColor.GOLD)
		        .send(args.getSender());
		Prefix.KIT.getPrefix().then("Klicke hier ").color(ChatColor.YELLOW).suggest("/kit give ").then("um ein Kit zu vergeben").color(ChatColor.GOLD)
		        .send(args.getSender());
		Prefix.KIT.getPrefix().then("Klicke hier ").color(ChatColor.YELLOW).suggest("/kit modify ").then("um ein Kit zu modifizieren").color(ChatColor.GOLD)
		        .send(args.getSender());
		Prefix.KIT.getPrefix().then("Klicke hier ").color(ChatColor.YELLOW).suggest("/kit delete ").then("um ein Kit zu löschen").color(ChatColor.GOLD)
		        .send(args.getSender());
	}
	
	@Command(name = "kit.create", permission = "kit.create", usage = "", description = "Macht alles mit Kits", min = 1, consol = false)
	public void create(final CommandArgs args) {
		if (Core.getCore().getKitHandler().getKit(args.getArgs()[0]) != null) {
			args.getUser().sendMessage(Prefix.KIT.getPrefix().then("Diese Kit gibt es bereits!").color(ChatColor.RED));
		}
		
		final Kit k = Core.getCore().getKitHandler().createKit(args.getArgs()[0]);
		int i = 0;
		for (ItemStack is : args.getPlayer().getInventory().getContents()) {
			if (is == null) {
				is = new ItemStack(Material.AIR);
			}
			k.setContent(i, is);
			i++;
		}
		
		i = 0;
		for (ItemStack is : args.getPlayer().getInventory().getArmorContents()) {
			if (is == null) {
				is = new ItemStack(Material.AIR);
			}
			k.setArmor(i, is);
			i++;
		}
		
		k.save();
		args.getUser().sendMessage(Prefix.KIT.getPrefix().then("Kit erstellt").color(ChatColor.GREEN));
	}
	
	@Command(name = "kit.give", permission = "kit.give", usage = "<name> [player]", description = "Macht alles mit Kits", min = 1, max = 2, consol = false)
	public void give(final CommandArgs args) {
		if (Core.getCore().getKitHandler().getKit(args.getArgs()[0]) == null) {
			args.getUser().sendMessage(Prefix.KIT.getPrefix().then("Unbekanntes Kit!").color(ChatColor.RED));
			return;
		}
		
		if (args.getArgs().length == 1) {
			Core.getCore().getKitHandler().give(args.getPlayer(), args.getArgs()[0]);
		} else {
			final Player p = Bukkit.getPlayer(args.getArgs()[1]);
			if (p == null) {
				args.getUser().sendMessage(Prefix.KIT.getPrefix().then("Unbekannter Spieler!").color(ChatColor.RED));
				return;
			}
			Core.getCore().getKitHandler().give(p, args.getArgs()[0]);
		}
		
		args.getUser().sendMessage(Prefix.KIT.getPrefix().then("Kit gegeben!").color(ChatColor.GREEN));
	}
	
	@Command(name = "kit.modify", permission = "kit.modify", usage = "<name>", description = "Macht alles mit Kits", min = 1, consol = false)
	public void modify(final CommandArgs args) {
		if (Core.getCore().getKitHandler().getKit(args.getArgs()[0]) == null) {
			args.getUser().sendMessage(Prefix.KIT.getPrefix().then("Diese Kit gibt es nicht!").color(ChatColor.RED));
		}
		
		final Kit k = Core.getCore().getKitHandler().getKit(args.getArgs()[0]);
		int i = 0;
		for (ItemStack is : args.getPlayer().getInventory().getContents()) {
			if (is == null) {
				is = new ItemStack(Material.AIR);
			}
			k.setContent(i, is);
			i++;
		}
		
		i = 0;
		for (ItemStack is : args.getPlayer().getInventory().getArmorContents()) {
			if (is == null) {
				is = new ItemStack(Material.AIR);
			}
			k.setArmor(i, is);
			i++;
		}
		
		k.save();
		args.getUser().sendMessage(Prefix.KIT.getPrefix().then("Kit modifiziert!").color(ChatColor.GREEN));
	}
	
	@Command(name = "kit.delete", permission = "kit.delete", usage = "<name>", description = "Macht alles mit Kits", min = 1)
	public void delete(final CommandArgs args) {
		if (Core.getCore().getKitHandler().getKit(args.getArgs()[0]) == null) {
			args.getUser().sendMessage(Prefix.KIT.getPrefix().then("Unbekanntes Kit!").color(ChatColor.RED));
			return;
		}
		Core.getCore().getKitHandler().removeKit(args.getArgs()[0]);
		
		args.getUser().sendMessage(Prefix.KIT.getPrefix().then("Kit gelöscht!").color(ChatColor.GREEN));
	}
}
