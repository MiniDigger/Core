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

import org.bukkit.ChatColor;
import org.bukkit.WeatherType;
import org.bukkit.event.EventHandler;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Game.Game;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.Util.EntityUtil.Type;

import me.MiniDigger.CraftCore.Event.Events.CoreUserJoinGameEvent;
import me.MiniDigger.CraftCore.Feature.Features.ClearInvFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedFoodFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedHealthFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedTimeFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedWeatherFeature;
import me.MiniDigger.CraftCore.Feature.Features.MapFeature;
import me.MiniDigger.CraftCore.Feature.Features.MobFeature;
import me.MiniDigger.CraftCore.Feature.Features.PvPFeature;
import me.MiniDigger.CraftCore.Feature.Features.SpawnFeature;
import me.MiniDigger.CraftCore.Phase.CorePhase;

import mkremins.fanciful.FancyMessage;

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
		addFeature(new FixedTimeFeature(this, 12000));
		addFeature(new FixedWeatherFeature(this, WeatherType.CLEAR));
		addFeature(new MapFeature(this, getGame().getGameData("Lobby")));
		addFeature(new SpawnFeature(this, true));
		addFeature(new PvPFeature(this, false));
	}
	
	@Override
	public void startPhase() {
		getGame().broadCastMessage(Prefix.getByGameType(getGame().getType()).getPrefix().then("Die Lobby Phase hat begonnen.").color(ChatColor.GOLD));
		final int needed = players - getGame().getPlayers().size();
		if (needed > 0) {
			final FancyMessage msg = Prefix.getByGameType(getGame().getType()).getPrefix().then("Es werden noch ").color(ChatColor.GOLD).then("" + needed)
			        .color(ChatColor.YELLOW).then(" Spieler zum starten ben").color(ChatColor.GOLD).then("\u00f6").color(ChatColor.GOLD).then("tigt.")
			        .color(ChatColor.GOLD);
			getGame().broadCastMessage(msg);
		} else {
			endPhase();
		}
		super.startPhase();
	}
	
	@Override
	public Phase getNextPhase() {
		return next;
	}
	
	@Override
	public void endPhase() {
		getGame().broadCastMessage(Prefix.getByGameType(getGame().getType()).getPrefix().then("Es sind genügend Spieler da").color(ChatColor.GOLD));
		getGame().broadCastMessage(Prefix.getByGameType(getGame().getType()).getPrefix().then("Das Spiel kann nun starten!").color(ChatColor.GOLD));
		super.endPhase();
	}
	
	@Override
	public String getName() {
		return "Lobby";
	}
	
	@Override
	public String getBarMessage() {
		return ChatColor.RED + "" + ChatColor.MAGIC + "||" + ChatColor.RESET + "" + ChatColor.GOLD + getName() + ChatColor.RED + "" + ChatColor.MAGIC + "||";
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
			getGame().broadCastMessage(
			        Prefix.getByGameType(getGame().getType()).getPrefix().then(event.getUser().getDisplayName() + " ist dem Spiel beigetreten").color(ChatColor.GOLD));
			final int needed = players - getGame().getPlayers().size();
			getGame().broadCastMessage(
			        Prefix.getByGameType(getGame().getType()).getPrefix().then("Es werden noch ").color(ChatColor.GOLD).then("" + needed).color(ChatColor.YELLOW)
			                .then(" Spieler zum starten benötigt."));
			if (game.getPlayers().size() >= players) {
				endPhase();
			}
		}
	}
	
}
