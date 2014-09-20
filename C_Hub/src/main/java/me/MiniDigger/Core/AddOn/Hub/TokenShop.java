package me.MiniDigger.Core.AddOn.Hub;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Event.Result;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.ChatColor;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.Stats.Stats;
import me.MiniDigger.Core.Stats.StatsType;
import me.MiniDigger.Core.User.User;
import me.MiniDigger.CraftCore.CoreMain;

public class TokenShop implements Listener {
	
	private static String	                      title	      = "Tokenshop";
	private HashMap<String, ArrayList<String>>	  all_perms	  = new HashMap<String, ArrayList<String>>();
	private HashMap<String, ArrayList<Integer>>	  all_preise	= new HashMap<String, ArrayList<Integer>>();
	private HashMap<String, ArrayList<ItemStack>>	all_items	= new HashMap<String, ArrayList<ItemStack>>();
	
	public TokenShop() {
		Bukkit.getServer().getPluginManager().registerEvents(this, (CoreMain) Core.getCore().getInstance());
	}
	
	private Inventory setupHeader(Inventory inv, String name) {
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
	public void open(Player p) {
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
	
	private Inventory getCrank(Player p) {
		User user = Core.getCore().getUserHandler().get(p.getUniqueId());
		Inventory inv = Bukkit.createInventory(p, 3 * 9, "Crank - TokenShop");
		
		inv = setupHeader(inv, ChatColor.GOLD + "Crank - TokenShop");
		
		ArrayList<ItemStack> items = new ArrayList<>();
		ArrayList<Integer> preise = new ArrayList<Integer>();
		ArrayList<String> perm = new ArrayList<>();
		
		// item eins
		String snowman_perm = "kit.snowman";
		int snowman_preis = 10000;
		ItemStack snowman = new ItemStack(Material.SNOW_BALL);
		ItemMeta snowman_meta = snowman.getItemMeta();
		snowman_meta.setDisplayName("Ice-Men Kit");
		ArrayList<String> snowman_lore = new ArrayList<>();
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
		
		ItemStack soon = new ItemStack(Material.PAPER);
		ItemMeta soon_meta = soon.getItemMeta();
		soon_meta.setDisplayName(ChatColor.RED + "Comming soon");
		ArrayList<String> soon_lore = new ArrayList<>();
		soon_meta.setLore(soon_lore);
		soon.setItemMeta(soon_meta);
		
		int slot = 18;
		for (int i = 0; i < 5; i++) {
			try {
				inv.setItem(slot, items.get(i));
			} catch (Exception ex) {
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
	
	private Inventory getGTD(Player p) {
		User user = Core.getCore().getUserHandler().get(p.getUniqueId());
		Inventory inv = Bukkit.createInventory(p, 3 * 9, "GetTheDrop - TokenShop");
		
		inv = setupHeader(inv, ChatColor.GOLD + "GetTheDrop - TokenShop");
		
		ArrayList<ItemStack> items = new ArrayList<>();
		ArrayList<Integer> preise = new ArrayList<Integer>();
		ArrayList<String> perm = new ArrayList<>();
		
		// item eins
		String snowman_perm = "kit.snowman";
		int snowman_preis = 100;
		ItemStack snowman = new ItemStack(Material.SNOW_BALL);
		ItemMeta snowman_meta = snowman.getItemMeta();
		snowman_meta.setDisplayName("Snowman Kit");
		ArrayList<String> snowman_lore = new ArrayList<>();
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
		
		ItemStack soon = new ItemStack(Material.PAPER);
		ItemMeta soon_meta = soon.getItemMeta();
		soon_meta.setDisplayName(ChatColor.RED + "Comming soon");
		ArrayList<String> soon_lore = new ArrayList<>();
		soon_meta.setLore(soon_lore);
		soon.setItemMeta(soon_meta);
		
		int slot = 18;
		for (int i = 0; i < 5; i++) {
			try {
				inv.setItem(slot, items.get(i));
			} catch (Exception ex) {
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
	
	private ItemStack getGlass(int color) {
		@SuppressWarnings("deprecation") ItemStack is = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) color, (byte) color);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(" ");
		is.setItemMeta(im);
		return is;
	}
	
	private ItemStack getIs(String name, Material type, short dmg, byte data) {
		@SuppressWarnings("deprecation") ItemStack is = new ItemStack(type, 1, dmg, data);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(name);
		is.setItemMeta(im);
		return is;
	}
	
	@EventHandler
	public void handleClick(final InventoryClickEvent event) {
		final Player p = (Player) event.getWhoClicked();
		User user = Core.getCore().getUserHandler().get(p.getUniqueId());
		if (event.getCurrentItem() == null) {
			return;
		}
		
		if (event.getCurrentItem().getItemMeta() == null) {
			return;
		}
		if (event.getInventory().getTitle().equalsIgnoreCase(title)) {
			Inventory inv = null;
			Bukkit.getScheduler().runTaskLater((CoreMain) Core.getCore().getInstance(), new Runnable() {
				
				@SuppressWarnings("deprecation")
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
			Bukkit.getScheduler().runTaskLater((CoreMain) Core.getCore().getInstance(), new Runnable() {
				
				@Override
				public void run() {
					p.openInventory(fInv);
				}
			}, 3);
			make(event);
		} else if (event.getInventory().getTitle().contains("TokenShop")) {
			ArrayList<ItemStack> items = all_items.get(event.getInventory().getTitle());
			ArrayList<Integer> preise = all_preise.get(event.getInventory().getTitle());
			ArrayList<String> perms = all_perms.get(event.getInventory().getTitle());
			
			for (int i = 0; i < items.size(); i++) {
				if (items.get(i).getItemMeta().getDisplayName().equalsIgnoreCase(event.getCurrentItem().getItemMeta().getDisplayName())) {
					String perm = perms.get(i);
					ItemStack item = items.get(i);
					long preis = preise.get(i);
					
					Stats stats = Core.getCore().getStatsHandler().get(user.getUUID());
					
					long tokens = stats.get(StatsType.Common.TOKENS);
					
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
						user.sendMessage(Prefix.SHOP.getPrefix().then("Du hast " + item.getItemMeta().getDisplayName() + " für " + preis + " Tokens gekauft")
						        .color(ChatColor.GREEN));
						stats.remove(StatsType.Common.TOKENS, (int) preis);
						stats.add(StatsType.Common.TOKENS_SPEND, (int) preis);
						Core.getCore().getInstance().getPermissionManager().getUser(user.getUUID()).addPermission(perm);
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
	
	@SuppressWarnings("deprecation")
	private void make(final InventoryClickEvent event) {
		event.setCancelled(true);
		event.setResult(Result.DENY);
		event.setCursor(null);
		Bukkit.getScheduler().runTaskLater((CoreMain) Core.getCore().getInstance(), new Runnable() {
			
			@Override
			public void run() {
				((Player) event.getWhoClicked()).updateInventory();
			}
		}, 10);
	}
}
