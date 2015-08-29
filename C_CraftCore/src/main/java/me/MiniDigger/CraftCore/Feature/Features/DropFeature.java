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

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Map.MapData;
import me.MiniDigger.Core.Phase.Phase;

import me.MiniDigger.CraftCore.Feature.CoreFeature;

public class DropFeature extends CoreFeature {

	public DropFeature(final Phase phase) {
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
		final MapData data = ((MapFeature) getPhase().getFeature(FeatureType.MAP)).getMap();

		HashMap<String, Location> n = data.getLocs(DyeColor.YELLOW);
		try {
			normal = n.values().toArray(new Location[n.values().size()]);
		}
		catch (final Exception ex) {
			Core.getCore().getInstance().debug("No normal drop points found");
		}

		try {
			n = data.getLocs(DyeColor.LIME);
			special = n.values().toArray(new Location[n.values().size()]);
		}
		catch (final Exception ex) {
			Core.getCore().getInstance().debug("No special drop points found");
		}
	}

	@Override
	public void end() {
		normal = null;
		special = null;
	}

	public void drop(final boolean special) {
		if (special) {
			for (final Location loc : this.special) {
				if (loc.getWorld() != null) {
					loc.getWorld().dropItem(loc, getSpecialItem());
				}
			}
		} else {
			for (int i = 0; i < getPhase().getGame().getPlayers().size(); i++) {
				final Location loc = normal[Core.getCore().getRandomUtil().nextInt(normal.length)];
				if (loc.getWorld() != null) {
					loc.getWorld().dropItemNaturally(loc, getNormalItem());
				}
			}
		}

	}

	private ItemStack getNormalItem() {
		final Material[] mats = new Material[] { Material.STICK, Material.STICK, Material.STICK, Material.STICK, Material.STICK, Material.STICK, Material.ARROW, Material.ARROW, Material.ARROW, Material.STRING,
				Material.WOOD, Material.WOOD, Material.WOOD, Material.WOOD, Material.WOOD, Material.WOOD, Material.WOOD, Material.WOOD, Material.COBBLESTONE, Material.COBBLESTONE, Material.COBBLESTONE,
				Material.COBBLESTONE, Material.COBBLESTONE, Material.LEATHER, Material.LEATHER, Material.LEATHER, Material.LEATHER, Material.LEATHER, Material.LEATHER, Material.LEATHER, Material.LEATHER,
				Material.LEATHER, Material.LEATHER, Material.LEATHER, Material.GOLD_INGOT, Material.GOLD_INGOT, Material.GOLD_INGOT, Material.GOLD_INGOT, Material.GOLD_INGOT, Material.IRON_INGOT, Material.FISHING_ROD };

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

		final ItemStack is = new ItemStack(mats[Core.getCore().getRandomUtil().nextInt(mats.length)]);

		return is;
	}

}
