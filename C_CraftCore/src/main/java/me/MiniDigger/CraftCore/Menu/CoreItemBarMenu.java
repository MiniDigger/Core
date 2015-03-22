package me.MiniDigger.CraftCore.Menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Menu.ItemBarMenu;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Item.CoreItemBuilder;

public class CoreItemBarMenu implements ItemBarMenu {
	
	private ItemStack[]	               icons	    = new ItemStack[9];
	private ItemBarMenu.ClickHandler[]	actions	    = new ItemBarMenu.ClickHandler[9];
	private boolean	                   isRegistered	= false;
	private String	                   name;
	
	public CoreItemBarMenu(String name, ItemStack[] icons, ItemBarMenu.ClickHandler[] actions) {
		this(name);
		this.icons = icons;
		this.actions = actions;
	}
	
	public CoreItemBarMenu(String name) {
		this.name = name;
		for (int i = 0; i < icons.length; i++) {
			icons[i] = new CoreItemBuilder(Material.AIR).name("<empty>").build();
			actions[i] = new ItemBarMenu.ClickHandler() {
				
				@Override
				public void click(ItemBarMenu m, ItemStack is, User u) {
				}
			};
		}
		isRegistered = true;
		Bukkit.getServer().getPluginManager().registerEvents(this, Core.getCore().getInstance());
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setIcon(int id, ItemStack icon) {
		icons[id] = icon;
	}
	
	@Override
	public void setAction(int id, ItemBarMenu.ClickHandler action) {
		actions[id] = action;
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) {
			return;
		}
		try {
			if (name != Core.getCore().getMenuHandler().getMenu(e.getPlayer().getUniqueId()).getName()) {
				return;
			}
		} catch (Exception ex) {}
		
		if (e.getPlayer() == null) {
			HandlerList.unregisterAll(this);
			return; // RIP
		}
		for (int i = 0; i < icons.length; i++) {
			ItemStack is = icons[i];
			if (is != null && e.getItem() != null && is.hasItemMeta() && e.getItem().hasItemMeta()
			        && e.getItem().getItemMeta().getDisplayName() == is.getItemMeta().getDisplayName()) {
				User u = Core.getCore().getUserHandler().get(e.getPlayer().getUniqueId());
				if (actions[i] != null) {
					actions[i].click(this, is, u);
					e.setCancelled(true);
					e.setUseInteractedBlock(Result.DENY);
					e.setUseItemInHand(Result.DENY);
				}
			}
		}
	}
	
	@Override
	public void open(User u) {
		if (!isRegistered) {
			isRegistered = true;
			Bukkit.getServer().getPluginManager().registerEvents(this, Core.getCore().getInstance());
		}
		Core.getCore().getPlayerUtil().clearInv(u.getPlayer());
		for (int i = 0; i < icons.length; i++) {
			u.getPlayer().getInventory().setItem(i, icons[i]);
		}
		u.getPlayer().updateInventory();
	}
	
	@Override
	public void close(User u) {
		isRegistered = false;
		HandlerList.unregisterAll(this);
		Core.getCore().getPlayerUtil().clearInv(u.getPlayer());
	}
	
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder(this.getClass().toString() + ": ");
		for (ItemStack is : icons) {
			b.append("i:" + is.toString() + ",");
		}
		return b.toString();
	}
}
