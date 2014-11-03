package me.MiniDigger.Core.Cinematic;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Cinematic.commands.CineCommands;
import me.MiniDigger.Core.Cinematic.commands.CineCompleter;
import me.MiniDigger.CraftCore.AddOn.CoreAddOn;

public class CinematicAddOn extends CoreAddOn {
	
	@Override
	public void enable() {
		Core.getCore().getCommandHandler().registerCommands(new CineCommands());
		Core.getCore().getCommandHandler().registerCommands(new CineCompleter());
	}
	
	@Override
	public void disable() {
		Core.getCore().getCommandHandler().unregisterCommands(new CineCommands());
		Core.getCore().getCommandHandler().unregisterCommands(new CineCompleter());
	}
}
