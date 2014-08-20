package me.MiniDigger.CraftCore.Command.Commands;

import org.bukkit.ChatColor;
import me.MiniDigger.Core.Core;
import mkremins.fanciful.FancyMessage;

public class SettingsCommands {
	
	@Command(name = "setting.gametype", description = "Ändert den Spielmodi des Servers", usage = "", permission = "setting.gametype")
	public void gamemode(CommandArgs args) {
		if (!args.isUser()) {
			args.getSender().sendMessage(Prefix.API.getConsolPrefix() + " Nur für Spieler!");
			return;
		}
		
		if (args.getArgs().length == 0) {
			FancyMessage msg = Prefix.API.getPrefix();
			for (GameType t : GameType.values()) {
				msg.then(t.getName() + " ").color(ChatColor.AQUA).command("/setting gametype " + t.name());
			}
			args.getUser().sendMessage(Prefix.API.getPrefix().then("Es stehen folgende Modi zu Auswahl:"));
			args.getUser().sendMessage(msg);
		} else {
			GameType type;
			try {
				type = GameType.valueOf(args.getArgs()[0]);
			} catch (Exception ex) {
				args.getUser().sendMessage(Prefix.API.getPrefix().then("Unbekannter Modi: " + args.getArgs()[0]).color(ChatColor.RED));
				return;
			}
			
			Core.getInstance().getConfig().set("server-type", type.name());
			Core.getInstance().saveConfig();
			
			args.getUser().sendMessage(Prefix.API.getPrefix().then("Modi geändert!").color(ChatColor.GREEN));
		}
	}
}
