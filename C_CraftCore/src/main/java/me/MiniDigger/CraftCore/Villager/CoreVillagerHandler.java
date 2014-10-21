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
package me.MiniDigger.CraftCore.Villager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import me.MiniDigger.Core.Villager.VillagerHandler;
import me.MiniDigger.Core.Villager.VillagerTrade;
import net.minecraft.server.v1_7_R4.EntityVillager;
import net.minecraft.server.v1_7_R4.ItemStack;
import net.minecraft.server.v1_7_R4.MerchantRecipe;
import net.minecraft.server.v1_7_R4.MerchantRecipeList;

import org.bukkit.craftbukkit.v1_7_R4.entity.CraftVillager;
import org.bukkit.craftbukkit.v1_7_R4.inventory.CraftItemStack;
import org.bukkit.entity.Villager;

public class CoreVillagerHandler implements VillagerHandler {
	
	@Override
	public boolean clearTrades(final Villager villager) {
		final EntityVillager entityVillager = ((CraftVillager) villager).getHandle();
		try {
			final Field recipes = entityVillager.getClass().getDeclaredField("bu");
			recipes.setAccessible(true);
			final MerchantRecipeList list = new MerchantRecipeList();
			recipes.set(entityVillager, list);
			return true;
		} catch (final Exception exc) {
			exc.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean addTrade(final Villager villager, final VillagerTrade villagerTrade) {
		final EntityVillager entityVillager = ((CraftVillager) villager).getHandle();
		try {
			final Field recipes = entityVillager.getClass().getDeclaredField("bu");
			recipes.setAccessible(true);
			final MerchantRecipeList list = (MerchantRecipeList) recipes.get(entityVillager);
			if (villagerTrade.hasItem2()) {
				final ItemStack item1 = CraftItemStack.asNMSCopy(villagerTrade.getItem1());
				final ItemStack item2 = CraftItemStack.asNMSCopy(villagerTrade.getItem2());
				final ItemStack rewardItem = CraftItemStack.asNMSCopy(villagerTrade.getRewardItem());
				list.a(new MerchantRecipe(item1, item2, rewardItem));
			} else {
				final ItemStack item1 = CraftItemStack.asNMSCopy(villagerTrade.getItem1());
				final ItemStack rewardItem = CraftItemStack.asNMSCopy(villagerTrade.getRewardItem());
				list.a(new MerchantRecipe(item1, rewardItem));
			}
			recipes.set(entityVillager, list);
			return true;
		} catch (final Exception exc) {
			exc.printStackTrace();
			return false;
		}
	}
	
	@Override
	public List<VillagerTrade> getTrades(final Villager villager) {
		final List<VillagerTrade> result = new ArrayList<VillagerTrade>();
		final EntityVillager entityVillager = ((CraftVillager) villager).getHandle();
		try {
			final Field recipes = entityVillager.getClass().getDeclaredField("bu");
			recipes.setAccessible(true);
			final MerchantRecipeList list = (MerchantRecipeList) recipes.get(entityVillager);
			
			for (int i = 0; i < list.size(); i++) {
				final MerchantRecipe r = (MerchantRecipe) list.get(i);
				result.add(new CoreVillagerTrade(r.getBuyItem1(), r.getBuyItem2(), r.getBuyItem3()));
			}
			
			return result;
		} catch (final Exception exc) {
			exc.printStackTrace();
			return result;
		}
	}
	
	@Override
	public boolean setTrades(final Villager villager, final List<VillagerTrade> trades) {
		final EntityVillager entityVillager = ((CraftVillager) villager).getHandle();
		try {
			final Field recipes = entityVillager.getClass().getDeclaredField("bu");
			recipes.setAccessible(true);
			final MerchantRecipeList list = new MerchantRecipeList();
			
			for (final VillagerTrade villagerTrade : trades) {
				if (villagerTrade.hasItem2()) {
					final ItemStack item1 = CraftItemStack.asNMSCopy(villagerTrade.getItem1());
					final ItemStack item2 = CraftItemStack.asNMSCopy(villagerTrade.getItem2());
					final ItemStack rewardItem = CraftItemStack.asNMSCopy(villagerTrade.getRewardItem());
					list.a(new MerchantRecipe(item1, item2, rewardItem));
				} else {
					final ItemStack item1 = CraftItemStack.asNMSCopy(villagerTrade.getItem1());
					final ItemStack rewardItem = CraftItemStack.asNMSCopy(villagerTrade.getRewardItem());
					list.a(new MerchantRecipe(item1, rewardItem));
				}
			}
			
			return true;
		} catch (final Exception exc) {
			exc.printStackTrace();
			return false;
		}
	}
}
