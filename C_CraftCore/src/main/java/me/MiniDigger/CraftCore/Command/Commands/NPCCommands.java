package me.MiniDigger.CraftCore.Command.Commands;




public class NPCCommands {
	@Command(name="npc.spawn",description="Spawnt einen NPC",permission="npc.spawn")
	public void spawn(CommandArgs args){
		NPCHandler.getInstance().spawn(args.getUser());
	}
}
