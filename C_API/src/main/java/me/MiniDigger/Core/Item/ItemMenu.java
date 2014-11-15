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
 * Written by Martin Benndorf <admin@minidigger.me>, 2013-2014 and others
 */
package me.MiniDigger.Core.Item;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.inventory.ItemStack;

public interface ItemMenu extends Listener {
	
	public interface onClick {
		
		/**
		 * Gets called when an option was clicked
		 * 
		 * @param clicker
		 *            the player who clicked
		 * @param menu
		 *            the menu
		 * @param row
		 *            the row in which a item was clicked
		 * @param slot
		 *            the slot in this row
		 * @param item
		 *            the item which was clicked
		 * @return
		 */
		public abstract boolean click(final Player clicker, final ItemMenu menu, final Row row, final int slot, final ItemStack item);
	}
	
	public interface Row {
		
		/**
		 * @return all items in this row
		 */
		ItemStack[] getRowItems();
		
		/**
		 * @param item
		 *            the column
		 * @return the item in that column
		 */
		ItemStack getRowItem(final int item);
		
		/**
		 * @return the row #
		 */
		int getRow();
		
	}
	
	/**
	 * Closes the menu for all players
	 */
	void onPluginDisable(final PluginDisableEvent event);
	
	/**
	 * Opens this menu for the player p
	 */
	ItemMenu open(final Player p);
	
	/**
	 * Opens this menu for the player p
	 */
	ItemMenu close(final Player p);
	
	/**
	 * @return all viewers
	 */
	List<Player> getViewers();
	
	/**
	 * handles clicks
	 */
	void onInventoryClick(final InventoryClickEvent event);
	
	/**
	 * prevents draging
	 */
	void onInventoryDrag(final InventoryDragEvent event);
	
	/**
	 * remove the player from viewing
	 */
	void onInventoryClose(final InventoryCloseEvent event);
	
	/**
	 * Add a new option
	 * 
	 * @param row
	 *            the row
	 * @param position
	 *            the position in this row
	 * @param item
	 *            the item to add
	 * @param name
	 *            the name of the item
	 * @param lore
	 *            the lore of the item
	 * @return this menu for chaining
	 */
	ItemMenu addButton(final Row row, final int position, final ItemStack item, final String name, final String... lore);
	
	/**
	 * Gets the row from a minecraft slot number
	 * 
	 * @param slot
	 *            the slot number
	 * @return the row
	 */
	Row getRowFromSlot(final int slot);
	
	/**
	 * Gets the row with that number
	 * 
	 * @param row
	 *            the number
	 * @return the row
	 */
	Row getRow(final int row);
}
