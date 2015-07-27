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
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Game.GameType;
import me.MiniDigger.Core.Lang.LogLevel;
import me.MiniDigger.Core.Menu.ItemBarMenu;
import me.MiniDigger.Core.Menu.ItemBarMenu.ClickHandler;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.Scoreboard.Scoreboard;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Event.Events.CoreUserJoinGameEvent;
import me.MiniDigger.CraftCore.Event.Events.CoreUserLeaveGameEvent;
import me.MiniDigger.CraftCore.Feature.CoreFeature;
import me.MiniDigger.CraftCore.Item.CoreItemBuilder;
import me.MiniDigger.CraftCore.Lang._;
import me.MiniDigger.CraftCore.Menu.CoreItemBarMenu;
import me.MiniDigger.CraftCore.Scoreboard.CoreScoreboardLine;
import me.MiniDigger.CraftCore.Scoreboard.CoreScoreboardTitle;

public class HubFeature extends CoreFeature {
	
	private final HashMap<UUID, Long>	cooldowns	= new HashMap<>();
	private boolean						event		= false;
	private final List<UUID>			eventlist	= new ArrayList<>();
	private List<UUID>					hide		= new ArrayList<>();
	
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
			u.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999999, 2));
		}
		showBoard();
	}
	
	private void modBoard(final Scoreboard board) {
		board.clear(DisplaySlot.SIDEBAR);
		board.setTitle(new CoreScoreboardTitle(ChatColor.GOLD + "" + ChatColor.BOLD + "" + ChatColor.UNDERLINE + "Hub", DisplaySlot.SIDEBAR));
		
		board.addLine(new CoreScoreboardLine(7, ChatColor.GOLD + "Der Server befindet", DisplaySlot.SIDEBAR));
		board.addLine(new CoreScoreboardLine(6, ChatColor.GOLD + "sich aktuell noch", DisplaySlot.SIDEBAR));
		board.addLine(new CoreScoreboardLine(5, ChatColor.GOLD + "in der Alpha-Phase!", DisplaySlot.SIDEBAR));
		board.addLine(new CoreScoreboardLine(4, ChatColor.GOLD + "", DisplaySlot.SIDEBAR));
		board.addLine(new CoreScoreboardLine(3, ChatColor.GOLD + "" + ChatColor.BOLD + "" + ChatColor.UNDERLINE + "Bug gefunden?", DisplaySlot.SIDEBAR));
		board.addLine(new CoreScoreboardLine(2, ChatColor.GOLD + "Schick eine kurze", DisplaySlot.SIDEBAR));
		board.addLine(new CoreScoreboardLine(1, ChatColor.GOLD + "E-Mail an:", DisplaySlot.SIDEBAR));
		board.addLine(new CoreScoreboardLine(0, ChatColor.GOLD + "bugs@minidigger.me", DisplaySlot.SIDEBAR));
	}
	
	public void showBoard() {
		final List<UUID> retry = new ArrayList<UUID>();
		
		Core.getCore().getTaskHandler().runTask(new BukkitRunnable() {
			
			@Override
			public void run() {
				
				for (final UUID uuid : getPhase().getGame().getPlayers()) {
					if (Bukkit.getPlayer(uuid) == null) {
						retry.add(uuid);
						continue;
					}
					modBoard(Core.getCore().getScoreboardHandler().getBoard(uuid));
					Core.getCore().getScoreboardHandler().update(uuid);
				}
			}
		}, getPhase());
		
		Core.getCore().getTaskHandler().runTaskLater(new BukkitRunnable() {
			
			@Override
			public void run() {
				for (final UUID uuid : retry) {
					if (Bukkit.getPlayer(uuid) == null) {
						continue;// Fuck you
					}
					modBoard(Core.getCore().getScoreboardHandler().getBoard(uuid));
					Core.getCore().getScoreboardHandler().update(uuid);
				}
			}
		}, 20, getPhase());// WAit for respawn
	}
	
	@Override
	public void end() {
		for (final UUID uuid : getPhase().getGame().getPlayers()) {
			if (Bukkit.getPlayer(uuid) == null) {
				continue;// Fuck you
			}
			Core.getCore().getScoreboardHandler().getBoard(uuid).clear();
			Core.getCore().getScoreboardHandler().update(uuid);
		}
	}
	
	@EventHandler
	public void onPlayerJoin(final CoreUserJoinGameEvent e) {
		if (e.getGame().getIdentifier().equals(getPhase().getGame().getIdentifier())) {
			Core.getCore().getMenuHandler().openMenu(e.getUser(), "Hub");
			e.getUser().getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999999, 2));
		}
		showBoard();
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				for (UUID id : hide) {
					Player p = Bukkit.getPlayer(id);
					if (p != null) {
						p.showPlayer(e.getUser().getPlayer());
					}
				}
			}
		}.runTaskLater(Core.getCore().getInstance(), 15);
	}
	
	@EventHandler
	public void onGameModeChange(final PlayerGameModeChangeEvent e) {
		final User u = Core.getCore().getUserHandler().get(e.getPlayer().getUniqueId());
		if (getPhase().getGame().getPlayers().contains(u.getUUID())) {
			if (e.getNewGameMode() == GameMode.SURVIVAL) {
				Core.getCore().getMenuHandler().openMenu(u, "Hub");
				e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999999, 2));
			} else {
				Core.getCore().getMenuHandler().closeMenu(u);
			}
		}
	}
	
	@EventHandler
	public void onLeave(final CoreUserLeaveGameEvent e) {
		if (e.getGame().getIdentifier() == getPhase().getGame().getIdentifier()) {
			eventlist.remove(e.getUser().getUUID());
		}
	}
	
	@Command(name = "event", permission = "event", usage = "")
	public void event(final CommandArgs args) {
		if (event) {
			getPhase().getGame().broadCastMessage(Prefix.API.getPrefix().then("Es werden keine Spieler mehr reingelassen, nächstes mal musst du schneller sein!"));
			
			for (final UUID id : getPhase().getGame().getPlayers()) {
				final Player p = Core.getCore().getUserHandler().get(id).getPlayer();
				Core.getCore().getTitleHandler().sendTitle(p, 1 * 20, 150, 1 * 20, ChatColor.GOLD + "" + ChatColor.BOLD + "Event ist voll");
				Core.getCore().getTitleHandler().sendSubTitle(p, 1 * 20, 150, 1 * 20,
				        ChatColor.GOLD + "" + ChatColor.BOLD + "Das nächste mal musst du wohl schneller sein ;D");
			}
		} else {
			getPhase().getGame().broadCastMessage(Prefix.API.getPrefix().then("Ein Event wurde gestartet, begib dich zum Sammelpunkt!"));
			
			for (final UUID id : getPhase().getGame().getPlayers()) {
				final Player p = Core.getCore().getUserHandler().get(id).getPlayer();
				Core.getCore().getTitleHandler().sendTitle(p, 1 * 20, 150, 1 * 20, ChatColor.GOLD + "" + ChatColor.BOLD + "Event");
				Core.getCore().getTitleHandler().sendSubTitle(p, 1 * 20, 150, 1 * 20, ChatColor.GOLD + "" + ChatColor.BOLD + "Begib dich zum Sammelpunkt");
			}
		}
		event = !event;
	}
	
	@Command(name = "eventp", permission = "eventtp", usage = "", description = "Teleportiert alle Spieler im Event zum Server")
	public void eventtp(final CommandArgs args) {
		for (final UUID id : eventlist) {
			final User u = Core.getCore().getUserHandler().get(id);
			if (u == null) {
				continue;
			}
			
			Core.getCore().getServerHandler().connect(u, "event1");
		}
	}
	
	public void menu() {
		final ItemBarMenu hub = new CoreItemBarMenu("Hub");
		
		hub.setIcon(0, new CoreItemBuilder(Material.SKULL_ITEM).name(ChatColor.RED + "Spieler verstecken").lore("Benutze dieses Item").lore("um alle anderen")
		        .lore("Spieler zu verstecken").data(3).durability(3).build());
		hub.setAction(0, new ClickHandler() {
			
			final int cooldownTime = 5;
			
			@Override
			public void click(final ItemBarMenu m, final ItemStack is, final User u, final Entity entity) {
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
				hide.add(u.getUUID());
				Prefix.API.getPrefix().then("Alle anderen Spieler sind nun ").color(ChatColor.GREEN).then("unsichtbar!").color(ChatColor.GOLD).send(u.getPlayer());
				Core.getCore().getMenuHandler().closeMenu(u);
				Core.getCore().getMenuHandler().openMenu(u, "Hub2");
			}
		});
		
		hub.setIcon(1, new CoreItemBuilder(Material.COMPASS).name("Teleporter").lore("Öffnet den Teleporter").lore("Mit diesem kannst du").lore("zu jedem Spielmodi")
		        .lore("hinteleportieren!").build());
		hub.setAction(1, new ClickHandler() {
			
			@Override
			public void click(final ItemBarMenu m, final ItemStack is, final User u, final Entity entity) {
				Core.getCore().getMenuHandler().closeMenu(u);
				Core.getCore().getMenuHandler().openMenu(u, "Teleporter");
			}
		});
		
		hub.setIcon(2, new CoreItemBuilder(Material.FEATHER).name("Fly umschalten").lore("Klicke um den Fly Modus umzuschalten").build());
		hub.setAction(2, new ClickHandler() {
			
			@Override
			public void click(final ItemBarMenu m, final ItemStack is, final User u, final Entity entity) {
				Bukkit.dispatchCommand(u.getPlayer(), "toggle fly");
			}
		});
		hub.setPermission(2, "fly");
		
		hub.setIcon(3, new CoreItemBuilder(Material.STICK).name("Spieler zum Event porten").lore("Geht nur wenn ein Event am laufen ist ;D").build());
		hub.setAction(3, new ClickHandler() {
			
			@Override
			public void click(final ItemBarMenu m, final ItemStack is, final User u, final Entity entity) {
				if (event) {
					if (entity != null) {
						try {
							entity.teleport(Core.getCore().getMapHandler().getMap("Spawn").getLocs(DyeColor.ORANGE).get("EVENT2"));
							eventlist.add(entity.getUniqueId());
						} catch (final Exception ex) {
							Prefix.API.getPrefix().then("Not setup!").send(u.getPlayer());
						}
					}
				}
			}
		});
		hub.setPermission(3, "tptoevent");
		
		hub.setIcon(4, new CoreItemBuilder(Material.WOOD_DOOR).name("Selber zum Event Porten").lore("Geht nur wenn ein Event am laufen ist ;D").build());
		hub.setAction(4, new ClickHandler() {
			
			@Override
			public void click(final ItemBarMenu m, final ItemStack is, final User u, final Entity entity) {
				if (event) {
					try {
						u.getPlayer().teleport(Core.getCore().getMapHandler().getMap("Spawn").getLocs(DyeColor.ORANGE).get("EVENT2"));
						eventlist.add(u.getUUID());
					} catch (final Exception ex) {
						Prefix.API.getPrefix().then("Not setup!").send(u.getPlayer());
					}
				}
			}
		});
		hub.setPermission(4, "tptoevent");
		
		/*************************************************/
		
		// TODO this is not cool, add an option to change a item in a menu
		final ItemBarMenu hub2 = hub.clone();
		hub2.setName("Hub2");
		hub2.setIcon(0, new CoreItemBuilder(Material.SKULL_ITEM).name(ChatColor.GREEN + "Spieler anzeigen").lore("Benutze dieses Item").lore("um alle anderen")
		        .lore("Spieler anzuzeigen").data(1).durability(1).build());
		hub2.setAction(0, new ClickHandler() {
			
			@Override
			public void click(final ItemBarMenu m, final ItemStack is, final User u, final Entity entity) {
				for (final Player pl : Core.getCore().getUserHandler().getOnlinePlayers()) {
					u.getPlayer().showPlayer(pl);
				}
				hide.remove(u.getUUID());
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
			public void click(final ItemBarMenu m, final ItemStack is, final User u, final Entity entity) {
				Core.getCore().getMenuHandler().closeMenu(u);
				Core.getCore().getMenuHandler().openMenu(u, "Hub");
			}
		});
		
		tp.setIcon(1, new CoreItemBuilder(Material.BOW).name("OneInTheChamber").build());
		tp.setAction(1, new ClickHandler() {
			
			@Override
			public void click(final ItemBarMenu m, final ItemStack is, final User u, final Entity entity) {
				try {
					if (Core.getCore().getGameHandler().isDisabled(GameType.OITC)) {
						Prefix.API.getPrefix().then("Deaktiviert!").send(u.getPlayer());
					} else {
						u.getPlayer().teleport(Core.getCore().getMapHandler().getMap("Spawn").getLocs(DyeColor.ORANGE).get("OITC"));
					}
				} catch (final Exception ex) {
					_.stacktrace(LogLevel.DEBUG, ex);
					Prefix.API.getPrefix().then("Deaktiviert!").send(u.getPlayer());
				}
			}
		});
		
		tp.setIcon(2, new CoreItemBuilder(Material.FIREWORK).name("Event").build());
		tp.setAction(2, new ClickHandler() {
			
			@Override
			public void click(final ItemBarMenu m, final ItemStack is, final User u, final Entity entity) {
				try {
					u.getPlayer().teleport(Core.getCore().getMapHandler().getMap("Spawn").getLocs(DyeColor.ORANGE).get("EVENT"));
				} catch (final Exception ex) {
					_.stacktrace(LogLevel.DEBUG, ex);
					Prefix.API.getPrefix().then("Deaktiviert!").send(u.getPlayer());
				}
			}
		});
		
		tp.setIcon(3, new CoreItemBuilder(Material.CHEST).name("KitPvP").build());
		tp.setAction(3, new ClickHandler() {
			
			@Override
			public void click(final ItemBarMenu m, final ItemStack is, final User u, final Entity entity) {
				try {
					if (Core.getCore().getGameHandler().isDisabled(GameType.KP)) {
						Prefix.API.getPrefix().then("Deaktiviert!").send(u.getPlayer());
					} else {
						u.getPlayer().teleport(Core.getCore().getMapHandler().getMap("Spawn").getLocs(DyeColor.ORANGE).get("KITPVP"));
					}
				} catch (final Exception ex) {
					_.stacktrace(LogLevel.DEBUG, ex);
					Prefix.API.getPrefix().then("Deaktiviert!").send(u.getPlayer());
				}
				
			}
		});
		
		tp.setIcon(4, new CoreItemBuilder(Material.MONSTER_EGG).name("GetTheDrop").build());
		tp.setAction(4, new ClickHandler() {
			
			@Override
			public void click(final ItemBarMenu m, final ItemStack is, final User u, final Entity entity) {
				try {
					if (Core.getCore().getGameHandler().isDisabled(GameType.GETTHEDROP)) {
						Prefix.API.getPrefix().then("Deaktiviert!").send(u.getPlayer());
					} else {
						u.getPlayer().teleport(Core.getCore().getMapHandler().getMap("Spawn").getLocs(DyeColor.ORANGE).get("GETTHEDROP"));
					}
				} catch (final Exception ex) {
					_.stacktrace(LogLevel.DEBUG, ex);
					Prefix.API.getPrefix().then("Deaktiviert!").send(u.getPlayer());
				}
				
			}
		});
		
		tp.setIcon(5, new CoreItemBuilder(Material.GRAVEL).name("GravityKing").build());
		tp.setAction(5, new ClickHandler() {
			
			@Override
			public void click(final ItemBarMenu m, final ItemStack is, final User u, final Entity entity) {
				try {
					if (Core.getCore().getGameHandler().isDisabled(GameType.GK)) {
						Prefix.API.getPrefix().then("Deaktiviert!").send(u.getPlayer());
					} else {
						u.getPlayer().teleport(Core.getCore().getMapHandler().getMap("Spawn").getLocs(DyeColor.ORANGE).get("GK"));
					}
				} catch (final Exception ex) {
					_.stacktrace(LogLevel.DEBUG, ex);
					Prefix.API.getPrefix().then("Deaktiviert!").send(u.getPlayer());
				}
				
			}
		});
		
		tp.setIcon(6, new CoreItemBuilder(Material.TNT).name("Crank").build());
		tp.setAction(6, new ClickHandler() {
			
			@Override
			public void click(final ItemBarMenu m, final ItemStack is, final User u, final Entity entity) {
				try {
					if (Core.getCore().getGameHandler().isDisabled(GameType.CRANK)) {
						Prefix.API.getPrefix().then("Deaktiviert!").send(u.getPlayer());
					} else {
						u.getPlayer().teleport(Core.getCore().getMapHandler().getMap("Spawn").getLocs(DyeColor.ORANGE).get("CRANK"));
					}
				} catch (final Exception ex) {
					_.stacktrace(LogLevel.DEBUG, ex);
					Prefix.API.getPrefix().then("Deaktiviert!").send(u.getPlayer());
				}
				
			}
		});
		
		tp.setIcon(7, new CoreItemBuilder(Material.INK_SACK).data(10).durability(10).name("BuildMyThing").build());
		tp.setAction(7, new ClickHandler() {
			
			@Override
			public void click(final ItemBarMenu m, final ItemStack is, final User u, final Entity entity) {
				try {
					if (Core.getCore().getGameHandler().isDisabled(GameType.BMT)) {
						Prefix.API.getPrefix().then("Deaktiviert!").send(u.getPlayer());
					} else {
						u.getPlayer().teleport(Core.getCore().getMapHandler().getMap("Spawn").getLocs(DyeColor.ORANGE).get("BMT"));
					}
				} catch (final Exception ex) {
					_.stacktrace(LogLevel.DEBUG, ex);
					Prefix.API.getPrefix().then("Deaktiviert!").send(u.getPlayer());
				}
				
			}
		});
		
		tp.setIcon(8, new CoreItemBuilder(Material.TRIPWIRE_HOOK).name("Next Page").build());
		tp.setAction(8, new ClickHandler() {
			
			@Override
			public void click(final ItemBarMenu m, final ItemStack is, final User u, final Entity entity) {
				Core.getCore().getMenuHandler().closeMenu(u);
				Core.getCore().getMenuHandler().openMenu(u, "Teleporter2");
			}
		});
		
		/************************************************/
		
		final ItemBarMenu tp2 = new CoreItemBarMenu("Teleporter2");
		tp2.setIcon(0, new CoreItemBuilder(Material.BARRIER).name("Back").lore("Eine Seite zurück").build());
		tp2.setAction(0, new ClickHandler() {
			
			@Override
			public void click(final ItemBarMenu m, final ItemStack is, final User u, final Entity entity) {
				Core.getCore().getMenuHandler().closeMenu(u);
				Core.getCore().getMenuHandler().openMenu(u, "Teleporter");
			}
		});
		
		tp2.setIcon(1, new CoreItemBuilder(Material.WOOD_SWORD).name("Survival Games").build());
		tp2.setAction(1, new ClickHandler() {
			
			@Override
			public void click(final ItemBarMenu m, final ItemStack is, final User u, final Entity entity) {
				try {
					if (Core.getCore().getGameHandler().isDisabled(GameType.SG)) {
						Prefix.API.getPrefix().then("Deaktiviert!").send(u.getPlayer());
					} else {
						u.getPlayer().teleport(Core.getCore().getMapHandler().getMap("Spawn").getLocs(DyeColor.ORANGE).get("SURVIVALGAMES"));
					}
				} catch (final Exception ex) {
					_.stacktrace(LogLevel.DEBUG, ex);
					Prefix.API.getPrefix().then("Deaktiviert!").send(u.getPlayer());
				}
				
			}
		});
		
		tp2.setIcon(2, new CoreItemBuilder(Material.SKULL_ITEM).data(2).durability(2).name("Infected").build());
		tp2.setAction(2, new ClickHandler() {
			
			@Override
			public void click(final ItemBarMenu m, final ItemStack is, final User u, final Entity entity) {
				try {
					if (Core.getCore().getGameHandler().isDisabled(GameType.IF)) {
						Prefix.API.getPrefix().then("Deaktiviert!").send(u.getPlayer());
					} else {
						u.getPlayer().teleport(Core.getCore().getMapHandler().getMap("Spawn").getLocs(DyeColor.ORANGE).get("INFECTED"));
					}
				} catch (final Exception ex) {
					_.stacktrace(LogLevel.DEBUG, ex);
					Prefix.API.getPrefix().then("Deaktiviert!").send(u.getPlayer());
				}
				
			}
		});
		
		tp2.setIcon(3, new CoreItemBuilder(Material.BED).name("BedWars").build());
		tp2.setAction(3, new ClickHandler() {
			
			@Override
			public void click(final ItemBarMenu m, final ItemStack is, final User u, final Entity entity) {
				try {
					if (Core.getCore().getGameHandler().isDisabled(GameType.BEDWARS)) {
						Prefix.API.getPrefix().then("Deaktiviert!").send(u.getPlayer());
					} else {
						u.getPlayer().teleport(Core.getCore().getMapHandler().getMap("Spawn").getLocs(DyeColor.ORANGE).get("BEDWARS"));
					}
				} catch (final Exception ex) {
					_.stacktrace(LogLevel.DEBUG, ex);
					Prefix.API.getPrefix().then("Deaktiviert!").send(u.getPlayer());
				}
				
			}
		});
		tp2.setIcon(4, new CoreItemBuilder(Material.NETHER_STAR).name("Spawn").build());
		tp2.setAction(4, new ClickHandler() {
			
			@Override
			public void click(final ItemBarMenu m, final ItemStack is, final User u, final Entity entity) {
				Bukkit.dispatchCommand(u.getPlayer(), "hub");
			}
		});
		
		/*********************************************/
		
		Core.getCore().getMenuHandler().addMenu(hub);
		Core.getCore().getMenuHandler().addMenu(hub2);
		Core.getCore().getMenuHandler().addMenu(tp);
		Core.getCore().getMenuHandler().addMenu(tp2);
	}
}
