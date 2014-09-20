package me.MiniDigger.Core.AddOn.GetTheDrop;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Game.GameType;
import me.MiniDigger.Core.User.User;
import me.MiniDigger.CraftCore.Feature.Features.MapFeature;
import me.MiniDigger.CraftCore.Game.CoreGame;
import me.MiniDigger.CraftCore.Phase.Phases.CountdownPhase;
import me.MiniDigger.CraftCore.Phase.Phases.GracePhase;
import me.MiniDigger.CraftCore.Phase.Phases.LobbyPhase;
import me.MiniDigger.CraftCore.Phase.Phases.VotePhase;

import org.bukkit.ChatColor;

public class GetTheDropGame extends CoreGame {
	
	LobbyPhase	       lobby;
	VotePhase	       vote;
	CountdownPhase	   countdown;
	GetTheDropPhaseOne	one;
	GracePhase	       grace;
	GetTheDropPhaseTwo	two;
	
	@Override
	public GameType getType() {
		return GameType.GETTHEDROP;
	}
	
	@Override
	public void init() {
		setGameData("Lobby", "GTD_Lobby");
		
		lobby = new LobbyPhase(this, null, 5);
		vote = new VotePhase(this, null, 30);
		countdown = new CountdownPhase(this, null, 15);
		one = new GetTheDropPhaseOne(this, null, 90);
		grace = new GracePhase(this, null, 15);
		two = new GetTheDropPhaseTwo(this, null, 60);
		
		two.setNextPhase(two);
		grace.setNextPhase(two);
		one.setNextPhase(grace);
		countdown.setNextPhase(one);
		vote.setNextPhase(countdown);
		lobby.setNextPhase(vote);
		
		((MapFeature) lobby.getFeature(FeatureType.MAP)).setMap("GTD_Lobby");
		((MapFeature) vote.getFeature(FeatureType.MAP)).setMap("GTD_Lobby");
		((MapFeature) countdown.getFeature(FeatureType.MAP)).setMap("GTD_Arena");
		((MapFeature) one.getFeature(FeatureType.MAP)).setMap("GTD_Arena");
		
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
		Core.getCore().getShutdownUtil().doShutdown();
	}
	
	@Override
	public void start() {
		super.start();
		
		lobby.init();
		
		getPhase().startPhase();
	}
	
}
