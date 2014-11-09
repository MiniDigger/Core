package me.MiniDigger.CraftCore.Item;

import me.MiniDigger.Core.Item.ItemMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

public class CoreItemMenu implements ItemMenu {
	
	private String	    name;
	private int	        size;
	private onClick	    click;
	List<String>	    viewing	= new ArrayList<String>();
	
	private ItemStack[]	items;
	
	public CoreItemMenu(String name, int size, onClick click) {
		this.name = name;
		this.size = size * 9;
		items = new ItemStack[this.size];
		this.click = click;
		Bukkit.getPluginManager().registerEvents(this, Bukkit.getPluginManager().getPlugins()[0]);
	}
	
	@Override
	@EventHandler
	public void onPluginDisable(PluginDisableEvent event) {
		for (Player p : this.getViewers()) {
			close(p);
		}
	}
	
	@Override
	public ItemMenu open(Player p) {
		p.openInventory(getInventory(p));
		viewing.add(p.getName());
		return this;
	}
	
	private Inventory getInventory(Player p) {
		Inventory inv = Bukkit.createInventory(p, size, name);
		for (int i = 0; i < items.length; i++) {
			if (items[i] != null) {
				inv.setItem(i, items[i]);
			}
		}
		return inv;
	}
	
	@Override
	public ItemMenu close(Player p) {
		if (p.getOpenInventory().getTitle().equals(name)) {
			p.closeInventory();
		}
		return this;
	}
	
	@Override
	public List<Player> getViewers() {
		List<Player> viewers = new ArrayList<Player>();
		for (String s : viewing) {
			viewers.add(Bukkit.getPlayer(s));
		}
		return viewers;
	}
	
	@Override
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (viewing.contains(event.getWhoClicked().getName())) {
			event.setCancelled(true);
			Player p = (Player) event.getWhoClicked();
			if (event.getClickedInventory() == null || !event.getClickedInventory().getName().equals(name) || event.getSlot() == -999) {
				p.updateInventory();
				return;
			}
			Row row = getRowFromSlot(event.getSlot());
			if (!click.click(p, this, row, event.getSlot() - row.getRow() * 9, event.getCurrentItem())) {
				close(p);
			}
			p.updateInventory();
		}
	}
	
	@Override
	@EventHandler
	public void onInventoryDrag(InventoryDragEvent event) {
		if (viewing.contains(event.getWhoClicked().getName())) {
			event.setCancelled(true);
			((Player) event.getWhoClicked()).updateInventory();
		}
	}
	
	@Override
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event) {
		if (viewing.contains(event.getPlayer().getName())) {
			viewing.remove(event.getPlayer().getName());
		}
	}
	
	@Override
	public ItemMenu addButton(Row row, int position, ItemStack item, String name, String... lore) {
		items[row.getRow() * 9 + position] = getItem(item, name, lore);
		return this;
	}
	
	@Override
	public Row getRowFromSlot(int slot) {
		return new CoreRow(slot / 9, items);
	}
	
	@Override
	public Row getRow(int row) {
		return new CoreRow(row, items);
	}
	
	private ItemStack getItem(ItemStack item, String name, String... lore) {
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(name);
		im.setLore(Arrays.asList(lore));
		item.setItemMeta(im);
		return item;
	}
	
	public class CoreRow implements Row {
		
		private ItemStack[]	rowItems	= new ItemStack[9];
		int		            row;
		
		public CoreRow(int row, ItemStack[] items) {
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
		public ItemStack getRowItem(int item) {
			return rowItems[item] == null ? new ItemStack(Material.AIR) : rowItems[item];
		}
		
		@Override
		public int getRow() {
			return row;
		}
	}
}
