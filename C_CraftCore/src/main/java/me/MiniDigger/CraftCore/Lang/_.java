package me.MiniDigger.CraftCore.Lang;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Lang.LangKeyType;
import me.MiniDigger.Core.Lang.LangType;
import me.MiniDigger.Core.Lang.LogLevel;

public class _ {
	
	@SuppressWarnings("all")
	public static String _(LangKeyType type) {
		return Core.getCore().getLangHandler().getStorage().get(type);
	}
	
	@SuppressWarnings("all")
	public static String _(LangType lang, LangKeyType type) {
		return Core.getCore().getLangHandler().getStorage(lang).get(type);
	}
	
	public static void log(LogLevel lvl, LangKeyType type) {
		log(lvl, type, Core.getCore().getLangHandler().getDefaultLang());
	}
	
	public static void log(LogLevel lvl, LangKeyType type, LangType lang) {
		log(lvl, type, lang, Bukkit.getConsoleSender());
	}
	
	public static void log(LogLevel lvl, LangKeyType type, LangType lang, CommandSender sender) {
		if (Core.getCore().getLangHandler().getLogLevel().isGreaterThen(lvl)) {
			lvl.getMsg(_(lang, type)).send(sender);
		}
	}
}
