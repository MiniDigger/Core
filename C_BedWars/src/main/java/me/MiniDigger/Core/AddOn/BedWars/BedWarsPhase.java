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
package me.MiniDigger.Core.AddOn.BedWars;

import java.util.ArrayList;
import java.util.List;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Chat.ChatChars;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Game.Game;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.User.User;
import me.MiniDigger.Core.Util.EntityUtil.Type;
import me.MiniDigger.Core.Villager.VillagerTrade;
import me.MiniDigger.CraftCore.Feature.Features.AutoRespawnFeature;
import me.MiniDigger.CraftCore.Feature.Features.BleedFeature;
import me.MiniDigger.CraftCore.Feature.Features.DropFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedFoodFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedTimeFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedWeatherFeature;
import me.MiniDigger.CraftCore.Feature.Features.MapFeature;
import me.MiniDigger.CraftCore.Feature.Features.MobFeature;
import me.MiniDigger.CraftCore.Feature.Features.PvPFeature;
import me.MiniDigger.CraftCore.Feature.Features.SpawnFeature;
import me.MiniDigger.CraftCore.Feature.Features.SpawnerFeature;
import me.MiniDigger.CraftCore.Feature.Features.TeamDeathMatchFeature;
import me.MiniDigger.CraftCore.Feature.Features.TeamFeature;
import me.MiniDigger.CraftCore.Feature.Features.VillagerFeature;
import me.MiniDigger.CraftCore.Item.CoreItemBuilder;
import me.MiniDigger.CraftCore.Phase.CorePhase;
import me.MiniDigger.CraftCore.Villager.CoreVillagerTrade;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.WeatherType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class BedWarsPhase extends CorePhase {
	
	public BedWarsPhase(final Game g, final Phase next) {
		super(g, next);
	}
	
	@Override
	public String getName() {
		return "BedWars";
	}
	
	@Override
	public String getBarMessage() {
		return ChatColor.RED + "Bed" + ChatColor.BLUE + "Wars";
	}
	
	@Override
	public boolean displayBar() {
		return true;
	}
	
	@Override
	public boolean displayLevel() {
		return false;
	}
	
	@Override
	public void init() {
		addFeature(new BleedFeature(this));
		addFeature(new AutoRespawnFeature(this));
		addFeature(new FixedFoodFeature(this));
		addFeature(new MobFeature(this, Core.getCore().getEntityUtil().getAll(Type.OTHER, Type.CART, Type.PROJECTILE, Type.UTILITY)));
		addFeature(new FixedTimeFeature(this, 12000));
		addFeature(new FixedWeatherFeature(this, WeatherType.CLEAR));
		addFeature(new DropFeature(this));
		addFeature(new MapFeature(this, null));
		addFeature(new PvPFeature(this, true));
		addFeature(new SpawnFeature(this, false));
		addFeature(new TeamBedFeature(this, 4));
		addFeature(new TeamFeature(this, 4, 4));
		addFeature(new TeamDeathMatchFeature(this));
		addFeature(new VillagerFeature(this));
		
		final ItemStack silver = new CoreItemBuilder(Material.IRON_INGOT).name(ChatColor.AQUA + "Silber").build();
		
		final ItemStack bronce = new CoreItemBuilder(Material.BRICK).name(ChatColor.AQUA + "Bronze").build();
		
		final ItemStack gold = new CoreItemBuilder(Material.GOLD_INGOT).name(ChatColor.AQUA + "Gold").build();
		
		addFeature(new SpawnerFeature(this, DyeColor.GREEN, 20, null, bronce));
		addFeature(new SpawnerFeature(this, DyeColor.GREEN, 20 * 15, null, silver));
		addFeature(new SpawnerFeature(this, DyeColor.GREEN, 20 * 60, null, gold));
		
		genFoodVillager(null, bronce, silver, gold);
		genArmorVillager(null, bronce, silver, gold);
		genBowVillager(null, bronce, silver, gold);
		genWeaponVillager(null, bronce, silver, gold);
		genBlockVillager(null, bronce, silver, gold);
		genPotionVillager(null, bronce, silver, gold);
	}
	
	private void genPotionVillager(Location loc, ItemStack bronce, ItemStack silver, ItemStack gold) {
		VillagerFeature f = (VillagerFeature) getFeature(FeatureType.VILLAGER);
		ItemStack is;
		List<VillagerTrade> trades = new ArrayList<VillagerTrade>();
		
		bronce.setAmount(32);
		is = new CoreItemBuilder(Material.POTION).durability(0).name(ChatColor.LIGHT_PURPLE + "Speed").lore(ChatChars.Misc.bullet + "Causes Hunger")
		        .effect(PotionEffectType.HUNGER, 90 * 20, 2).effect(PotionEffectType.SPEED, 90 * 20, 2).build();
		trades.add(new CoreVillagerTrade(bronce, is));
		
		silver.setAmount(1);
		is = new CoreItemBuilder(Material.POTION).durability(0).name(ChatColor.LIGHT_PURPLE + "Haste").lore(ChatChars.Misc.bullet + "Causes Hunger")
		        .effect(PotionEffectType.HUNGER, 90 * 20, 2).effect(PotionEffectType.FAST_DIGGING, 90 * 20).build();
		trades.add(new CoreVillagerTrade(silver, is));
		
		silver.setAmount(1);
		is = new CoreItemBuilder(Material.POTION).durability(0).name(ChatColor.LIGHT_PURPLE + "Strength").lore(ChatChars.Misc.bullet + "Causes Hunger")
		        .effect(PotionEffectType.HUNGER, 90 * 20, 2).effect(PotionEffectType.INCREASE_DAMAGE, 90 * 20).build();
		trades.add(new CoreVillagerTrade(silver, is));
		
		silver.setAmount(1);
		is = new CoreItemBuilder(Material.POTION).durability(0).name(ChatColor.LIGHT_PURPLE + "Resistance").lore(ChatChars.Misc.bullet + "Causes Hunger")
		        .effect(PotionEffectType.HUNGER, 90 * 20, 2).effect(PotionEffectType.ABSORPTION, 90 * 20).build();
		trades.add(new CoreVillagerTrade(silver, is));
		
		is = new CoreItemBuilder(Material.POTION).durability(0).name(ChatColor.RED + "" + ChatColor.BOLD + "Beast Mode")
		        .effect(PotionEffectType.NIGHT_VISION, 2 * 60 * 20).effect(PotionEffectType.SPEED, 2 * 60 * 20, 2).effect(PotionEffectType.FAST_DIGGING, 2 * 60 * 20)
		        .effect(PotionEffectType.INCREASE_DAMAGE, 2 * 60 * 20).effect(PotionEffectType.JUMP, 2 * 60 * 20, 2).effect(PotionEffectType.ABSORPTION, 2 * 60 * 20)
		        .lore(ChatChars.Misc.bullet + "Become The Beast").build();
		trades.add(new CoreVillagerTrade(new ItemStack(Material.BED), is));
		
		f.createVillager(loc, trades, Profession.BUTCHER, "Potion Trader", true);
	}
	
	private void genBlockVillager(Location loc, ItemStack bronce, ItemStack silver, ItemStack gold) {
		VillagerFeature f = (VillagerFeature) getFeature(FeatureType.VILLAGER);
		ItemStack is;
		List<VillagerTrade> trades = new ArrayList<VillagerTrade>();
		
		bronce.setAmount(8);
		is = new CoreItemBuilder(Material.NETHERRACK).amount(32).name(ChatColor.GOLD + "Block").lore(" " + ChatChars.Misc.bullet + " Level 1").build();
		trades.add(new CoreVillagerTrade(bronce, is));
		
		bronce.setAmount(16);
		is = new CoreItemBuilder(Material.BRICK).amount(16).name(ChatColor.GOLD + "Block").lore(" " + ChatChars.Misc.bullet + " Level 2").build();
		trades.add(new CoreVillagerTrade(bronce, is));
		
		bronce.setAmount(32);
		is = new CoreItemBuilder(Material.EMERALD_BLOCK).amount(8).name(ChatColor.GOLD + "Block").lore(" " + ChatChars.Misc.bullet + " Level 3").build();
		trades.add(new CoreVillagerTrade(bronce, is));
		
		bronce.setAmount(8);
		is = new CoreItemBuilder(Material.GLOWSTONE).amount(8).name(ChatColor.GOLD + "Block").lore(" " + ChatChars.Misc.bullet + " Level 4")
		        .lore(ChatChars.Misc.bullet + "Macht Licht!").build();
		trades.add(new CoreVillagerTrade(bronce, is));
		
		bronce.setAmount(6);
		is = new CoreItemBuilder(Material.WOOD_PICKAXE).name(ChatColor.DARK_PURPLE + "Pickaxe").enchantment(Enchantment.ARROW_INFINITE)
		        .lore(" " + ChatChars.Misc.bullet + " Level 1").build();
		trades.add(new CoreVillagerTrade(bronce, is));
		
		silver.setAmount(3);
		is = new CoreItemBuilder(Material.IRON_PICKAXE).name(ChatColor.DARK_PURPLE + "Pickaxe").enchantment(Enchantment.ARROW_INFINITE)
		        .enchantment(Enchantment.DIG_SPEED).lore(" " + ChatChars.Misc.bullet + " Level 2").build();
		trades.add(new CoreVillagerTrade(silver, is));
		
		gold.setAmount(1);
		is = new CoreItemBuilder(Material.IRON_PICKAXE).name(ChatColor.DARK_PURPLE + "Pickaxe").enchantment(Enchantment.ARROW_INFINITE)
		        .enchantment(Enchantment.DIG_SPEED, 3).lore(" " + ChatChars.Misc.bullet + " Level 3").build();
		trades.add(new CoreVillagerTrade(gold, is));
		
		silver.setAmount(1);
		trades.add(new CoreVillagerTrade(silver, new ItemStack(Material.CHEST)));
		
		f.createVillager(loc, trades, Profession.BUTCHER, "Block Trader", true);
	}
	
	private void genWeaponVillager(Location loc, ItemStack bronce, ItemStack silver, ItemStack gold) {
		VillagerFeature f = (VillagerFeature) getFeature(FeatureType.VILLAGER);
		ItemStack is;
		List<VillagerTrade> trades = new ArrayList<VillagerTrade>();
		
		silver.setAmount(2);
		is = new CoreItemBuilder(Material.GOLD_SWORD).name(ChatColor.RED + "Sword").enchantment(Enchantment.ARROW_INFINITE)
		        .lore(" " + ChatChars.Misc.bullet + " Level 1").build();
		trades.add(new CoreVillagerTrade(silver, is));
		
		silver.setAmount(8);
		is = new CoreItemBuilder(Material.GOLD_SWORD).name(ChatColor.RED + "Sword").enchantment(Enchantment.ARROW_INFINITE).enchantment(Enchantment.DAMAGE_ALL)
		        .lore(" " + ChatChars.Misc.bullet + " Level 2").build();
		trades.add(new CoreVillagerTrade(silver, is));
		
		silver.setAmount(16);
		is = new CoreItemBuilder(Material.GOLD_SWORD).name(ChatColor.RED + "Sword").enchantment(Enchantment.ARROW_INFINITE).enchantment(Enchantment.DAMAGE_ALL, 2)
		        .lore(" " + ChatChars.Misc.bullet + " Level 3").build();
		trades.add(new CoreVillagerTrade(silver, is));
		
		silver.setAmount(48);
		is = new CoreItemBuilder(Material.GOLD_SWORD).name(ChatColor.RED + "Sword").enchantment(Enchantment.ARROW_INFINITE).enchantment(Enchantment.DAMAGE_ALL, 2)
		        .enchantment(Enchantment.KNOCKBACK).lore(" " + ChatChars.Misc.bullet + " Level 4").build();
		trades.add(new CoreVillagerTrade(silver, is));
		
		silver.setAmount(6);
		trades.add(new CoreVillagerTrade(silver, new ItemStack(Material.TNT)));
		
		gold.setAmount(1);
		trades.add(new CoreVillagerTrade(gold, new ItemStack(Material.FLINT_AND_STEEL)));
		
		f.createVillager(loc, trades, Profession.BLACKSMITH, "Weapon Trader", true);
	}
	
	private void genBowVillager(Location loc, ItemStack bronce, ItemStack silver, ItemStack gold) {
		VillagerFeature f = (VillagerFeature) getFeature(FeatureType.VILLAGER);
		ItemStack is;
		List<VillagerTrade> trades = new ArrayList<VillagerTrade>();
		
		gold.setAmount(2);
		is = new CoreItemBuilder(Material.BOW).name(ChatColor.GREEN + "Bow").enchantment(Enchantment.ARROW_INFINITE).lore(" " + ChatChars.Misc.bullet + " Level 1")
		        .build();
		trades.add(new CoreVillagerTrade(gold, is));
		
		gold.setAmount(8);
		is = new CoreItemBuilder(Material.BOW).name(ChatColor.GREEN + "Bow").enchantment(Enchantment.ARROW_INFINITE).enchantment(Enchantment.ARROW_DAMAGE)
		        .lore(" " + ChatChars.Misc.bullet + " Level 2").build();
		trades.add(new CoreVillagerTrade(gold, is));
		
		gold.setAmount(12);
		is = new CoreItemBuilder(Material.BOW).name(ChatColor.GREEN + "Bow").enchantment(Enchantment.ARROW_INFINITE).enchantment(Enchantment.ARROW_DAMAGE, 2)
		        .lore(" " + ChatChars.Misc.bullet + " Level 3").build();
		trades.add(new CoreVillagerTrade(gold, is));
		
		gold.setAmount(24);
		is = new CoreItemBuilder(Material.BOW).name(ChatColor.GREEN + "Bow").enchantment(Enchantment.ARROW_INFINITE).enchantment(Enchantment.ARROW_DAMAGE, 2)
		        .enchantment(Enchantment.ARROW_KNOCKBACK).lore(" " + ChatChars.Misc.bullet + " Level 4").build();
		trades.add(new CoreVillagerTrade(gold, is));
		
		gold.setAmount(1);
		trades.add(new CoreVillagerTrade(gold, new ItemStack(Material.ARROW)));
		
		silver.setAmount(1);
		trades.add(new CoreVillagerTrade(silver, new ItemStack(Material.LADDER, 3)));
		
		silver.setAmount(1);
		trades.add(new CoreVillagerTrade(silver, new ItemStack(Material.WEB, 3)));
		
		f.createVillager(loc, trades, Profession.PRIEST, "Bow Trader", true);
	}
	
	private void genArmorVillager(Location loc, ItemStack bronce, ItemStack silver, ItemStack gold) {
		// TODO Team armor, vlt beim traden erst, oder beim anziehen?
		VillagerFeature f = (VillagerFeature) getFeature(FeatureType.VILLAGER);
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
		        .enchantment(Enchantment.ARROW_INFINITE).lore(" " + ChatChars.Misc.bullet + " Level 1").build();
		trades.add(new CoreVillagerTrade(silver, is));
		
		silver.setAmount(8);
		is = new CoreItemBuilder(Material.CHAINMAIL_CHESTPLATE).name(ChatColor.YELLOW + "Chestplate").enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2)
		        .enchantment(Enchantment.ARROW_INFINITE).lore(" " + ChatChars.Misc.bullet + " Level 2").build();
		trades.add(new CoreVillagerTrade(silver, is));
		
		silver.setAmount(16);
		is = new CoreItemBuilder(Material.CHAINMAIL_CHESTPLATE).name(ChatColor.YELLOW + "Chestplate").enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
		        .enchantment(Enchantment.ARROW_INFINITE).lore(" " + ChatChars.Misc.bullet + " Level 3").build();
		trades.add(new CoreVillagerTrade(silver, is));
		
		silver.setAmount(48);
		is = new CoreItemBuilder(Material.CHAINMAIL_CHESTPLATE).name(ChatColor.YELLOW + "Chestplate").enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5)
		        .enchantment(Enchantment.ARROW_INFINITE).lore(" " + ChatChars.Misc.bullet + " Level 4").build();
		trades.add(new CoreVillagerTrade(silver, is));
		f.createVillager(loc, trades, Profession.FARMER, "Armor Trader", true);
	}
	
	private void genFoodVillager(Location loc, ItemStack bronce, ItemStack silver, ItemStack gold) {
		VillagerFeature f = (VillagerFeature) getFeature(FeatureType.VILLAGER);
		ItemStack is;
		List<VillagerTrade> trades = new ArrayList<VillagerTrade>();
		
		bronce.setAmount(16);
		is = new CoreItemBuilder(Material.APPLE).amount(4).name(ChatColor.YELLOW + "Food").lore(" " + ChatChars.Misc.bullet + " Level 1").build();
		trades.add(new CoreVillagerTrade(bronce, is));
		
		bronce.setAmount(32);
		is = new CoreItemBuilder(Material.APPLE).amount(2).name(ChatColor.YELLOW + "Food").lore(" " + ChatChars.Misc.bullet + " Level 2").build();
		trades.add(new CoreVillagerTrade(bronce, is));
		
		silver.setAmount(3);
		is = new CoreItemBuilder(Material.GOLDEN_APPLE).name(ChatColor.YELLOW + "Food").lore(" " + ChatChars.Misc.bullet + " Level 3").build();
		trades.add(new CoreVillagerTrade(silver, is));
		
		bronce.setAmount(64);
		silver.setAmount(1);
		trades.add(new CoreVillagerTrade(bronce, silver));
		
		silver.setAmount(16);
		gold.setAmount(1);
		trades.add(new CoreVillagerTrade(silver, gold));
		
		f.createVillager(loc, trades, Profession.BUTCHER, "Food Trader", true);
	}
	
	@Override
	public void startPhase() {
		getGame().broadCastMessage(game.getPrefix().then("Das Spiel hat begonnen").color(ChatColor.AQUA));
		getGame().broadCastMessage(game.getPrefix().then("Sammel Ressource, tausche diese und zerstöre die Betten deiner Gegener!").color(ChatColor.AQUA));
		final String winner = getGame().getGameData("VoteWinner");
		
		((MapFeature) getFeature(FeatureType.MAP)).setMap(winner);
		super.startPhase();
	}
	
	@Override
	public void endPhase() {
		super.endPhase();
		final TeamFeature tf = (TeamFeature) getFeature(FeatureType.TEAM);
		final User[] winner = new User[tf.getTeams().size()];
		game.end(winner);
	}
}