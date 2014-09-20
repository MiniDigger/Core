package me.MiniDigger.CraftCore.Feature.Features;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Event.Events.UserJoinGameEvent;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.User.User;
import me.MiniDigger.CraftCore.Feature.CoreFeature;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;

public class VoteFeature extends CoreFeature {
	
	public VoteFeature(Phase phase) {
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
		List<FeatureType> result = new ArrayList<>();
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
		
		System.out.println("searching maps for gametype " + getPhase().getGame().getType());
		ArrayList<String> maps = Core.getCore().getMapHandler().loadMapConfig(getPhase().getGame().getType());
		System.out.println(maps.size() + " maps found");
		
		Collections.shuffle(maps);
		try {
			mapOne = maps.get(0);
			mapTwo = maps.get(1);
			mapThree = maps.get(2);
		} catch (Exception ex) {}
		
		if (mapOne == null) {
			mapCount = 0;
		} else if (mapTwo == null) {
			mapCount = 1;
		} else if (mapThree == null) {
			mapCount = 2;
		} else {
			mapCount = 3;
		}
	}
	
	@Override
	public void end() {
		mapOne = null;
		mapTwo = null;
		mapThree = null;
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
	
	public boolean vote(User user, int id) {
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
		
		return true;
	}
	
	public void sendVoteMessage(User user) {
		if (mapCount == 0) {
			user.sendMessage(Prefix.VOTE.getPrefix().then("Keine Map gefunden! Breche ab...").color(ChatColor.RED));
			return;
		}
		
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
		for (UUID id : getPhase().getGame().getPlayers()) {
			sendVoteMessage(Core.getCore().getUserHandler().get(id));
		}
	}
	
	public void announceWinner() {
		for (UUID id : getPhase().getGame().getPlayers()) {
			try {
				User user = Core.getCore().getUserHandler().get(id);
				user.sendMessage(Prefix.VOTE.getPrefix().then("Das Voting wurde beendet!").color(ChatColor.GOLD));
				user.sendMessage(Prefix.VOTE
				        .getPrefix()
				        .then("Es wird auf Map " + Core.getCore().getMapHandler().getName(getWinner()) + " von " + Core.getCore().getMapHandler().getAuthor(getWinner())
				                + " gespielt!").color(ChatColor.GOLD));
			} catch (Exception ex) {}
		}
	}
	
	@EventHandler
	public void onJoin(UserJoinGameEvent e) {
		if (e.getGame().getIdentifier().equals(getPhase().getGame().getIdentifier())) {
			sendVoteMessage(e.getUser());
		}
	}
}