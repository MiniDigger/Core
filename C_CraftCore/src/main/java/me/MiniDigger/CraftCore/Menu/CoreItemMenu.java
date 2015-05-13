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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Menu.ItemMenu;

public class CoreItemMenu implements ItemMenu {
	
	private final String	  name;
	private final int	      size;
	private final onClick	  click;
	List<UUID>	              viewing	= new ArrayList<UUID>();
	
	private final ItemStack[]	items;
	
	public CoreItemMenu(final String name, final int size, final onClick click) {
		this.name = name;
		this.size = size * 9;
		items = new ItemStack[this.size];
		this.click = click;
		Bukkit.getPluginManager().registerEvents(this, Core.getCore().getInstance());
	}
	
	@Override
	@EventHandler
	public void onPluginDisable(final PluginDisableEvent event) {
		for (final Player p : getViewers()) {
			close(p);
		}
	}
	
	@Override
	public ItemMenu open(final Player p) {
		p.openInventory(getInventory(p));
		viewing.add(p.getUniqueId());
		return this;
	}
	
	private Inventory getInventory(final Player p) {
		final Inventory inv = Bukkit.createInventory(p, size, name);
		for (int i = 0; i < items.length; i++) {
			if (items[i] != null) {
				inv.setItem(i, items[i]);
			}
		}
		return inv;
	}
	
	@Override
	public ItemMenu close(final Player p) {
		if (p.getOpenInventory().getTitle().equals(name)) {
			p.closeInventory();
		}
		return this;
	}
	
	@Override
	public List<Player> getViewers() {
		final List<Player> viewers = new ArrayList<Player>();
		for (final UUID s : viewing) {
			viewers.add(Bukkit.getPlayer(s));
		}
		return viewers;
	}
	
	@Override
	@EventHandler
	public void onInventoryClick(final InventoryClickEvent event) {
		if (viewing.contains(event.getWhoClicked().getUniqueId())) {
			event.setCancelled(true);
			final Player p = (Player) event.getWhoClicked();
			if (event.getClickedInventory() == null || !event.getClickedInventory().getName().equals(name) || event.getSlot() == -999) {
				p.updateInventory();
				return;
			}
			final Row row = getRowFromSlot(event.getSlot());
			if (!click.click(p, this, row, event.getSlot() - row.getRow() * 9, event.getCurrentItem())) {
				close(p);
			}
			p.updateInventory();
		}
	}
	
	@Override
	@EventHandler
	public void onInventoryDrag(final InventoryDragEvent event) {
		if (viewing.contains(event.getWhoClicked().getUniqueId())) {
			event.setCancelled(true);
			((Player) event.getWhoClicked()).updateInventory();
		}
	}
	
	@Override
	@EventHandler
	public void onInventoryClose(final InventoryCloseEvent event) {
		if (viewing.contains(event.getPlayer().getUniqueId())) {
			viewing.remove(event.getPlayer().getName());
		}
	}
	
	@Override
	public ItemMenu addButton(final Row row, final int position, final ItemStack item, final String name, final String... lore) {
		items[row.getRow() * 9 + position] = getItem(item, name, lore);
		return this;
	}
	
	@Override
	public Row getRowFromSlot(final int slot) {
		return new CoreRow(slot / 9, items);
	}
	
	@Override
	public Row getRow(final int row) {
		return new CoreRow(row, items);
	}
	
	private ItemStack getItem(final ItemStack item, final String name, final String... lore) {
		final ItemMeta im = item.getItemMeta();
		im.setDisplayName(name);
		im.setLore(Arrays.asList(lore));
		item.setItemMeta(im);
		return item;
	}
	
	public class CoreRow implements Row {
		
		private final ItemStack[]	rowItems	= new ItemStack[9];
		int		                  row;
		
		public CoreRow(final int row, final ItemStack[] items) {
			this.row = row;
			int j = 0;
			for (int i = (row * 9); i < (row * 9) + 9; i++) {
				rowItems[j] = items[i];
				j++;
			}
		}
		
		@Override
		public ItemStack[] getRowItems() {
			return rowItems;
		}
		
		@Override
		public ItemStack getRowItem(final int item) {
			return rowItems[item] == null ? new ItemStack(Material.AIR) : rowItems[item];
		}
		
		@Override
		public int getRow() {
			return row;
		}
	}
}
