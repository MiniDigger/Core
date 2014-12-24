package me.MiniDigger.Core.Lang;

import org.bukkit.ChatColor;

import mkremins.fanciful.FancyMessage;

public enum LogLevel {
	ERROR("[Error]", ChatColor.RED), WARNING("[Warning]", ChatColor.YELLOW), INFO("[Info]", ChatColor.WHITE), DEBUG("[Debug]", ChatColor.GRAY);
	
	private String	  key;
	private ChatColor	color;
	
	private LogLevel(String key, ChatColor color) {
		this.key = key;
		this.color = color;
	}
	
	public boolean isGreaterThen(LogLevel level) {
		switch (this) {
		case DEBUG:
			if (level != DEBUG) {
				return false;
			}
		case INFO:
			if (level != WARNING && level != ERROR) {
				return true;
			}
			break;
		case WARNING:
			if (level != ERROR) {
				return true;
			}
			break;
		case ERROR:
			return true;
		}
		return false;
	}
	
	public FancyMessage getMsg(String key) {
		return new FancyMessage(this.key + " " + key).color(this.color);
	}
}
