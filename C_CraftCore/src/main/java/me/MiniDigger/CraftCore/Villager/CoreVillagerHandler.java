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

import java.io.PrintStream;
import java.lang.reflect.Field;
import net.minecraft.server.v1_7_R4.EntityVillager;
import net.minecraft.server.v1_7_R4.ItemStack;
import net.minecraft.server.v1_7_R4.MerchantRecipe;
import net.minecraft.server.v1_7_R4.MerchantRecipeList;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftVillager;
import org.bukkit.craftbukkit.v1_7_R4.inventory.CraftItemStack;
import org.bukkit.entity.Villager;

public class CoreVillagerHandler implements VillagerHandler {

	@Override
	public boolean clearTrades(Villager villager) {
		EntityVillager entityVillager = ((CraftVillager) villager).getHandle();
		try {
			Field recipes = entityVillager.getClass().getDeclaredField("bu");
			recipes.setAccessible(true);
			MerchantRecipeList list = new MerchantRecipeList();
			recipes.set(entityVillager, list);
			return true;
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean addTrade(Villager villager, VillagerTrade villagerTrade) {
		EntityVillager entityVillager = ((CraftVillager) villager).getHandle();
		try {
			Field recipes = entityVillager.getClass().getDeclaredField("bu");
			recipes.setAccessible(true);
			MerchantRecipeList list = (MerchantRecipeList) recipes
					.get(entityVillager);
			if (villagerTrade.hasItem2()) {
				ItemStack item1 = CraftItemStack.asNMSCopy(villagerTrade
						.getItem1());
				ItemStack item2 = CraftItemStack.asNMSCopy(villagerTrade
						.getItem2());
				ItemStack rewardItem = CraftItemStack.asNMSCopy(villagerTrade
						.getRewardItem());
				list.a(new MerchantRecipe(item1, item2, rewardItem));
			} else {
				ItemStack item1 = CraftItemStack.asNMSCopy(villagerTrade
						.getItem1());
				ItemStack rewardItem = CraftItemStack.asNMSCopy(villagerTrade
						.getRewardItem());
				list.a(new MerchantRecipe(item1, rewardItem));
			}
			recipes.set(entityVillager, list);
			return true;
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
	}

	@Override
	public List<VillagerTrade> getTrades(Villager villager) {
		List<VillagerTrade> result = new ArrayList<VillagerTrade>();
		EntityVillager entityVillager = ((CraftVillager) villager).getHandle();
		try {
			Field recipes = entityVillager.getClass().getDeclaredField("bu");
			recipes.setAccessible(true);
			MerchantRecipeList list = (MerchantRecipeList) recipes
					.get(entityVillager);

			for (MerchantRecipe r : list) {
				result.add(new CoreVillagerTrade(r.getBuyItem1(), r
						.getBuyItem2(), r.getBuyItem3()));
			}

			return result;
		} catch (Exception exc) {
			exc.printStackTrace();
			return result;
		}
	}

	@Override
	public boolean setTrades(Villager villager, List<VillagerTrade> trades) {
		EntityVillager entityVillager = ((CraftVillager) villager).getHandle();
		try {
			Field recipes = entityVillager.getClass().getDeclaredField("bu");
			recipes.setAccessible(true);
			MerchantRecipeList list = new MerchantRecipeList();

			for (VillagerTrade villagerTrade : trades) {
				if (villagerTrade.hasItem2()) {
					ItemStack item1 = CraftItemStack.asNMSCopy(villagerTrade
							.getItem1());
					ItemStack item2 = CraftItemStack.asNMSCopy(villagerTrade
							.getItem2());
					ItemStack rewardItem = CraftItemStack
							.asNMSCopy(villagerTrade.getRewardItem());
					list.a(new MerchantRecipe(item1, item2, rewardItem));
				} else {
					ItemStack item1 = CraftItemStack.asNMSCopy(villagerTrade
							.getItem1());
					ItemStack rewardItem = CraftItemStack
							.asNMSCopy(villagerTrade.getRewardItem());
					list.a(new MerchantRecipe(item1, rewardItem));
				}
			}
			
			return true;
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
	}
}
