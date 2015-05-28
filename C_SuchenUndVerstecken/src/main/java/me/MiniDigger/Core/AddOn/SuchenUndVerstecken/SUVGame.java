package me.MiniDigger.Core.AddOn.SuchenUndVerstecken;

import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Game.GameType;
import me.MiniDigger.Core.Lang.LangKeyType;
import me.MiniDigger.Core.Lang.MsgType;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Feature.Features.MapFeature;
import me.MiniDigger.CraftCore.Game.CoreGame;
import me.MiniDigger.CraftCore.Lang._;
import me.MiniDigger.CraftCore.Phase.Phases.GracePhase;
import me.MiniDigger.CraftCore.Phase.Phases.LobbyPhase;
import me.MiniDigger.CraftCore.Phase.Phases.PostPhase;
import me.MiniDigger.CraftCore.Phase.Phases.VotePhase;

public class SUVGame extends CoreGame {
	
	LobbyPhase	lobby;
	VotePhase	vote;
	GracePhase	grace;
	SUVPhase	suv;
	PostPhase	post;
	
	@Override
	public GameType getType() {
		return GameType.OITC;
	}
	
	@Override
	public void init() {
		super.maxplayers = 16;
		
		setGameData("Lobby", "Lobby");
		
		lobby = new LobbyPhase(this, null, 5);
		vote = new VotePhase(this, null, 30);
		grace = new GracePhase(this, null, 15);
		suv = new SUVPhase(this);
		post = new PostPhase(this, 10);
		
		grace.setNextPhase(suv);
		vote.setNextPhase(grace);
		lobby.setNextPhase(vote);
		suv.setNextPhase(post);
		
		((MapFeature) lobby.getFeature(FeatureType.MAP)).setMap("Lobby");
		((MapFeature) vote.getFeature(FeatureType.MAP)).setMap("Lobby");
		((MapFeature) post.getFeature(FeatureType.MAP)).setMap("Lobby");
		
		setPhase(lobby);
		super.init();
	}
	
	@Override
	public void end(final User... winner) {
		if (winner != null && winner.length == 1) {
			final User w = winner[0];
			if (w != null) {
				_.msg(getGamePrefix(), LangKeyType.Game.WIN, MsgType.IMPORTANT, w.getPlayer());
				broadCastMessage(LangKeyType.Game.WON, MsgType.IMPORTANT, w.getDisplayName());
				
				leave(w);
			}
		}
		broadCastMessage(LangKeyType.Game.END, MsgType.IMPORTANT);
		super.end(winner);
	}
	
	@Override
	public void start() {
		super.start();
		
		lobby.init();
		
		getPhase().startPhase();
	}
}
