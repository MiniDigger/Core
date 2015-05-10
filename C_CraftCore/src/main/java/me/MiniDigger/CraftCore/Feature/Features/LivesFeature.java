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
package me.MiniDigger.CraftCore.Feature.Features;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Map.MapData;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Scoreboard.Scoreboard;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Event.Events.CoreUserDeathEvent;
import me.MiniDigger.CraftCore.Event.Events.CoreUserJoinGameEvent;
import me.MiniDigger.CraftCore.Event.Events.CoreUserLeaveGameEvent;
import me.MiniDigger.CraftCore.Feature.CoreFeature;
import me.MiniDigger.CraftCore.Scoreboard.CoreScoreboardLine;
import me.MiniDigger.CraftCore.Scoreboard.CoreScoreboardTitle;

public class LivesFeature extends CoreFeature {
	
	private HashMap<UUID, Integer>	lives	= new HashMap<UUID, Integer>();
	private final int	           max;
	
	public LivesFeature(final Phase phase, final int lives) {
		super(phase);
		max = lives;
	}
	
	public int getLives(final UUID id) {
		return lives.get(id) == null ? -1 : lives.get(id);
	}
	
	public int getMaxLives() {
		return max;
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.LIVES;
	}
	
	@Override
	public List<FeatureType> getDependencies() {
		return new ArrayList<FeatureType>();
	}
	
	@Override
	public List<FeatureType> getSoftDependencies() {
		return new ArrayList<FeatureType>();
	}
	
	@Override
	public List<FeatureType> getIncompabilities() {
		return new ArrayList<FeatureType>();
	}
	
	@Override
	public void start() {
		for (final UUID id : getPhase().getGame().getPlayers()) {
			lives.put(id, max);
		}
		
		showLives();
	}
	
	@Override
	public void end() {
		lives = null;
	}
	
	private void modBoard(final Scoreboard board) {
		board.clear(DisplaySlot.SIDEBAR);
		board.setTitle(new CoreScoreboardTitle(ChatColor.GOLD + "Lives", DisplaySlot.SIDEBAR));
		
		for (final UUID id : lives.keySet()) {
			final User u = Core.getCore().getUserHandler().get(id);
			if (lives.get(id) != 0) {
				board.addLine(new CoreScoreboardLine(lives.get(id), u.getDisplayName(), DisplaySlot.SIDEBAR));
			}
		}
	}
	
	@EventHandler
	public void onQuit(CoreUserLeaveGameEvent e) {
		if (e.getGame().getIdentifier() == getPhase().getGame().getIdentifier()) {
			lives.remove(e.getUser().getUUID());
			getPhase().getGame().broadCastMessage(
			        getPhase().getGame().getPrefix().then("Der Spieler ").color(ChatColor.AQUA).then(e.getUser().getDisplayName()).color(ChatColor.BLUE)
			                .then(" ist draußen!").color(ChatColor.AQUA));
			
			Core.getCore().getScoreboardHandler().getBoard(e.getUser().getUUID()).clear();
			
			if (getPhase().getGame().getPlayers().size() < 2) {
				try {
					getPhase().getGame().end(Core.getCore().getUserHandler().get(getPhase().getGame().getPlayers().get(0)));
				} catch (final Exception ex) {
					getPhase().getGame().end(new User[] { null });
				}
			}
		}
	}
	
	@EventHandler
	public void onUserJoin(CoreUserJoinGameEvent e) {
		if (e.getGame().getIdentifier() == getPhase().getGame().getIdentifier()) {
			lives.put(e.getUser().getUUID(), max);
			showLives();
		}
	}
	
	public void showLives() {
		if (lives == null) {
			return; // Games over, who cares
		}
		
		final List<UUID> retry = new ArrayList<UUID>();
		
		new BukkitRunnable() {
			
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
		}.runTask(Core.getCore().getInstance());
		
		new BukkitRunnable() {
			
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
		}.runTaskLater(Core.getCore().getInstance(), 20);// WAit for respawn
	}
	
	@EventHandler
	public void onDeath(final CoreUserDeathEvent e) {
		if (getPhase().getGame().getPlayers().contains(e.getUser().getUUID())) {
			final int lives = this.lives.remove(e.getUser().getUUID());
			if (lives == 1) {
				this.lives.put(e.getUser().getUUID(), 0);
				
				e.setShouldRespawn(false);
				final MapData map = Core.getCore().getMapHandler().getMap(getPhase().getGame().getGameData("Lobby"));
				final HashMap<String, Location> locs = map.getLocs(DyeColor.RED);
				final Location loc = locs.get(locs.keySet().iterator().next());
				e.getUser().getPlayer().teleport(loc);
				
				new BukkitRunnable() {
					
					@Override
					public void run() {
						e.getUser().getPlayer().teleport(loc);
					}
				}.runTaskLater(Core.getCore().getInstance(), 10);
				
				getPhase().getGame().broadCastMessage(
				        getPhase().getGame().getPrefix().then("Der Spieler ").color(ChatColor.AQUA).then(e.getUser().getDisplayName()).color(ChatColor.BLUE)
				                .then(" ist draußen!").color(ChatColor.AQUA));
				
				getPhase().getGame().leave(e.getUser());
				
				Core.getCore().getScoreboardHandler().getBoard(e.getUser().getUUID()).clear();
				
				if (getPhase().getGame().getPlayers().size() < 2) {
					try {
						getPhase().getGame().end(Core.getCore().getUserHandler().get(getPhase().getGame().getPlayers().get(0)));
					} catch (final Exception ex) {
						getPhase().getGame().end(new User[] { null });
					}
				}
			} else {
				this.lives.put(e.getUser().getUUID(), lives - 1);
			}
			
			showLives();
		}
	}
}
