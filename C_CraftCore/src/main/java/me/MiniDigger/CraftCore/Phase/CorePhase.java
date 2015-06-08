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
package me.MiniDigger.CraftCore.Phase;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.HandlerList;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Command.Completer;
import me.MiniDigger.Core.Feature.Feature;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Game.Game;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.Tasks.Task;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Feature.Features.JoinHandlerFeature;
import me.MiniDigger.CraftCore.Feature.Features.LeaveHandlerFeature;

public abstract class CorePhase implements Phase {
	
	protected Game	        game;
	protected Phase	        next;
	protected List<Feature>	features	= new ArrayList<>();
	
	public CorePhase(final Game game, final Phase next) {
		this();
		this.game = game;
		this.next = next;
	}
	
	public CorePhase() {
		addFeature(new LeaveHandlerFeature(this));
		addFeature(new JoinHandlerFeature(this));
	}
	
	@Override
	public String getBarMessage() {
		return ChatColor.RED + "" + ChatColor.MAGIC + "||" + ChatColor.RESET + "" + ChatColor.GOLD + getName() + ChatColor.RED + "" + ChatColor.MAGIC + "||";
	}
	
	@Override
	public void startPhase() {
		if (!checkCombability()) {
			System.out.println("Incombability found!");
			return;
		}
		for (final Feature f : features) {
			f.init(this);
		}
		
		Bukkit.getServer().getPluginManager().registerEvents(this, Core.getCore().getInstance());
		Core.getCore().getCommandHandler().registerCommands(this);
		for (final Feature f : features) {
			Bukkit.getServer().getPluginManager().registerEvents(f, Core.getCore().getInstance());
			Core.getCore().getCommandHandler().registerCommands(f);
			f.start();
		}
	}
	
	@Override
	public Phase getNextPhase() {
		return next;
	}
	
	@Override
	public void endPhase() {
		for (Task t : Core.getCore().getTaskHandler().getTaskByPhase(this)) {
			Core.getCore().getTaskHandler().cancel(t);
		}
		
		HandlerList.unregisterAll(this);
		Core.getCore().getCommandHandler().unregisterCommands(this);
		
		if (getNextPhase() != null) {
			getNextPhase().init();
		}
		
		for (final Feature f : features) {
			HandlerList.unregisterAll(f);
			Core.getCore().getCommandHandler().unregisterCommands(f);
			f.end();
		}
		
		getGame().setPhase(getNextPhase());
		if (getNextPhase() != null) {
			getNextPhase().startPhase();
		}
		
		try {
			finalize();
		} catch (final Throwable e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Game getGame() {
		return game;
	}
	
	@Override
	public void setNextPhase(final Phase next) {
		this.next = next;
	}
	
	@Override
	public List<Feature> getFeatures() {
		return features;
	}
	
	@Override
	public boolean checkCombability() {
		final List<FeatureType> fType = new ArrayList<>();
		for (final Feature f : features) {
			fType.add(f.getType());
		}
		
		for (final Feature f : features) {
			for (final FeatureType s : f.getDependencies()) {
				if (!fType.contains(s)) {
					Core.getCore().getInstance()
					        .info("Dependency " + s + " for Feature " + f.getType() + " in Game " + getGame().getIdentifier().toString() + " is missing!");
					return false;
				}
			}
			
			for (final FeatureType s : f.getIncompabilities()) {
				if (fType.contains(s)) {
					Core.getCore().getInstance()
					        .error("Dependency " + s + " is incompatible with Feature " + f.getType() + " in Game " + getGame().getIdentifier().toString() + "!");
					return false;
				}
			}
		}
		
		return true;
	}
	
	@Override
	public Feature getFeature(final Class<Feature> clazz) {
		for (final Feature feature : features) {
			if (feature.getClass().getCanonicalName().equals(clazz.getCanonicalName())) {
				return feature;
			}
		}
		return null;
	}
	
	@Override
	public void addFeature(final Feature f) {
		if (getFeature(f.getType()) == null) {
			features.add(f);
		}
	}
	
	@Override
	public void remFeature(final FeatureType t) {
		if (getFeature(t) != null) {
			features.remove(getFeature(t));
		}
	}
	
	@Override
	public Feature getFeature(final FeatureType feature) {
		for (final Feature f : features) {
			if (f.getType().equals(feature)) {
				return f;
			}
		}
		return null;
	}
	
	@Command(name = "skip", description = "überspringt eine Phase", usage = "", permission = "skip", max = 0, sync = true)
	public void skip(final CommandArgs args) {
		Bukkit.getScheduler().runTask(Core.getCore().getInstance(), new Runnable() {
			
			@Override
			public void run() {
				getGame().broadCastMessage(Prefix.API.getPrefix().then("Die Phase wurde übersprungen!").color(ChatColor.RED));
				endPhase();
			}
		});
	}
	
	@Completer(name = "skip")
	public List<String> skipC(final CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		result.add("");
		
		return result;
	}
	
	@Command(name = "end", description = "beendet das Spiel", usage = "", permission = "end", max = 0, sync = true)
	public void end(final CommandArgs args) {
		getGame().broadCastMessage(Prefix.API.getPrefix().then("Das Spiel wurde von einem Admin beendet!").color(ChatColor.RED));
		
		final User[] u = new User[getGame().getPlayers().size()];
		for (int i = 0; i < u.length; i++) {
			u[i] = Core.getCore().getUserHandler().get(getGame().getPlayers().get(i));
		}
		
		endPhase();
		
		getGame().end(u);
		
	}
	
	@Completer(name = "end")
	public List<String> endC(final CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		result.add("");
		
		return result;
	}
}
