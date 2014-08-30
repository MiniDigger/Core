package me.MiniDigger.CraftCore.Command.Commands;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Game.GameType;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.CraftCore.CoreMain;
import mkremins.fanciful.FancyMessage;

import org.bukkit.ChatColor;

public class SettingsCommands {
	
	@Command(name = "setting.gametype", description = "Ändert den Spielmodi des Servers", usage = "<type>", permission = "setting.gametype", consol = false, min = 1, max = 1)
	public void gamemode(final CommandArgs args) {
		GameType type;
		try {
			type = GameType.valueOf(args.getArgs()[0]);
		} catch (final Exception ex) {
			args.getUser().sendMessage(Prefix.API.getPrefix().then("Unbekannter Modi: " + args.getArgs()[0]).color(ChatColor.RED));
			final FancyMessage msg = Prefix.API.getPrefix();
			for (final GameType t : GameType.values()) {
				msg.then(t.getName() + " ").color(ChatColor.AQUA).command("/setting gametype " + t.name());
			}
			args.getUser().sendMessage(Prefix.API.getPrefix().then("Es stehen folgende Modi zu Auswahl:"));
			args.getUser().sendMessage(msg);// TODO only installed modi
			return;
		}
		
		((CoreMain) Core.getCore().getInstance()).getConfig().set("server-type", type.name());
		((CoreMain) Core.getCore().getInstance()).saveConfig();
		
		args.getUser().sendMessage(Prefix.API.getPrefix().then("Modi geändert!").color(ChatColor.GREEN));
	}
}
