package me.MiniDigger.CraftCore.Command.Commands;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;

public class NPCCommands {
	
	@Command(name = "npc.spawn", description = "Spawnt einen NPC", permission = "npc.spawn", consol = false, max = 0)
	public void spawn(CommandArgs args) {
		Core.getCore().getNPCHandler().spawn(args.getPlayer().getLocation());
	}
}
