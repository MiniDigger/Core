package me.MiniDigger.CraftCore.Command.Commands;

import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;

public class KitCommands {
	
	@Command(name = "kit", permission = "kit", usage = "", description = "Macht alles mit Kits")
	public void kit(CommandArgs args) {
		
	}
	
	@Command(name = "kit.create", permission = "kit.create", usage = "", description = "Macht alles mit Kits", min = 1, consol = false)
	public void create(CommandArgs args) {
		
	}
	
	@Command(name = "kit.give", permission = "kit.give", usage = "<name> [player]", description = "Macht alles mit Kits", min = 1, max = 2, consol = false)
	public void give(CommandArgs args) {
		
	}
	
	@Command(name = "kit.modify", permission = "kit.modify", usage = "<name>", description = "Macht alles mit Kits", min = 1, consol = false)
	public void modify(CommandArgs args) {
		
	}
	
	@Command(name = "kit.delete", permission = "kit.delete", usage = "<name>", description = "Macht alles mit Kits", min = 1)
	public void delete(CommandArgs args) {
		
	}
}
