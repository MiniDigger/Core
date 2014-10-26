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
package me.MiniDigger.CraftCore.Command.Commands;

import java.util.ArrayList;
import java.util.List;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Chat.ChatChars;
import me.MiniDigger.Core.Villager.VillagerTrade;
import me.MiniDigger.CraftCore.Item.CoreItemBuilder;
import me.MiniDigger.CraftCore.Villager.CoreVillagerTrade;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.inventory.ItemStack;


/**
 * @author Martin
 *
 */
public class v {
	public void createVillager(Location loc, List<VillagerTrade> trades, Profession prof, String name, boolean invincible) {
		Villager v = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
		if (name != null) {
			v.setCustomName(name);
			v.setCustomNameVisible(true);
		}
		if (invincible) {
			v.setNoDamageTicks(Integer.MAX_VALUE);
		}
		v.setCanPickupItems(false);
		v.setRemoveWhenFarAway(false);
		v.setTarget(v);
		v.setProfession(prof);
		Core.getCore().getVillagerHandler().setTrades(v, trades);
	}
	
	public void genPotionVillager(Location loc, ItemStack bronce, ItemStack silver, ItemStack gold) {
		ItemStack is;
		List<VillagerTrade> trades = new ArrayList<VillagerTrade>();
		
		bronce.setAmount(32);
		is = new CoreItemBuilder(Material.POTION).durability(0).name(ChatColor.LIGHT_PURPLE + "Speed").lore(ChatChars.Misc.bullet + "Causes Hunger").build();
		trades.add(new CoreVillagerTrade(bronce, is));
		
		silver.setAmount(1);
		is = new CoreItemBuilder(Material.POTION).durability(0).name(ChatColor.LIGHT_PURPLE + "Haste").lore(ChatChars.Misc.bullet + "Causes Hunger").build();
		trades.add(new CoreVillagerTrade(silver, is));
		
		silver.setAmount(1);
		is = new CoreItemBuilder(Material.POTION).durability(0).name(ChatColor.LIGHT_PURPLE + "Strength").lore(ChatChars.Misc.bullet + "Causes Hunger").build();
		trades.add(new CoreVillagerTrade(silver, is));
		
		silver.setAmount(1);
		is = new CoreItemBuilder(Material.POTION).durability(0).name(ChatColor.LIGHT_PURPLE + "Resistance").lore(ChatChars.Misc.bullet + "Causes Hunger").build();
		trades.add(new CoreVillagerTrade(silver, is));
		
		is = new CoreItemBuilder(Material.POTION).durability(0).name(ChatColor.RED + "" + ChatColor.BOLD + "Beast Mode").lore(ChatChars.Misc.bullet + "Become The Beast")
		        .build();
		trades.add(new CoreVillagerTrade(new ItemStack(Material.BED), is));
		
		createVillager(loc, trades, Profession.BUTCHER, "Potion Trader", true);
	}
	
	public void genBlockVillager(Location loc, ItemStack bronce, ItemStack silver, ItemStack gold) {
		ItemStack is;
		List<VillagerTrade> trades = new ArrayList<VillagerTrade>();
		
		bronce.setAmount(8);
		is = new CoreItemBuilder(Material.NETHERRACK).amount(32).name(ChatColor.GOLD + "Block").lore(ChatChars.Misc.bullet + "Level 1").build();
		trades.add(new CoreVillagerTrade(bronce, is));
		
		bronce.setAmount(16);
		is = new CoreItemBuilder(Material.BRICK).amount(16).name(ChatColor.GOLD + "Block").lore(ChatChars.Misc.bullet + "Level 2").build();
		trades.add(new CoreVillagerTrade(bronce, is));
		
		bronce.setAmount(32);
		is = new CoreItemBuilder(Material.EMERALD_BLOCK).amount(8).name(ChatColor.GOLD + "Block").lore(ChatChars.Misc.bullet + "Level 3").build();
		trades.add(new CoreVillagerTrade(bronce, is));
		
		bronce.setAmount(8);
		is = new CoreItemBuilder(Material.GLOWSTONE).amount(8).name(ChatColor.GOLD + "Block").lore(ChatChars.Misc.bullet + "Level 4")
		        .lore(ChatChars.Misc.bullet + "Macht Licht!").build();
		trades.add(new CoreVillagerTrade(bronce, is));
		
		bronce.setAmount(6);
		is = new CoreItemBuilder(Material.WOOD_PICKAXE).name(ChatColor.DARK_PURPLE + "Pickaxe").enchantment(Enchantment.ARROW_INFINITE)
		        .lore(ChatChars.Misc.bullet + "Level 1").build();
		trades.add(new CoreVillagerTrade(bronce, is));
		
		silver.setAmount(3);
		is = new CoreItemBuilder(Material.IRON_PICKAXE).name(ChatColor.DARK_PURPLE + "Pickaxe").enchantment(Enchantment.ARROW_INFINITE)
		        .enchantment(Enchantment.DIG_SPEED).lore(ChatChars.Misc.bullet + "Level 2").build();
		trades.add(new CoreVillagerTrade(silver, is));
		
		gold.setAmount(1);
		is = new CoreItemBuilder(Material.IRON_PICKAXE).name(ChatColor.DARK_PURPLE + "Pickaxe").enchantment(Enchantment.ARROW_INFINITE)
		        .enchantment(Enchantment.DIG_SPEED, 3).lore(ChatChars.Misc.bullet + "Level 3").build();
		trades.add(new CoreVillagerTrade(gold, is));
		
		silver.setAmount(1);
		trades.add(new CoreVillagerTrade(silver, new ItemStack(Material.CHEST)));
		
		createVillager(loc, trades, Profession.BUTCHER, "Block Trader", true);
	}
	
