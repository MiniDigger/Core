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

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.Stats.Stats;
import me.MiniDigger.Core.Stats.StatsType;
import me.MiniDigger.Core.User.User;

public class TokenShop implements Listener {

	private static String								title		= "Tokenshop";
	private final HashMap<String, ArrayList<String>>	all_perms	= new HashMap<String, ArrayList<String>>();
	private final HashMap<String, ArrayList<Integer>>	all_preise	= new HashMap<String, ArrayList<Integer>>();
	private final HashMap<String, ArrayList<ItemStack>>	all_items	= new HashMap<String, ArrayList<ItemStack>>();

	public TokenShop() {
		Bukkit.getServer().getPluginManager().registerEvents(this, Core.getCore().getInstance());
	}

	private Inventory setupHeader(final Inventory inv, final String name) {
		inv.setItem(0, getGlass(14));
		inv.setItem(1, getGlass(1));
		inv.setItem(2, getGlass(4));
		// inv.setItem(3, );
		inv.setItem(4, getIs(name, Material.CHEST, (short) 1, (byte) 1));
		// inv.setItem(5, );
		inv.setItem(6, getGlass(4));
		inv.setItem(7, getGlass(1));
		inv.setItem(8, getGlass(14));

		inv.setItem(9, getGlass(15));
		inv.setItem(10, getGlass(15));
		inv.setItem(11, getGlass(15));
		inv.setItem(12, getGlass(15));
		inv.setItem(13, getGlass(15));
		inv.setItem(14, getGlass(15));
		inv.setItem(15, getGlass(15));
		inv.setItem(16, getGlass(15));
		inv.setItem(17, getGlass(15));

		return inv;
	}

	@SuppressWarnings("deprecation")
	public void open(final Player p) {
		Inventory inv = Bukkit.createInventory(p, 3 * 9, title);

		inv = setupHeader(inv, ChatColor.GOLD + "Tokenshop");

		inv.setItem(18, getIs(ChatColor.GOLD + "Crank", Material.TNT, (short) 1, (byte) 1));
		inv.setItem(19, getGlass(7));
		inv.setItem(20, getIs(ChatColor.DARK_BLUE + "GetTheDrop", Material.getMaterial(383), (short) 50, (byte) 50));
		inv.setItem(21, getGlass(7));
		inv.setItem(22, getIs(ChatColor.DARK_PURPLE + "OneInTheChamber", Material.BOW, (short) 0, (byte) 0));
		inv.setItem(23, getGlass(7));
		inv.setItem(24, getIs(ChatColor.RED + "Coming soon", Material.PAPER, (short) 1, (byte) 1));
		inv.setItem(25, getGlass(7));
		inv.setItem(26, getIs(ChatColor.RED + "Coming soon", Material.PAPER, (short) 1, (byte) 1));

		p.openInventory(inv);
	}

