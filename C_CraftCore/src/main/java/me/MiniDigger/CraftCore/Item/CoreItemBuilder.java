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
package me.MiniDigger.CraftCore.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.MiniDigger.Core.Core;

/**
 * This is a chainable builder for {@link ItemStack}s in {@link Bukkit}
 * <br>
 * Example Usage:<br>
 * {@code ItemStack is = new ItemBuilder(Material.LEATHER_HELMET).amount(2).data(4).durability(4).enchantment(Enchantment.ARROW_INFINITE).enchantment(Enchantment.LUCK, 2).name(ChatColor.RED + "the name").lore(ChatColor.GREEN + "line 1").lore(ChatColor.BLUE + "line 2").color(Color.MAROON).build();
 * 
 * @author MiniDigger
 * @version 1.2
 */
public class CoreItemBuilder implements Listener {
	
	private static final Plugin	                       plugin	= Core.getCore().getInstance();
	private static boolean	                           listener	= false;
	private static final HashMap<String, PotionEffect>	effects	= new HashMap<String, PotionEffect>();
	
	private final ItemStack	                           is;
	
	/**
	 * Inits the builder with the given {@link Material}
	 * 
	 * @param mat
	 *            the {@link Material} to start the builder from
	 * @since 1.0
	 */
	public CoreItemBuilder(final Material mat) {
		is = new ItemStack(mat);
		is.setItemMeta(is.hasItemMeta() ? is.getItemMeta() : Bukkit.getItemFactory().getItemMeta(is.getType()));
	}
	
	/**
	 * Inits the builder with the given {@link ItemStack}
	 * 
	 * @param is
	 *            the {@link ItemStack} to start the builder from
	 * @since 1.0
	 */
	public CoreItemBuilder(final ItemStack is) {
		this.is = is;
	}
	
	/**
	 * Changes the amount of the {@link ItemStack}
	 * 
	 * @param amount
	 *            the new amount to set
	 * @return this builder for chaining
	 * @since 1.0
	 */
	public CoreItemBuilder amount(final int amount) {
		is.setAmount(amount);
		return this;
	}
	
	/**
	 * Changes the display name of the {@link ItemStack}
	 * 
	 * @param name
	 *            the new display name to set
	 * @return this builder for chaining
	 * @since 1.0
	 */
	public CoreItemBuilder name(final String name) {
		ItemMeta meta = is.hasItemMeta() && is.getItemMeta() != null ? is.getItemMeta() : Bukkit.getItemFactory().getItemMeta(is.getType());
		
		if (meta == null) {
//			System.out.println("could not apply name...");
			return this;
		}
		
		meta.setDisplayName(name);
		is.setItemMeta(meta);
		return this;
	}
	
	/**
	 * Adds a new line to the lore of the {@link ItemStack}
	 * 
	 * @param text
	 *            the new line to add
	 * @return this builder for chaining
	 * @since 1.0
	 */
	public CoreItemBuilder lore(final String name) {
		final ItemMeta meta = is.getItemMeta();
		List<String> lore = meta.getLore();
		if (lore == null) {
			lore = new ArrayList<String>();
		}
		lore.add(name);
		meta.setLore(lore);
		is.setItemMeta(meta);
		return this;
	}
	
	/**
	 * Changes the durability of the {@link ItemStack}
	 * 
	 * @param durability
	 *            the new durability to set
	 * @return this builder for chaining
	 * @since 1.0
	 */
	public CoreItemBuilder durability(final int durability) {
		is.setDurability((short) durability);
		return this;
	}
	
	/**
	 * Changes the data of the {@link ItemStack}
	 * 
	 * @param data
	 *            the new data to set
	 * @return this builder for chaining
	 * @since 1.0
	 */
	@SuppressWarnings("deprecation")
	public CoreItemBuilder data(final int data) {
		is.setData(new MaterialData(is.getType(), (byte) data));
		return this;
	}
	
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
	public CoreItemBuilder enchantment(final Enchantment enchantment, final int level) {
		is.addUnsafeEnchantment(enchantment, level);
		return this;
	}
	
	/**
	 * Adds an {@link Enchantment} with the level 1 to the {@link ItemStack}
	 * 
	 * @param enchantment
	 *            the enchantment to add
	 * @return this builder for chaining
	 * @since 1.0
	 */
	public CoreItemBuilder enchantment(final Enchantment enchantment) {
		is.addUnsafeEnchantment(enchantment, 1);
		return this;
	}
	
	/**
	 * Changes the {@link Material} of the {@link ItemStack}
	 * 
	 * @param data
	 *            the new material to set
	 * @return this builder for chaining
	 * @since 1.0
	 */
	public CoreItemBuilder type(final Material material) {
		is.setType(material);
		return this;
	}
	
