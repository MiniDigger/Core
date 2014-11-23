package me.MiniDigger.Core.Kit;

import java.util.List;

import org.bukkit.entity.Player;

import me.MiniDigger.Core.User.User;

public interface KitHandler {
	
	/**
	 * Creates an empty kit with that name
	 * 
	 * @param name
	 * @return
	 */
	public Kit createKit(String name);
	
	/**
	 * Adds an precreated kit to the list
	 * 
	 * @param kit
	 * @return
	 */
	public Kit createKit(Kit kit);
	
	/**
	 * @param name
	 * @return the kit with that name, can be null
	 */
	public Kit getKit(String name);
	
	/**
	 * Removes the kit with that name if exists
	 * 
	 * @param name
	 */
	public void removeKit(String name);
	
	void give(User user, String kit);
	
	void give(User user, Kit kit);
	
	void give(Player p, String kit);
	
	void give(Player p, Kit kit);
	
	/**
	 * @return a list with all kits
	 */
	public List<Kit> getKits();
	
}
