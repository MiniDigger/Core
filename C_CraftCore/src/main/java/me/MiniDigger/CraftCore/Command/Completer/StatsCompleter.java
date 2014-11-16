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
 * Written by Martin Benndorf <admin@minidigger.me>, 2013-2014 and others
 */
package me.MiniDigger.CraftCore.Command.Completer;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Command.Completer;
import me.MiniDigger.Core.Stats.StatsType;

public class StatsCompleter {
	
	@Completer(name = "stats")
	public List<String> statsC(final CommandArgs args) {
		final List<String> result = new ArrayList<String>();
		
		if (args.getArgs().length == 1) {
			result.add("add");
			result.add("remove");
			result.add("set");
			result.add("get");
			result.add("top");
			
			return Core.getCore().getCommonMethods().completer(result, args.getArgs()[0]);
		} else {
			return new ArrayList<String>();
		}
	}
	
	@Completer(name = "stats.add")
	public List<String> addC(final CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		if (args.getArgs().length == 1) {
			for (final Player p : Core.getCore().getUserHandler().getOnlinePlayers()) {
				result.add(p.getName());
			}
			
			return Core.getCore().getCommonMethods().completer(result, args.getArgs()[0]);
		} else if (args.getArgs().length == 2) {
			for (final StatsType t : StatsType.values()) {
				result.add(t.getGame() + "." + t.getStats());
			}
			
			return Core.getCore().getCommonMethods().completer(result, args.getArgs()[1]);
		} else {
			return new ArrayList<String>();
		}
	}
	
	@Completer(name = "stats.remove")
	public List<String> removeC(final CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		if (args.getArgs().length == 1) {
			for (final Player p : Core.getCore().getUserHandler().getOnlinePlayers()) {
				result.add(p.getName());
			}
			
			return Core.getCore().getCommonMethods().completer(result, args.getArgs()[0]);
		} else if (args.getArgs().length == 2) {
			for (final StatsType t : StatsType.values()) {
				result.add(t.getGame() + "." + t.getStats());
			}
			
			return Core.getCore().getCommonMethods().completer(result, args.getArgs()[1]);
		} else {
			return new ArrayList<String>();
		}
	}
	
	@Completer(name = "stats.set")
	public List<String> setC(final CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		if (args.getArgs().length == 1) {
			for (final Player p : Core.getCore().getUserHandler().getOnlinePlayers()) {
				result.add(p.getName());
			}
			
			return Core.getCore().getCommonMethods().completer(result, args.getArgs()[0]);
		} else if (args.getArgs().length == 2) {
			for (final StatsType t : StatsType.values()) {
				result.add(t.getGame() + "." + t.getStats());
			}
			
			return Core.getCore().getCommonMethods().completer(result, args.getArgs()[1]);
		} else {
			return new ArrayList<String>();
		}
	}
	
	@Completer(name = "stats.get")
	public List<String> getC(final CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		if (args.getArgs().length == 1) {
			for (final Player p : Core.getCore().getUserHandler().getOnlinePlayers()) {
				result.add(p.getName());
			}
			
			return Core.getCore().getCommonMethods().completer(result, args.getArgs()[0]);
		} else if (args.getArgs().length == 2) {
			for (final StatsType t : StatsType.values()) {
				result.add(t.getGame() + "." + t.getStats());
			}
			
			return Core.getCore().getCommonMethods().completer(result, args.getArgs()[1]);
		} else {
			return new ArrayList<String>();
		}
	}
	
	@Completer(name = "stats.top")
	public List<String> topC(final CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		if (args.getArgs().length == 1) {
			for (final StatsType t : StatsType.values()) {
				result.add(t.getGame() + "." + t.getStats());
			}
			
			return Core.getCore().getCommonMethods().completer(result, args.getArgs()[0]);
		} else {
			return new ArrayList<String>();
		}
	}
}
