package me.MiniDigger.Core.AddOn.OITC;

import org.bukkit.ChatColor;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Game.GameType;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Feature.Features.MapFeature;
import me.MiniDigger.CraftCore.Game.CoreGame;
import me.MiniDigger.CraftCore.Phase.Phases.GracePhase;
import me.MiniDigger.CraftCore.Phase.Phases.LobbyPhase;
import me.MiniDigger.CraftCore.Phase.Phases.VotePhase;

public class OITCGame extends CoreGame {
	
	LobbyPhase	lobby;
	VotePhase	vote;
	GracePhase	grace;
	OITCPhase	oitc;
	
	@Override
	public GameType getType() {
		return GameType.CRANK;
	}
	
	@Override
	public void init() {
		setGameData("Lobby", "OITC_Lobby");
		
		lobby = new LobbyPhase(this, null, 5);
		vote = new VotePhase(this, null, 30);
		grace = new GracePhase(this, null, 15);
		oitc = new OITCPhase(this);
		
		grace.setNextPhase(oitc);
		vote.setNextPhase(grace);
		lobby.setNextPhase(vote);
		
		((MapFeature) lobby.getFeature(FeatureType.MAP)).setMap("OITC_Lobby");
		((MapFeature) vote.getFeature(FeatureType.MAP)).setMap("OITC_Lobby");
		
		setPhase(lobby);
		super.init();
	}
	
	@Override
	public void end(final User... winner) {
		if (winner != null && winner.length == 1) {
			final User w = winner[0];
			if (w != null) {
				
				w.sendMessage(getPrefix().then("Du hast gewonnen!").color(ChatColor.GOLD));
				Core.getCore()
				        .getInstance()
				        .broadcast(
				                getPrefix().then("Der Spieler ").color(ChatColor.GOLD).then(w.getDisplayName()).color(ChatColor.AQUA).then(" hat gewonnen!")
				                        .color(ChatColor.GOLD));
				leave(w);
			} else {
				System.out.println("winner null");
			}
		} else {
			System.out.println("no winner?! " + winner.length);
		}
		Core.getCore().getInstance().broadcast(getPrefix().then("Das Spiel ist vorbei!").color(ChatColor.GOLD));
		super.end(winner);
	}
	
	@Override
	public void start() {
		super.start();
		
		lobby.init();
		
		getPhase().startPhase();
	}
}
