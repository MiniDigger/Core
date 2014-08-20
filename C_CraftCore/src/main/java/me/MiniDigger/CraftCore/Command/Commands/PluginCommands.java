package me.MiniDigger.CraftCore.Command.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

import mkremins.fanciful.FancyMessage;


public class PluginCommands {
	
	@Command(name = "plugin", aliases = { "plugins", "version", "v", "bukkit", "spigot", "core" }, description = "Zeigt Informationen über das Plugin und den Server", usage = "", permission = "plugins")
	public void pluginCommand(CommandArgs args) {
		if (args.isUser()) {
			FancyMessage msg = Prefix.CORE.getPrefix()
			        .then("Dieser Server benutzt " + Core.getInstance().getDescription().getFullName() + " by MiniDigger zusammen mit " + Bukkit.getBukkitVersion())
			        .color(ChatColor.GRAY);
			msg.send(args.getPlayer());
		} else {
			String m = ChatColor.GRAY + "Dieser Server benutzt " + Core.getInstance().getDescription().getFullName() + " by MiniDigger zusammen mit "
			        + Bukkit.getVersion();
			args.getSender().sendMessage(Prefix.CORE.getConsolPrefix() + m);
		}
	}
	
	@Command(name = "plugin.list", aliases = { "plugins.list", "version.list", "v.list", "bukkit.list", "spigot.list", "core.list" }, description = "Zeigt alle Plugins", usage = "", permission = "plugins.list")
	public void pluginListCommand(CommandArgs args) {
		FancyMessage msg = Prefix.CORE.getPrefix().then("Es sind folgende Plugins installiert ").color(ChatColor.AQUA);
		String plugins = ChatColor.AQUA + "Es sind folgende Plugins installiert:";
		for (Plugin p : Bukkit.getServer().getPluginManager().getPlugins()) {
			ChatColor c;
			if (Bukkit.getServer().getPluginManager().isPluginEnabled(p)) {
				c = ChatColor.GREEN;
			} else {
				c = ChatColor.RED;
			}
			plugins += c + p.getName();
			msg.then(p.getName())
			        .color(c)
			        .tooltip(
			                p.getDescription().getFullName() + " by " + StringUtils.listToString(p.getDescription().getAuthors()) + " Info: "
			                        + p.getDescription().getDescription() + " More Infos:" + p.getDescription().getWebsite());
		}
		if (args.isUser()) {
			msg.send(args.getPlayer());
		} else {
			args.getSender().sendMessage(plugins);
		}
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
	
	@Command(name = "stop", usage = "", description = "Stopt den Server", permission = "stop")
	public void stopCommand(CommandArgs args) {
		CommonMethods.stopServer();
	}
}
