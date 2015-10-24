/**
 * █████████████████████████████████████████████████████████████████████████████████████████████████████████████████
 * █░░░░░░░░░░░░░░█░░░░░░██░░░░░░█░░░░░░░░░░░░░░████░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░░░███░░░░░░░░░░░░░░█
 * █░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █░░░░░░▄▀░░░░░░█░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░████████████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░████░░▄▀░░███░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░▄▀░░░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░████████████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░██░░▄▀░░█████░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░██░░▄▀░░░░░░█░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░░░░░█████░░░░░░██░░░░░░█░░░░░░░░░░░░░░████░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░██░░░░░░░░░░█░░░░░░░░░░░░░░█
 * █████████████████████████████████████████████████████████████████████████████████████████████████████████████████
 *
 * Copyright © MiniDigger and others - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Martin Benndorf <admin@minidigger.me>, 2013-2015 and others
 */
package me.MiniDigger.CraftCore.Menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Menu.ItemBarMenu;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Item.CoreItemBuilder;

public class CoreItemBarMenu implements ItemBarMenu {

	private ItemStack[]					icons			= new ItemStack[9];
	private ItemBarMenu.ClickHandler[]	actions			= new ItemBarMenu.ClickHandler[9];
	private final String[]				perms			= new String[9];
	private boolean						isRegistered	= false;
	private String						name;

	public CoreItemBarMenu(final String name, final ItemStack[] icons, final ItemBarMenu.ClickHandler[] actions) {
		this(name);
		this.icons = icons;
		this.actions = actions;
	}

	public CoreItemBarMenu(final String name) {
		this.name = name;
		for (int i = 0; i < icons.length; i++) {
			icons[i] = new CoreItemBuilder(Material.AIR).name("<empty>").build();
			actions[i] = new ItemBarMenu.ClickHandler() {

				@Override
				public void click(final ItemBarMenu m, final ItemStack is, final User u, final Entity entity) {
				}
			};
			perms[i] = "";
		}
		isRegistered = true;
		Bukkit.getServer().getPluginManager().registerEvents(this, Core.getCore().getInstance());
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setIcon(final int id, final ItemStack icon) {
		icons[id] = icon;
	}

	@Override
	public void setAction(final int id, final ItemBarMenu.ClickHandler action) {
		actions[id] = action;
	}

	@Override
	public void setPermission(final int i, final String string) {
		perms[i] = string;
	}

	@EventHandler
	public void onInteract(final PlayerInteractEvent e) {
		if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) {
			return;
		}
		try {
			if (name != Core.getCore().getMenuHandler().getMenu(e.getPlayer().getUniqueId()).getName()) {
				return;
			}
		}
		catch (final Exception ex) {}

		if (e.getPlayer() == null) {
			HandlerList.unregisterAll(this);
			return; // RIP
		}
		for (int i = 0; i < icons.length; i++) {
			final ItemStack is = icons[i];
			if (is != null && e.getItem() != null && is.hasItemMeta() && e.getItem().hasItemMeta() && e.getItem().getItemMeta().getDisplayName() == is.getItemMeta().getDisplayName()) {
				final User u = Core.getCore().getUserHandler().get(e.getPlayer().getUniqueId());
				if (actions[i] != null) {
					actions[i].click(this, is, u, null);
					e.setCancelled(true);
					e.setUseInteractedBlock(Result.DENY);
					e.setUseItemInHand(Result.DENY);
				}
			}
		}
	}

	@EventHandler
	public void onInteractEntitx(final PlayerInteractEntityEvent e) {
		try {
			if (name != Core.getCore().getMenuHandler().getMenu(e.getPlayer().getUniqueId()).getName()) {
				return;
			}
		}
		catch (final Exception ex) {}

		if (e.getPlayer() == null) {
			HandlerList.unregisterAll(this);
			return; // RIP
		}
		for (int i = 0; i < icons.length; i++) {
			final ItemStack is = icons[i];
			if (is != null && e.getPlayer().getItemInHand() != null && is.hasItemMeta() && e.getPlayer().getItemInHand().hasItemMeta()
					&& e.getPlayer().getItemInHand().getItemMeta().getDisplayName() == is.getItemMeta().getDisplayName()) {
				final User u = Core.getCore().getUserHandler().get(e.getPlayer().getUniqueId());
				if (actions[i] != null) {
					actions[i].click(this, is, u, e.getRightClicked());
					e.setCancelled(true);
				}
			}
		}
	}

	@EventHandler
	public void onItemClick(final InventoryClickEvent e) {
		final ItemStack[] i = new ItemStack[] { e.getCurrentItem(), e.getCursor() };
		for (final ItemStack item : i) {
			if (item != null) {
				for (final ItemStack is : icons) {
					if (is.equals(item) && is.getType() != Material.AIR) {
						e.setResult(Result.DENY);
						e.setCancelled(true);
						// e.setCurrentItem(null);
						((Player) e.getWhoClicked()).updateInventory();

						new BukkitRunnable() {

							@Override
							public void run() {
								if (e.getWhoClicked() != null) {
									((Player) e.getWhoClicked()).updateInventory();
								}
							}
						}.runTaskLater(Core.getCore().getInstance(), 10);
					}
				}
			}
		}
	}

	@EventHandler
	public void onItemClick(final InventoryDragEvent e) {
		final ItemStack[] i = new ItemStack[] { e.getOldCursor(), e.getCursor() };
		for (final ItemStack item : i) {
			if (item != null) {
				for (final ItemStack is : icons) {
					if (is.equals(item) && is.getType() != Material.AIR) {
						e.setResult(Result.DENY);
						e.setCancelled(true);
						// e.setCursor(null);
						((Player) e.getWhoClicked()).updateInventory();

						new BukkitRunnable() {

							@Override
							public void run() {
								if (e.getWhoClicked() != null) {
									((Player) e.getWhoClicked()).updateInventory();
								}
							}
						}.runTaskLater(Core.getCore().getInstance(), 10);
					}
				}
			}
		}
	}

	@Override
	public void open(final User u) {
		if (!isRegistered) {
			isRegistered = true;
			Bukkit.getServer().getPluginManager().registerEvents(this, Core.getCore().getInstance());
		}
		Core.getCore().getPlayerUtil().clearInv(u.getPlayer());
		for (int i = 0; i < icons.length; i++) {
			if (!perms[i].equals("")) {
				if (!u.hasPermission(perms[i])) {
					continue;
				}
			}
			u.getPlayer().getInventory().setItem(i, icons[i]);
		}
		u.getPlayer().updateInventory();
	}

	@Override
	public void close(final User u) {
		isRegistered = false;
		HandlerList.unregisterAll(this);
		Core.getCore().getPlayerUtil().clearInv(u.getPlayer());
	}

	@Override
	public String toString() {
		final StringBuilder b = new StringBuilder(this.getClass().toString() + ": ");
		for (final ItemStack is : icons) {
			b.append("i:" + is.toString() + ",");
		}
		return b.toString();
	}

	@Override
	public ItemBarMenu clone() {
		final ItemBarMenu m = new CoreItemBarMenu(getName());
		for (int i = 0; i < 9; i++) {
			m.setAction(i, actions[i]);
			m.setIcon(i, icons[i]);
			m.setPermission(i, perms[i]);
		}
		return m;
	}

	@Override
	public void setName(final String string) {
		name = string;
	}
}
