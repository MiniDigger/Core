package me.MiniDigger.Core.Cinematic;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Cinematic.commands.Commands;
import me.MiniDigger.CraftCore.AddOn.CoreAddOn;

public class CinematicAddOn extends CoreAddOn {
	
	@Override
	public void enable() {
		Core.getCore().getCommandHandler().registerCommands(new Commands());
	}
	
	@Override
	public void disable() {
		Core.getCore().getCommandHandler().unregisterCommands(new Commands());
	}
}
