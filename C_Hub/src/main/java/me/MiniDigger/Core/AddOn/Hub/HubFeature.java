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
import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Event.Events.CoreUserJoinGameEvent;
import me.MiniDigger.CraftCore.Feature.CoreFeature;

public class HubFeature extends CoreFeature {
	
	private StatsManager	            stats;
	private Teleporter	                tp;
	private TokenShop	                shop;
	private final HashMap<String, Long>	cooldowns	= new HashMap<>();
	
	public HubFeature(final Phase phase) {
		super(phase);
	}
	
	public StatsManager getStatsManager() {
		return stats;
	}
	
	public Teleporter getTeleporter() {
		return tp;
	}
	
	public TokenShop getTokenShop() {
		return shop;
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.HUB;
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
		stats = new StatsManager();
		tp = new Teleporter();
		shop = new TokenShop();
	}
	
	@Override
	public void end() {
		HandlerList.unregisterAll(shop);
		HandlerList.unregisterAll(tp);
		HandlerList.unregisterAll(stats);
	}
	
	@EventHandler
	public void onPlayerJoin(final CoreUserJoinGameEvent e) {
		if (e.getGame().getIdentifier().equals(getPhase().getGame().getIdentifier())) {
			giveStartItems(e.getUser().getPlayer());
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void handleInteract(final PlayerInteractEvent event) {
		final Player p = event.getPlayer();
		final User u = Core.getCore().getUserHandler().get(p.getUniqueId());
		try {
			Core.getCore().getMenuHandler().getMenu("menu").open(u);
		} catch (Exception ex) {
			
		}
		@SuppressWarnings("unused")
        boolean b;
		if ( (b = true) == true) {
			return;
		}
		// OLD STUFF
		final int cooldownTime = 5;
		if ((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
			if (p.getItemInHand().getType() == Material.SKULL_ITEM && p.getItemInHand().getData().getData() == 3) {
				if (cooldowns.containsKey(p.getName())) {
					final long secondsLeft = cooldowns.get(p.getName()).longValue() / 1000L + cooldownTime - System.currentTimeMillis() / 1000L;
					if (secondsLeft > 0L) {
						p.sendMessage("§4Warte bitte kurz!");
						return;
					}
				}
				
				cooldowns.put(p.getName(), Long.valueOf(System.currentTimeMillis()));
				for (final Player pl : Core.getCore().getUserHandler().getOnlinePlayers()) {
					if (!pl.hasPermission("lm.kickjoin")) {
						p.hidePlayer(pl);
					}
				}
				p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "§6Alle anderen Spieler sind nun §aunsichtbar!");
				p.getInventory().remove(p.getItemInHand());
				
				final ItemStack normalSkull = new ItemStack(Material.SKULL_ITEM.getId(), 1, (short) 0, (byte) 1);
				final ItemMeta normalSkullMeta = normalSkull.getItemMeta();
				normalSkullMeta.setDisplayName(ChatColor.RED + "Spieler aus!");
				normalSkull.setItemMeta(normalSkullMeta);
				
				p.getInventory().setItemInHand(normalSkull);
				p.updateInventory();
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 10.0F, 1.0F);
			} else if (p.getItemInHand().getType() == Material.SKULL_ITEM && p.getItemInHand().getData().getData() == 1) {
				if (cooldowns.containsKey(p.getName())) {
					final long secondsLeft = cooldowns.get(p.getName()).longValue() / 1000L + cooldownTime - System.currentTimeMillis() / 1000L;
					if (secondsLeft > 0L) {
						p.sendMessage("§4Warte bitte kurz!");
						return;
					}
				}
				
				cooldowns.put(p.getName(), Long.valueOf(System.currentTimeMillis()));
				for (final Player pl : Core.getCore().getUserHandler().getOnlinePlayers()) {
					p.showPlayer(pl);
				}
				
				p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "§6Alle anderen Spieler sind nun §asichtbar!");
				p.getInventory().remove(p.getItemInHand());
				
				final ItemStack playerSkull = new ItemStack(Material.SKULL_ITEM.getId(), 1, (short) 0, (byte) 3);
				final ItemMeta playerSkullMeta = playerSkull.getItemMeta();
				playerSkullMeta.setDisplayName(ChatColor.GREEN + "Spieler an!");
				playerSkull.setItemMeta(playerSkullMeta);
				
				p.getInventory().setItemInHand(playerSkull);
				p.updateInventory();
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 10.0F, 1.0F);
			} else if (p.getItemInHand().getType() == Material.WATCH) {
				boolean staff = false;
				if (p.hasPermission("staffspawn")) {
					staff = true;
				}
				p.openInventory(tp.QuickTP(staff));
			} else if (p.getItemInHand().getType() == Material.DIAMOND) {
				p.openInventory(stats.SelectStats(p));
			} else if (p.getItemInHand().getType() == Material.CHEST) {
				shop.open(p);
			} else if (p.getItemInHand().getType() == Material.MINECART) {
				p.sendMessage("§cComing soon...");
			} else if (!p.isOp()) {
				if (event.getClickedBlock() != null) {
					if ((event.getClickedBlock().getState() instanceof Sign)) {
						event.setCancelled(false);
					} else {
						event.setCancelled(true);
					}
				}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public void giveStartItems(final Player p) {
		final ItemStack QuickTP = new ItemStack(Material.WATCH, 1);
		final ItemMeta QuickTPMeta = QuickTP.getItemMeta();
		QuickTPMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Server Buddy");
		QuickTP.setItemMeta(QuickTPMeta);
		
		final ItemStack holder = new ItemStack(Material.STAINED_GLASS_PANE);
		holder.setData(new MaterialData(Material.STAINED_GLASS_PANE.getId(), (byte) 5));
		final ItemMeta holderMeta = holder.getItemMeta();
		holderMeta.setDisplayName(ChatColor.RESET + "");
		holder.setItemMeta(holderMeta);
		
		final ItemStack playerSkull = new ItemStack(Material.SKULL_ITEM.getId(), 1, (short) 0, (byte) 3);
		final ItemMeta playerSkullMeta = playerSkull.getItemMeta();
		playerSkullMeta.setDisplayName(ChatColor.GREEN + "Spieler an!");
		playerSkull.setItemMeta(playerSkullMeta);
		
		final ItemStack normalSkull = new ItemStack(Material.SKULL_ITEM.getId(), 1, (short) 0, (byte) 1);
		final ItemMeta normalSkullMeta = normalSkull.getItemMeta();
		normalSkullMeta.setDisplayName(ChatColor.RED + "Spieler aus!");
		normalSkull.setItemMeta(normalSkullMeta);
		
		final ItemStack stats = new ItemStack(Material.DIAMOND, 1);
		final ItemMeta statsMeta = stats.getItemMeta();
		statsMeta.setDisplayName(ChatColor.AQUA + "Stats");
		stats.setItemMeta(statsMeta);
		
		final ItemStack shop = new ItemStack(Material.CHEST, 1);
		final ItemMeta shopMeta = shop.getItemMeta();
		shopMeta.setDisplayName(ChatColor.GOLD + "Token-Shop");
		shop.setItemMeta(shopMeta);
		
		final ItemStack lobbymanager = new ItemStack(Material.MINECART, 1);
		final ItemMeta lobbymanagerMeta = lobbymanager.getItemMeta();
		lobbymanagerMeta.setDisplayName(ChatColor.DARK_GRAY + "Lobby Manager");
		lobbymanager.setItemMeta(lobbymanagerMeta);
		
		final ItemStack buddy = new ItemStack(Material.WRITTEN_BOOK, 1);
		final BookMeta buddyMeta = (BookMeta) buddy.getItemMeta();
		buddyMeta
		        .addPage("Herzlich Willkommen auf Zone-Games.eu\nInhaltsverzeichnis:\n1.Das Team\n2.Die Regeln\n3.Spiel beitreten\n4.Der Ingame-Shop\n5.Premium-Artikel\n6.Die Buddy List\n7.Hilfe und Support");
		buddyMeta
		        .addPage("1.Das Team\nUnser Team besteht aus vielen unterschiedlichen Personen, die dir bei Fragen zur Seite stehen und helfen.\nOwner:\n- EclipseGamer\n- Zockebester\n- MiniDigger\n- werda07\n- maanu113\n \nAdmins:\n- SchafiHD\n- _EnziAnditore_\n- Prom3d\n \nDeveloper:\n- MiniDigger\n \nModeratoren:\n- JustSayLucy\n- Infinity989\n- LisiPlay\n- Sloxh\n \nArchitekten:\n- Prom3d\n- Killbracker\n- General_Efects\n- JockelCrafter\n- pandabear1\n- kaito_kid1");
		buddyMeta.addPage("2.Die Regeln\n- Keine Beleidigungen!\n- Kein Spamming!\n- Maximale Teamgröße 4 Spieler!\n- Spaß haben!");
		buddyMeta
		        .addPage("3.Spiel beitreten\n \nUm einem Spiel/Server beizutreten muss du lediglich Rechtsklick auf ein Serverschild machen, wenn du dem Server nach einmaligen Rechtsklick nicht beitretest wird der Server wahrscheinlich voll sein. Um volle Server trotzdem beitreten zu können musst du dir eins unser drei Premium-Pakete im Shop erwerben. Mehr zu den Premium-Paketen findest du unter www.shop.zone-games.eu oder unter Punkt 6.");
		buddyMeta
		        .addPage("4.Der Ingame-Shop\n \nIm Ingame-Shop kannst du dir Klassen oder Items für gewisse Spielmodi freischalten, indem du sie mit der Ingame-Währung TOKENS erwirbst. Nach dem Erwerb einer Klasse oder eines Items kannst du sie sofort ausprobieren. Du muss lediglich für die Benutzung ein kleinen Token-Betrag zahlen.");
		buddyMeta.setAuthor("Zone-Games.eu Team");
		buddyMeta.setTitle("Willkommen");
		buddyMeta.setDisplayName(ChatColor.GOLD + "Zone-Games");
		buddy.setItemMeta(buddyMeta);
		
		p.getInventory().clear();
		p.getInventory().setItem(0, QuickTP);
		p.getInventory().setItem(1, lobbymanager);
		p.getInventory().setItem(2, playerSkull);
		p.getInventory().setItem(3, holder);
		p.getInventory().setItem(4, holder);
		p.getInventory().setItem(5, holder);
		p.getInventory().setItem(6, stats);
		p.getInventory().setItem(7, shop);
		p.getInventory().setItem(8, buddy);
		p.updateInventory();
		
	}
	
}
