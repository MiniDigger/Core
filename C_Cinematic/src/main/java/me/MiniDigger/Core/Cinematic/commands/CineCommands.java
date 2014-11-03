package me.MiniDigger.Core.Cinematic.commands;

import org.bukkit.ChatColor;

import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Prefix.Prefix;

public class CineCommands {
	
	@Command(name = "cinematic", description = "Macht alles mit Cinematics", permission = "cinematic", aliases = "cine")
	public void cinematic(final CommandArgs args) {
		Prefix.CINE.getPrefix().then("Klicke hier ").color(ChatColor.YELLOW).command("/cine record ").then("um eine Cinematic aufzunehmen").color(ChatColor.GOLD)
		        .send(args.getSender());
		Prefix.CINE.getPrefix().then("Klicke hier ").color(ChatColor.YELLOW).suggest("/cine play ").then("um eine Cinematic abzuspielen").color(ChatColor.GOLD)
		        .send(args.getSender());
		Prefix.CINE.getPrefix().then("Klicke hier ").color(ChatColor.YELLOW).suggest("/cine list").then("um alle Cinematics anzuzeigen").color(ChatColor.GOLD)
		        .send(args.getSender());
	}
	
	@Command(name = "cinematic.record", description = "Recorded eine neue Cinematic", permission = "cinematic.record", usage = "<name>", min = 1)
	public void record(final CommandArgs args) {
		
	}
	
	@Command(name = "cinematic.play", description = "Spielt eine Cinematic ab", permission = "cinematic.play", usage = "<name>", min = 1)
	public void play(final CommandArgs args) {
		
	}
	
	@Command(name = "cinematic.list", description = "Listet alle Cinematics auf", permission = "cinematic.list")
	public void list(final CommandArgs args) {
		
	}
}
