package me.MiniDigger.CraftCore.Command.Commands;

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
	public void pluginCommand(CommandArgs args) {
		FancyMessage msg = Prefix.CORE
		        .getPrefix()
		        .then("Dieser Server benutzt " + ((Plugin) Core.getCore().getInstance()).getDescription().getFullName() + " by MiniDigger zusammen mit "
		                + Bukkit.getBukkitVersion()).color(ChatColor.GRAY);
		msg.send(args.getSender());
	}
	
	@Command(name = "plugin.list", aliases = { "plugins.list", "version.list", "v.list", "bukkit.list", "spigot.list", "core.list" }, description = "Zeigt alle Plugins", usage = "", permission = "plugins.list")
	public void pluginListCommand(CommandArgs args) {
		FancyMessage msg = Prefix.CORE.getPrefix().then("Es sind folgende Plugins installiert ").color(ChatColor.AQUA);
		for (Plugin p : Bukkit.getServer().getPluginManager().getPlugins()) {
			ChatColor c;
			if (Bukkit.getServer().getPluginManager().isPluginEnabled(p)) {
				c = ChatColor.GREEN;
			} else {
				c = ChatColor.RED;
			}
			msg.then(p.getName())
			        .color(c)
			        .tooltip(
			                p.getDescription().getFullName() + " by " + Core.getCore().getStringUtil().listToString(p.getDescription().getAuthors()) + " Info: "
			                        + p.getDescription().getDescription() + " More Infos:" + p.getDescription().getWebsite()).then(" ");
		}
		msg.send(args.getSender());
	}
	
	@Command(name = "reload", aliases = { "rl", "warning" }, usage = "", description = "Wird ausgeführt um Commands zu Blocken", permission = "reload")
	public void reloadCommand(CommandArgs args) {
		if (args.isUser()) {
			FancyMessage msg = Prefix.CORE.getPrefix().then("Dieser Command wurde deativiert!").color(ChatColor.RED)
			        .then(" Wenn du glaubst, dass dies ein Fehler ist wende dich an einen Admin!").color(ChatColor.RED);
			msg.send(args.getPlayer());
		} else {
			String msg = Prefix.CORE.getConsolPrefix() + ChatColor.RED + "Dieser Command wurde deaktiviert!" + ChatColor.RED
			        + " Wenn du glaubst, dass dies ein Fehler ist wende dich an einen Admin!";
			args.getSender().sendMessage(msg);
		}
	}
	
	@SuppressWarnings("deprecation")
    @Command(name = "stop", usage = "", description = "Stopt den Server", permission = "stop")
	public void stopCommand(CommandArgs args) {
		Core.getCore().getCommonMethods().stopServer();
	}
}
