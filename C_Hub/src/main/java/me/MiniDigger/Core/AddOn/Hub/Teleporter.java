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
package me.MiniDigger.Core.AddOn.Hub;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Map.MapData;

public class Teleporter implements Listener {

	private MapData	map;
	String			prefix	= ChatColor.GRAY + "[" + ChatColor.RED + "Teleporter" + ChatColor.GRAY + "]" + ChatColor.RESET;

	public Teleporter() {
		Bukkit.getServer().getPluginManager().registerEvents(this, Core.getCore().getInstance());
	}

	public Inventory QuickTP(final boolean staff) {
		final Inventory items = Bukkit.createInventory(null, 9 * 5, "§a§lServer Buddy");

		items.setItem(0, premiumspawnitem());
		items.setItem(1, placeholderitem(15));
		items.setItem(2, placeholderitem(5));
		items.setItem(3, soonitem());
		items.setItem(4, survivalgamesitem());
		items.setItem(5, soonitem());
		items.setItem(6, placeholderitem(5));
		items.setItem(7, placeholderitem(15));
		items.setItem(8, premiumspawnitem());

		// items.setItem(9,);
		items.setItem(10, placeholderitem(15));
		items.setItem(11, soonitem());
		items.setItem(12, placeholderitem(1));
		items.setItem(13, placeholderitem(1));
		items.setItem(14, placeholderitem(1));
		items.setItem(15, soonitem());
		items.setItem(16, placeholderitem(15));
		// items.setItem(17,);

		items.setItem(18, teamitem());
		items.setItem(19, placeholderitem(15));
		items.setItem(20, crankitem());
		items.setItem(21, placeholderitem(1));
		items.setItem(22, spawnitem());
		items.setItem(23, placeholderitem(1));
		items.setItem(24, getthedropitem());
		items.setItem(25, placeholderitem(15));
		items.setItem(26, teamitem());

		// items.setItem(27,);
		items.setItem(28, placeholderitem(15));
		items.setItem(29, soonitem());
		items.setItem(30, placeholderitem(1));
		items.setItem(31, placeholderitem(1));
		items.setItem(32, placeholderitem(1));
		items.setItem(33, oitcitem());
		items.setItem(34, placeholderitem(15));
		// items.setItem(35,);

		items.setItem(36, staffitem());
		items.setItem(37, placeholderitem(15));
		items.setItem(38, placeholderitem(5));
		items.setItem(39, soonitem());
		items.setItem(40, catchtheterroristitem());
		items.setItem(41, soonitem());
		items.setItem(42, placeholderitem(5));
		items.setItem(43, placeholderitem(15));
		items.setItem(44, staffitem());

		return items;
	}

	public ItemStack placeholderitem(final int data) {
		@SuppressWarnings("deprecation") final ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE.getId(), 1, (short) 0, (byte) data);
		final ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName("§a");

		final List<String> lore = new ArrayList<>();

		itemMeta.setLore(lore);

