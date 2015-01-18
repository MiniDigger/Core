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
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.Scoreboard.Scoreboard;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Event.Events.CoreUserDeathEvent;
import me.MiniDigger.CraftCore.Feature.CoreFeature;
import me.MiniDigger.CraftCore.Scoreboard.CoreScoreboardLine;
import me.MiniDigger.CraftCore.Scoreboard.CoreScoreboardTitle;

public class LastManStandingFeature extends CoreFeature {
	
	public LastManStandingFeature(final Phase phase) {
		super(phase);
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.LASTMANSTANDING;
	}
	
	@Override
	public List<FeatureType> getDependencies() {
		final List<FeatureType> result = new ArrayList<>();
		result.add(FeatureType.PVP);
		return result;
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
		showLives();
	}
	
	@Override
	public void end() {
		Core.getCore().getScoreboardHandler().clearAll();
	}
	
	private void modBoard(final Scoreboard board) {
		board.clear(DisplaySlot.SIDEBAR);
		board.setTitle(new CoreScoreboardTitle(ChatColor.GOLD + "Noch da", DisplaySlot.SIDEBAR));
		
		int i = 0;
		for (final UUID id : getPhase().getGame().getPlayers()) {
			final User u = Core.getCore().getUserHandler().get(id);
			board.addLine(new CoreScoreboardLine(i, u.getDisplayName(), DisplaySlot.SIDEBAR));
			i++;
		}
	}
	
	public void showLives() {
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
		if (e.getGame() != null && e.getGame().getIdentifier().equals(getPhase().getGame().getIdentifier())) {
			e.setShouldRespawn(false);
			final MapData map = Core.getCore().getMapHandler().getMap(getPhase().getGame().getGameData("Lobby"));
			final HashMap<String, Location> locs = map.getLocs(DyeColor.RED);
			final Location loc = locs.get(locs.keySet().iterator().next());
			e.getUser().getPlayer().teleport(loc);
			
			getPhase().getGame().leave(e.getUser());
			
			if (getPhase().getGame().getPlayers().size() < 2) {
				Bukkit.getScheduler().runTaskLater(Core.getCore().getInstance(), new Runnable() {
					
					@Override
					public void run() {
						try {
							getPhase().getGame().end(Core.getCore().getUserHandler().get(getPhase().getGame().getPlayers().get(0)));
						} catch (final Exception ex) {
							getPhase().getGame().end((User) null);
						}
					}
				}, 10);// till respawn is finished
			} else {
				getPhase().getGame().broadCastMessage(
				        Prefix.getByGameType(getPhase().getGame().getType()).getPrefix().then("Es sind noch ").color(ChatColor.AQUA)
				                .then(getPhase().getGame().getPlayers().size() + "").color(ChatColor.BLUE).then(" Spieler am leben!").color(ChatColor.AQUA));
			}
		}
		showLives();
	}
}
