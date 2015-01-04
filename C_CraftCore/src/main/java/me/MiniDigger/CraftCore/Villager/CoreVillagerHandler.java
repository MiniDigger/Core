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

import com.comphenix.protocol.wrappers.nbt.NbtBase;
import com.comphenix.protocol.wrappers.nbt.NbtCompound;
import com.comphenix.protocol.wrappers.nbt.NbtFactory;
import com.comphenix.protocol.wrappers.nbt.NbtVisitor;
import com.comphenix.protocol.wrappers.nbt.NbtWrapper;

import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftVillager;
import org.bukkit.craftbukkit.v1_8_R1.event.CraftEventFactory;

import net.minecraft.server.v1_8_R1.Container;
import net.minecraft.server.v1_8_R1.ContainerMerchant;
import net.minecraft.server.v1_8_R1.EntityPlayer;
import net.minecraft.server.v1_8_R1.EntityVillager;
import net.minecraft.server.v1_8_R1.IChatBaseComponent;
import net.minecraft.server.v1_8_R1.IMerchant;
import net.minecraft.server.v1_8_R1.InventoryMerchant;
import net.minecraft.server.v1_8_R1.MerchantRecipe;
import net.minecraft.server.v1_8_R1.MerchantRecipeList;
import net.minecraft.server.v1_8_R1.PacketDataSerializer;
import net.minecraft.server.v1_8_R1.PacketPlayOutCustomPayload;
import net.minecraft.server.v1_8_R1.PacketPlayOutOpenWindow;

import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import me.MiniDigger.Core.Villager.VillagerHandler;
import me.MiniDigger.Core.Villager.VillagerTrade;

import io.netty.buffer.Unpooled;

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
		NbtWrapper<EntityVillager> nbt = NbtFactory.fromNMS(((CraftVillager) v).getHandle(), "Test");
		NbtBase<EntityVillager> base = nbt.deepClone();
		NbtCompound comp = NbtFactory.asCompound(base);
//		comp. Lets make a own nbt lib first ;D
		
		return true;
	}
	
	private net.minecraft.server.v1_8_R1.ItemStack convertItemStack(final org.bukkit.inventory.ItemStack item) {
		if (item == null) {
			return null;
		}
		return org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack.asNMSCopy(item);
	}
}
