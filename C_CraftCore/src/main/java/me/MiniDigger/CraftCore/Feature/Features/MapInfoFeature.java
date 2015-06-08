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
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Chat.ChatChars;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Game.GameType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Scoreboard.Scoreboard;

import me.MiniDigger.CraftCore.Event.Events.CoreUserJoinGameEvent;
import me.MiniDigger.CraftCore.Event.Events.CoreUserLeaveGameEvent;
import me.MiniDigger.CraftCore.Feature.CoreFeature;
import me.MiniDigger.CraftCore.Scoreboard.CoreScoreboardLine;
import me.MiniDigger.CraftCore.Scoreboard.CoreScoreboardTitle;

public class MapInfoFeature extends CoreFeature {
	
	public MapInfoFeature(final Phase phase) {
		super(phase);
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.MAPINFO;
	}
	
	@Override
	public List<FeatureType> getDependencies() {
		final List<FeatureType> result = new ArrayList<FeatureType>();
		result.add(FeatureType.MAP);
		return result;
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
		final List<UUID> retry = new ArrayList<UUID>();
		
		for (final UUID id : getPhase().getGame().getPlayers()) {
			try {
				modBoard(Core.getCore().getScoreboardHandler().getBoard(id));
				Core.getCore().getScoreboardHandler().update(id);
			} catch (final Exception ex) {
				retry.add(id);
			}
		}
		Core.getCore().getTaskHandler().runTaskLater(new BukkitRunnable() {
			
			@Override
			public void run() {
				for (final UUID id : retry) {
					try {
						modBoard(Core.getCore().getScoreboardHandler().getBoard(id));
						Core.getCore().getScoreboardHandler().update(id);
					} catch (final Exception ex) {
						retry.add(id);
					}
				}
			}
		}, 10, getPhase());
		
	}
	
	@EventHandler
	public void onQuit(final CoreUserLeaveGameEvent e) {
		if (e.getGame().getIdentifier() == getPhase().getGame().getIdentifier()) {
			Core.getCore().getScoreboardHandler().getBoard(e.getUser().getUUID()).clear();
		}
	}
	
	@EventHandler
	public void onUserJoin(final CoreUserJoinGameEvent e) {
		if (e.getGame().getIdentifier() == getPhase().getGame().getIdentifier()) {
			modBoard(Core.getCore().getScoreboardHandler().getBoard(e.getUser().getUUID()));
			Core.getCore().getScoreboardHandler().update(e.getUser().getUUID());
		}
	}
	
	private void modBoard(final Scoreboard b) {
		b.clear(DisplaySlot.SIDEBAR);
		b.setTitle(new CoreScoreboardTitle(ChatColor.GOLD + "MapInfo", DisplaySlot.SIDEBAR));
		
		final MapFeature m = (MapFeature) getPhase().getFeature(FeatureType.MAP);
		int i = 1;
		
		for (final GameType t : Core.getCore().getMapHandler().getGameTypes(m.getMap().getName())) {
			b.addLine(new CoreScoreboardLine(i, ChatColor.AQUA + ChatChars.Misc.bullet + " " + t.getName(), DisplaySlot.SIDEBAR));
			i++;
		}
		
		b.addLine(new CoreScoreboardLine(i, ChatColor.GOLD + "GameTypes: ", DisplaySlot.SIDEBAR));
		i++;
		b.addLine(new CoreScoreboardLine(i, ChatColor.AQUA + ChatChars.Misc.bullet + " " + Core.getCore().getMapHandler().getAuthor(m.getMap().getName()),
		        DisplaySlot.SIDEBAR));
		i++;
		b.addLine(new CoreScoreboardLine(i, ChatColor.GOLD + "Author: ", DisplaySlot.SIDEBAR));
		i++;
		b.addLine(new CoreScoreboardLine(i, ChatColor.AQUA + ChatChars.Misc.bullet + " " + Core.getCore().getMapHandler().getName(m.getMap().getName()),
		        DisplaySlot.SIDEBAR));
		i++;
		b.addLine(new CoreScoreboardLine(i, ChatColor.GOLD + "Name: ", DisplaySlot.SIDEBAR));
	}
	
	@Override
	public void end() {
		
	}
	
}
