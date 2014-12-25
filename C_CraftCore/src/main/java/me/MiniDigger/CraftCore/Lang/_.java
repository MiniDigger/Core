package me.MiniDigger.CraftCore.Lang;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Lang.LangKeyType;
import me.MiniDigger.Core.Lang.LangType;
import me.MiniDigger.Core.Lang.LogLevel;
import me.MiniDigger.Core.Lang.MsgType;
import me.MiniDigger.Core.Prefix.Prefix;

public class _ {
	
	@SuppressWarnings("all")
	public static String _(final LangKeyType type, final String... args) {
		return _(Core.getCore().getLangHandler().getDefaultLang(), type, args);
	}
	
	@SuppressWarnings("all")
	public static String _(final LangType lang, final LangKeyType type, final String... args) {
		String result = Core.getCore().getLangHandler().getStorage(lang).get(type);
		for (int i = 0; i < args.length; i++) {
			result = result.replaceAll("%" + i + "%", args[i]);
		}
		return result;
	}
	
	public static void log(final LogLevel lvl, final LangKeyType type, final String... args) {
		log(lvl, type, Core.getCore().getLangHandler().getDefaultLang(), args);
	}
	
	public static void log(final LogLevel lvl, final LangKeyType type, final LangType lang, final String... args) {
		log(lvl, type, lang, Bukkit.getConsoleSender(), args);
	}
	
	public static void log(final LogLevel lvl, final LangKeyType type, final LangType lang, final CommandSender sender, final String... args) {
		if (Core.getCore().getLangHandler().getLogLevel().isGreaterThen(lvl)) {
			lvl.getMsg(_(lang, type, args)).send(sender);
		}
	}
	
	public static void msg(final Prefix prefix, final LangKeyType key, final MsgType type, final CommandSender sender, final String... args) {
		msg(prefix, key, type, sender, Core.getCore().getLangHandler().getDefaultLang(), args);
	}
	
	public static void msg(final Prefix prefix, final LangKeyType key, final MsgType type, final CommandSender sender, final LangType lang, final String... args) {
		final String msg = _(lang, key, args);
		prefix.getPrefix().then(msg).color(type.getColor()).send(sender);
	}
}
