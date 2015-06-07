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
package me.MiniDigger.CraftCore.Event;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Event.EventListener;
import me.MiniDigger.Core.Game.Game;
import me.MiniDigger.Core.Game.GameType;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Event.Events.CoreUserDamageEvent;
import me.MiniDigger.CraftCore.Event.Events.CoreUserDeathEvent;

public class CoreEventListener implements EventListener {
	
	@Override
	@EventHandler
	public void onPlayerJoin(final PlayerJoinEvent e) {
		final User user = Core.getCore().getUserHandler().get(e.getPlayer().getUniqueId());
		
		e.setJoinMessage(null);
		Core.getCore().getPlayerUtil().prepare(e.getPlayer());
		Core.getCore().getCommonMethods().printJoinMessage(user);
		Core.getCore().getScoreboardHandler().update(e.getPlayer().getUniqueId());
		
		if (Core.getCore().getGameHandler().getMainGame() != null && Core.getCore().getGameHandler().getMainGame().getType() != GameType.NOTHING) {
			Core.getCore().getGameHandler().joinGame(user, Core.getCore().getGameHandler().getMainGame());
			
			if (!Core.getCore().getGameHandler().isMainGameStarted()) {
				Core.getCore().getGameHandler().setMainGameStarted(true);
				Core.getCore().getGameHandler().getMainGame().init();
				Core.getCore().getGameHandler().getMainGame().start();
			}
		}
	}
	
	@Override
	@EventHandler
	public void onPlayerQuit(final PlayerQuitEvent e) {
		final User user = Core.getCore().getUserHandler().get(e.getPlayer().getUniqueId());
		final List<Game> games = Core.getCore().getGameHandler().getGames(user);
		if (games != null && games.size() != 0) {
			for (final Game game : games) {
				Core.getCore().getGameHandler().leaveGame(user, game);
			}
		}
		e.setQuitMessage(null);
	}
	
	private static final HashMap<UUID, UUID>	lastDamaged	= new HashMap<>();
	
	public static void clearLastDmg(final UUID id) {
		lastDamaged.remove(id);
	}
	
	@Override
	@EventHandler
	public void onPlayerDamage(final EntityDamageEvent e) {
		if (e.getEntityType() == EntityType.PLAYER) {
			final User user = Core.getCore().getUserHandler().get(((Player) e.getEntity()).getUniqueId());
			
			User damager = null;
			if (lastDamaged.containsKey(user.getUUID())) {
				damager = Core.getCore().getUserHandler().get(lastDamaged.get(user.getUUID()));
			}
			
			for (final Game game : Core.getCore().getGameHandler().getGames(user)) {
				final CoreUserDamageEvent event = new CoreUserDamageEvent(e.getDamage(), damager, user, game, e.isCancelled());
				Bukkit.getPluginManager().callEvent(event);
				System.out.println("c " + event.isCancelled());
				e.setDamage(event.getDmg());
				e.setCancelled(event.isCancelled());
				if (!event.isCancelled()) {
					System.out.println(";(");
					lastDamaged.remove(user.getUUID());
					if (damager != null) {
						lastDamaged.put(user.getUUID(), damager.getUUID());
					}
				}
			}
		}
	}
	
	@Override
	@EventHandler
	public void onPlayerDamageByPlayer(final EntityDamageByEntityEvent e) {
		if (e.getEntityType() == EntityType.PLAYER) {
			final User user = Core.getCore().getUserHandler().get(((Player) e.getEntity()).getUniqueId());
			
			User damager = null;
			if (e.getDamager().getType() == EntityType.PLAYER) {
				damager = Core.getCore().getUserHandler().get(((Player) e.getDamager()).getUniqueId());
			} else if (lastDamaged.containsKey(user.getUUID())) {
				damager = Core.getCore().getUserHandler().get(lastDamaged.get(user.getUUID()));
			}
			
			for (final Game game : Core.getCore().getGameHandler().getGames(user)) {
				final CoreUserDamageEvent event = new CoreUserDamageEvent(e.getDamage(), damager, user, game, e.isCancelled());
				Bukkit.getPluginManager().callEvent(event);
				e.setCancelled(event.isCancelled());
				if (!event.isCancelled()) {
					e.setDamage(event.getDmg());
					lastDamaged.remove(user.getUUID());
					if (damager != null) {
						lastDamaged.put(user.getUUID(), damager.getUUID());
					}
				}
			}
		}
	}
	
	@Override
	@EventHandler
	public void onPlayerDeath(final PlayerDeathEvent e) {
		// resets
		e.setDeathMessage(null);
		e.setDroppedExp(0);
		e.setKeepLevel(false);
		e.setNewExp(0);
		e.setNewLevel(0);
		e.setNewTotalExp(0);
		
		final User user = Core.getCore().getUserHandler().get(e.getEntity().getUniqueId());
		
		User damager = null;
		if (e.getEntity().getKiller() != null) {
			damager = Core.getCore().getUserHandler().get(e.getEntity().getKiller().getUniqueId());
		} else if (lastDamaged.containsKey(user.getUUID())) {
			damager = Core.getCore().getUserHandler().get(lastDamaged.get(user.getUUID()));
		}
		
		for (final Game game : Core.getCore().getGameHandler().getGames(user)) {
			final CoreUserDeathEvent event = new CoreUserDeathEvent(user, damager, game, true, true);
			Bukkit.getPluginManager().callEvent(event);
			if (!event.keepDrops()) {
				e.getDrops().clear();
			}
		}
	}
	
	@Override
	@EventHandler
	public void onPlayerMove(final PlayerMoveEvent e) {
		// Don't fly thru barriers
		if (e.getPlayer().getGameMode() == GameMode.SPECTATOR) {
			if (e.getTo().getBlock().getType() == Material.BARRIER) {
				e.setCancelled(true);
			}
		}
	}
}
