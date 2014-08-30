package me.MiniDigger.CraftCore.Command.Commands;

import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Item.ItemType;
import me.MiniDigger.Core.Prefix.Prefix;

import org.bukkit.ChatColor;

public class ItemCommands {
	
	@Command(name = "item", description = "Gibt das Item", permission = "item", usage = "<ITEM>", consol = false, max = 1, min = 1)
	public void item(CommandArgs args) {
		ItemType item;
		try {
			item = ItemType.valueOf(args.getArgs()[0]);
		} catch (Exception ex) {
			args.getUser().sendMessage(Prefix.API.getPrefix().then("Unbekanntes Item").color(ChatColor.RED));
			return;
		}
		
		args.getPlayer().getInventory().addItem(item.getItem().getItem());
		args.getUser().sendMessage(Prefix.API.getPrefix().then("Item hinzugefügt!").color(ChatColor.GREEN));
	}
}
