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
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Menu.ItemBarMenu;
import me.MiniDigger.Core.Menu.ItemBarMenu.ClickHandler;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Event.Events.CoreUserJoinGameEvent;
import me.MiniDigger.CraftCore.Feature.CoreFeature;
import me.MiniDigger.CraftCore.Item.CoreItemBuilder;
import me.MiniDigger.CraftCore.Menu.CoreItemBarMenu;

public class HubFeature extends CoreFeature {
	
	private final HashMap<UUID, Long>	cooldowns	= new HashMap<>();
	
	public HubFeature(final Phase phase) {
		super(phase);
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
		menu();
		for (final UUID id : getPhase().getGame().getPlayers()) {
			final User u = Core.getCore().getUserHandler().get(id);
			Core.getCore().getMenuHandler().openMenu(u, "Hub");
		}
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				for (final UUID id : getPhase().getGame().getPlayers()) {
					final Player p = Bukkit.getPlayer(id);
					if (p != null) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, 2));
					}
				}
			}
		}.runTaskTimer(Core.getCore().getInstance(), 1, 20);
	}
	
	@Override
	public void end() {
		
	}
	
	@EventHandler
	public void onPlayerJoin(final CoreUserJoinGameEvent e) {
		if (e.getGame().getIdentifier().equals(getPhase().getGame().getIdentifier())) {
			Core.getCore().getMenuHandler().openMenu(e.getUser(), "Hub");
		}
	}
	
	public void menu() {
		final ItemBarMenu hub = new CoreItemBarMenu("Hub");
		
		hub.setIcon(
		        0,
		        new CoreItemBuilder(Material.SKULL_ITEM).name(ChatColor.RED + "Spieler verstecken").lore("Benutze dieses Item").lore("um alle anderen")
		                .lore("Spieler zu verstecken").data(3).durability(3).build());
		hub.setAction(0, new ClickHandler() {
			
			final int	cooldownTime	= 5;
			
			@Override
			public void click(ItemBarMenu m, ItemStack is, User u) {
				if (cooldowns.containsKey(u.getUUID())) {
					final long secondsLeft = cooldowns.get(u.getUUID()).longValue() / 1000L + cooldownTime - System.currentTimeMillis() / 1000L;
					if (secondsLeft > 0L) {
						Prefix.API.getPrefix().then("Bitte warte kurz bevor du dieses Item erneut benutzt!").color(ChatColor.RED).send(u.getPlayer());
						return;
					}
					cooldowns.remove(u.getUUID());
				}
				
				cooldowns.put(u.getUUID(), System.currentTimeMillis());
				for (final Player pl : Core.getCore().getUserHandler().getOnlinePlayers()) {
					if (!pl.hasPermission("donthideme")) {
						u.getPlayer().hidePlayer(pl);
					}
				}
				Prefix.API.getPrefix().then("Alle anderen Spieler sind nun ").color(ChatColor.GREEN).then("unsichtbar!").color(ChatColor.GOLD).send(u.getPlayer());
				Core.getCore().getMenuHandler().closeMenu(u);
				Core.getCore().getMenuHandler().openMenu(u, "Hub2");
			}
		});
		
		hub.setIcon(1, new CoreItemBuilder(Material.COMPASS).name("Teleporter").lore("Öffnet den Teleporter").lore("Mit diesem kannst du").lore("zu jedem Spielmodi")
		        .lore("hinteleportieren!").build());
		hub.setAction(1, new ClickHandler() {
			
			@Override
			public void click(ItemBarMenu m, ItemStack is, User u) {
				Core.getCore().getMenuHandler().closeMenu(u);
				Core.getCore().getMenuHandler().openMenu(u, "Teleporter");
			}
		});
		
		/*************************************************/
		
		final ItemBarMenu hub2 = hub.clone();
		hub2.setName("Hub2");
		hub2.setIcon(
		        0,
		        new CoreItemBuilder(Material.SKULL_ITEM).name(ChatColor.GREEN + "Spieler anzeigen").lore("Benutze dieses Item").lore("um alle anderen")
		                .lore("Spieler anzuzeigen").data(1).durability(1).build());
		hub2.setAction(0, new ClickHandler() {
			
			@Override
			public void click(ItemBarMenu m, ItemStack is, User u) {
				for (final Player pl : Core.getCore().getUserHandler().getOnlinePlayers()) {
					u.getPlayer().showPlayer(pl);
				}
				Prefix.API.getPrefix().then("Alle anderen Spieler sind nun ").color(ChatColor.GREEN).then("sichtbar!").color(ChatColor.GOLD).send(u.getPlayer());
				Core.getCore().getMenuHandler().closeMenu(u);
				Core.getCore().getMenuHandler().openMenu(u, "Hub");
			}
		});
		
		/************************************************/
		
		final ItemBarMenu tp = new CoreItemBarMenu("Teleporter");
		tp.setIcon(0, new CoreItemBuilder(Material.BARRIER).name("Back").lore("Zurück zum Hauptmenü").build());
		tp.setAction(0, new ClickHandler() {
			
			@Override
			public void click(ItemBarMenu m, ItemStack is, User u) {
				Core.getCore().getMenuHandler().closeMenu(u);
				Core.getCore().getMenuHandler().openMenu(u, "Hub");
			}
		});
		
		tp.setIcon(1, new CoreItemBuilder(Material.BOW).name("OneInTheChamber").build());
		tp.setAction(1, new ClickHandler() {
			
			@Override
			public void click(ItemBarMenu m, ItemStack is, User u) {
				Core.getCore().getMapHandler().getMap("Hub").getLocs(DyeColor.BLACK).get("");
			}
		});
		
		tp.setIcon(2, new CoreItemBuilder(Material.FIREWORK).name("Event").build());
		tp.setAction(2, new ClickHandler() {
			
			@Override
			public void click(ItemBarMenu m, ItemStack is, User u) {
				
			}
		});
		
		tp.setIcon(3, new CoreItemBuilder(Material.CHEST).name("KitPvP").build());
		tp.setAction(3, new ClickHandler() {
			
			@Override
			public void click(ItemBarMenu m, ItemStack is, User u) {
				
			}
		});
		
		tp.setIcon(4, new CoreItemBuilder(Material.MONSTER_EGG).name("GetTheDrop").build());
		tp.setAction(4, new ClickHandler() {
			
			@Override
			public void click(ItemBarMenu m, ItemStack is, User u) {
				
			}
		});
		
		tp.setIcon(5, new CoreItemBuilder(Material.GOLD_SPADE).name("CityBuild").build());
		tp.setAction(5, new ClickHandler() {
			
			@Override
			public void click(ItemBarMenu m, ItemStack is, User u) {
				
			}
		});
		
		tp.setIcon(6, new CoreItemBuilder(Material.TNT).name("Crank").build());
		tp.setAction(6, new ClickHandler() {
			
			@Override
			public void click(ItemBarMenu m, ItemStack is, User u) {
				
			}
		});
		
		tp.setIcon(7, new CoreItemBuilder(Material.EXPLOSIVE_MINECART).name("TNTRun").build());
		tp.setAction(7, new ClickHandler() {
			
			@Override
			public void click(ItemBarMenu m, ItemStack is, User u) {
				
			}
		});
		
		tp.setIcon(8, new CoreItemBuilder(Material.TRIPWIRE_HOOK).name("Next Page").build());
		tp.setAction(8, new ClickHandler() {
			
			@Override
			public void click(ItemBarMenu m, ItemStack is, User u) {
				Core.getCore().getMenuHandler().closeMenu(u);
				Core.getCore().getMenuHandler().openMenu(u, "Teleporter2");
			}
		});
		
		/************************************************/
		
		final ItemBarMenu tp2 = new CoreItemBarMenu("Teleporter2");
		tp2.setIcon(0, new CoreItemBuilder(Material.BARRIER).name("Back").lore("Eine Seite zurück").build());
		tp2.setAction(0, new ClickHandler() {
			
			@Override
			public void click(ItemBarMenu m, ItemStack is, User u) {
				Core.getCore().getMenuHandler().closeMenu(u);
				Core.getCore().getMenuHandler().openMenu(u, "Teleporter");
			}
		});
		
		tp2.setIcon(1, new CoreItemBuilder(Material.WOOD_SWORD).name("Survival Games").build());
		tp2.setAction(1, new ClickHandler() {
			
			@Override
			public void click(ItemBarMenu m, ItemStack is, User u) {
				
			}
		});
		
		tp2.setIcon(2, new CoreItemBuilder(Material.SKULL_ITEM).data(2).durability(2).name("Infected").build());
		tp2.setAction(2, new ClickHandler() {
			
			@Override
			public void click(ItemBarMenu m, ItemStack is, User u) {
				
			}
		});
		
		tp2.setIcon(3, new CoreItemBuilder(Material.STAINED_CLAY).name("BuildMyThing").build());
		tp2.setAction(3, new ClickHandler() {
			
			@Override
			public void click(ItemBarMenu m, ItemStack is, User u) {
				
			}
		});
		
		tp2.setIcon(4, new CoreItemBuilder(Material.BED).name("BedWars").build());
		tp2.setAction(4, new ClickHandler() {
			
			@Override
			public void click(ItemBarMenu m, ItemStack is, User u) {
				
			}
		});
		
		/*********************************************/
		
		Core.getCore().getMenuHandler().addMenu(hub);
		Core.getCore().getMenuHandler().addMenu(hub2);
		Core.getCore().getMenuHandler().addMenu(tp);
		Core.getCore().getMenuHandler().addMenu(tp2);
	}
}
