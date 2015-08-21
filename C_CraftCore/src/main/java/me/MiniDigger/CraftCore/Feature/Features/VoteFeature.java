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
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.Scoreboard.Scoreboard;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Event.Events.CoreUserJoinGameEvent;
import me.MiniDigger.CraftCore.Feature.CoreFeature;
import me.MiniDigger.CraftCore.Scoreboard.CoreScoreboard;
import me.MiniDigger.CraftCore.Scoreboard.CoreScoreboardLine;
import me.MiniDigger.CraftCore.Scoreboard.CoreScoreboardTitle;

public class VoteFeature extends CoreFeature {
	
	public VoteFeature(final Phase phase) {
		super(phase);
	}
	
	private String	        mapOne;
	private String	        mapTwo;
	private String	        mapThree;
	
	private int	            votesOne;
	private int	            votesTwo;
	private int	            votesThree;
	
	private int	            mapCount;
	
	private ArrayList<UUID>	voted;
	
	@Override
	public FeatureType getType() {
		return FeatureType.VOTE;
	}
	
	@Override
	public List<FeatureType> getDependencies() {
		final List<FeatureType> result = new ArrayList<>();
		result.add(FeatureType.MAP);
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
		voted = new ArrayList<>();
		
		Core.getCore().getInstance().debug("searching maps for gametype " + getPhase().getGame().getType());
		final ArrayList<String> maps = Core.getCore().getMapHandler().loadMapConfig(getPhase().getGame().getType());
		Core.getCore().getInstance().debug(maps.size() + " maps found");
		
		Collections.shuffle(maps);
		try {
			mapOne = maps.get(0);
			mapTwo = maps.get(1);
			mapThree = maps.get(2);
		} catch (final Exception ex) {}
		
		modBoard(new CoreScoreboard());// Cause the map count
		
		sendVoteMessages();
	}
	
	@EventHandler
	public void onUserJoin(final CoreUserJoinGameEvent e) {
		if (e.getGame().getIdentifier() == getPhase().getGame().getIdentifier()) {
			sendVoteMessage(e.getUser());
		}
	}
	
	private void modBoard(final Scoreboard board) {
		board.clear(DisplaySlot.SIDEBAR);
		board.setTitle(new CoreScoreboardTitle(ChatColor.GOLD + "Votes", DisplaySlot.SIDEBAR));
		
		if (mapOne == null) {
			mapCount = 0;
		} else if (mapTwo == null) {
			board.addLine(new CoreScoreboardLine(2, Core.getCore().getMapHandler().getName(mapOne), DisplaySlot.SIDEBAR));
			board.addLine(new CoreScoreboardLine(1, votesOne + " Vote" + (votesOne != 1 ? "s" : "") + ChatColor.AQUA, DisplaySlot.SIDEBAR));
			mapCount = 1;
		} else if (mapThree == null) {
			board.addLine(new CoreScoreboardLine(4, Core.getCore().getMapHandler().getName(mapOne), DisplaySlot.SIDEBAR));
			board.addLine(new CoreScoreboardLine(3, votesOne + " Vote" + (votesOne != 1 ? "s" : "") + ChatColor.AQUA, DisplaySlot.SIDEBAR));
			board.addLine(new CoreScoreboardLine(2, Core.getCore().getMapHandler().getName(mapTwo), DisplaySlot.SIDEBAR));
			board.addLine(new CoreScoreboardLine(1, votesTwo + " Vote" + (votesTwo != 1 ? "s" : "") + ChatColor.BLACK, DisplaySlot.SIDEBAR));
			mapCount = 2;
		} else {
			board.addLine(new CoreScoreboardLine(6, Core.getCore().getMapHandler().getName(mapOne), DisplaySlot.SIDEBAR));
			board.addLine(new CoreScoreboardLine(5, votesOne + " Vote" + (votesOne != 1 ? "s" : ""), DisplaySlot.SIDEBAR));
			board.addLine(new CoreScoreboardLine(4, Core.getCore().getMapHandler().getName(mapTwo), DisplaySlot.SIDEBAR));
			board.addLine(new CoreScoreboardLine(3, votesTwo + " Vote" + (votesTwo != 1 ? "s " : " ") + ChatColor.BLACK, DisplaySlot.SIDEBAR));
			board.addLine(new CoreScoreboardLine(2, Core.getCore().getMapHandler().getName(mapThree), DisplaySlot.SIDEBAR));
			board.addLine(new CoreScoreboardLine(1, votesThree + " Vote" + (votesThree != 1 ? "s  " : "  ") + ChatColor.AQUA, DisplaySlot.SIDEBAR));
			mapCount = 3;
		}
	}
	
	@Override
	public void end() {
		mapOne = null;
		mapTwo = null;
		mapThree = null;
		Core.getCore().getScoreboardHandler().clearAll(DisplaySlot.SIDEBAR);
	}
	