		item.setItemMeta(itemMeta);
		return item;
	}

	public ItemStack spawnitem() {
		final ItemStack item = new ItemStack(Material.COMPASS, 1);
		final ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Spawn");

		final List<String> lore = new ArrayList<>();

		itemMeta.setLore(lore);

		item.setItemMeta(itemMeta);
		return item;
	}

	public ItemStack soonitem() {
		final ItemStack item = new ItemStack(Material.PAPER, 1);
		final ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Bald/Soon");

		final List<String> lore = new ArrayList<>();

		lore.add(ChatColor.WHITE + "Wir sind ständig bemüht");
		lore.add(ChatColor.WHITE + "immer neue Modi zu entwickeln");
		itemMeta.setLore(lore);

		item.setItemMeta(itemMeta);
		return item;
	}

	public ItemStack teamitem() {
		@SuppressWarnings("deprecation") final ItemStack item = new ItemStack(Material.SKULL_ITEM.getId(), 1, (short) 0, (byte) 3);
		final SkullMeta itemMeta = (SkullMeta) item.getItemMeta();
		itemMeta.setOwner("E");
		itemMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Das Team");

		final List<String> lore = new ArrayList<>();

		itemMeta.setLore(lore);

		item.setItemMeta(itemMeta);
		return item;
	}

	public ItemStack oitcitem() {
		final ItemStack item = new ItemStack(Material.BOW, 1);
		final ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "OneInTheChamber");

		final List<String> lore = new ArrayList<>();

		lore.add("§cKlicke hier um zu den OneInTheChamber Server zu gelangen");

		itemMeta.setLore(lore);

		item.setItemMeta(itemMeta);
		return item;
	}

	public ItemStack staffitem() {
		final ItemStack item = new ItemStack(Material.NAME_TAG, 1);
		final ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Staff Spawn");

		final List<String> lore = new ArrayList<>();

		lore.add("§cOnly for staff!");
		itemMeta.setLore(lore);

		item.setItemMeta(itemMeta);
		return item;
	}

	public ItemStack crankitem() {
		final ItemStack item = new ItemStack(Material.TNT, 1);
		final ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Crank");

		final List<String> lore = new ArrayList<>();

		lore.add("§cKlicke hier um zu den Crank Server zu gelangen");

		itemMeta.setLore(lore);

		item.setItemMeta(itemMeta);

		return item;
	}

	public ItemStack survivalgamesitem() {
		final ItemStack item = new ItemStack(Material.DIAMOND_SWORD, 1);
		final ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "SurvivalGames");

		final List<String> lore = new ArrayList<>();

		lore.add("§cKlicke hier um zu den SurvivalGames Server zu gelangen");

		itemMeta.setLore(lore);

		item.setItemMeta(itemMeta);

		return item;
	}

	public ItemStack getthedropitem() {
		@SuppressWarnings("deprecation") final ItemStack item = new ItemStack(Material.MONSTER_EGG.getId(), 1, (short) 0, (byte) 50);
		final ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(ChatColor.DARK_BLUE + "" + ChatColor.BOLD + "Get The Drop");

		final List<String> lore = new ArrayList<>();

		lore.add("§cKlicke hier um zu den Get The Drop Server zu gelangen");

		itemMeta.setLore(lore);

		item.setItemMeta(itemMeta);

		return item;
	}

	public ItemStack catchtheterroristitem() {
		final ItemStack item = new ItemStack(Material.LEASH, 1);
		final ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Catch The Terrorist");
		final List<String> lore = new ArrayList<>();

		lore.add("§cKlicke hier um zu den Catch The Terrorist Server zu gelangen");

		itemMeta.setLore(lore);

		item.setItemMeta(itemMeta);

		return item;
	}

	public ItemStack premiumspawnitem() {
		@SuppressWarnings("deprecation") final ItemStack item = new ItemStack(175, 1);
		final ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Premium Spawn");

		final List<String> lore = new ArrayList<>();

		lore.add("§cKlicke hier um in die Premium Area zu gelangen");
		lore.add("§6Noch kein Premium? Shop.Zone-Games.eu");

		itemMeta.setLore(lore);

		item.setItemMeta(itemMeta);

		return item;
	}

	// DEPRECATION

	@SuppressWarnings("deprecation")
	@EventHandler
	public void handleClick(final InventoryClickEvent event) {
		final Player p = (Player) event.getWhoClicked();
		if (event.getInventory().getTitle().equals(QuickTP(false).getTitle())) {
			if (map == null) {
				map = Core.getCore().getMapHandler().getMap("Spawn");
			}

			event.setCancelled(true);
			((Player) event.getView().getPlayer()).updateInventory();
			p.updateInventory();
			Bukkit.getScheduler().runTaskLater(Core.getCore().getInstance(), new Runnable() {

				@Override
				public void run() {
					((Player) event.getView().getPlayer()).updateInventory();
					p.updateInventory();
				}
			}, 20);
			if (event.getCurrentItem() == null) {
				return;
			}

			if (!event.getCurrentItem().hasItemMeta()) {
				return;
			}

			if (event.getCurrentItem().getItemMeta().getDisplayName().equals(getthedropitem().getItemMeta().getDisplayName())) {
				p.teleport(map.getLocs(DyeColor.BLUE).values().iterator().next());
				p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 5, 5);
				p.playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 0);
			} else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(crankitem().getItemMeta().getDisplayName())) {
				p.teleport(map.getLocs(DyeColor.BROWN).values().iterator().next());
				p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 5, 5);
				p.playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 0);
			} else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(survivalgamesitem().getItemMeta().getDisplayName())) {
				p.teleport(map.getLocs(DyeColor.CYAN).values().iterator().next());
				p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 5, 5);
				p.playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 0);
			} else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(catchtheterroristitem().getItemMeta().getDisplayName())) {
				p.teleport(map.getLocs(DyeColor.GRAY).values().iterator().next());
				p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 5, 5);
				p.playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 0);
			} else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(oitcitem().getItemMeta().getDisplayName())) {
				p.teleport(map.getLocs(DyeColor.LIGHT_BLUE).values().iterator().next());
				p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 5, 5);
				p.playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 0);
			} else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(teamitem().getItemMeta().getDisplayName())) {
				p.teleport(map.getLocs(DyeColor.LIME).values().iterator().next());
				p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 5, 5);
				p.playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 0);
			} else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(spawnitem().getItemMeta().getDisplayName())) {
				p.teleport(map.getLocs(DyeColor.RED).values().iterator().next());
				p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 5, 5);
				p.playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 0);
			} else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(staffitem().getItemMeta().getDisplayName())) {
				if (p.hasPermission("staff.spawn")) {
					p.teleport(map.getLocs(DyeColor.ORANGE).values().iterator().next());
					p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 5, 5);
					p.playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 0);
				} else {
					p.sendMessage(
					        ChatColor.GRAY + "[" + ChatColor.RED + "Teleporter" + ChatColor.GRAY + "]" + ChatColor.RED + "Du darfst die Staff Area nicht betreten!");
				}
			} else if (event.getCurrentItem().getItemMeta().getDisplayName().equals(premiumspawnitem().getItemMeta().getDisplayName())) {
				if (p.hasPermission("premium.spawn")) {
					p.teleport(map.getLocs(DyeColor.MAGENTA).values().iterator().next());
					p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 5, 5);
					p.playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 0);
				} else {
					p.sendMessage(
					        ChatColor.GRAY + "[" + ChatColor.RED + "Teleporter" + ChatColor.GRAY + "]" + ChatColor.RED + "Du darfst den Premium Spawn nicht benutzen!");
					p.sendMessage(
					        ChatColor.GRAY + "[" + ChatColor.RED + "Teleporter" + ChatColor.GRAY + "]" + ChatColor.RED + "Kaufe jetzt Preium auf shop.zone-games.eu !");
				}
			}
		}
	}
}
