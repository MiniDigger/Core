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
package me.MiniDigger.CraftCore.Util;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import com.bobacadodl.imgmessage.ImageChar;
import com.bobacadodl.imgmessage.ImageMessage;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.User.User;
import me.MiniDigger.Core.Util.CommonMethods;

public class CoreCommonMethods implements CommonMethods {
	
	@Override
	public void onlyPlayer(final CommandSender sender, final String command) {
		sender.sendMessage(Prefix.SECURITY.getConsolPrefix() + ChatColor.RED + "Der Command " + command + " kann nur als Spieler ausgeführt werden!");
		sender.sendMessage(Prefix.HINT.getConsolPrefix() + ChatColor.GOLD + "Maybe use sudo? ;D");
	}
	
	@Override
	public void printJoinMessage(final User user) {
		if (user.getPlayer() == null) {
			System.out.println("no join msg for you, sir!");
			return;
		}
		final Date d1 = new Date();
		Bukkit.getScheduler().runTaskAsynchronously(Core.getCore().getInstance(), new Runnable() {
			
			@Override
			public void run() {
				try {
					final ImageMessage msg = new ImageMessage(ImageIO.read(new URL("https://minotar.net/avatar/" + user.getRealName() + "/200.png")), 8,
					        ImageChar.DARK_SHADE.getChar());
					msg.appendCenteredText("", "", ChatColor.GOLD + "Willkommen", ChatColor.YELLOW + user.getDisplayName(), ChatColor.GOLD + "Du bist auf Server:",
					        ChatColor.YELLOW + (Core.getCore().getInstance()).getConfig().getString("server-name"));
					msg.sendToPlayer(user.getPlayer());
				} catch (final IOException e) {
					// e.printStackTrace();
					// Fallback
					
					final Date d2 = new Date();
					if (d2.getTime() - d1.getTime() > 1000 * 5) {
						System.out.println("fuck that");
					} else {
						user.sendMessage(Prefix.API.getPrefix().then("Willkommen").color(ChatColor.GOLD));
						user.sendMessage(Prefix.API.getPrefix().then(user.getDisplayName()).color(ChatColor.YELLOW));
						user.sendMessage(Prefix.API.getPrefix().then("Du bist auf Server").color(ChatColor.GOLD));
						user.sendMessage(Prefix.API.getPrefix().then((Core.getCore().getInstance()).getConfig().getString("server-name")).color(ChatColor.YELLOW));
					}
				}
			}
		});
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				try {
					// if
					// (Core.getCore().getProtocolHandler().getProtocolVersion(user.getPlayer().getName())
					// > 5) {
					// Prefix.API.getPrefix().then("Es sieht so aus als würdest du 1.8.* oder einen Snapshot benutzen.").color(ChatColor.GOLD).style(ChatColor.BOLD)
					// .send(user.getPlayer());
					// Prefix.API.getPrefix().then("Dies kannst du tun, kann aber zu Problemen führen").color(ChatColor.GOLD).style(ChatColor.BOLD)
					// .send(user.getPlayer());
					// }
					
					Core.getCore().getHoloHandler().createSpawnHolo(user.getPlayer().getEyeLocation(), user);
				} catch (final Exception ex) {
					
				}
			}
		}.runTaskLater(Core.getCore().getInstance(), 20 * 2);
		
	}
	
	@Override
	public void killPlugin() {
		// System.out.println("Licene check failed but I don't care as long as you registered it at startup");
		System.out.println("The Plugin decided to kill itselve");
		System.out.println("This mostly happens, if your licence is wrong, or a other server uses the same licence");
		Bukkit.getPluginManager().disablePlugin(Core.getCore().getInstance());
	}
	
	@Override
	public List<String> completer(final List<String> list, final String prefix) {
		final List<String> result = new ArrayList<>();
		
		for (final String s : list) {
			if (s.toLowerCase().startsWith(prefix.toLowerCase())) {
				result.add(s);
			}
		}
		
		return result;
	}
}