	public void genWeaponVillager(Location loc, ItemStack bronce, ItemStack silver, ItemStack gold) {
		ItemStack is;
		List<VillagerTrade> trades = new ArrayList<VillagerTrade>();
		
		silver.setAmount(2);
		is = new CoreItemBuilder(Material.GOLD_SWORD).name(ChatColor.RED + "Sword").enchantment(Enchantment.ARROW_INFINITE).lore(ChatChars.Misc.bullet + "Level 1")
		        .build();
		trades.add(new CoreVillagerTrade(silver, is));
		
		silver.setAmount(8);
		is = new CoreItemBuilder(Material.GOLD_SWORD).name(ChatColor.RED + "Sword").enchantment(Enchantment.ARROW_INFINITE).enchantment(Enchantment.DAMAGE_ALL)
		        .lore(ChatChars.Misc.bullet + "Level 2").build();
		trades.add(new CoreVillagerTrade(silver, is));
		
		silver.setAmount(16);
		is = new CoreItemBuilder(Material.GOLD_SWORD).name(ChatColor.RED + "Sword").enchantment(Enchantment.ARROW_INFINITE).enchantment(Enchantment.DAMAGE_ALL, 2)
		        .lore(ChatChars.Misc.bullet + "Level 3").build();
		trades.add(new CoreVillagerTrade(silver, is));
		
		silver.setAmount(48);
		is = new CoreItemBuilder(Material.GOLD_SWORD).name(ChatColor.RED + "Sword").enchantment(Enchantment.ARROW_INFINITE).enchantment(Enchantment.DAMAGE_ALL, 2)
		        .enchantment(Enchantment.KNOCKBACK).lore(ChatChars.Misc.bullet + "Level 4").build();
		trades.add(new CoreVillagerTrade(silver, is));
		
		silver.setAmount(6);
		trades.add(new CoreVillagerTrade(silver, new ItemStack(Material.TNT)));
		
		gold.setAmount(1);
		trades.add(new CoreVillagerTrade(gold, new ItemStack(Material.FLINT_AND_STEEL)));
		
		createVillager(loc, trades, Profession.BLACKSMITH, "Weapon Trader", true);
	}
	
	public void genBowVillager(Location loc, ItemStack bronce, ItemStack silver, ItemStack gold) {
		ItemStack is;
		List<VillagerTrade> trades = new ArrayList<VillagerTrade>();
		
		gold.setAmount(2);
		is = new CoreItemBuilder(Material.BOW).name(ChatColor.GREEN + "Bow").enchantment(Enchantment.ARROW_INFINITE).lore(ChatChars.Misc.bullet + "Level 1").build();
		trades.add(new CoreVillagerTrade(gold, is));
		
		gold.setAmount(8);
		is = new CoreItemBuilder(Material.BOW).name(ChatColor.GREEN + "Bow").enchantment(Enchantment.ARROW_INFINITE).enchantment(Enchantment.ARROW_DAMAGE)
		        .lore(ChatChars.Misc.bullet + "Level 2").build();
		trades.add(new CoreVillagerTrade(gold, is));
		
		gold.setAmount(12);
		is = new CoreItemBuilder(Material.BOW).name(ChatColor.GREEN + "Bow").enchantment(Enchantment.ARROW_INFINITE).enchantment(Enchantment.ARROW_DAMAGE, 2)
		        .lore(ChatChars.Misc.bullet + "Level 3").build();
		trades.add(new CoreVillagerTrade(gold, is));
		
		gold.setAmount(24);
		is = new CoreItemBuilder(Material.BOW).name(ChatColor.GREEN + "Bow").enchantment(Enchantment.ARROW_INFINITE).enchantment(Enchantment.ARROW_DAMAGE, 2)
		        .enchantment(Enchantment.ARROW_KNOCKBACK).lore(ChatChars.Misc.bullet + "Level 4").build();
		trades.add(new CoreVillagerTrade(gold, is));
		
		gold.setAmount(1);
		trades.add(new CoreVillagerTrade(gold, new ItemStack(Material.ARROW)));
		
		silver.setAmount(1);
		trades.add(new CoreVillagerTrade(silver, new ItemStack(Material.LADDER, 3)));
		
		silver.setAmount(1);
		trades.add(new CoreVillagerTrade(silver, new ItemStack(Material.WEB, 3)));
		
		createVillager(loc, trades, Profession.PRIEST, "Bow Trader", true);
	}
	
