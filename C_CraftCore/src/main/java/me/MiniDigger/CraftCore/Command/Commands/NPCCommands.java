/*******************************************************************************
 * Test
 *******************************************************************************/
package me.MiniDigger.CraftCore.Command.Commands;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Prefix.Prefix;

public class NPCCommands {
	
	@Command(name = "npc", description = "Macht alles mit NPCS", permission = "npc")
	public void npc(final CommandArgs args) {
		Prefix.API.getPrefix().then("Versuchs mal mit '/npc spawn' :D");
	}
	
	@Command(name = "npc.spawn", description = "Spawnt einen NPC", permission = "npc.spawn", consol = false, max = 0)
	public void spawn(final CommandArgs args) {
		Core.getCore().getNPCHandler().spawn(args.getPlayer().getLocation());
	}
}
