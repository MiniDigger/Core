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
package me.MiniDigger.CraftCore.Villager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;

import net.minecraft.server.v1_8_R3.EntityVillager;
import net.minecraft.server.v1_8_R3.MerchantRecipe;
import net.minecraft.server.v1_8_R3.MerchantRecipeList;
import net.minecraft.server.v1_8_R3.StatisticList;

import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import me.MiniDigger.Core.Villager.VillagerHandler;
import me.MiniDigger.Core.Villager.VillagerTrade;

public class CoreVillagerHandler implements VillagerHandler {
	
	private final HashMap<UUID, List<VillagerTrade>>	villager	= new HashMap<UUID, List<VillagerTrade>>();
	
	@Override
	public boolean clearTrades(final Villager villager) {
		this.villager.remove(villager.getUniqueId());
		return true;
	}
	
	@Override
	public boolean addTrade(final Villager villager, final VillagerTrade villagerTrade) {
		List<VillagerTrade> l = this.villager.remove(villager.getUniqueId());
		if (l == null) {
			l = new ArrayList<VillagerTrade>();
		}
		l.add(villagerTrade);
		this.villager.put(villager.getUniqueId(), l);
		return true;
	}
	
	@Override
	public List<VillagerTrade> getTrades(final Villager villager) {
		return this.villager.get(villager.getUniqueId());
	}
	
	@Override
	public boolean setTrades(final Villager villager, final List<VillagerTrade> trades) {
		this.villager.remove(villager.getUniqueId());
		this.villager.put(villager.getUniqueId(), trades);
		return true;
	}
	
	@Override
	public boolean open(final Villager v, final Player p) {
		return openTradeWindow(v.getCustomName(), getTrades(v), p);
	}
	
	// https://github.com/nisovin/Shopkeepers/blob/master/modules/v1_8_R3/src/main/java/com/nisovin/shopkeepers/compat/v1_8_R3/NMSHandler.java
	public boolean openTradeWindow(final String name, final List<VillagerTrade> recipes, final Player player) {
		try {
			final EntityVillager villager = new EntityVillager(((CraftPlayer) player).getHandle().world, 0);
			// custom name:
			if (name != null && !name.isEmpty()) {
				villager.setCustomName(name);
			}
			// career level (to prevent trade progression):
			final Field careerLevelField = EntityVillager.class.getDeclaredField("bw");
			careerLevelField.setAccessible(true);
			careerLevelField.set(villager, 10);
			
			// recipes:
			final Field recipeListField = EntityVillager.class.getDeclaredField("bp");
			recipeListField.setAccessible(true);
			MerchantRecipeList recipeList = (MerchantRecipeList) recipeListField.get(villager);
			if (recipeList == null) {
				recipeList = new MerchantRecipeList();
				recipeListField.set(villager, recipeList);
			}
			recipeList.clear();
			for (final VillagerTrade recipe : recipes) {
				recipeList.add(createMerchantRecipe(recipe.getItem1(), recipe.getItem2(), recipe.getRewardItem()));
			}
			
			// this will trigger the "create child" code of minecraft when the
			// player is holding a spawn egg in his hands,
			// but bypasses craftbukkits interact events and therefore removes
			// the spawn egg from the players hands
			// result: we have to prevent openTradeWindow if the shopkeeper
			// entity is being clicking with a spawn egg in hands
			// villager.a(((CraftPlayer) player).getHandle());
			villager.a_(((CraftPlayer) player).getHandle()); // set trading
			                                                 // player
			((CraftPlayer) player).getHandle().openTrade(villager); // open
			                                                        // trade
			                                                        // window
			((CraftPlayer) player).getHandle().b(StatisticList.F); // minecraft
			                                                       // statistics
			
			return true;
		} catch (final Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private MerchantRecipe createMerchantRecipe(final org.bukkit.inventory.ItemStack item1, final org.bukkit.inventory.ItemStack item2,
	        final org.bukkit.inventory.ItemStack item3) {
		final MerchantRecipe recipe = new MerchantRecipe(convertItemStack(item1), convertItemStack(item2), convertItemStack(item3));
		try {
			// max uses:
			final Field maxUsesField = MerchantRecipe.class.getDeclaredField("maxUses");
			maxUsesField.setAccessible(true);
			maxUsesField.set(recipe, 10000);
			
			// reward exp:
			final Field rewardExpField = MerchantRecipe.class.getDeclaredField("rewardExp");
			rewardExpField.setAccessible(true);
			rewardExpField.set(recipe, false);
		} catch (final Exception e) {}
		return recipe;
	}
	
	private net.minecraft.server.v1_8_R3.ItemStack convertItemStack(final org.bukkit.inventory.ItemStack item) {
		if (item == null) {
			return null;
		}
		return org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack.asNMSCopy(item);
	}
	
}