	/**
	 * Clears the lore of the {@link ItemStack}
	 * 
	 * @return this builder for chaining
	 * @since 1.0
	 */
	public CoreItemBuilder clearLore() {
		final ItemMeta meta = is.getItemMeta();
		meta.setLore(new ArrayList<String>());
		is.setItemMeta(meta);
		return this;
	}
	
	/**
	 * Clears the list of {@link Enchantment}s of the {@link ItemStack}
	 * 
	 * @return this builder for chaining
	 * @since 1.0
	 */
	public CoreItemBuilder clearEnchantments() {
		for (final Enchantment e : is.getEnchantments().keySet()) {
			is.removeEnchantment(e);
		}
		return this;
	}
	
	/**
	 * Sets the {@link Color} of a part of leather armor
	 * 
	 * @param color
	 *            the {@link Color} to use
	 * @return this builder for chaining
	 * @since 1.1
	 */
	public CoreItemBuilder color(final Color color) {
		if (is.getType() == Material.LEATHER_BOOTS || is.getType() == Material.LEATHER_CHESTPLATE || is.getType() == Material.LEATHER_HELMET
		        || is.getType() == Material.LEATHER_LEGGINGS) {
			final LeatherArmorMeta meta = (LeatherArmorMeta) is.getItemMeta();
			meta.setColor(color);
			is.setItemMeta(meta);
			return this;
		} else {
			throw new IllegalArgumentException("color() only applicable for leather armor!");
		}
	}
	
	/**
	 * Adds a effects to the item. The effects gets applied to player when
	 * <s>wearing the item</s> (later) or consuming it
	 * 
	 * @param type
	 *            the {@link PotionEffectType} to apply
	 * @param duration
	 *            the duration in ticks (-1 for endless)
	 * @param amplifier
	 *            the amplifier of the effect
	 * @param the
	 *            ambient status
	 * @return this builder for chaining
	 * @since 1.2
	 */
	public CoreItemBuilder effect(final PotionEffectType type, final int duration, final int amplifier, final boolean ambient) {
		effect(new PotionEffect(type, duration, amplifier, ambient));
		return this;
	}
	
	/**
	 * Adds a effects to the item. The effects gets applied to player when
	 * <s>wearing the item</s> (later) or consuming it
	 * 
	 * @param effect
	 *            the effect to apply
	 * @return this builder for chaining
	 * @since 1.2
	 */
	public CoreItemBuilder effect(final PotionEffect effect) {
		if (!listener) {
			Bukkit.getPluginManager().registerEvents(this, plugin);
			listener = true;
		}
		String name = is.getItemMeta().getDisplayName();
		while (effects.containsKey(name)) {
			name = name + "#";
		}
		effects.put(name, effect);
		return this;
	}
	
	/**
	 * Adds a effects to the item. The effects gets applied to player when
	 * <s>wearing the item</s> (later) or consuming it
	 * 
	 * @param type
	 *            the {@link PotionEffectType} to apply
	 * @param duration
	 *            the duration in ticks (-1 for endless)
	 * @param amplifier
	 *            the amplifier of the effect
	 * @return this builder for chaining
	 * @since 1.2
	 */
	public CoreItemBuilder effect(final PotionEffectType type, final int duration, final int amplifier) {
		effect(new PotionEffect(type, duration == -1 ? 1000000 : duration, amplifier));
		return this;
	}
	
	/**
	 * Adds a effects to the item. The effects gets applied to player when
	 * <s>wearing the item</s> (later) or consuming it
	 * 
	 * @param type
	 *            the {@link PotionEffectType} to apply
	 * @param duration
	 *            the duration (-1 for endless)
	 * @return this builder for chaining
	 * @since 1.2
	 */
	public CoreItemBuilder effect(final PotionEffectType type, final int duration) {
		effect(new PotionEffect(type, duration == -1 ? 1000000 : duration, 1));
		return this;
	}
	
	/**
	 * Builds the {@link ItemStack}
	 * 
	 * @return the created {@link ItemStack}
	 * @since 1.0
	 */
	public ItemStack build() {
		return is;
	}
	
	@EventHandler
	public void onItemConsume(final PlayerItemConsumeEvent e) {
		if (e.getItem().hasItemMeta()) {
			@SuppressWarnings("unchecked") final HashMap<String, PotionEffect> copy = (HashMap<String, PotionEffect>) effects.clone();
			String name = e.getItem().getItemMeta().getDisplayName();
			while (copy.containsKey(name)) {
				e.getPlayer().addPotionEffect(copy.get(name), true);
				copy.remove(name);
				name += "#";
			}
		}
	}
	
	@EventHandler
	public void onItemApply(final InventoryClickEvent e) {
		// TODO add effects when item is applied
	}
	
}
