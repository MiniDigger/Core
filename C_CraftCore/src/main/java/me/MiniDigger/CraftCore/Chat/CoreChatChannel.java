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
package me.MiniDigger.CraftCore.Chat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.sqlite.SQLiteConfig.SynchronousMode;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Chat.ChatChannel;
import me.MiniDigger.Core.Chat.ChatChars;
import me.MiniDigger.Core.User.User;

import mkremins.fanciful.FancyMessage;

public class CoreChatChannel implements ChatChannel {
	
	protected String		name;
	protected ChatColor		color;
	protected String		hearPerm;
	protected String		speakPerm;
	protected FancyMessage	prefix;
	protected boolean		global;
	protected boolean		showMsg;
	
	@Override
	public void init(final String name, final ChatColor color, final String hearPerm, final String speakPerm, final FancyMessage prefix) {
		this.name = name;
		this.color = color;
		this.prefix = prefix;
		this.hearPerm = hearPerm;
		this.speakPerm = speakPerm;
		showMsg = true;
	}
	
	@Override
	public void init(final String name, final ChatColor color, final String hearPerm, final String speakPerm, final FancyMessage prefix, final boolean showMsg) {
		init(name, color, hearPerm, speakPerm, prefix);
		this.showMsg = showMsg;
	}
	
	@Override
	public boolean equals(final ChatChannel channel) {
		if (channel.getName().equalsIgnoreCase(name)) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void join(final User chatUser) {
		ChatColor color = Core.getCore().getChatColorUtil().toChatColor(chatUser.getPrefix());
		if (color == ChatColor.RESET) {
			color = ChatColor.WHITE;
		}
		if (showMsg) {
			broadcast(getPrefix().then("+ ").color(ChatColor.YELLOW).style(ChatColor.BOLD).then("Der Spieler ").color(ChatColor.YELLOW)
			        .then(chatUser.getPrefix() + chatUser.getDisplayName()).tooltip("Klicke hier um " + chatUser.getDisplayName() + " eine Nachricht zu schreiben")
			        .suggest("/pm " + chatUser.getDisplayName() + " ").color(color).then(" hat den Raum betreten.").color(ChatColor.YELLOW));
		}
	}
	
	@Override
	public void leave(final User chatUser) {
		ChatColor color = Core.getCore().getChatColorUtil().toChatColor(chatUser.getPrefix());
		if (color == ChatColor.RESET) {
			color = ChatColor.WHITE;
		}
		if (showMsg) {
			broadcast(getPrefix().then("- ").color(ChatColor.YELLOW).style(ChatColor.BOLD).then("Der Spieler ").color(ChatColor.YELLOW)
			        .then(chatUser.getPrefix() + chatUser.getDisplayName()).tooltip("Klicke hier um " + chatUser.getDisplayName() + " eine Nachricht zu schreiben")
			        .suggest("/pm " + chatUser.getDisplayName() + " ").color(color).then(" hat den Raum verlassen.").color(ChatColor.YELLOW));
		}
	}
	
	@Override
	public void chat(final User chatUser, String message) {
		ChatColor color;
		if (this.color == ChatColor.RESET) {
			color = Core.getCore().getChatColorUtil().toChatColor(chatUser.getSuffix());
		} else {
			color = this.color;
		}
		
		String server = "";
		if (message.contains("|") && message.split(Pattern.quote("|")).length >= 2) {
			if (Bukkit.getPlayer(chatUser.getUUID()) == null || chatUser.hasPermission("chat.spoof")) {
				server = message.split(Pattern.quote("|"))[0];
				message = message.replace(server + "|", "");
				server = "<" + server + ">";
			}
		}
		
		Core.getCore().getDashingHandler().say(message);
		
		if (chatUser.hasPermission("chat.color")) {
			message = Core.getCore().getChatColorUtil().replaceAndToMc(message);
		}
		
		if (chatUser.hasPermission("chat.emote")) {
			Map<String, String> map = ChatChars.getAll();
			for (String key : map.keySet()) {
				if (message.contains(":" + key + ":")) {
					message = message.replace(":" + key + ":", map.get(key));
				}
			}
		}
		
		broadcast(getPrefix().then(chatUser.getPrefix() + server + chatUser.getDisplayName())
		        .tooltip("Klicke hier um " + chatUser.getDisplayName() + " eine Nachricht zu schreiben").suggest("/pm " + chatUser.getDisplayName()).then("> " + message)
		        .color(color));
				
		Bukkit.getConsoleSender().sendMessage("[CHAT]<" + name + "> " + chatUser.getDisplayName() + ": " + message);
	}
	
	@Override
	public List<User> getListeners() {
		final List<User> result = new ArrayList<>();
		
		for (final User user : Core.getCore().getUserHandler().getOnlineUsers()) {
			// if (user.hasPermission(hearPerm)) {
			// result.add(user);
			// }
			if (user.getListenChannels().contains(this)) {
				result.add(user);
			}
		}
		
		return result;
	}
	
	@Override
	public void broadcast(final FancyMessage message) {
		for (final User user : getListeners()) {
			user.sendMessage(message);
		}
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public ChatColor getColor() {
		return color;
	}
	
	@Override
	public String getHearPerm() {
		return hearPerm;
	}
	
	@Override
	public String getSpeakPerm() {
		return speakPerm;
	}
	
	@Override
	public FancyMessage getPrefix() {
		try {
			return prefix.clone();
		} catch (final CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean isGlobal() {
		return global;
	}
}
