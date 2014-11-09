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
		public abstract boolean click(Player clicker, ItemMenu menu, Row row, int slot, ItemStack item);
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
		ItemStack getRowItem(int item);
		
		/**
		 * @return the row #
		 */
		int getRow();
		
	}
	
	/**
	 * Closes the menu for all players
	 */
	void onPluginDisable(PluginDisableEvent event);
	
	/**
	 * Opens this menu for the player p
	 */
	ItemMenu open(Player p);
	
	/**
	 * Opens this menu for the player p
	 */
	ItemMenu close(Player p);
	
	/**
	 * @return all viewers
	 */
	List<Player> getViewers();
	
	/**
	 * handles clicks
	 */
	void onInventoryClick(InventoryClickEvent event);
	
	/**
	 * prevents draging
	 */
	void onInventoryDrag(InventoryDragEvent event);
	
	/**
	 * remove the player from viewing
	 */
	void onInventoryClose(InventoryCloseEvent event);
	
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
	ItemMenu addButton(Row row, int position, ItemStack item, String name, String... lore);
	
	/**
	 * Gets the row from a minecraft slot number
	 * 
	 * @param slot
	 *            the slot number
	 * @return the row
	 */
	Row getRowFromSlot(int slot);
	
	/**
	 * Gets the row with that number
	 * 
	 * @param row
	 *            the number
	 * @return the row
	 */
	Row getRow(int row);
}
