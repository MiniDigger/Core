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
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		User user = Core.getCore().getUserHandler().get(e.getPlayer().getUniqueId());
		if (Core.getCore().getGameHandler().getMainGame() != null && Core.getCore().getGameHandler().getMainGame().getType() != GameType.NOTHING) {
			if (!Core.getCore().getGameHandler().isMainGameStarted()) {
				Core.getCore().getGameHandler().setMainGameStarted(true);
				Core.getCore().getGameHandler().getMainGame().init();
				Core.getCore().getGameHandler().getMainGame().start();
			}
			CoreUserJoinGameEvent event = new CoreUserJoinGameEvent(Core.getCore().getGameHandler().getMainGame(), user);
			Bukkit.getPluginManager().callEvent(event);
			Core.getCore().getGameHandler().getMainGame().join(user);
		}
		e.setJoinMessage(null);
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		User user = Core.getCore().getUserHandler().get(e.getPlayer().getUniqueId());
		List<Game> games = Core.getCore().getGameHandler().getGames(user);
		if (games != null && games.size() != 0) {
			for (Game game : games) {
				CoreUserLeaveGameEvent event = new CoreUserLeaveGameEvent(game, user);
				Bukkit.getPluginManager().callEvent(event);
				game.leave(user);
			}
		}
		
	}
	
	private HashMap<UUID, UUID>	lastDamaged	= new HashMap<>();
	
	@EventHandler
	public void onPlayerDamage(EntityDamageEvent e) {
		if (e.getEntityType() == EntityType.PLAYER) {
			User user = Core.getCore().getUserHandler().get(((Player) e.getEntity()).getUniqueId());
			
			User damager = null;
			if (lastDamaged.containsKey(user.getUUID())) {
				damager = Core.getCore().getUserHandler().get(lastDamaged.get(user.getUUID()));
			}
			
			for (Game game : Core.getCore().getGameHandler().getGames(user)) {
				CoreUserDamageEvent event = new CoreUserDamageEvent(e.getDamage(), damager, user, game);
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
	
	@EventHandler
	public void onPlayerDamageByPlayer(EntityDamageByEntityEvent e) {
		if (e.getEntityType() == EntityType.PLAYER) {
			User user = Core.getCore().getUserHandler().get(((Player) e.getEntity()).getUniqueId());
			
			User damager = null;
			if (e.getDamager().getType() == EntityType.PLAYER) {
				damager = Core.getCore().getUserHandler().get(((Player) e.getDamager()).getUniqueId());
			} else if (lastDamaged.containsKey(user.getUUID())) {
				damager = Core.getCore().getUserHandler().get(lastDamaged.get(user.getUUID()));
			}
			
			for (Game game : Core.getCore().getGameHandler().getGames(user)) {
				CoreUserDamageEvent event = new CoreUserDamageEvent(e.getDamage(), damager, user, game);
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
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		// resets
		e.setDeathMessage(null);
		e.setDroppedExp(0);
		e.setKeepLevel(false);
		e.setNewExp(0);
		e.setNewLevel(0);
		e.setNewTotalExp(0);
		
		User user = Core.getCore().getUserHandler().get(e.getEntity().getUniqueId());
		
		User damager = null;
		if (e.getEntity().getKiller() != null) {
			damager = Core.getCore().getUserHandler().get(e.getEntity().getKiller().getUniqueId());
		} else if (lastDamaged.containsKey(user.getUUID())) {
			damager = Core.getCore().getUserHandler().get(lastDamaged.get(user.getUUID()));
		}
		
		for (Game game : Core.getCore().getGameHandler().getGames(user)) {
			CoreUserDeathEvent event = new CoreUserDeathEvent(user, damager, game, true, true);
			Bukkit.getPluginManager().callEvent(event);
			if (!event.keepDrops()) {
				e.getDrops().clear();
			}
		}
	}
}
