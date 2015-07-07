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
package me.MiniDigger.CraftCore.Phase.Phases;

import org.bukkit.WeatherType;
import org.bukkit.event.EventHandler;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Game.Game;
import me.MiniDigger.Core.Lang.LangKeyType;
import me.MiniDigger.Core.Lang.MsgType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Util.EntityUtil.Type;

import me.MiniDigger.CraftCore.Event.Events.CoreUserJoinGameEvent;
import me.MiniDigger.CraftCore.Feature.Features.ClearInvFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedFoodFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedHealthFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedTimeFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedWeatherFeature;
import me.MiniDigger.CraftCore.Feature.Features.LadderKingFeature;
import me.MiniDigger.CraftCore.Feature.Features.LobbyFeature;
import me.MiniDigger.CraftCore.Feature.Features.MapFeature;
import me.MiniDigger.CraftCore.Feature.Features.MobFeature;
import me.MiniDigger.CraftCore.Feature.Features.NoDropFeature;
import me.MiniDigger.CraftCore.Feature.Features.NoPickupFeature;
import me.MiniDigger.CraftCore.Feature.Features.PvPFeature;
import me.MiniDigger.CraftCore.Feature.Features.SpawnFeature;
import me.MiniDigger.CraftCore.Phase.CorePhase;

public class LobbyPhase extends CorePhase {
	
	private int	players;
	
	public LobbyPhase(final Game game, final Phase nextPhase, final int players) {
		super(game, nextPhase);
		this.players = players;
		init();
	}
	
	public int getPlayers() {
		return players;
	}
	
	public void setPlayers(final int players) {
		this.players = players;
	}
	
	@Override
	public void init() {
		addFeature(new FixedHealthFeature(this));
		addFeature(new ClearInvFeature(this, false));
		addFeature(new FixedFoodFeature(this));
		addFeature(new MobFeature(this, Core.getCore().getEntityUtil().getAll(Type.OTHER)));
		addFeature(new FixedTimeFeature(this, 6000));
		addFeature(new FixedWeatherFeature(this, WeatherType.CLEAR));
		addFeature(new MapFeature(this, getGame().getGameData("Lobby"), false));
		addFeature(new SpawnFeature(this, true));
		addFeature(new PvPFeature(this, false));
		addFeature(new LobbyFeature(this));
		addFeature(new NoPickupFeature(this));
		addFeature(new NoDropFeature(this));
		addFeature(new LadderKingFeature(this));
	}
	
	@Override
	public void startPhase() {
		getGame().broadCastMessage(LangKeyType.Game.LOBBY_START1, MsgType.IMPORTANT);
		final int needed = players - getGame().getPlayers().size();
		if (needed > 0) {
			getGame().broadCastMessage(LangKeyType.Game.LOBBY_START2, MsgType.IMPORTANT, needed + "");
		} else {
			endPhase();
		}
		
		getGame().setAllowJoin(true);
		getGame().setAllowSpectate(true);
		
		super.startPhase();
	}
	
	@Override
	public Phase getNextPhase() {
		return next;
	}
	
	@Override
	public void endPhase() {
		getGame().broadCastMessage(LangKeyType.Game.LOBBY_STOP1, MsgType.IMPORTANT);
		getGame().broadCastMessage(LangKeyType.Game.LOBBY_STOP2, MsgType.IMPORTANT);
		super.endPhase();
	}
	
	@Override
	public String getName() {
		return "Lobby";
	}
	
	@Override
	public boolean displayBar() {
		return true;
	}
	
	@Override
	public boolean displayLevel() {
		return false;
	}
	
	@Override
	public Game getGame() {
		return game;
	}
	
	@EventHandler
	public void onUserJoinGame(final CoreUserJoinGameEvent event) {
		if (game.getIdentifier().equals(event.getGame().getIdentifier())) {
			getGame().broadCastMessage(LangKeyType.Game.LOBBY_START3, MsgType.IMPORTANT, event.getUser().getDisplayName());
			final int needed = players - getGame().getPlayers().size();
			getGame().broadCastMessage(LangKeyType.Game.LOBBY_START2, MsgType.IMPORTANT, needed + "");
			if (game.getPlayers().size() >= players) {
				endPhase();
			}
		}
	}
	
}
