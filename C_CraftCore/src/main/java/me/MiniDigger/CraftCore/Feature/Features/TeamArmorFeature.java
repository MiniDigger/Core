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
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Team.Team;
import me.MiniDigger.Core.User.User;
import me.MiniDigger.CraftCore.Feature.CoreFeature;

public class TeamArmorFeature extends CoreFeature {

	private final boolean giveOnStartUp;

	public TeamArmorFeature(final Phase phase, final boolean giveOnStartup) {
		super(phase);
		giveOnStartUp = giveOnStartup;
	}

	@Override
	public FeatureType getType() {
		return FeatureType.TEAM_ARMOR;
	}

	@Override
	public List<FeatureType> getDependencies() {
		return new ArrayList<FeatureType>();
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
		if (giveOnStartUp) {
			for (final UUID id : getPhase().getGame().getPlayers()) {
				final Player p = Bukkit.getPlayer(id);
				final TeamFeature f = (TeamFeature) getPhase().getFeature(FeatureType.TEAM);

				if (f.getTeam(p) == null) {
					Core.getCore().getInstance().debug("no team for player " + p.getName());
					continue;
				}

				final ItemStack head = new ItemStack(Material.LEATHER_HELMET);
				final ItemStack body = new ItemStack(Material.LEATHER_CHESTPLATE);
				final ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
				final ItemStack feet = new ItemStack(Material.LEATHER_BOOTS);

				final LeatherArmorMeta meta = (LeatherArmorMeta) head.getItemMeta();
				meta.setColor(Core.getCore().getChatColorUtil().toColor(f.getTeam(p).getColor()));

				Core.getCore().getInstance().debug("use color " + meta.getColor().toString() + " for player " + p.getName() + " from team " + f.getTeam(p).getName());

				head.setItemMeta(meta);
				body.setItemMeta(meta);
				leg.setItemMeta(meta);
				feet.setItemMeta(meta);

				p.getInventory().setHelmet(head);
				p.getInventory().setChestplate(body);
				p.getInventory().setLeggings(leg);
				p.getInventory().setBoots(feet);
			}
		}
	}

	@Override
	public void end() {
	}

	@EventHandler
	public void onIvnCLick(final InventoryClickEvent e) {
		if (e.getWhoClicked() instanceof Player) {
			final User u = Core.getCore().getUserHandler().get(e.getWhoClicked().getUniqueId());
			if (getPhase().getGame().getPlayers().contains(u.getUUID())) {
				switch (e.getAction()) {
					case COLLECT_TO_CURSOR:
					case DROP_ALL_CURSOR:
					case DROP_ALL_SLOT:
					case DROP_ONE_CURSOR:
					case DROP_ONE_SLOT:
					case MOVE_TO_OTHER_INVENTORY:
						try {
							if (e.getCursor().getType() == Material.LEATHER_BOOTS || e.getCursor().getType() == Material.LEATHER_LEGGINGS || e.getCursor().getType() == Material.LEATHER_HELMET) {
								if (e.getCursor().hasItemMeta() && e.getCursor().getItemMeta().hasLore() && e.getCursor().getItemMeta().getLore().contains("TeamArmor")) {
									final LeatherArmorMeta m = (LeatherArmorMeta) e.getCursor().getItemMeta();
									m.setColor(Color.fromRGB(102, 76, 51));// Standard
																			// brown
									e.getCursor().setItemMeta(m);
								}
							}
						}
						catch (final Exception ex) {}
						try {
							if (e.getCurrentItem().getType() == Material.LEATHER_BOOTS || e.getCurrentItem().getType() == Material.LEATHER_LEGGINGS || e.getCurrentItem().getType() == Material.LEATHER_HELMET) {
								if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasLore() && e.getCurrentItem().getItemMeta().getLore().contains("TeamArmor")) {
									final LeatherArmorMeta m = (LeatherArmorMeta) e.getCurrentItem().getItemMeta();
									m.setColor(Color.fromRGB(102, 76, 51));// Standard
																			// brown
									e.getCursor().setItemMeta(m);
								}
							}
						}
						catch (final Exception ex) {}
						break;
					case HOTBAR_MOVE_AND_READD:
					case HOTBAR_SWAP:
					case NOTHING:
					case PICKUP_ALL:
					case PICKUP_HALF:
					case PICKUP_ONE:
					case PICKUP_SOME:
					case PLACE_ALL:
					case PLACE_ONE:
					case PLACE_SOME:
					case SWAP_WITH_CURSOR:
					case CLONE_STACK:
					case UNKNOWN:
						final TeamFeature tf = (TeamFeature) getPhase().getFeature(FeatureType.TEAM);
						final Team t = tf.getTeam(u);
						if (t == null) {
							return;
						}
						final Color c = Core.getCore().getChatColorUtil().toColor(t.getColor());

						try {
							if (e.getCursor().getType() == Material.LEATHER_BOOTS || e.getCursor().getType() == Material.LEATHER_LEGGINGS || e.getCursor().getType() == Material.LEATHER_HELMET) {
								if (e.getCursor().hasItemMeta() && e.getCursor().getItemMeta().hasLore() && e.getCursor().getItemMeta().getLore().contains("TeamArmor")) {
									final LeatherArmorMeta m = (LeatherArmorMeta) e.getCursor().getItemMeta();
									m.setColor(c);
									e.getCursor().setItemMeta(m);
								}
							}
						}
						catch (final Exception ex) {}
						try {
							if (e.getCurrentItem().getType() == Material.LEATHER_BOOTS || e.getCurrentItem().getType() == Material.LEATHER_LEGGINGS || e.getCurrentItem().getType() == Material.LEATHER_HELMET) {
								if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasLore() && e.getCurrentItem().getItemMeta().getLore().contains("TeamArmor")) {
									final LeatherArmorMeta m = (LeatherArmorMeta) e.getCurrentItem().getItemMeta();
									m.setColor(c);
									e.getCursor().setItemMeta(m);
								}
							}
						}
						catch (final Exception ex) {}
						break;
				}
			}
		}
	}

}
