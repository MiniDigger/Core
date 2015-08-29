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
package me.MiniDigger.CraftCore.Feature.Features;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftItem;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Map.MapData;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.CraftCore.Feature.CoreFeature;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.EntityItem;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.TileEntity;
import net.minecraft.server.v1_8_R3.TileEntityMobSpawner;
import net.minecraft.server.v1_8_R3.World;

public class SpawnerFeature extends CoreFeature {

	static {
		try {
			// Get the new registry HashMp from the Item class
			// final Field registryField =
			// net.minecraft.server.v1_8_R3.Item.class.getDeclaredField("REGISTRY");
			// registryField.setAccessible(true);
			// final RegistryMaterials<?, ?> registry = (RegistryMaterials<?,
			// ?>) registryField.get(null);
			// Get entry of the spawner
			// final Object spawnerEntry = registry.a(52);
			// final Object spawnerEntry =
			// net.minecraft.server.v1_8_R3.Item.d("52");
			// Set maxStackSize "e(int maxStackSize)"
			// maxStackSize.setAccessible(true);
			// maxStackSize.setInt(spawnerEntry, 1);
			// Cleanup
			// registryField.setAccessible(false);
			// maxStackSize.setAccessible(false);

			// final net.minecraft.server.v1_8_R3.Item spawnerEntry =
			// net.minecraft.server.v1_8_R3.Item.d("52");
			// spawnerEntry.c(1);

		}
		catch (final Exception ex) {
			Core.getCore().getInstance().debug("could not set spawner items unstackable");
			ex.printStackTrace();
		}
	}

	private final int			interval;
	private final EntityType	type;
	private final DyeColor		locKey;
	private final ItemStack		item;
	private Location[]			locs;

	public SpawnerFeature(final Phase phase, final DyeColor locKey, final int interval, final EntityType type, final ItemStack item) {
		super(phase);
		this.locKey = locKey;
		this.interval = interval;
		this.type = type;
		this.item = item;
	}

	@Override
	public FeatureType getType() {
		return FeatureType.SPAWNER;
	}

	@Override
	public List<FeatureType> getDependencies() {
		final List<FeatureType> list = new ArrayList<FeatureType>();
		list.add(FeatureType.MAP);
		return list;
	}

	@Override
	public List<FeatureType> getSoftDependencies() {
		return new ArrayList<FeatureType>();
	}

	@Override
	public List<FeatureType> getIncompabilities() {
		return new ArrayList<FeatureType>();
	}

	@Override
	public void start() {
		// Core.getCore().getInstance().debug("start spawner search for " +
		// locKey.name());
		final MapData data = ((MapFeature) getPhase().getFeature(FeatureType.MAP)).getMap();

		final HashMap<String, Location> n = data.getLocs(locKey);
		try {
			locs = n.values().toArray(new Location[n.values().size()]);
		}
		catch (final Exception ex) {
			Core.getCore().getInstance().error("No SpawnerPoint found for locKey " + locKey.name());
			return;
		}

		if (locs.length == 0) {
			Core.getCore().getInstance().error("No SpawnerPoints found for locKey " + locKey.name());
		}

		for (final Location l : locs) {
			l.getBlock().setType(Material.MOB_SPAWNER);
			final Block b = l.getBlock();
			if (b.getType() == Material.MOB_SPAWNER) {
				if (item != null) {
					// Core.getCore().getInstance().debug("create spawner: " +
					// locKey.name());
					final World world = ((CraftWorld) b.getWorld()).getHandle();
					final TileEntity tileEntity = world.getTileEntity(new BlockPosition(b.getX(), b.getY(), b.getZ()));
					if ((tileEntity instanceof TileEntityMobSpawner)) {
						final TileEntityMobSpawner mobSpawner = (TileEntityMobSpawner) tileEntity;
						final NBTTagCompound spawnerTag = new NBTTagCompound();
						mobSpawner.b(spawnerTag);
						spawnerTag.remove("SpawnPotentials");
						spawnerTag.setString("EntityId", "Item");
						final NBTTagCompound itemTag = new NBTTagCompound();
						itemTag.setShort("Health", (short) 1);
						itemTag.setShort("Age", (short) 0);
						final net.minecraft.server.v1_8_R3.ItemStack itemStack = CraftItemStack.asNMSCopy(item);
						// itemStack.getItem().c(1);
						final NBTTagCompound itemStackTag = new NBTTagCompound();
						itemStack.save(itemStackTag);
						itemStackTag.setByte("Count", (byte) 1);
						itemTag.set("Item", itemStackTag);
						spawnerTag.set("SpawnData", itemTag);
						spawnerTag.setShort("SpawnCount", (short) 1);
						// Core.getCore().getInstance().debug("SpawnCount" +
						// spawnerTag.getShort("SpawnCount"));
						spawnerTag.setShort("SpawnRange", (short) 0.1);
						// Core.getCore().getInstance().debug("SpawnRange" +
						// spawnerTag.getShort("SpawnRange"));
						spawnerTag.setShort("Delay", (short) interval);
						spawnerTag.setShort("MinSpawnDelay", (short) (interval));
						spawnerTag.setShort("MaxSpawnDelay", (short) (interval));
						spawnerTag.setShort("MaxNearbyEntities", (short) 300);
						spawnerTag.setShort("RequiredPlayerRange", (short) 300);

						mobSpawner.a(spawnerTag);
					}
				} else if (type != null) {
					final CreatureSpawner s = (CreatureSpawner) b;
					s.setSpawnedType(type);
				} else {
					Core.getCore().getInstance().debug("failed to spawner");
				}
			} else {
				Core.getCore().getInstance().debug("no spawner!");
			}
		}
	}

	// @EventHandler
	public void onEntitySpawn(final EntitySpawnEvent event) {
		if (event.getEntityType() == EntityType.DROPPED_ITEM) {
			final Item item = (Item) event.getEntity();
			if (item.getItemStack().getType() != this.item.getType()) {
				return;
			}
			if (event.getEntity().hasMetadata("doNotRemove")) {
				return;
			}
			event.setCancelled(true);
			final EntityItem e = new CoreEntityItem(((CraftWorld) event.getLocation().getWorld()).getHandle(), event.getLocation().getX(), event.getLocation().getY(), event.getLocation().getZ());
			e.setItemStack(CraftItemStack.asNMSCopy(item.getItemStack()));
			e.fromMobSpawner = true;
			CraftItem ee = new CraftItem((CraftServer) Bukkit.getServer(), e);
			ee.setMetadata("doNotRemove", new FixedMetadataValue(Core.getCore().getInstance(), "true"));
			ee = new CraftItem((CraftServer) Bukkit.getServer(), e);
			((CraftWorld) event.getLocation().getWorld()).getHandle().addEntity(e);
		}
	}

	@Override
	public void end() {

	}

	public class CoreEntityItem extends EntityItem {

		public CoreEntityItem(final World world, final double d0, final double d1, final double d2) {
			super(world, d0, d1, d2);
		}

		@SuppressWarnings("unused")
		private boolean a(final EntityItem entity) {
			Core.getCore().getInstance().debug("weird method call");
			return false;
		}

	}
}
