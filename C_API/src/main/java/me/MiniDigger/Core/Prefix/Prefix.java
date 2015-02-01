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
package me.MiniDigger.Core.Prefix;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Game.GameType;

import mkremins.fanciful.FancyMessage;

public enum Prefix {
	ERROR(ChatColor.RED, ChatColor.DARK_RED, "Error", GameType.NOTHING),
	CORE(ChatColor.RED, ChatColor.GRAY, "Core", GameType.LOBBY),
	TICTACTOE(ChatColor.RESET, ChatColor.BLUE, "TicTacToe> ", GameType.TICTACTOE),
	STATS(ChatColor.RESET, ChatColor.GOLD, "Stats> ", GameType.NOTHING),
	API(ChatColor.GRAY, ChatColor.RED, "Core", GameType.NOTHING),
	SECURITY(ChatColor.RESET, ChatColor.AQUA, "Security> ", GameType.NOTHING),
	CHAT(ChatColor.RESET, ChatColor.DARK_GRAY, "ChatManagment> ", GameType.NOTHING),
	SQUAD(ChatColor.AQUA, ChatColor.BLUE, "Squad", GameType.NOTHING),
	HINT(ChatColor.MAGIC, ChatColor.GOLD, "JustATip", GameType.NOTHING),
	GETHEDROP(ChatColor.AQUA, ChatColor.BLUE, "GetTheDrop", GameType.GETTHEDROP),
	VOTE(ChatColor.AQUA, ChatColor.BLUE, "Vote", GameType.NOTHING),
	SHOP(ChatColor.AQUA, ChatColor.BLUE, "Shop", GameType.LOBBY),
	ADDON(ChatColor.RESET, ChatColor.AQUA, "AddOn>", GameType.NOTHING),
	BEDWARS(ChatColor.AQUA, ChatColor.BLUE, "BedWars", GameType.BEDWARS),
	CINE(ChatColor.RESET, ChatColor.AQUA, "Cinematic>", GameType.NOTHING),
	CRANK(ChatColor.AQUA, ChatColor.BLUE, "Crank", GameType.CRANK),
	KIT(ChatColor.RED, ChatColor.GREEN, "Kit", GameType.NOTHING),
	LANG(ChatColor.AQUA, ChatColor.BLUE, "Lang", GameType.NOTHING),
	SPEC(ChatColor.AQUA, ChatColor.BLUE, "Spec", GameType.NOTHING),
	OITC(ChatColor.AQUA, ChatColor.BLUE, "OITC", GameType.OITC),
	ULTRASPLEEF(ChatColor.AQUA, ChatColor.BLUE, "UltraSpleef", GameType.ULTRASPLEEF),
	TAG(ChatColor.AQUA, ChatColor.BLUE, "Tag", GameType.NOTHING);
	
	private ChatColor	klammer;
	private ChatColor	color;
	private String	  name;
	private GameType	type;
	
	private Prefix(final ChatColor klammer, final ChatColor color, final String name, final GameType type) {
		this.klammer = klammer;
		this.color = color;
		this.name = name;
		this.type = type;
	}
	
	public static void load() {
		FileConfiguration c = Core.getCore().getInstance().getConfig();
		for (Prefix p : values()) {
			if (c.contains(p.name())) {
				if (c.getString("prefix." + p.name() + ".colorBrackets") != null) {
					p.setKlammer(ChatColor.valueOf(c.getString("prefix." + p.name() + ".colorBrackets")));
				}
				if (c.getString("prefix." + p.name() + ".color") != null) {
					p.setColor(ChatColor.valueOf(c.getString("prefix." + p.name() + ".color")));
				}
				if (c.getString("prefix." + p.name() + ".name") != null) {
					p.setName(c.getString("prefix." + p.name() + ".name"));
				}
			}
		}
	}
	
	public static void save() {
		FileConfiguration c = Core.getCore().getInstance().getConfig();
		for (Prefix p : values()) {
			c.set("prefix." + p.name() + ".colorBrackets", p.getBracket().name());
			c.set("prefix." + p.name() + ".color", p.getColor().name());
			c.set("prefix." + p.name() + ".name", p.getName());
		}
		Core.getCore().getInstance().saveConfig();
	}
	
	/**
	 * @param klammer
	 *            the klammer to set
	 */
	public void setKlammer(ChatColor klammer) {
		this.klammer = klammer;
	}
	
	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(ChatColor color) {
		this.color = color;
	}
	
	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return The color used for the brackets
	 */
	public ChatColor getBracket() {
		return klammer;
	}
	
	/**
	 * @return The color used for the name
	 */
	public ChatColor getColor() {
		return color;
	}
	
	/**
	 * @return The name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return The type, this prefix is assiocated with
	 */
	public GameType getType() {
		return type;
	}
	
	/**
	 * @return The final prefix
	 */
	public FancyMessage getPrefix() {
		if (klammer == ChatColor.RESET) {
			return new FancyMessage(name).color(color).tooltip("Made by MiniDigger", "more Infos:", "info@minidigger.me");
		}
		if (klammer == ChatColor.MAGIC) {
			return new FancyMessage("s").color(ChatColor.YELLOW).style(ChatColor.MAGIC).then("u").color(ChatColor.GOLD).style(ChatColor.MAGIC).then(name)
			        .tooltip("Made by MiniDigger", "more Infos:", "info@minidigger.me").color(color).then("c").color(ChatColor.GOLD).style(ChatColor.MAGIC).then("s")
			        .color(ChatColor.YELLOW).style(ChatColor.MAGIC);
		}
		return new FancyMessage("[").color(klammer).then(name).color(color).tooltip("Made by MiniDigger", "more Infos:", "info@minidigger.me").then("] ").color(klammer);
		
	}
	
	/**
	 * @return The prefix, optimized for consol use
	 */
	public String getConsolPrefix() {
		return klammer + "[" + color + name + klammer + "]";
	}
	
	/**
	 * Searches for a prefix for that game
	 * 
	 * @param type
	 * @return may be null
	 */
	public static Prefix getByGameType(final GameType type) {
		for (final Prefix p : Prefix.values()) {
			if (p.getType().equals(type)) {
				return p;
			}
		}
		return API;
	}
}
