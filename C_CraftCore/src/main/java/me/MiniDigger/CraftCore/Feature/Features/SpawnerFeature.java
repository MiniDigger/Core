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
package me.MiniDigger.CraftCore.Feature.Features;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Map.MapData;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.CraftCore.Feature.CoreFeature;
import net.minecraft.server.v1_7_R4.NBTTagCompound;
import net.minecraft.server.v1_7_R4.TileEntity;
import net.minecraft.server.v1_7_R4.TileEntityMobSpawner;
import net.minecraft.server.v1_7_R4.World;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;
import org.bukkit.craftbukkit.v1_7_R4.inventory.CraftItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public class SpawnerFeature extends CoreFeature {
	
	private final int	     interval;
	private final EntityType	type;
	private final DyeColor	 locKey;
	private final ItemStack	 item;
	private Location[]	     locs;
	
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
		final MapData data = ((MapFeature) getPhase().getFeature(FeatureType.MAP)).getMap();
		
		final HashMap<String, Location> n = data.getLocs(DyeColor.YELLOW);
		try {
			locs = n.values().toArray(new Location[n.values().size()]);
		} catch (final Exception ex) {
			Core.getCore().getInstance().error("No SpawnerPoint found for locKey " + locKey.name());
			return;
		}
		
		for (final Location l : locs) {
			l.getBlock().setType(Material.MOB_SPAWNER);
			final Block b = l.getBlock();
			if (b.getType() == Material.MOB_SPAWNER) {
				if (item != null) {
					final World world = ((CraftWorld) b.getWorld()).getHandle();
					final TileEntity tileEntity = world.getTileEntity(b.getX(), b.getY(), b.getZ());
					if ((tileEntity instanceof TileEntityMobSpawner)) {
						final TileEntityMobSpawner mobSpawner = (TileEntityMobSpawner) tileEntity;
						final NBTTagCompound spawnerTag = new NBTTagCompound();
						mobSpawner.b(spawnerTag);
						spawnerTag.remove("SpawnPotentials");
						spawnerTag.setString("EntityId", "Item");
						final NBTTagCompound itemTag = new NBTTagCompound();
						itemTag.setShort("Health", (short) 5);
						itemTag.setShort("Age", (short) 0);
						final net.minecraft.server.v1_7_R4.ItemStack itemStack = CraftItemStack.asNMSCopy(item);
						final NBTTagCompound itemStackTag = new NBTTagCompound();
						itemStack.save(itemStackTag);
						itemStackTag.setByte("Count", (byte) 1);
						itemTag.set("Item", itemStackTag);
						spawnerTag.set("SpawnData", itemTag);
						spawnerTag.setShort("SpawnCount", (short) itemStack.count);
						spawnerTag.setShort("SpawnRange", (short) 3);
						spawnerTag.setShort("Delay", (short) 0);
						spawnerTag.setShort("MinSpawnDelay", (short) (interval));
						spawnerTag.setShort("MaxSpawnDelay", (short) (interval));
						spawnerTag.setShort("MaxNearbyEntities", (short) 300);
						spawnerTag.setShort("RequiredPlayerRange", (short) 300);
						
						mobSpawner.a(spawnerTag);
					}
				} else if (type != null) {
					final CreatureSpawner s = (CreatureSpawner) b;
					s.setSpawnedType(type);
				}
			}
		}
	}
	
	@Override
	public void end() {
		
	}
}
