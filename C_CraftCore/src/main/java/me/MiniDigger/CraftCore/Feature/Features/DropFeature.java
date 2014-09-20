package me.MiniDigger.CraftCore.Feature.Features;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Map.MapData;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.CraftCore.Feature.CoreFeature;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class DropFeature extends CoreFeature {
	
	public DropFeature(Phase phase) {
		super(phase);
	}
	
	private Location[]	normal;
	private Location[]	special;
	
	@Override
	public FeatureType getType() {
		return FeatureType.DROP;
	}
	
	@Override
	public List<FeatureType> getDependencies() {
		return new ArrayList<>();
	}
	
	@Override
	public List<FeatureType> getSoftDependencies() {
		return new ArrayList<>();
	}
	
	@Override
	public List<FeatureType> getIncompabilities() {
		return new ArrayList<>();
	}
	
	@Override
	public void start() {
		MapData data = ((MapFeature) getPhase().getFeature(FeatureType.MAP)).getMap();
		
		HashMap<String, Location> n = data.getLocs(DyeColor.YELLOW);
		try {
			normal = n.values().toArray(new Location[n.values().size()]);
		} catch (Exception ex) {
			System.out.println("No normal drop points found");
		}
		
		try {
			n = data.getLocs(DyeColor.LIME);
			special = n.values().toArray(new Location[n.values().size()]);
		} catch (Exception ex) {
			System.out.println("No special drop points found");
		}
	}
	
	@Override
	public void end() {
		normal = null;
		special = null;
	}
	
	public void drop(boolean special) {
		if (special) {
			for (Location loc : this.special) {
				loc.getWorld().dropItem(loc, getSpecialItem());
			}
		} else {
			for (int i = 0; i < getPhase().getGame().getPlayers().size(); i++) {
				Location loc = normal[Core.getCore().getRandomUtil().nextInt(normal.length)];
				loc.getWorld().dropItemNaturally(loc, getNormalItem());
			}
		}
		
	}
	
	private ItemStack getNormalItem() {
		final Material[] mats = new Material[] { Material.STICK, Material.STICK, Material.STICK, Material.STICK, Material.STICK, Material.STICK, Material.ARROW,
		        Material.ARROW, Material.ARROW, Material.STRING, Material.WOOD, Material.WOOD, Material.WOOD, Material.WOOD, Material.WOOD, Material.WOOD, Material.WOOD,
		        Material.WOOD, Material.COBBLESTONE, Material.COBBLESTONE, Material.COBBLESTONE, Material.COBBLESTONE, Material.COBBLESTONE, Material.LEATHER,
		        Material.LEATHER, Material.LEATHER, Material.LEATHER, Material.LEATHER, Material.LEATHER, Material.LEATHER, Material.LEATHER, Material.LEATHER,
		        Material.LEATHER, Material.LEATHER, Material.GOLD_INGOT, Material.GOLD_INGOT, Material.GOLD_INGOT, Material.GOLD_INGOT, Material.GOLD_INGOT,
		        Material.IRON_INGOT, Material.FISHING_ROD };
		
		ItemStack is = new ItemStack(mats[Core.getCore().getRandomUtil().nextInt(mats.length)]);
		
		if (is.getType() == Material.ARROW) {
			is.setAmount(5);
		} else if (is.getType() == Material.FISHING_ROD) {
			if (Core.getCore().getRandomUtil().nextBoolean()) {
				is = getNormalItem();
			}
		}
		
		return is;
	}
	
	private ItemStack getSpecialItem() {
		final Material[] mats = new Material[] { Material.BOW, Material.IRON_SWORD, Material.DIAMOND };
		
		ItemStack is = new ItemStack(mats[Core.getCore().getRandomUtil().nextInt(mats.length)]);
		
		return is;
	}
	
}
