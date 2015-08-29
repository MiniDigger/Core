/**
 * █████████████████████████████████████████████████████████████████████████████
 * ████████████████████████████████████
 * █░░░░░░░░░░░░░░█░░░░░░██░░░░░░█░░░░░░░░░░░░░░████░░░░░░░░░░░░░░█░░░░░░░░░░░░░
 * ░█░░░░░░░░░░░░░░░░███░░░░░░░░░░░░░░█
 * █░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░
 * ░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █░░░░░░▄▀░░░░░░█░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░
 * ░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░████████████░░▄▀░░█████████░░▄▀░░██░░▄▀░
 * ░█░░▄▀░░████░░▄▀░░███░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░
 * ░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░
 * ░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░
 * ░█░░▄▀░░░░░░▄▀░░░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░████████████░░▄▀░░█████████░░▄▀░░██░░▄▀░
 * ░█░░▄▀░░██░░▄▀░░█████░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░
 * ░█░░▄▀░░██░░▄▀░░░░░░█░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░
 * ░█░░▄▀░░██░░▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░░░░░█████░░░░░░██░░░░░░█░░░░░░░░░░░░░░████░░░░░░░░░░░░░░█░░░░░░░░░░░░░
 * ░█░░░░░░██░░░░░░░░░░█░░░░░░░░░░░░░░█
 * █████████████████████████████████████████████████████████████████████████████
 * ████████████████████████████████████
 *
 * Copyright © MiniDigger and others - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Martin Benndorf <admin@minidigger.me>, 2013-2015 and others
 */
package me.MiniDigger.Core.AddOn.Hub;

import java.text.DecimalFormat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Stats.Stats;
import me.MiniDigger.Core.Stats.StatsType;
import me.MiniDigger.Core.Stats.StatsType.StatsGame;
import me.MiniDigger.Core.User.User;

public class StatsManager implements Listener {

	public StatsManager() {
		Bukkit.getPluginManager().registerEvents(this, Core.getCore().getInstance());
	}

	@EventHandler
	public void handleCLick(final InventoryClickEvent event) {
		if (event.isCancelled()) {
			return;
		}
		final Player p = (Player) event.getWhoClicked();
		final User user = Core.getCore().getUserHandler().get(p.getUniqueId());
		if (event.getInventory().getTitle().equals("§a§lGame-Statistiken")) {
			try {
				final Stats stats = Core.getCore().getStatsHandler().get(user.getUUID());
				if (event.getCurrentItem().getType() == crankitem().getType()) {
					message(p, "§cComing soon...");
				} else if (event.getCurrentItem().getType() == getthedropitem().getType()) {
					message(p, "§5<><><><[§aGet The Drop Stats§5]><><><>");
					message(p, "§bSpiele gespielt: §e" + stats.get(StatsGame.GAMES));
					message(p, "§bWins: §e" + stats.get(StatsGame.WINS));
					final double kills = stats.get(StatsGame.KILLS);
					final double deaths = stats.get(StatsGame.DEATHS);
					message(p, "§bKills: §e" + (int) kills);
					message(p, "§bTode: §e" + (int) deaths);
					double kd = 0;
					try {
						kd = kills / deaths;
					}
					catch (final Exception ex) {
						// plugin.getLogger().warning(
						// "Could not progress kd of player "
						// + p.getName());
					}
					final DecimalFormat df = new DecimalFormat("0.00");
					final String rkd = df.format(kd);
					message(p, "§bK/D: §e" + rkd);
					message(p, "§bDrops (Phase 1): §e" + stats.get(StatsType.GetTheDrop.DROPS_1));
					message(p, "§bDrops (Phase 2): §e" + stats.get(StatsType.GetTheDrop.DROPS_2));
					message(p, "§5<><><><[§aGet The Drop Stats§5]><><><>");
				}
			}
			catch (final Exception ex) {
				ex.printStackTrace();
			}
			event.setCancelled(true);
			p.closeInventory();
		}
	}

	public void message(final Player p, final String message) {
		p.sendMessage(message);
	}

	public Inventory SelectStats(final Player p) {
		final Inventory items = Bukkit.createInventory(null, 9, "§a§lGame-Statistiken");

		items.setItem(0, crankitem());
		items.setItem(1, ph());
		items.setItem(2, getthedropitem());
		items.setItem(3, ph());
		items.setItem(4, ph());
		items.setItem(5, ph());
		items.setItem(6, ph());
		items.setItem(7, ph());
		items.setItem(8, ph());

		return items;
	}

	public ItemStack crankitem() {
		final ItemStack item = new ItemStack(Material.TNT, 1);
		final ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Crank");
		item.setItemMeta(itemMeta);

		return item;
	}

	public ItemStack getthedropitem() {
		@SuppressWarnings("deprecation")
		final ItemStack item = new ItemStack(Material.MONSTER_EGG.getId(), 1, (short) 0, (byte) 50);
		final ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(ChatColor.DARK_BLUE + "" + ChatColor.BOLD + "Get The Drop");
		item.setItemMeta(itemMeta);
		return item;
	}

	public ItemStack ph() {
		@SuppressWarnings("deprecation")
		final ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE.getId(), 1, (short) 0, (byte) 5);
		final ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName("§c");
		item.setItemMeta(itemMeta);

		return item;
	}
}
