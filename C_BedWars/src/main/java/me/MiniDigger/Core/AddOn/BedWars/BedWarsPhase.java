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
package me.MiniDigger.Core.AddOn.BedWars;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.WeatherType;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Chat.ChatChars;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Game.Game;
import me.MiniDigger.Core.Lang.LangKeyType;
import me.MiniDigger.Core.Lang.MsgType;
import me.MiniDigger.Core.Map.MapData;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Team.Team;
import me.MiniDigger.Core.User.User;
import me.MiniDigger.Core.Util.EntityUtil.Type;
import me.MiniDigger.Core.Villager.VillagerTrade;

import me.MiniDigger.CraftCore.Feature.Features.AutoRespawnFeature;
import me.MiniDigger.CraftCore.Feature.Features.BleedFeature;
import me.MiniDigger.CraftCore.Feature.Features.BuildFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedTimeFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedWeatherFeature;
import me.MiniDigger.CraftCore.Feature.Features.MapFeature;
import me.MiniDigger.CraftCore.Feature.Features.MobFeature;
import me.MiniDigger.CraftCore.Feature.Features.PvPFeature;
import me.MiniDigger.CraftCore.Feature.Features.SpawnerFeature;
import me.MiniDigger.CraftCore.Feature.Features.SpawnersFeature;
import me.MiniDigger.CraftCore.Feature.Features.SpecateFeature;
import me.MiniDigger.CraftCore.Feature.Features.TeamArmorFeature;
import me.MiniDigger.CraftCore.Feature.Features.TeamDeathMatchFeature;
import me.MiniDigger.CraftCore.Feature.Features.TeamFeature;
import me.MiniDigger.CraftCore.Feature.Features.TeamSpawnFeature;
import me.MiniDigger.CraftCore.Feature.Features.VillagerFeature;
import me.MiniDigger.CraftCore.Item.CoreItemBuilder;
import me.MiniDigger.CraftCore.Phase.CorePhase;
import me.MiniDigger.CraftCore.Villager.CoreVillagerTrade;

public class BedWarsPhase extends CorePhase {

	public BedWarsPhase(final Game g, final Phase next) {
		super(g, next);
	}

	@Override
	public String getName() {
		return "BedWars";
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
		Core.getCore().getInstance().debug("init");
		addFeature(new BleedFeature(this));
		addFeature(new AutoRespawnFeature(this));
		final List<EntityType> allowed = Core.getCore().getEntityUtil().getAll(Type.OTHER, Type.CART, Type.PROJECTILE, Type.UTILITY);
		allowed.add(EntityType.VILLAGER);
		addFeature(new MobFeature(this, allowed));
		addFeature(new FixedTimeFeature(this, 6000));
		addFeature(new FixedWeatherFeature(this, WeatherType.CLEAR));
		addFeature(new MapFeature(this, getGame().getGameData("VoteWinner"), true));
		addFeature(new PvPFeature(this, true));
		addFeature(new TeamSpawnFeature(this, true, DyeColor.WHITE));
		addFeature(new TeamBedFeature(this, 4));
		addFeature(new TeamFeature(this, 4, 4));
		addFeature(new TeamDeathMatchFeature(this, 0, false));
		addFeature(new VillagerFeature(this));
		final List<Material> list = new ArrayList<>();
		for (final Material m : Material.values()) {
			list.add(m);
		}
		list.remove(Material.WOOL);
		addFeature(new BuildFeature(this, list.toArray(new Material[list.size()])));
		addFeature(new SpecateFeature(this));
		addFeature(new SpawnersFeature(this));
		addFeature(new TeamArmorFeature(this, false));

		final ItemStack silver = new CoreItemBuilder(Material.IRON_INGOT).name(ChatColor.AQUA + "Silber").amount(1).build();

		final ItemStack bronce = new CoreItemBuilder(Material.CLAY_BRICK).name(ChatColor.AQUA + "Bronze").amount(1).build();

		final ItemStack gold = new CoreItemBuilder(Material.GOLD_INGOT).name(ChatColor.AQUA + "Gold").amount(1).build();

		((SpawnersFeature) getFeature(FeatureType.SPAWNERS)).add(new SpawnerFeature(this, DyeColor.PINK, 20 * 2, null, bronce));
		((SpawnersFeature) getFeature(FeatureType.SPAWNERS)).add(new SpawnerFeature(this, DyeColor.LIGHT_BLUE, 20 * 6, null, silver));
		((SpawnersFeature) getFeature(FeatureType.SPAWNERS)).add(new SpawnerFeature(this, DyeColor.MAGENTA, 20 * 30, null, gold));

		Core.getCore().getTaskHandler().runTaskLater(new BukkitRunnable() {

			@Override
			public void run() {
				genBowVillager(DyeColor.ORANGE, bronce.clone(), silver.clone(), gold.clone(), 1);
			}
		}, 6 * 20, this);
		Core.getCore().getTaskHandler().runTaskLater(new BukkitRunnable() {

			@Override
			public void run() {
				genWeaponVillager(DyeColor.ORANGE, bronce.clone(), silver.clone(), gold.clone(), 2);
			}
		}, 7 * 20, this);
		Core.getCore().getTaskHandler().runTaskLater(new BukkitRunnable() {

			@Override
			public void run() {
				genArmorVillager(DyeColor.ORANGE, bronce.clone(), silver.clone(), gold.clone(), 3);
			}
		}, 8 * 20, this);
		Core.getCore().getTaskHandler().runTaskLater(new BukkitRunnable() {

			@Override
			public void run() {
				genFoodVillager(DyeColor.ORANGE, bronce.clone(), silver.clone(), gold.clone(), 4);
			}
		}, 9 * 20, this);
		Core.getCore().getTaskHandler().runTaskLater(new BukkitRunnable() {

			@Override
			public void run() {
				genBlockVillager(DyeColor.ORANGE, bronce.clone(), silver.clone(), gold.clone(), 5);
			}
		}, 10 * 20, this);
		Core.getCore().getTaskHandler().runTaskLater(new BukkitRunnable() {

			@Override
			public void run() {
				genPotionVillager(DyeColor.ORANGE, bronce.clone(), silver.clone(), gold.clone(), 6);
			}
		}, 11 * 20, this);

	}