	public String getWinner() {
		if (votesThree >= votesTwo && votesThree >= votesOne && votesThree != 0) {
			return mapThree;
		} else if (votesTwo >= votesOne && votesTwo >= votesThree && votesTwo != 0) {
			return mapTwo;
		} else {
			return mapOne;
		}
	}
	
	public boolean vote(final User user, final int id) {
		if (id > mapCount) {
			return false;
		}
		
		if (voted.contains(user.getUUID())) {
			return false;
		}
		
		voted.add(user.getUUID());
		
		switch (id) {
		case 3:
			votesThree++;
			break;
		case 2:
			votesTwo++;
			break;
		case 1:
			votesOne++;
			break;
		default:
			return false;
		}
		
		Core.getCore().getTaskHandler().runTask(new BukkitRunnable() {
			
			@Override
			public void run() {
				
				for (final UUID uuid : getPhase().getGame().getPlayers()) {
					modBoard(Core.getCore().getScoreboardHandler().getBoard(uuid));
					Core.getCore().getScoreboardHandler().update(uuid);
				}
			}
		}, getPhase());
		
		return true;
	}
	
	public void sendVoteMessage(final User user) {
		if (mapCount == 0) {
			user.sendMessage(Prefix.VOTE.getPrefix().then("Keine Map gefunden! Breche ab...").color(ChatColor.RED));
			return;
		}
		
		modBoard(Core.getCore().getScoreboardHandler().getBoard(user.getUUID()));
		Core.getCore().getScoreboardHandler().update(user.getUUID());
		
		user.sendMessage(Prefix.VOTE.getPrefix().then("========").color(ChatColor.GOLD).then("Voting").color(ChatColor.YELLOW).then("========").color(ChatColor.GOLD));
		user.sendMessage(Prefix.VOTE.getPrefix().then("Klicke auf die Map für die du abstimmen willst!").color(ChatColor.YELLOW));
		
		switch (mapCount) {
		case 3:
			user.sendMessage(Prefix.VOTE.getPrefix().then("#3").color(ChatColor.GRAY).then(" " + Core.getCore().getMapHandler().getName(mapThree)).color(ChatColor.GOLD)
			        .command("/vote 3").then(" by " + Core.getCore().getMapHandler().getAuthor(mapThree)).command("/vote 3").color(ChatColor.YELLOW));
		case 2:
			user.sendMessage(Prefix.VOTE.getPrefix().then("#2").color(ChatColor.GRAY).then(" " + Core.getCore().getMapHandler().getName(mapTwo)).color(ChatColor.GOLD)
			        .command("/vote 2").then(" by " + Core.getCore().getMapHandler().getAuthor(mapTwo)).command("/vote 2").color(ChatColor.YELLOW));
		case 1:
			user.sendMessage(Prefix.VOTE.getPrefix().then("#1").color(ChatColor.GRAY).then(" " + Core.getCore().getMapHandler().getName(mapOne)).color(ChatColor.GOLD)
			        .command("/vote 1").then(" by " + Core.getCore().getMapHandler().getAuthor(mapOne)).command("/vote 1").color(ChatColor.YELLOW));
			break;
		default:
			user.sendMessage(Prefix.VOTE.getPrefix().then("Keine Map gefunden! Breche ab...").color(ChatColor.RED));
			return;
		}
		
		user.sendMessage(Prefix.VOTE.getPrefix().then("Klicke auf die Map für die du abstimmen willst!").color(ChatColor.YELLOW));
		user.sendMessage(Prefix.VOTE.getPrefix().then("========").color(ChatColor.GOLD).then("Voting").color(ChatColor.YELLOW).then("========").color(ChatColor.GOLD));
		
	}
	
	public void sendVoteMessages() {
		for (final UUID id : getPhase().getGame().getPlayers()) {
			sendVoteMessage(Core.getCore().getUserHandler().get(id));
		}
	}
	
	public void announceWinner() {
		for (final UUID id : getPhase().getGame().getPlayers()) {
			try {
				final User user = Core.getCore().getUserHandler().get(id);
				user.sendMessage(Prefix.VOTE.getPrefix().then("Das Voting wurde beendet!").color(ChatColor.GOLD));
				user.sendMessage(Prefix.VOTE
				        .getPrefix()
				        .then("Es wird auf Map " + Core.getCore().getMapHandler().getName(getWinner()) + " von " + Core.getCore().getMapHandler().getAuthor(getWinner())
				                + " gespielt!").color(ChatColor.GOLD));
			} catch (final Exception ex) {}
		}
	}
	
	@EventHandler
	public void onJoin(final CoreUserJoinGameEvent e) {
		if (e.getGame().getIdentifier().equals(getPhase().getGame().getIdentifier())) {
			sendVoteMessage(e.getUser());
		}
	}
	
	public int getMapCount() {
		return mapCount;
	}
}
