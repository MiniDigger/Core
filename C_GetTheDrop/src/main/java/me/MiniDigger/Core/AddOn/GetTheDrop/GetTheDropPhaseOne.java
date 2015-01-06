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
package me.MiniDigger.Core.AddOn.GetTheDrop;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.WeatherType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.DisplaySlot;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Game.Game;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Scoreboard.Scoreboard;
import me.MiniDigger.Core.Util.EntityUtil.Type;

import me.MiniDigger.CraftCore.Feature.Features.ClearInvFeature;
import me.MiniDigger.CraftCore.Feature.Features.DropFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedFoodFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedHealthFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedTimeFeature;
import me.MiniDigger.CraftCore.Feature.Features.FixedWeatherFeature;
import me.MiniDigger.CraftCore.Feature.Features.MapFeature;
import me.MiniDigger.CraftCore.Feature.Features.MobFeature;
import me.MiniDigger.CraftCore.Feature.Features.PvPFeature;
import me.MiniDigger.CraftCore.Feature.Features.SpawnFeature;
import me.MiniDigger.CraftCore.Feature.Features.SpecateFeature;
import me.MiniDigger.CraftCore.Phase.CoreTimedPhase;
import me.MiniDigger.CraftCore.Scoreboard.CoreScoreboard;
import me.MiniDigger.CraftCore.Scoreboard.CoreScoreboardLine;

public class GetTheDropPhaseOne extends CoreTimedPhase {
	
	private BukkitTask	       task;
	private Map<UUID, Integer>	drops	= new HashMap<UUID, Integer>();
	
	public GetTheDropPhaseOne(final Game game, final Phase next, final int secs) {
		super(game, next, secs);
		init();
	}
	
	@Override
	public String getName() {
		return "GetTheDropOne";
	}
	
	@Override
	public String getBarMessage() {
		return "GetTheDrops!";
	}
	
	@Override
	public boolean displayBar() {
		return true;
	}
	
	@Override
	public boolean displayLevel() {
		return true;
	}
	
	@Override
	public void init() {
		addFeature(new FixedHealthFeature(this));
		addFeature(new ClearInvFeature(this, false));
		addFeature(new FixedFoodFeature(this));
		addFeature(new MobFeature(this, Core.getCore().getEntityUtil().getAll(Type.OTHER, Type.CART, Type.PROJECTILE, Type.UTILITY)));
		addFeature(new FixedTimeFeature(this, 12000));
		addFeature(new FixedWeatherFeature(this, WeatherType.CLEAR));
		addFeature(new DropFeature(this));
		addFeature(new MapFeature(this, "GTD_Arena"));
		addFeature(new PvPFeature(this, false));
		addFeature(new SpawnFeature(this, false));
		addFeature(new SpecateFeature(this));
	}
	
	@Override
	public void endPhase() {
		getGame().broadCastMessage(game.getPrefix().then("Die Phase ist vorbei!").color(ChatColor.AQUA));
		final String winner = getGame().getGameData("VoteWinner");
		Core.getCore().getWorldHandler().copyWorld(winner);
		Core.getCore().getWorldHandler().loadWorld(winner);
		
		if (task != null) {
			task.cancel();
			task = null;
		}
		
		Core.getCore().getScoreboardHandler().clearAll();
		
		super.endPhase();
	}
	
	@Override
	public void startPhase() {
		getGame().broadCastMessage(game.getPrefix().then("Die Erste Phase hat begonnen!").color(ChatColor.AQUA));
		getGame().broadCastMessage(game.getPrefix().then("Sammle deine Items und crafte deine Waffen!").color(ChatColor.AQUA));
		Core.getCore().getWorldHandler().copyWorld("GTD_Arena");
		Core.getCore().getWorldHandler().loadWorld("GTD_Arena");
		
		scoreboard();
		
		super.startPhase();
	}
	
	public void scoreboard() {
		if (task != null) {
			task.cancel();
			task = null;
		}
		final Scoreboard b = new CoreScoreboard(ChatColor.GOLD + "Items");
		
		task = new BukkitRunnable() {
			
			@Override
			public void run() {
				for (UUID id : drops.keySet()) {
					try {
						b.addLine(new CoreScoreboardLine(drops.get(id).intValue(), Core.getCore().getUserHandler().get(id).getDisplayName(), DisplaySlot.SIDEBAR));
					} catch (Exception ex) {
						
					}
				}
			}
		}.runTaskTimer(Core.getCore().getInstance(), 20, 20);
		
		for (UUID id : getGame().getPlayers()) {
			try {
				Core.getCore().getScoreboardHandler().addToPlayer(b, Bukkit.getPlayer(id));
			} catch (Exception ex) {
				
			}
		}
	}
	
	@EventHandler
	public void onPickup(PlayerPickupItemEvent e) {
		if (getGame().getPlayers().contains(e.getPlayer().getUniqueId())) {
			int i = 0;
			if (drops.containsKey(e.getPlayer().getUniqueId())) {
				i = drops.remove(e.getPlayer().getUniqueId());
			}
			i++;
			drops.put(e.getPlayer().getUniqueId(), i);
		}
	}
	
	@Override
	public void tick(final int secsPassed, final int secsToGo) {
		final DropFeature drop = (DropFeature) getFeature(FeatureType.DROP);
		drop.drop(false);
		super.tick(secsPassed, secsToGo);
	}
}