	private void genPotionVillager(final DyeColor locKey, final ItemStack bronce, final ItemStack silver, final ItemStack gold, final int id) {
		final VillagerFeature f = (VillagerFeature) getFeature(FeatureType.VILLAGER);
		ItemStack is;
		final List<VillagerTrade> trades = new ArrayList<VillagerTrade>();

		bronce.setAmount(32);
		is = new CoreItemBuilder(Material.POTION).durability(0).name(ChatColor.LIGHT_PURPLE + "Speed").lore(ChatChars.Misc.bullet + "Causes Hunger").effect(PotionEffectType.HUNGER, 90 * 20, 2)
				.effect(PotionEffectType.SPEED, 90 * 20, 2).build();
		trades.add(new CoreVillagerTrade(bronce.clone(), is));

		silver.setAmount(1);
		is = new CoreItemBuilder(Material.POTION).durability(0).name(ChatColor.LIGHT_PURPLE + "Haste").lore(ChatChars.Misc.bullet + "Causes Hunger").effect(PotionEffectType.HUNGER, 90 * 20, 2)
				.effect(PotionEffectType.FAST_DIGGING, 90 * 20).build();
		trades.add(new CoreVillagerTrade(silver.clone(), is));

		silver.setAmount(1);
		is = new CoreItemBuilder(Material.POTION).durability(0).name(ChatColor.LIGHT_PURPLE + "Strength").lore(ChatChars.Misc.bullet + "Causes Hunger").effect(PotionEffectType.HUNGER, 90 * 20, 2)
				.effect(PotionEffectType.INCREASE_DAMAGE, 90 * 20).build();
		trades.add(new CoreVillagerTrade(silver.clone(), is));

		silver.setAmount(1);
		is = new CoreItemBuilder(Material.POTION).durability(0).name(ChatColor.LIGHT_PURPLE + "Resistance").lore(ChatChars.Misc.bullet + "Causes Hunger").effect(PotionEffectType.HUNGER, 90 * 20, 2)
				.effect(PotionEffectType.ABSORPTION, 90 * 20).build();
		trades.add(new CoreVillagerTrade(silver.clone(), is));

		is = new CoreItemBuilder(Material.POTION).durability(0).name(ChatColor.RED + "" + ChatColor.BOLD + "Beast Mode").effect(PotionEffectType.NIGHT_VISION, 2 * 60 * 20).effect(PotionEffectType.SPEED, 2 * 60 * 20, 2)
				.effect(PotionEffectType.FAST_DIGGING, 2 * 60 * 20).effect(PotionEffectType.INCREASE_DAMAGE, 2 * 60 * 20).effect(PotionEffectType.JUMP, 2 * 60 * 20, 2).effect(PotionEffectType.ABSORPTION, 2 * 60 * 20)
				.lore(ChatChars.Misc.bullet + "Become The Beast").build();
		trades.add(new CoreVillagerTrade(new ItemStack(Material.BED), is));

		final MapData data = ((MapFeature) getFeature(FeatureType.MAP)).getMap();
		final HashMap<String, Location> locs = data.getLocs(locKey);
		for (final Location loc : locs.values()) {
			final Block b = loc.getBlock().getRelative(BlockFace.DOWN, 2);
			if (b.getType() == Material.SIGN || b.getType() == Material.SIGN_POST || b.getType() == Material.WALL_SIGN) {
				final Sign s = (Sign) b.getState();
				if (s.getLine(0).equalsIgnoreCase("Villager")) {
					final int i = Integer.parseInt(s.getLine(1));
					if (i == id) {
						f.createVillager(loc, trades, Profession.BUTCHER, "Potion Trader", true);
					}
				}
			}
		}
	}

