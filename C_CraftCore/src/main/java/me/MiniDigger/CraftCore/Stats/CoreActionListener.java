package me.MiniDigger.CraftCore.Stats;

import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.MiniDigger.Core.Stats.ActionType;

import me.MiniDigger.CraftCore.Event.Events.CoreUserChatEvent;

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

	@EventHandler(priority = EventPriority.MONITOR)
	public void onJoin(PlayerJoinEvent e) {
		new CoreAction(ActionType.JOIN, "", e.getPlayer().getUniqueId()).submit();
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onLeave(PlayerQuitEvent e) {
		new CoreAction(ActionType.LEAVE, "", e.getPlayer().getUniqueId()).submit();
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onBucketFill(PlayerBucketFillEvent e) {
		if (!e.isCancelled()) {
			new CoreAction(ActionType.BUCKET_FILLED, e.getBucket().name(), e.getPlayer().getUniqueId()).submit();
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onBucketFill(PlayerBucketEmptyEvent e) {
		if (!e.isCancelled()) {
			new CoreAction(ActionType.BUCKET_EMPTIED, e.getBucket().name(), e.getPlayer().getUniqueId()).submit();
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onChestOpen(InventoryOpenEvent e) {
		if (e.getInventory().getHolder() instanceof Chest || e.getInventory().getHolder() instanceof DoubleChest) {
			if (!e.isCancelled()) {
				new CoreAction(ActionType.CHEST_OPENED, "", e.getPlayer().getUniqueId()).submit();
			}
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onChat(CoreUserChatEvent e) {
		if (!e.isCancelled()) {
			new CoreAction(ActionType.LINE_CHATTED, e.getMsg(), e.getUser().getUUID()).submit();
		}
	}
}
