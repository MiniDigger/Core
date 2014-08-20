package me.MiniDigger.CraftCore.Command.Commands;

import org.bukkit.ChatColor;


public class ItemCommands {
	
	@Command(name = "item", description = "Gibt das Item", permission = "item", usage = "<ITEM>")
	public void item(CommandArgs args) {
		if (!args.isUser()) {
			args.getSender().sendMessage(Prefix.API.getConsolPrefix() + ChatColor.RED + "Nur für Spieler!");
			return;
		}
		
		Item item;
		try {
			item = Item.valueOf(args.getArgs()[0]);
		} catch (Exception ex) {
			args.getUser().sendMessage(Prefix.API.getPrefix().then("Unbekanntes Item").color(ChatColor.RED));
			return;
		}
		
		args.getPlayer().getInventory().addItem(item.getItem().getItem());
		args.getUser().sendMessage(Prefix.API.getPrefix().then("Item hinzugefügt!").color(ChatColor.GREEN));
	}
}
