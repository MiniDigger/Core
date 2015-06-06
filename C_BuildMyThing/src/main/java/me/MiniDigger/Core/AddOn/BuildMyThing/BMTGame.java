package me.MiniDigger.Core.AddOn.BuildMyThing;

import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Game.GameType;
import me.MiniDigger.Core.Lang.LangKeyType;
import me.MiniDigger.Core.Lang.MsgType;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Feature.Features.MapFeature;
import me.MiniDigger.CraftCore.Game.CoreGame;
import me.MiniDigger.CraftCore.Lang._;
import me.MiniDigger.CraftCore.Phase.Phases.LobbyPhase;
import me.MiniDigger.CraftCore.Phase.Phases.PostPhase;

public class BMTGame extends CoreGame {
	
	LobbyPhase	lobby;
	BMTPhase	btm;
	PostPhase	post;
	
	public PostPhase getPost(){
		return post;
	}
	
	@Override
	public GameType getType() {
		return GameType.BMT;
	}
	
	@Override
	public void init() {
		super.maxplayers = 16;
		
		setGameData("Lobby", "Lobby");
		
		lobby = new LobbyPhase(this, null, 5);
		btm = new BMTPhase(this);
		post = new PostPhase(this, 10);
		
		lobby.setNextPhase(btm);
		btm.setNextPhase(btm);
		
		((MapFeature) lobby.getFeature(FeatureType.MAP)).setMap("Lobby");
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