	public void genArmorVillager(Location loc, ItemStack bronce, ItemStack silver, ItemStack gold) {
		ItemStack is;
		List<VillagerTrade> trades = new ArrayList<VillagerTrade>();
		
		bronce.setAmount(1);
		is = new CoreItemBuilder(Material.LEATHER_HELMET).name(ChatColor.BLUE + "Armor").enchantment(Enchantment.PROTECTION_ENVIRONMENTAL)
		        .enchantment(Enchantment.ARROW_INFINITE).lore("TeamArmor").build();
		trades.add(new CoreVillagerTrade(bronce, is));
		
		bronce.setAmount(1);
		is = new CoreItemBuilder(Material.LEATHER_LEGGINGS).name(ChatColor.BLUE + "Armor").enchantment(Enchantment.PROTECTION_ENVIRONMENTAL)
		        .enchantment(Enchantment.ARROW_INFINITE).lore("TeamArmor").build();
		trades.add(new CoreVillagerTrade(bronce, is));
		
		bronce.setAmount(1);
		is = new CoreItemBuilder(Material.LEATHER_BOOTS).name(ChatColor.BLUE + "Armor").enchantment(Enchantment.PROTECTION_ENVIRONMENTAL)
		        .enchantment(Enchantment.ARROW_INFINITE).lore("TeamArmor").build();
		trades.add(new CoreVillagerTrade(bronce, is));
		
		silver.setAmount(2);
		is = new CoreItemBuilder(Material.CHAINMAIL_CHESTPLATE).name(ChatColor.YELLOW + "Chestplate").enchantment(Enchantment.PROTECTION_ENVIRONMENTAL)
		        .enchantment(Enchantment.ARROW_INFINITE).lore(ChatChars.Misc.bullet + "Level 1").build();
		trades.add(new CoreVillagerTrade(silver, is));
		
		silver.setAmount(8);
		is = new CoreItemBuilder(Material.CHAINMAIL_CHESTPLATE).name(ChatColor.YELLOW + "Chestplate").enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2)
		        .enchantment(Enchantment.ARROW_INFINITE).lore(ChatChars.Misc.bullet + "Level 2").build();
		trades.add(new CoreVillagerTrade(silver, is));
		
		silver.setAmount(16);
		is = new CoreItemBuilder(Material.CHAINMAIL_CHESTPLATE).name(ChatColor.YELLOW + "Chestplate").enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
		        .enchantment(Enchantment.ARROW_INFINITE).lore(ChatChars.Misc.bullet + "Level 3").build();
		trades.add(new CoreVillagerTrade(silver, is));
		
		silver.setAmount(48);
		is = new CoreItemBuilder(Material.CHAINMAIL_CHESTPLATE).name(ChatColor.YELLOW + "Chestplate").enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5)
		        .enchantment(Enchantment.ARROW_INFINITE).lore(ChatChars.Misc.bullet + "Level 4").build();
		trades.add(new CoreVillagerTrade(silver, is));
		createVillager(loc, trades, Profession.FARMER, "Armor Trader", true);
	}
	
	public void genFoodVillager(Location loc, ItemStack bronce, ItemStack silver, ItemStack gold) {
		ItemStack is;
		List<VillagerTrade> trades = new ArrayList<VillagerTrade>();
		
		bronce.setAmount(16);
		is = new CoreItemBuilder(Material.APPLE).amount(4).name(ChatColor.YELLOW + "Food").lore(ChatChars.Misc.bullet + "Level 1").build();
		trades.add(new CoreVillagerTrade(bronce, is));
		
		bronce.setAmount(32);
		is = new CoreItemBuilder(Material.APPLE).amount(2).name(ChatColor.YELLOW + "Food").lore(ChatChars.Misc.bullet + "Level 2").build();
		trades.add(new CoreVillagerTrade(bronce, is));
		
		silver.setAmount(3);
		is = new CoreItemBuilder(Material.GOLDEN_APPLE).name(ChatColor.YELLOW + "Food").lore(ChatChars.Misc.bullet + "Level 3").build();
		trades.add(new CoreVillagerTrade(silver, is));
		
		bronce.setAmount(64);
		silver.setAmount(1);
		trades.add(new CoreVillagerTrade(bronce, silver));
		
		silver.setAmount(16);
		gold.setAmount(1);
		trades.add(new CoreVillagerTrade(silver, gold));
		
		createVillager(loc, trades, Profession.BUTCHER, "Food Trader", true);
	}
	
}