	private Inventory getCrank(final Player p) {
		final User user = Core.getCore().getUserHandler().get(p.getUniqueId());
		Inventory inv = Bukkit.createInventory(p, 3 * 9, "Crank - TokenShop");

		inv = setupHeader(inv, ChatColor.GOLD + "Crank - TokenShop");

		final ArrayList<ItemStack> items = new ArrayList<>();
		final ArrayList<Integer> preise = new ArrayList<Integer>();
		final ArrayList<String> perm = new ArrayList<>();

		// item eins
		final String snowman_perm = "kit.snowman";
		final int snowman_preis = 10000;
		final ItemStack snowman = new ItemStack(Material.SNOW_BALL);
		final ItemMeta snowman_meta = snowman.getItemMeta();
		snowman_meta.setDisplayName("Ice-Men Kit");
		final ArrayList<String> snowman_lore = new ArrayList<>();
		snowman_lore.add(ChatColor.DARK_AQUA + "1x Holzschwert");
		snowman_lore.add(ChatColor.DARK_AQUA + "2x Stack Schneebälle");
		snowman_lore.add("====================");
		snowman_lore.add("Preis: " + snowman_preis);
		if (user.hasPermission(snowman_perm)) {
			snowman_lore.add("====================");
			snowman_lore.add("Bereits gekauft");
		}
		snowman_meta.setLore(snowman_lore);
		snowman.setItemMeta(snowman_meta);
		items.add(snowman);
		preise.add(snowman_preis);
		perm.add(snowman_perm);
		// ende item ein

		final ItemStack soon = new ItemStack(Material.PAPER);
		final ItemMeta soon_meta = soon.getItemMeta();
		soon_meta.setDisplayName(ChatColor.RED + "Comming soon");
		final ArrayList<String> soon_lore = new ArrayList<>();
		soon_meta.setLore(soon_lore);
		soon.setItemMeta(soon_meta);

		int slot = 18;
		for (int i = 0; i < 5; i++) {
			try {
				inv.setItem(slot, items.get(i));
			}
			catch (final Exception ex) {
				inv.setItem(slot, soon);
			}
			slot++;
			if (slot >= inv.getSize()) {
				break;
			}
			inv.setItem(slot, getGlass(7));
			slot++;
		}

		all_items.put("Crank - TokenShop", items);
		all_preise.put("Crank - TokenShop", preise);
		all_perms.put("Crank - TokenShop", perm);

		return inv;
	}

	private Inventory getGTD(final Player p) {
		final User user = Core.getCore().getUserHandler().get(p.getUniqueId());
		Inventory inv = Bukkit.createInventory(p, 3 * 9, "GetTheDrop - TokenShop");

		inv = setupHeader(inv, ChatColor.GOLD + "GetTheDrop - TokenShop");

		final ArrayList<ItemStack> items = new ArrayList<>();
		final ArrayList<Integer> preise = new ArrayList<Integer>();
		final ArrayList<String> perm = new ArrayList<>();

		// item eins
		final String snowman_perm = "kit.snowman";
		final int snowman_preis = 100;
		final ItemStack snowman = new ItemStack(Material.SNOW_BALL);
		final ItemMeta snowman_meta = snowman.getItemMeta();
		snowman_meta.setDisplayName("Snowman Kit");
		final ArrayList<String> snowman_lore = new ArrayList<>();
		snowman_lore.add("Test");
		snowman_lore.add("========");
		snowman_lore.add("Preis: " + snowman_preis);
		if (user.hasPermission(snowman_perm)) {
			snowman_lore.add("===============");
			snowman_lore.add("Bereits gekauft");
		}
		snowman_meta.setLore(snowman_lore);
		snowman.setItemMeta(snowman_meta);
		items.add(snowman);
		preise.add(snowman_preis);
		perm.add(snowman_perm);
		// ende item ein

		final ItemStack soon = new ItemStack(Material.PAPER);
		final ItemMeta soon_meta = soon.getItemMeta();
		soon_meta.setDisplayName(ChatColor.RED + "Comming soon");
		final ArrayList<String> soon_lore = new ArrayList<>();
		soon_meta.setLore(soon_lore);
		soon.setItemMeta(soon_meta);

		int slot = 18;
		for (int i = 0; i < 5; i++) {
			try {
				inv.setItem(slot, items.get(i));
			}
			catch (final Exception ex) {
				inv.setItem(slot, soon);
			}
			slot++;
			if (slot >= inv.getSize()) {
				break;
			}
			inv.setItem(slot, getGlass(7));
			slot++;
		}

		all_items.put("GetTheDrop - TokenShop", items);
		all_preise.put("GetTheDrop - TokenShop", preise);
		all_perms.put("GetTheDrop - TokenShop", perm);

		return inv;
	}

