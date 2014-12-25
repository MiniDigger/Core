package me.MiniDigger.Core.Lang;

import org.bukkit.ChatColor;

public enum MsgType {
	SUCESS(ChatColor.GREEN), FAIL(ChatColor.RED), NORMAL(ChatColor.GRAY), IMPORTANT(ChatColor.GOLD);
	
	private ChatColor	color;
	
	private MsgType(ChatColor color) {
		this.color = color;
	}
	
	public ChatColor getColor() {
		return color;
	}
}
