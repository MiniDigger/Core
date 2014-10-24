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
 */
package me.MiniDigger.Core.Item;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public interface ItemBuilder {
	
	/**
	 * Changes the amount of the {@link ItemStack}
	 * 
	 * @param amount
	 *            the new amount to set
	 * @return this builder for chaining
	 * @since 1.0
	 */
	ItemBuilder amount(int amount);
	
	/**
	 * Changes the display name of the {@link ItemStack}
	 * 
	 * @param name
	 *            the new display name to set
	 * @return this builder for chaining
	 * @since 1.0
	 */
	ItemBuilder name(String name);
	
	/**
	 * Adds a new line to the lore of the {@link ItemStack}
	 * 
	 * @param text
	 *            the new line to add
	 * @return this builder for chaining
	 * @since 1.0
	 */
	ItemBuilder lore(String text);
	
	/**
	 * Changes the durability of the {@link ItemStack}
	 * 
	 * @param durability
	 *            the new durability to set
	 * @return this builder for chaining
	 * @since 1.0
	 */
	ItemBuilder durability(int durability);
	
	/**
	 * Changes the data of the {@link ItemStack}
	 * 
	 * @param data
	 *            the new data to set
	 * @return this builder for chaining
	 * @since 1.0
	 */
	ItemBuilder data(int data);
	
	/**
	 * Adds an {@link Enchantment} with the given level to the {@link ItemStack}
	 * 
	 * @param enchantment
	 *            the enchantment to add
	 * @param level
	 *            the level of the enchantment
	 * @return this builder for chaining
	 * @since 1.0
	 */
	ItemBuilder enchantment(Enchantment enchantment, int level);
	
	/**
	 * Adds an {@link Enchantment} with the level 1 to the {@link ItemStack}
	 * 
	 * @param enchantment
	 *            the enchantment to add
	 * @return this builder for chaining
	 * @since 1.0
	 */
	ItemBuilder enchantment(Enchantment enchantment);
	
	/**
	 * Changes the {@link Material} of the {@link ItemStack}
	 * 
	 * @param data
	 *            the new material to set
	 * @return this builder for chaining
	 * @since 1.0
	 */
	ItemBuilder type(Material material);
	
	/**
	 * Builds the {@link ItemStack}
	 * 
	 * @return the created {@link ItemStack}
	 * @since 1.0
	 */
	ItemStack build();
	
	/**
	 * Clears the lore of the {@link ItemStack}
	 * 
	 * @return this builder for chaining
	 * @since 1.0
	 */
	ItemBuilder clearLore();
	
	/**
	 * Clears the list of {@link Enchantment}s of the {@link ItemStack}
	 * 
	 * @return this builder for chaining
	 * @since 1.0
	 */
	ItemBuilder clearEnchantments();
	
}
