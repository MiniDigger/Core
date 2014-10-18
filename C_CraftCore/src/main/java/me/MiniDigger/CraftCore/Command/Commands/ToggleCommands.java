package me.MiniDigger.CraftCore.Command.Commands;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.User.User;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class ToggleCommands {
	
	@Command(name = "toggle", description = "Stellt viele Sachen um", permission = "toggle")
	public void toggle(final CommandArgs args) {
		Prefix.API.getPrefix().then("Stellt viele Sachen um").send(args.getSender());
		Prefix.API.getPrefix().then("Sub-Commands: build,hacks").send(args.getSender());
	}
	
	@Command(name = "toggle.build", description = "Toggelt den Build Modus", permission = "toggle.build", consol = false, min = 0, max = 1)
	public void build(final CommandArgs args) {
		User user;
		if (args.getArgs().length == 1) {
			user = Core.getCore().getUserHandler().get(Bukkit.getPlayer(args.getArgs()[0]).getUniqueId());
			if (user == null) {
				args.getUser().sendMessage(Prefix.API.getPrefix().then("Unbekannter Spieler " + args.getArgs()[0]).color(ChatColor.RED));
				return;
			}
		} else {
			user = args.getUser();
		}
		
		if (Core.getCore().getBuildHandler().isBuilder(user)) {
			user.sendMessage(Prefix.API.getPrefix().then("BuildMode deaktiviert!").color(ChatColor.GREEN));
			Core.getCore().getBuildHandler().setBuilder(user, false);
		} else {
			user.sendMessage(Prefix.API.getPrefix().then("BuildMode aktiviert!").color(ChatColor.GREEN));
			Core.getCore().getBuildHandler().setBuilder(user, true);
		}
	}
	
	@Command(name = "toggle.hacks", description = "Toggelt die Hacks", permission = "toggle.hacks", consol = false, min = 0, max = 1)
	public void hacks(final CommandArgs args) {
		// TODO Toggle hacks
	}
}
