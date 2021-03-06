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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Prefix.Prefix;

import mkremins.fanciful.FancyMessage;

public class PluginCommands {

	@Command(name = "plugin", aliases = { "plugins", "version", "v", "bukkit", "spigot", "core" }, description = "Zeigt Informationen über das Plugin und den Server", usage = "", permission = "plugins")
	public void pluginCommand(final CommandArgs args) {
		final FancyMessage msg = Prefix.CORE.getPrefix()
				.then("Dieser Server benutzt " + (Core.getCore().getInstance()).getDescription().getFullName() + " by MiniDigger zusammen mit CoreBukkit v" + Bukkit.getBukkitVersion() + "(" + getMinecraftVersion() + ")")
				.color(ChatColor.GRAY);
		msg.send(args.getSender());
	}

	private String getMinecraftVersion() {
		final Matcher matcher = Pattern.compile("(\\(MC: )([\\d\\.]+)(\\))").matcher(Bukkit.getVersion());
		if (matcher.find()) {
			return matcher.group(2);
		} else {
			return null;
		}
	}

	@Command(name = "plugin.list", aliases = { "plugins.list", "version.list", "v.list", "bukkit.list", "spigot.list", "core.list" }, description = "Zeigt alle Plugins", usage = "", permission = "plugins.list")
	public void pluginListCommand(final CommandArgs args) {
		final FancyMessage msg = Prefix.CORE.getPrefix().then("Es sind folgende Plugins installiert ").color(ChatColor.AQUA);
		for (final Plugin p : Bukkit.getServer().getPluginManager().getPlugins()) {
			ChatColor c;
			if (Bukkit.getServer().getPluginManager().isPluginEnabled(p)) {
				c = ChatColor.GREEN;
			} else {
				c = ChatColor.RED;
			}
			msg.then(p.getName()).color(c).tooltip(p.getDescription().getFullName() + " by " + Core.getCore().getStringUtil().listToString(p.getDescription().getAuthors()) + System.getProperty("line.separator")
					+ " Info: " + p.getDescription().getDescription() + System.getProperty("line.separator") + " More Infos:" + p.getDescription().getWebsite()).then(" ");
		}
		msg.send(args.getSender());
	}

	@Command(name = "reload", aliases = { "rl", "warning" }, usage = "", description = "Wird ausgeführt um Commands zu Blocken", permission = "reload")
	public void reloadCommand(final CommandArgs args) {
		if (args.isUser()) {
			final FancyMessage msg = Prefix.CORE.getPrefix().then("Dieser Command wurde deativiert!").color(ChatColor.RED).then(" Wenn du glaubst, dass dies ein Fehler ist wende dich an einen Admin!")
					.color(ChatColor.RED);
			msg.send(args.getPlayer());
		} else {
			final String msg = Prefix.CORE.getConsolPrefix() + ChatColor.RED + "Dieser Command wurde deaktiviert!" + ChatColor.RED + " Wenn du glaubst, dass dies ein Fehler ist wende dich an einen Admin!";
			args.getSender().sendMessage(msg);
		}
	}

	@Command(name = "reloadbroadcaster", usage = "", description = "Läd den Broadcaster neu", permission = "reloadbroadcaster")
	public void reloadbroadcaster(final CommandArgs args) {
		Core.getCore().getBroadcastHandler().init();
		Prefix.API.getPrefix().then("Done.").send(args.getSender());
	}

	@Command(name = "reloaddisabledgames", usage = "", description = "Läd die Deaktivierten Spiele neu", permission = "reloaddisabledgames")
	public void reloaddisabledgames(final CommandArgs args) {
		Core.getCore().getGameHandler().reloadDisabled();
		Prefix.API.getPrefix().then("Done.").send(args.getSender());
	}

	@Command(name = "stop", usage = "", description = "Stopt den Server", permission = "stop")
	public void stopCommand(final CommandArgs args) {
		Core.getCore().getShutdownUtil().doShutdown();
	}
}
