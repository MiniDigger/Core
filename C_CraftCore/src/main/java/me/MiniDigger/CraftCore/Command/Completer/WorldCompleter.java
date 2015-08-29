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
package me.MiniDigger.CraftCore.Command.Completer;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Command.Completer;

public class WorldCompleter {

	@Completer(name = "weather")
	public List<String> weatherC(final CommandArgs args) {
		final List<String> result = new ArrayList<String>();

		if (args.getArgs().length == 1) {
			result.add("storm");
			result.add("rain");
			result.add("regen");
			result.add("sturm");
			result.add("thunder");
			result.add("gewitter");
			result.add("sun");
			result.add("sonne");

			return Core.getCore().getCommonMethods().completer(result, args.getArgs()[0]);
		} else {
			return new ArrayList<String>();
		}
	}

	@Completer(name = "world")
	public List<String> worldC(final CommandArgs args) {
		final List<String> result = new ArrayList<String>();

		if (args.getArgs().length == 1) {
			result.add("info");
			result.add("load");
			result.add("unload");
			result.add("tp");
			result.add("list");
			result.add("create");

			return Core.getCore().getCommonMethods().completer(result, args.getArgs()[0]);
		} else {
			return new ArrayList<String>();
		}
	}

	@Completer(name = "world.info")
	public List<String> infoC(final CommandArgs args) {
		return new ArrayList<String>();
	}

	@Completer(name = "tp")
	public List<String> tpC(final CommandArgs args) {
		final List<String> result = new ArrayList<String>();
		if (args.getArgs().length == 1) {
			for (final Player p : Core.getCore().getUserHandler().getOnlinePlayers()) {
				result.add(p.getDisplayName());
			}

			return Core.getCore().getCommonMethods().completer(result, args.getArgs()[0]);
		}
		return result;
	}

	@Completer(name = "world.load")
	public List<String> loadC(final CommandArgs args) {
		if (args.getArgs().length == 1) {
			return Core.getCore().getCommonMethods().completer(Core.getCore().getMapHandler().getMapNames(), args.getArgs()[0]);
		} else {
			return new ArrayList<String>();
		}
	}

	@Completer(name = "world.unload")
	public List<String> unloadC(final CommandArgs args) {
		final List<String> result = new ArrayList<String>();

		if (args.getArgs().length == 1) {
			for (final World w : Bukkit.getWorlds()) {
				result.add(w.getName());
			}

			return Core.getCore().getCommonMethods().completer(result, args.getArgs()[0]);
		} else {
			return new ArrayList<String>();
		}
	}

	@Completer(name = "world.tp")
	public List<String> worldtpC(final CommandArgs args) {
		final List<String> result = new ArrayList<String>();

		if (args.getArgs().length == 1) {
			for (final World w : Bukkit.getWorlds()) {
				result.add(w.getName());
			}

			return Core.getCore().getCommonMethods().completer(result, args.getArgs()[0]);
		} else {
			return new ArrayList<String>();
		}
	}

	@Completer(name = "world.list")
	public List<String> listC(final CommandArgs args) {
		return new ArrayList<String>();
	}

	@Completer(name = "world.create")
	public List<String> createC(final CommandArgs args) {
		return new ArrayList<String>();
	}
}
