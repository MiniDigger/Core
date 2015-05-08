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
package me.MiniDigger.CraftCore.Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.scheduler.BukkitRunnable;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Error.Error;
import me.MiniDigger.Core.Feature.Feature;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Game.Game;
import me.MiniDigger.Core.Game.GameType;
import me.MiniDigger.Core.Lang.LangKeyType;
import me.MiniDigger.Core.Lang.MsgType;
import me.MiniDigger.Core.Map.MapData;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Feature.Features.MapFeature;
import me.MiniDigger.CraftCore.Lang._;

import mkremins.fanciful.FancyMessage;

public class CoreGame implements Game {
	
	private final UUID	                  id	     = UUID.randomUUID();
	private final List<UUID>	          users	     = new ArrayList<>();
	private final List<UUID>	          specs	     = new ArrayList<UUID>();
	private final HashMap<String, String>	gameData	= new HashMap<>();
	private Phase	                      phase;
	private GameType	                  type;
	private boolean	                      allowJoin;
	private boolean	                      allowSpectate;
	
	@Override
	public Error join(final User user) {
		if (users.contains(user.getUUID())) {
			return Error.USER_ALLREADY_JOINED;
		} else {
			users.add(user.getUUID());
			return Error.NO_ERROR;
		}
	}
	
	@Override
	public Error leave(final User user) {
		Core.getCore().getBarHandler().removeBar(user.getPlayer());
		user.getPlayer().setLevel(0);
		user.getPlayer().setExp(0);
		if (users.contains(user.getUUID())) {
			users.remove(user.getUUID());
			return Error.NO_ERROR;
		} else {
			return Error.USER_NOT_JOINED;
		}
	}
	
	@Override
	public void start() {
	}
	
	@Override
	public UUID getIdentifier() {
		return id;
	}
	
	@Override
	public List<UUID> getPlayers() {
		return users;
	}
	
	@Override
	public void broadCastMessage(final FancyMessage msg) {
		for (final UUID id : users) {
			Core.getCore().getUserHandler().get(id).sendMessage(msg);
		}
		for (final UUID id : specs) {
			Core.getCore().getUserHandler().get(id).sendMessage(msg);
		}
	}
	
	@Override
	public void broadCastMessage(final LangKeyType type, final MsgType msg, final String... args) {
		for (final UUID id : users) {
			if (Bukkit.getPlayer(id) != null) {
				_.msg(getGamePrefix(), type, msg, Bukkit.getPlayer(id), args);
			}
		}
		for (final UUID id : specs) {
			if (Bukkit.getPlayer(id) != null) {
				_.msg(getGamePrefix(), type, msg, Bukkit.getPlayer(id), args);
			}
		}
	}
	
	@Override
	public void broadCastSound(final Sound sound, final float volume, final float pitch) {
		for (final UUID id : users) {
			Bukkit.getPlayer(id).playSound(Bukkit.getPlayer(id).getLocation(), sound, volume, pitch);
		}
		for (final UUID id : specs) {
			Bukkit.getPlayer(id).playSound(Bukkit.getPlayer(id).getLocation(), sound, volume, pitch);
		}
	}
	
	@Override
	public void broadCastSoundAtLocation(final Sound sound, final float volume, final float pitch, final Location loc) {
		for (final UUID id : users) {
			Bukkit.getPlayer(id).playSound(loc, sound, volume, pitch);
		}
		for (final UUID id : specs) {
			Bukkit.getPlayer(id).playSound(loc, sound, volume, pitch);
		}
	}
	
	@Override
	public FancyMessage getPrefix() {
		return Prefix.getByGameType(getType()).getPrefix();
	}
	
	@Override
	public Prefix getGamePrefix() {
		return Prefix.getByGameType(getType());
	}
	
	@Override
	public void init() {
	}
	
	@Override
	public Phase getPhase() {
		return phase;
	}
	
	@Override
	public void setPhase(final Phase nextPhase) {
		phase = nextPhase;
	}
	
	@Override
	public GameType getType() {
		return type;
	}
	
	@Override
	public boolean allowJoin() {
		return allowJoin;
	}
	
	@Override
	public boolean allowSpectate() {
		return allowSpectate;
	}
	
	@Override
	public void setAllowJoin(final boolean allow) {
		allowJoin = allow;
	}
	
	@Override
	public void setAllowSpectate(final boolean allow) {
		allowSpectate = allow;
	}
	
	@Override
	public String getGameData(final String key) {
		return gameData.get(key);
	}
	
	@Override
	public void setGameData(final String key, final String data) {
		if (gameData.containsKey(key)) {
			gameData.remove(gameData.get(key));
		}
		gameData.put(key, data);
	}
	
	@Override
	public void end(final User... winner) {
		final MapData lobby = Core.getCore().getMapHandler().getMap(getGameData("Lobby"));
		final Location loc = lobby.getLocs(DyeColor.RED).values().iterator().next();
		for (final User w : winner) {
			if (w != null) {
				w.getPlayer().teleport(loc);
			}
		}
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				for (final UUID w : specs) {
					final Player p = Bukkit.getPlayer(w);
					p.getInventory().clear();
					if (p != null && !p.getLocation().getWorld().getName().equalsIgnoreCase(loc.getWorld().getName())) {
						p.teleport(loc);
					}
				}
				
				for (final UUID w : users) {
					final Player p = Bukkit.getPlayer(w);
					p.getInventory().clear();
					if (p != null && !p.getLocation().getWorld().getName().equalsIgnoreCase(loc.getWorld().getName())) {
						p.teleport(loc);
					}
				}
			}
		}.runTaskLater(Core.getCore().getInstance(), 10);// Wait for respawn
		
		Core.getCore().getGameHandler().removeGame(this);
		HandlerList.unregisterAll(getPhase());
		Core.getCore().getCommandHandler().unregisterCommands(getPhase());
		
		((MapFeature) getPhase().getFeature(FeatureType.MAP)).unload();
		
		for (final Feature f : getPhase().getFeatures()) {
			HandlerList.unregisterAll(f);
			Core.getCore().getCommandHandler().unregisterCommands(f);
			f.end();
		}
		
		if (Core.getCore().getGameHandler().getMainGame().equals(this)) {
			Core.getCore().getShutdownUtil().doShutdown();
		}
		
		try {
			finalize();
		} catch (final Throwable e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void addSpec(final UUID id) {
		if (!specs.contains(id)) {
			specs.add(id);
		}
	}
	
	@Override
	public void remSpec(final UUID id) {
		specs.remove(id);
	}
	
	@Override
	public List<UUID> getSpecs() {
		return specs;
	}
	
}
