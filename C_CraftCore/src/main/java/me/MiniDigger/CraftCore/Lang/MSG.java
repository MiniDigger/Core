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
package me.MiniDigger.CraftCore.Lang;

import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Lang.LangKeyType;
import me.MiniDigger.Core.Lang.LangStorage;
import me.MiniDigger.Core.Lang.LangType;
import me.MiniDigger.Core.Lang.LogLevel;
import me.MiniDigger.Core.Lang.MsgType;
import me.MiniDigger.Core.Prefix.Prefix;

import me.MiniDigger.CraftCore.CoreMain;

public class MSG {

	@SuppressWarnings("all")
	public static String msg(final LangKeyType type, final String... args) {
		return msg(Core.getCore().getLangHandler().getDefaultLang(), type, args);
	}

	@SuppressWarnings("all")
	public static String msg(final LangType lang, final LangKeyType type, final String... args) {
		return msg(lang, type, ChatColor.YELLOW, args);
	}

	@SuppressWarnings("all")
	public static String msg(final LangType lang, final LangKeyType type, final ChatColor markup, final String... args) {
		if (Core.getCore() == null || Core.getCore().getLangHandler() == null) {
			System.out.println("well, we are fucked");
			return "FATAL ERRORZ OCCURED WHILE TRANSLATION SOME SHIT";
		}

		String result = null;
		LangStorage s = Core.getCore().getLangHandler().getStorage(lang);
		if (s == null) {
			s = Core.getCore().getLangHandler().getStorage();
			if (s == null) {
				result = type.getDefaultValue();
			}
		}
		if (result == null) {
			result = Core.getCore().getLangHandler().getStorage(lang).get(type);
		}

		for (int i = 0; i < args.length; i++) {
			try {
				result = result.replaceAll("%" + i + "%", args[i]);
			}
			catch (final Exception ex) {
				log(LogLevel.WARNING, LangKeyType.Lang.ERROR_NO_ARG, i + "", type.getFullType());
			}
		}

		boolean b = false;
		for (int i = 0; i < result.length(); i++) {
			if (result.charAt(i) == '`') {
				if (!b) {
					result.replaceFirst(Pattern.quote("`"), markup + "");
					b = true;
				} else {
					result.replaceFirst(Pattern.quote("`"), ChatColor.RESET + "");
					b = false;
				}
			}
		}
		return result;
	}

	public static void log(final LogLevel lvl, final LangKeyType type, final String... args) {
		try {
			log(lvl, type, Core.getCore().getLangHandler().getDefaultLang(), args);
		}
		catch (final Exception ex) {
			log(lvl, type, LangType.en_US, args);
		}
	}

	public static void log(final LogLevel lvl, final LangKeyType type, final LangType lang, final String... args) {
		log(lvl, type, lang, Bukkit.getConsoleSender(), args);
	}

	public static void log(final LogLevel lvl, final LangKeyType type, final LangType lang, final CommandSender sender, final String... args) {
		try {
			if (!Core.getCore().getLangHandler().getLogLevel().isGreaterThen(lvl) || Core.getCore().getLangHandler().getLogLevel() == lvl) {
				lvl.getMsg(msg(lang, type, args)).send(sender);
			}
		}
		catch (final Exception ex) {
			lvl.getMsg(msg(lang, type, args)).send(sender);
		}
	}

	public static void msg(final Prefix prefix, final LangKeyType key, final MsgType type, final CommandSender sender, final String... args) {
		msg(prefix, key, type, sender, Core.getCore().getLangHandler().getDefaultLang(), args);
	}

	public static void msg(final Prefix prefix, final LangKeyType key, final MsgType type, final CommandSender sender, final LangType lang, final String... args) {
		final String msg = msg(lang, key, args);
		prefix.getPrefix().then(msg).color(type.getColor()).send(sender);
	}

	public static void stacktrace(final LogLevel lvl, final Throwable t) {
		if (t == null) {
			return;
		}
		if (!Core.getCore().getLangHandler().getLogLevel().isGreaterThen(lvl) || Core.getCore().getLangHandler().getLogLevel() == lvl) {
			lvl.getMsg(t.getClass().getName() + (t.getMessage() == null ? "" : ": " + t.getMessage())).send(Bukkit.getConsoleSender());
			for (final StackTraceElement e : t.getStackTrace()) {
				lvl.getMsg(e.toString()).send(Bukkit.getConsoleSender());
			}

			if (t.getCause() != null) {
				lvl.getMsg("Caused by:").send(Bukkit.getConsoleSender());
				stacktrace(lvl, t.getCause());
			}
		}
	}

	public static void stacktrace(final LogLevel lvl, final Throwable t, final boolean websocket) {
		if (!websocket) {
			stacktrace(lvl, t);
		} else {
			if (t == null) {
				return;
			}
			if (!Core.getCore().getLangHandler().getLogLevel().isGreaterThen(lvl) || Core.getCore().getLangHandler().getLogLevel() == lvl) {
				CoreMain.logger.info(t.getClass().getName() + (t.getMessage() == null ? "" : ": " + t.getMessage()));
				for (final StackTraceElement e : t.getStackTrace()) {
					CoreMain.logger.info(e.toString());
				}

				if (t.getCause() != null) {
					CoreMain.logger.info("Caused by:");
					stacktrace(lvl, t.getCause(), true);
				}
			}
		}
	}
}