	private void genBlockVillager(final DyeColor locKey, final ItemStack bronce, final ItemStack silver, final ItemStack gold, final int id) {
		final VillagerFeature f = (VillagerFeature) getFeature(FeatureType.VILLAGER);
		ItemStack is;
		final List<VillagerTrade> trades = new ArrayList<VillagerTrade>();

		bronce.setAmount(8);
		is = new CoreItemBuilder(Material.NETHERRACK).amount(32).name(ChatColor.GOLD + "Block").lore(" " + ChatChars.Misc.bullet + " Level 1").build();
		trades.add(new CoreVillagerTrade(bronce.clone(), is));

		bronce.setAmount(16);
		is = new CoreItemBuilder(Material.BRICK).amount(16).name(ChatColor.GOLD + "Block").lore(" " + ChatChars.Misc.bullet + " Level 2").build();
		trades.add(new CoreVillagerTrade(bronce.clone(), is));

		bronce.setAmount(32);
		is = new CoreItemBuilder(Material.EMERALD_BLOCK).amount(8).name(ChatColor.GOLD + "Block").lore(" " + ChatChars.Misc.bullet + " Level 3").build();
		trades.add(new CoreVillagerTrade(bronce.clone(), is));

		bronce.setAmount(8);
		is = new CoreItemBuilder(Material.GLOWSTONE).amount(8).name(ChatColor.GOLD + "Block").lore(" " + ChatChars.Misc.bullet + " Level 4").lore(ChatChars.Misc.bullet + "Macht Licht!").build();
		trades.add(new CoreVillagerTrade(bronce.clone(), is));

		bronce.setAmount(6);
		is = new CoreItemBuilder(Material.WOOD_PICKAXE).name(ChatColor.DARK_PURPLE + "Pickaxe").enchantment(Enchantment.ARROW_INFINITE).lore(" " + ChatChars.Misc.bullet + " Level 1").build();
		trades.add(new CoreVillagerTrade(bronce.clone(), is));

		silver.setAmount(3);
		is = new CoreItemBuilder(Material.IRON_PICKAXE).name(ChatColor.DARK_PURPLE + "Pickaxe").enchantment(Enchantment.ARROW_INFINITE).enchantment(Enchantment.DIG_SPEED).lore(" " + ChatChars.Misc.bullet + " Level 2")
				.build();
		trades.add(new CoreVillagerTrade(silver.clone(), is));

		gold.setAmount(1);
		is = new CoreItemBuilder(Material.IRON_PICKAXE).name(ChatColor.DARK_PURPLE + "Pickaxe").enchantment(Enchantment.ARROW_INFINITE).enchantment(Enchantment.DIG_SPEED, 3).lore(" " + ChatChars.Misc.bullet + " Level 3")
				.build();
		trades.add(new CoreVillagerTrade(gold.clone(), is));

		silver.setAmount(1);
		trades.add(new CoreVillagerTrade(silver.clone(), new ItemStack(Material.CHEST)));

		final MapData data = ((MapFeature) getFeature(FeatureType.MAP)).getMap();
		final HashMap<String, Location> locs = data.getLocs(locKey);
		for (final Location loc : locs.values()) {
			final Block b = loc.getBlock().getRelative(BlockFace.DOWN, 2);
			if (b.getType() == Material.SIGN || b.getType() == Material.SIGN_POST || b.getType() == Material.WALL_SIGN) {
				final Sign s = (Sign) b.getState();
				if (s.getLine(0).equalsIgnoreCase("Villager")) {
					final int i = Integer.parseInt(s.getLine(1));
					if (i == id) {
						f.createVillager(loc, trades, Profession.BUTCHER, "Block Trader", true);
					}
				}
			}
		}
	}

