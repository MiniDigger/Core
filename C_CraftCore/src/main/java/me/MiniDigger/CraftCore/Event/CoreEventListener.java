package me.MiniDigger.CraftCore.Event;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Event.EventListener;
import me.MiniDigger.Core.Game.Game;
import me.MiniDigger.Core.Game.GameType;
import me.MiniDigger.Core.User.User;
import me.MiniDigger.CraftCore.Event.Events.CoreUserDamageEvent;
import me.MiniDigger.CraftCore.Event.Events.CoreUserDeathEvent;
import me.MiniDigger.CraftCore.Event.Events.CoreUserJoinGameEvent;
import me.MiniDigger.CraftCore.Event.Events.CoreUserLeaveGameEvent;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class CoreEventListener implements EventListener {
	
	@Override
	@EventHandler
	public void onPlayerJoin(final PlayerJoinEvent e) {
		final User user = Core.getCore().getUserHandler().get(e.getPlayer().getUniqueId());
		
		e.setJoinMessage(null);
		Core.getCore().getCommonMethods().printJoinMessage(user);
		
		if (Core.getCore().getGameHandler().getMainGame() != null && Core.getCore().getGameHandler().getMainGame().getType() != GameType.NOTHING) {
			final CoreUserJoinGameEvent event = new CoreUserJoinGameEvent(Core.getCore().getGameHandler().getMainGame(), user);
			Bukkit.getPluginManager().callEvent(event);
			Core.getCore().getGameHandler().getMainGame().join(user);
			
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
				final CoreUserLeaveGameEvent event = new CoreUserLeaveGameEvent(game, user);
				Bukkit.getPluginManager().callEvent(event);
				game.leave(user);
			}
		}
		
	}
	
	private final HashMap<UUID, UUID>	lastDamaged	= new HashMap<>();
	
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
}
