package me.MiniDigger.CraftCore.Command.Commands;

import org.bukkit.ChatColor;

public class ToggleCommands {
	
	@Command(name = "toggle", description = "Stellt viele Sachen um", permission = "toggle")
	public void toggle(CommandArgs args) {
		
	}
	
	@Command(name = "toggle.build", description = "Toggelt den Build Modus", permission = "toggle.build")
	public void build(CommandArgs args) {
		if (!args.isUser()) {
			args.getSender().sendMessage(Prefix.API.getConsolPrefix() + "Du kannst den Befehl nur als User ausführen!");
			return;
		}
		
		if (BuildHandler.getInstance().isBuilder(args.getUser())) {
			args.getUser().sendMessage(Prefix.API.getPrefix().then("BuildMode deaktiviert!").color(ChatColor.GREEN));
			BuildHandler.getInstance().setBuilder(args.getUser(), false);
		} else {
			args.getUser().sendMessage(Prefix.API.getPrefix().then("BuildMode aktiviert!").color(ChatColor.GREEN));
			BuildHandler.getInstance().setBuilder(args.getUser(), true);
		}
	}
}