	private void genWeaponVillager(final DyeColor locKey, final ItemStack bronce, final ItemStack silver, final ItemStack gold, final int id) {
		final VillagerFeature f = (VillagerFeature) getFeature(FeatureType.VILLAGER);
		ItemStack is;
		final List<VillagerTrade> trades = new ArrayList<VillagerTrade>();

		silver.setAmount(2);
		is = new CoreItemBuilder(Material.GOLD_SWORD).name(ChatColor.RED + "Sword").enchantment(Enchantment.ARROW_INFINITE).lore(" " + ChatChars.Misc.bullet + " Level 1").build();
		trades.add(new CoreVillagerTrade(silver.clone(), is));

		silver.setAmount(8);
		is = new CoreItemBuilder(Material.GOLD_SWORD).name(ChatColor.RED + "Sword").enchantment(Enchantment.ARROW_INFINITE).enchantment(Enchantment.DAMAGE_ALL).lore(" " + ChatChars.Misc.bullet + " Level 2").build();
		trades.add(new CoreVillagerTrade(silver.clone(), is));

		silver.setAmount(16);
		is = new CoreItemBuilder(Material.GOLD_SWORD).name(ChatColor.RED + "Sword").enchantment(Enchantment.ARROW_INFINITE).enchantment(Enchantment.DAMAGE_ALL, 2).lore(" " + ChatChars.Misc.bullet + " Level 3").build();
		trades.add(new CoreVillagerTrade(silver.clone(), is));

		silver.setAmount(48);
		is = new CoreItemBuilder(Material.GOLD_SWORD).name(ChatColor.RED + "Sword").enchantment(Enchantment.ARROW_INFINITE).enchantment(Enchantment.DAMAGE_ALL, 2).enchantment(Enchantment.KNOCKBACK)
				.lore(" " + ChatChars.Misc.bullet + " Level 4").build();
		trades.add(new CoreVillagerTrade(silver.clone(), is));

		silver.setAmount(6);
		trades.add(new CoreVillagerTrade(silver.clone(), new ItemStack(Material.TNT)));

		gold.setAmount(1);
		trades.add(new CoreVillagerTrade(gold.clone(), new ItemStack(Material.FLINT_AND_STEEL)));

		final MapData data = ((MapFeature) getFeature(FeatureType.MAP)).getMap();
		final HashMap<String, Location> locs = data.getLocs(locKey);
		for (final Location loc : locs.values()) {
			final Block b = loc.getBlock().getRelative(BlockFace.DOWN, 2);
			if (b.getType() == Material.SIGN || b.getType() == Material.SIGN_POST || b.getType() == Material.WALL_SIGN) {
				final Sign s = (Sign) b.getState();
				if (s.getLine(0).equalsIgnoreCase("Villager")) {
					final int i = Integer.parseInt(s.getLine(1));
					if (i == id) {
						f.createVillager(loc, trades, Profession.BUTCHER, "Weapon Trader", true);
					}
				}
			}
		}
	}

