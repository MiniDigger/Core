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
 */
package me.MiniDigger.Core.AddOn.BedWars;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Game.GameType;
import me.MiniDigger.Core.Team.Team;
import me.MiniDigger.Core.User.User;
import me.MiniDigger.CraftCore.Feature.Features.MapFeature;
import me.MiniDigger.CraftCore.Feature.Features.TeamFeature;
import me.MiniDigger.CraftCore.Feature.Features.TeamSelectFeature;
import me.MiniDigger.CraftCore.Game.CoreGame;
import me.MiniDigger.CraftCore.Phase.Phases.LobbyPhase;
import me.MiniDigger.CraftCore.Phase.Phases.VotePhase;

import org.bukkit.ChatColor;

public class BedWarsGame extends CoreGame {
	
	LobbyPhase	 lobby;
	VotePhase	 vote;
	BedWarsPhase	bw;
	
	@Override
	public GameType getType() {
		return GameType.BEDWARS;
	}
	
	@Override
	public void init() {
		setGameData("Lobby", "BW_Lobby");
		
		lobby = new LobbyPhase(this, null, 4);
		vote = new VotePhase(this, null, 60);
		bw = new BedWarsPhase(this, null);
		
		vote.addFeature(new TeamSelectFeature(vote, bw, 4, 4));
		
		vote.setNextPhase(bw);
		lobby.setNextPhase(vote);
		
		((MapFeature) lobby.getFeature(FeatureType.MAP)).setMap("BW_Lobby");
		((MapFeature) vote.getFeature(FeatureType.MAP)).setMap("BW_Lobby");
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
		} else if (winner.length > 1) {
			for (final User w : winner) {
				w.sendMessage(getPrefix().then("Dein Team hat gewonnen!").color(ChatColor.GOLD));
			}
			final Team t = ((TeamFeature) getPhase().getFeature(FeatureType.TEAM)).getTeam(winner[0]);
			Core.getCore().getInstance().broadcast(getPrefix().then("Das Team ").color(ChatColor.AQUA).then(t.getName()).then(" hat gewonnen").color(ChatColor.AQUA));
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
