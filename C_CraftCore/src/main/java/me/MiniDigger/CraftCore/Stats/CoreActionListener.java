package me.MiniDigger.CraftCore.Stats;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import me.MiniDigger.Core.Stats.ActionType;

public class CoreActionListener implements Listener {
	@EventHandler(priority = EventPriority.MONITOR)
	public void onItemPickup(PlayerPickupItemEvent e) {
		if (!e.isCancelled()) {
			new CoreAction(ActionType.ITEM_PICKUP, e.getItem().getItemStack().getAmount() + ":" + e.getItem().getItemStack().getType().name(), e.getPlayer().getUniqueId()).submit();
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onItemDrop(PlayerDropItemEvent e) {
		if (!e.isCancelled()) {
			new CoreAction(ActionType.ITEM_DROP, e.getItemDrop().getItemStack().getAmount() + ":" + e.getItemDrop().getItemStack().getType().name(), e.getPlayer().getUniqueId()).submit();
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onItemBreak(PlayerItemBreakEvent e) {
		new CoreAction(ActionType.ITEM_BREAK, e.getBrokenItem().getType().name(), e.getPlayer().getUniqueId()).submit();
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onItemConsume(PlayerItemConsumeEvent e) {
		if (!e.isCancelled()) {
			new CoreAction(ActionType.ITEM_EATEN, e.getItem().getType().name(), e.getPlayer().getUniqueId()).submit();
		}
	}
}