	private void genBowVillager(final DyeColor locKey, final ItemStack bronce, final ItemStack silver, final ItemStack gold, final int id) {
		final VillagerFeature f = (VillagerFeature) getFeature(FeatureType.VILLAGER);
		ItemStack is;
		final List<VillagerTrade> trades = new ArrayList<VillagerTrade>();

		gold.setAmount(2);
		is = new CoreItemBuilder(Material.BOW).name(ChatColor.GREEN + "Bow").enchantment(Enchantment.ARROW_INFINITE).lore(" " + ChatChars.Misc.bullet + " Level 1").build();
		trades.add(new CoreVillagerTrade(gold.clone(), is));

		gold.setAmount(8);
		is = new CoreItemBuilder(Material.BOW).name(ChatColor.GREEN + "Bow").enchantment(Enchantment.ARROW_INFINITE).enchantment(Enchantment.ARROW_DAMAGE).lore(" " + ChatChars.Misc.bullet + " Level 2").build();
		trades.add(new CoreVillagerTrade(gold.clone(), is));

		gold.setAmount(12);
		is = new CoreItemBuilder(Material.BOW).name(ChatColor.GREEN + "Bow").enchantment(Enchantment.ARROW_INFINITE).enchantment(Enchantment.ARROW_DAMAGE, 2).lore(" " + ChatChars.Misc.bullet + " Level 3").build();
		trades.add(new CoreVillagerTrade(gold.clone(), is));

		gold.setAmount(24);
		is = new CoreItemBuilder(Material.BOW).name(ChatColor.GREEN + "Bow").enchantment(Enchantment.ARROW_INFINITE).enchantment(Enchantment.ARROW_DAMAGE, 2).enchantment(Enchantment.ARROW_KNOCKBACK)
				.lore(" " + ChatChars.Misc.bullet + " Level 4").build();
		trades.add(new CoreVillagerTrade(gold.clone(), is));

		gold.setAmount(1);
		trades.add(new CoreVillagerTrade(gold.clone(), new ItemStack(Material.ARROW)));

		silver.setAmount(1);
		trades.add(new CoreVillagerTrade(silver.clone(), new ItemStack(Material.LADDER, 3)));

		silver.setAmount(1);
		trades.add(new CoreVillagerTrade(silver.clone(), new ItemStack(Material.WEB, 3)));

		final MapData data = ((MapFeature) getFeature(FeatureType.MAP)).getMap();
		final HashMap<String, Location> locs = data.getLocs(locKey);
		for (final Location loc : locs.values()) {
			final Block b = loc.getBlock().getRelative(BlockFace.DOWN, 2);
			if (b.getType() == Material.SIGN || b.getType() == Material.SIGN_POST || b.getType() == Material.WALL_SIGN) {
				final Sign s = (Sign) b.getState();
				if (s.getLine(0).equalsIgnoreCase("Villager")) {
					final int i = Integer.parseInt(s.getLine(1));
					if (i == id) {
						f.createVillager(loc, trades, Profession.BUTCHER, "Bow Trader", true);
					}
				}
			}
		}
	}

	private void genArmorVillager(final DyeColor locKey, final ItemStack bronce, final ItemStack silver, final ItemStack gold, final int id) {
		final VillagerFeature f = (VillagerFeature) getFeature(FeatureType.VILLAGER);
		ItemStack is;
		final List<VillagerTrade> trades = new ArrayList<VillagerTrade>();

		bronce.setAmount(1);
		is = new CoreItemBuilder(Material.LEATHER_HELMET).name(ChatColor.BLUE + "Armor").enchantment(Enchantment.PROTECTION_ENVIRONMENTAL).enchantment(Enchantment.ARROW_INFINITE).lore("TeamArmor").build();
		trades.add(new CoreVillagerTrade(bronce.clone(), is));

		bronce.setAmount(1);
		is = new CoreItemBuilder(Material.LEATHER_LEGGINGS).name(ChatColor.BLUE + "Armor").enchantment(Enchantment.PROTECTION_ENVIRONMENTAL).enchantment(Enchantment.ARROW_INFINITE).lore("TeamArmor").build();
		trades.add(new CoreVillagerTrade(bronce.clone(), is));

		bronce.setAmount(1);
		is = new CoreItemBuilder(Material.LEATHER_BOOTS).name(ChatColor.BLUE + "Armor").enchantment(Enchantment.PROTECTION_ENVIRONMENTAL).enchantment(Enchantment.ARROW_INFINITE).lore("TeamArmor").build();
		trades.add(new CoreVillagerTrade(bronce.clone(), is));

		silver.setAmount(2);
		is = new CoreItemBuilder(Material.CHAINMAIL_CHESTPLATE).name(ChatColor.YELLOW + "Chestplate").enchantment(Enchantment.PROTECTION_ENVIRONMENTAL).enchantment(Enchantment.ARROW_INFINITE)
				.lore(" " + ChatChars.Misc.bullet + " Level 1").build();
		trades.add(new CoreVillagerTrade(silver.clone(), is));

		silver.setAmount(8);
		is = new CoreItemBuilder(Material.CHAINMAIL_CHESTPLATE).name(ChatColor.YELLOW + "Chestplate").enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).enchantment(Enchantment.ARROW_INFINITE)
				.lore(" " + ChatChars.Misc.bullet + " Level 2").build();
		trades.add(new CoreVillagerTrade(silver.clone(), is));

