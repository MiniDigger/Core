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
import org.bukkit.inventory.meta.ItemMeta;

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
		
		final ItemStack silver = new ItemStack(Material.IRON_INGOT);
		ItemMeta meta = silver.getItemMeta();
		meta.setDisplayName("Silber");
		silver.setItemMeta(meta);
		
		final ItemStack bronce = new ItemStack(Material.BRICK);
		meta = bronce.getItemMeta();
		meta.setDisplayName("Bronze");
		bronce.setItemMeta(meta);
		
		final ItemStack gold = new ItemStack(Material.GOLD_INGOT);
		meta = gold.getItemMeta();
		meta.setDisplayName("Gold");
		gold.setItemMeta(meta);
		
		addFeature(new SpawnerFeature(this, DyeColor.GREEN, 20, null, bronce));
		addFeature(new SpawnerFeature(this, DyeColor.GREEN, 20 * 15, null, silver));
		addFeature(new SpawnerFeature(this, DyeColor.GREEN, 20 * 60, null, gold));
		
		//TODO Better machen: einzelne methoden, item meta usw. (API für is util evt.!!!)
		//TODO Infinity weg
		VillagerFeature f = (VillagerFeature) getFeature(FeatureType.VILLAGER);
		ItemStack is1;
		Location loc = null;
		List<VillagerTrade> trades = new ArrayList<VillagerTrade>();
		bronce.setAmount(16);
		trades.add(new CoreVillagerTrade(bronce, new ItemStack(Material.APPLE)));
		bronce.setAmount(32);
		is1 = new ItemStack(Material.COOKED_BEEF);
		is1.setAmount(2);
		trades.add(new CoreVillagerTrade(bronce, is1));
		silver.setAmount(3);
		trades.add(new CoreVillagerTrade(silver, new ItemStack(Material.GOLDEN_APPLE)));
		bronce.setAmount(64);
		silver.setAmount(1);
		trades.add(new CoreVillagerTrade(bronce, silver));
		silver.setAmount(16);
		gold.setAmount(1);
		trades.add(new CoreVillagerTrade(silver, gold));
		f.createVillager(loc, trades, Profession.BUTCHER);
		
		// TODO Team armor, vlt beim traden erst?
		loc = null;
		trades = new ArrayList<VillagerTrade>();
		bronce.setAmount(1);
		is1 = new ItemStack(Material.LEATHER_HELMET);
		is1.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		is1.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		meta = is1.getItemMeta();
		meta.setDisplayName("Armor");
		is1.setItemMeta(meta);
		trades.add(new CoreVillagerTrade(bronce, is1));
		bronce.setAmount(1);
		is1 = new ItemStack(Material.LEATHER_LEGGINGS);
		is1.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		is1.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		meta = is1.getItemMeta();
		meta.setDisplayName("Armor");
		is1.setItemMeta(meta);
		trades.add(new CoreVillagerTrade(bronce, is1));
		bronce.setAmount(1);
		is1 = new ItemStack(Material.LEATHER_BOOTS);
		is1.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		is1.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		meta = is1.getItemMeta();
		meta.setDisplayName("Armor");
		is1.setItemMeta(meta);
		trades.add(new CoreVillagerTrade(bronce, is1));
		silver.setAmount(2);
		is1 = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
		is1.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		is1.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		meta = is1.getItemMeta();
		meta.setDisplayName("Chestplate");
		is1.setItemMeta(meta);
		trades.add(new CoreVillagerTrade(silver, is1));
		silver.setAmount(8);
		is1 = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
		is1.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		is1.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
		meta = is1.getItemMeta();
		meta.setDisplayName("Chestplate");
		is1.setItemMeta(meta);
		trades.add(new CoreVillagerTrade(silver, is1));
		silver.setAmount(16);
		is1 = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
		is1.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		is1.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		meta = is1.getItemMeta();
		meta.setDisplayName("Chestplate");
		is1.setItemMeta(meta);
		trades.add(new CoreVillagerTrade(silver, is1));
		silver.setAmount(48);
		is1 = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
		is1.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		is1.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
		meta = is1.getItemMeta();
		meta.setDisplayName("Chestplate");
		is1.setItemMeta(meta);
		trades.add(new CoreVillagerTrade(silver, is1));
		f.createVillager(loc, trades, Profession.FARMER);
		
		loc = null;
		trades = new ArrayList<VillagerTrade>();
		gold.setAmount(2);
		is1 = new ItemStack(Material.BOW);
		is1.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		meta = is1.getItemMeta();
		meta.setDisplayName("Bow");
		is1.setItemMeta(meta);
		trades.add(new CoreVillagerTrade(gold, is1));
		gold.setAmount(8);
		is1 = new ItemStack(Material.BOW);
		is1.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		is1.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
		meta = is1.getItemMeta();
		meta.setDisplayName("Bow");
		is1.setItemMeta(meta);
		trades.add(new CoreVillagerTrade(gold, is1));
		gold.setAmount(12);
		is1 = new ItemStack(Material.BOW);
		is1.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		is1.addEnchantment(Enchantment.ARROW_DAMAGE, 2);
		meta = is1.getItemMeta();
		meta.setDisplayName("Bow");
		is1.setItemMeta(meta);
		trades.add(new CoreVillagerTrade(gold, is1));
		gold.setAmount(24);
		is1 = new ItemStack(Material.BOW);
		is1.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		is1.addEnchantment(Enchantment.ARROW_DAMAGE, 2);
		is1.addEnchantment(Enchantment.ARROW_KNOCKBACK, 1);
		meta = is1.getItemMeta();
		meta.setDisplayName("Bow");
		is1.setItemMeta(meta);
		trades.add(new CoreVillagerTrade(gold, is1));
		gold.setAmount(1);
		trades.add(new CoreVillagerTrade(gold, new ItemStack(Material.ARROW)));
		silver.setAmount(1);
		trades.add(new CoreVillagerTrade(silver, new ItemStack(Material.LADDER, 3)));
		silver.setAmount(1);
		trades.add(new CoreVillagerTrade(silver, new ItemStack(Material.WEB, 3)));
		f.createVillager(loc, trades, Profession.PRIEST);
		
		loc = null;
		trades = new ArrayList<VillagerTrade>();
		silver.setAmount(2);
		is1 = new ItemStack(Material.GOLD_SWORD);
		is1.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		trades.add(new CoreVillagerTrade(silver, is1));
		silver.setAmount(8);
		is1 = new ItemStack(Material.GOLD_SWORD);
		is1.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		is1.addEnchantment(Enchantment.DAMAGE_ALL, 1);
		trades.add(new CoreVillagerTrade(silver, is1));
		silver.setAmount(16);
		is1 = new ItemStack(Material.GOLD_SWORD);
		is1.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		is1.addEnchantment(Enchantment.DAMAGE_ALL, 2);
		trades.add(new CoreVillagerTrade(silver, is1));
		silver.setAmount(48);
		is1 = new ItemStack(Material.GOLD_SWORD);
		is1.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		is1.addEnchantment(Enchantment.DAMAGE_ALL, 2);
		is1.addEnchantment(Enchantment.KNOCKBACK, 1);
		trades.add(new CoreVillagerTrade(silver, is1));
		silver.setAmount(6);
		trades.add(new CoreVillagerTrade(silver, new ItemStack(Material.TNT)));
		gold.setAmount(1);
		trades.add(new CoreVillagerTrade(gold, new ItemStack(Material.FLINT_AND_STEEL)));
		f.createVillager(loc, trades, Profession.BLACKSMITH);
		
		loc = null;
		trades = new ArrayList<VillagerTrade>();
		bronce.setAmount(8);
		trades.add(new CoreVillagerTrade(bronce, new ItemStack(Material.NETHERRACK, 32)));
		bronce.setAmount(16);
		trades.add(new CoreVillagerTrade(bronce, new ItemStack(Material.BRICK, 16)));
		bronce.setAmount(32);
		trades.add(new CoreVillagerTrade(bronce, new ItemStack(Material.EMERALD_BLOCK, 8)));
		bronce.setAmount(8);
		trades.add(new CoreVillagerTrade(bronce, new ItemStack(Material.GLOWSTONE, 8)));
		bronce.setAmount(6);
		is1 = new ItemStack(Material.WOOD_PICKAXE);
		is1.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		trades.add(new CoreVillagerTrade(bronce, is1));
		silver.setAmount(3);
		is1 = new ItemStack(Material.IRON_PICKAXE);
		is1.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		is1.addEnchantment(Enchantment.DIG_SPEED, 1);
		trades.add(new CoreVillagerTrade(silver, is1));
		gold.setAmount(1);
		is1 = new ItemStack(Material.GOLD_PICKAXE);
		is1.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		is1.addEnchantment(Enchantment.DIG_SPEED, 3);
		trades.add(new CoreVillagerTrade(gold, is1));
		silver.setAmount(1);
		trades.add(new CoreVillagerTrade(silver, new ItemStack(Material.CHEST)));
		f.createVillager(loc, trades, Profession.BUTCHER);
		
		//TODO Add potion villager
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