	private ItemStack getGlass(final int color) {
		@SuppressWarnings("deprecation")
		final ItemStack is = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) color, (byte) color);
		final ItemMeta im = is.getItemMeta();
		im.setDisplayName(" ");
		is.setItemMeta(im);
		return is;
	}

	private ItemStack getIs(final String name, final Material type, final short dmg, final byte data) {
		@SuppressWarnings("deprecation")
		final ItemStack is = new ItemStack(type, 1, dmg, data);
		final ItemMeta im = is.getItemMeta();
		im.setDisplayName(name);
		is.setItemMeta(im);
		return is;
	}

	@EventHandler
	public void handleClick(final InventoryClickEvent event) {
		final Player p = (Player) event.getWhoClicked();
		final User user = Core.getCore().getUserHandler().get(p.getUniqueId());
		if (event.getCurrentItem() == null) {
			return;
		}

		if (event.getCurrentItem().getItemMeta() == null) {
			return;
		}
		if (event.getInventory().getTitle().equalsIgnoreCase(title)) {
			Inventory inv = null;
			Bukkit.getScheduler().runTaskLater(Core.getCore().getInstance(), new Runnable() {

				@Override
				public void run() {
					((Player) event.getView().getPlayer()).updateInventory();
				}
			}, 20);
			if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Crank")) {
				inv = getCrank(p);
			} else if (event.getCurrentItem().getItemMeta().getDisplayName().contains("GetTheDrop")) {
				inv = getGTD(p);
			} else {
				return;
			}

			p.closeInventory();
			final Inventory fInv = inv;
			Bukkit.getScheduler().runTaskLater(Core.getCore().getInstance(), new Runnable() {

				@Override
				public void run() {
					p.openInventory(fInv);
				}
			}, 3);
			make(event);
		} else if (event.getInventory().getTitle().contains("TokenShop")) {
			final ArrayList<ItemStack> items = all_items.get(event.getInventory().getTitle());
			final ArrayList<Integer> preise = all_preise.get(event.getInventory().getTitle());
			final ArrayList<String> perms = all_perms.get(event.getInventory().getTitle());

			for (int i = 0; i < items.size(); i++) {
				if (items.get(i).getItemMeta().getDisplayName().equalsIgnoreCase(event.getCurrentItem().getItemMeta().getDisplayName())) {
					final String perm = perms.get(i);
					final ItemStack item = items.get(i);
					final long preis = preise.get(i);

					final Stats stats = Core.getCore().getStatsHandler().get(user.getUUID());

					final long tokens = stats.get(StatsType.Common.TOKENS);

					if (preis > tokens) {
						user.sendMessage(Prefix.SHOP.getPrefix().then("Du hast nicht genug Tokens!").color(ChatColor.RED));
						make(event);
						p.closeInventory();
						return;
					} else if (user.hasPermission(perm)) {
						user.sendMessage(Prefix.SHOP.getPrefix().then("Du hast dieses Item bereits gekauft!").color(ChatColor.RED));
						make(event);
						p.closeInventory();
						return;
					} else {
						user.sendMessage(Prefix.SHOP.getPrefix().then("Du hast " + item.getItemMeta().getDisplayName() + " für " + preis + " Tokens gekauft").color(ChatColor.GREEN));
						stats.remove(StatsType.Common.TOKENS, (int) preis);
						stats.add(StatsType.Common.TOKENS_SPEND, (int) preis);
						// Core.getCore().getInstance().getPermissionManager().getUser(user.getUUID()).addPermission(perm);
						// TODO Use vault or so for perm stuff
						stats.save();
						make(event);
						p.closeInventory();
						return;
					}
				}
			}
			make(event);
		} else {
			return;
		}

	}

	private void make(final InventoryClickEvent event) {
		event.setCancelled(true);
		event.setResult(Result.DENY);
		event.setCurrentItem(null);
		Bukkit.getScheduler().runTaskLater(Core.getCore().getInstance(), new Runnable() {

			@Override
			public void run() {
				((Player) event.getWhoClicked()).updateInventory();
			}
		}, 10);
	}
}