		silver.setAmount(16);
		is = new CoreItemBuilder(Material.CHAINMAIL_CHESTPLATE).name(ChatColor.YELLOW + "Chestplate").enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3).enchantment(Enchantment.ARROW_INFINITE)
				.lore(" " + ChatChars.Misc.bullet + " Level 3").build();
		trades.add(new CoreVillagerTrade(silver.clone(), is));

		silver.setAmount(48);
		is = new CoreItemBuilder(Material.CHAINMAIL_CHESTPLATE).name(ChatColor.YELLOW + "Chestplate").enchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5).enchantment(Enchantment.ARROW_INFINITE)
				.lore(" " + ChatChars.Misc.bullet + " Level 4").build();
		trades.add(new CoreVillagerTrade(silver.clone(), is));

		final MapData data = ((MapFeature) getFeature(FeatureType.MAP)).getMap();
		final HashMap<String, Location> locs = data.getLocs(locKey);
		for (final Location loc : locs.values()) {
			final Block b = loc.getBlock().getRelative(BlockFace.DOWN, 2);
			if (b.getType() == Material.SIGN || b.getType() == Material.SIGN_POST || b.getType() == Material.WALL_SIGN) {
				final Sign s = (Sign) b.getState();
				if (s.getLine(0).equalsIgnoreCase("Villager")) {
					final int i = Integer.parseInt(s.getLine(1));
					if (i == id) {
						f.createVillager(loc, trades, Profession.BUTCHER, "Armor Trader", true);
					}
				}
			}
		}
	}

	private void genFoodVillager(final DyeColor locKey, final ItemStack bronce, final ItemStack silver, final ItemStack gold, final int id) {
		final VillagerFeature f = (VillagerFeature) getFeature(FeatureType.VILLAGER);
		ItemStack is;
		final List<VillagerTrade> trades = new ArrayList<VillagerTrade>();

		bronce.setAmount(16);
		is = new CoreItemBuilder(Material.APPLE).amount(4).name(ChatColor.YELLOW + "Food").lore(" " + ChatChars.Misc.bullet + " Level 1").build();
		trades.add(new CoreVillagerTrade(bronce.clone(), is));

		bronce.setAmount(32);
		is = new CoreItemBuilder(Material.APPLE).amount(2).name(ChatColor.YELLOW + "Food").lore(" " + ChatChars.Misc.bullet + " Level 2").build();
		trades.add(new CoreVillagerTrade(bronce.clone(), is));

		silver.setAmount(3);
		is = new CoreItemBuilder(Material.GOLDEN_APPLE).name(ChatColor.YELLOW + "Food").lore(" " + ChatChars.Misc.bullet + " Level 3").build();
		trades.add(new CoreVillagerTrade(silver.clone(), is));

		bronce.setAmount(64);
		silver.setAmount(1);
		trades.add(new CoreVillagerTrade(bronce.clone(), silver.clone()));

		silver.setAmount(16);
		gold.setAmount(1);
		trades.add(new CoreVillagerTrade(silver.clone(), gold.clone()));

		final MapData data = ((MapFeature) getFeature(FeatureType.MAP)).getMap();
		if (data == null) {
			if (((MapFeature) getFeature(FeatureType.MAP)).getMap() == null) {
				Core.getCore().getInstance().debug("data and map null!");
			}
		}
		final HashMap<String, Location> locs = data.getLocs(locKey);
		for (final Location loc : locs.values()) {
			final Block b = loc.getBlock().getRelative(BlockFace.DOWN, 2);
			if (b.getType() == Material.SIGN || b.getType() == Material.SIGN_POST || b.getType() == Material.WALL_SIGN) {
				final Sign s = (Sign) b.getState();
				if (s.getLine(0).equalsIgnoreCase("Villager")) {
					final int i = Integer.parseInt(s.getLine(1));
					if (i == id) {
						f.createVillager(loc, trades, Profession.BUTCHER, "Food Trader", true);
					}
				}
			}
		}
	}

	@Override
	public void startPhase() {
		getGame().broadCastMessage(LangKeyType.Game.BedWars.START1, MsgType.NORMAL);
		getGame().broadCastMessage(LangKeyType.Game.BedWars.START2, MsgType.NORMAL);
		final String winner = getGame().getGameData("VoteWinner");

		((MapFeature) getFeature(FeatureType.MAP)).setMap(winner);
		super.startPhase();
	}

	@Override
	public void endPhase() {
		super.endPhase();
		final TeamFeature tf = (TeamFeature) getFeature(FeatureType.TEAM);
		final List<User> winner = new ArrayList<User>();
		for (final Team t : tf.getTeams()) {
			for (final UUID id : t.getPlayers()) {
				winner.add(Core.getCore().getUserHandler().get(id));
			}
		}
		game.end(winner.toArray(new User[winner.size()]));
	}
}
