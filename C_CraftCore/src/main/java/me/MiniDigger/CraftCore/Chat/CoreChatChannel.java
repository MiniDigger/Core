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
package me.MiniDigger.CraftCore.Chat;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Chat.ChatChannel;
import me.MiniDigger.Core.User.User;

import mkremins.fanciful.FancyMessage;

public class CoreChatChannel implements ChatChannel {
	
	protected String	   name;
	protected ChatColor	   color;
	protected String	   hearPerm;
	protected String	   speakPerm;
	protected FancyMessage	prefix;
	
	@Override
	public void init(final String name, final ChatColor color, final String hearPerm, final String speakPerm, final FancyMessage prefix) {
		this.name = name;
		this.color = color;
		this.prefix = prefix;
		this.hearPerm = hearPerm;
		this.speakPerm = speakPerm;
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
		broadcast(getPrefix().then("+ ").color(ChatColor.YELLOW).style(ChatColor.BOLD).then("Der Spieler ").color(ChatColor.YELLOW)
		        .then(chatUser.getPrefix() + chatUser.getDisplayName()).tooltip("Klicke hier um " + chatUser.getDisplayName() + " eine Nachricht zu schreiben")
		        .suggest("/pm " + chatUser.getDisplayName() + " ").color(color).then(" hat den Raum betreten.").color(ChatColor.YELLOW));
	}
	
	@Override
	public void leave(final User chatUser) {
		ChatColor color = Core.getCore().getChatColorUtil().toChatColor(chatUser.getPrefix());
		if (color == ChatColor.RESET) {
			color = ChatColor.WHITE;
		}
		broadcast(getPrefix().then("- ").color(ChatColor.YELLOW).style(ChatColor.BOLD).then("Der Spieler ").color(ChatColor.YELLOW)
		        .then(chatUser.getPrefix() + chatUser.getDisplayName()).tooltip("Klicke hier um " + chatUser.getDisplayName() + " eine Nachricht zu schreiben")
		        .suggest("/pm " + chatUser.getDisplayName() + " ").color(color).then(" hat den Raum verlassen.").color(ChatColor.YELLOW));
	}
	
	@Override
	public void chat(final User chatUser, String message) {
		ChatColor color;
		if (this.color == ChatColor.RESET) {
			color = Core.getCore().getChatColorUtil().toChatColor(chatUser.getSuffix());
		} else {
			color = this.color;
		}
		
		if (chatUser.hasPermission("chat.color")) {
			message = Core.getCore().getChatColorUtil().replaceAndToMc(message);
		}
		
		broadcast(getPrefix().then(chatUser.getPrefix() + chatUser.getDisplayName())
		        .tooltip("Klicke hier um " + chatUser.getDisplayName() + " eine Nachricht zu schreiben").suggest("/pm " + chatUser.getDisplayName()).then("> " + message)
		        .color(color));
		
		Bukkit.getConsoleSender().sendMessage("[CHAT]<" + name + "> " + chatUser.getDisplayName() + ": " + message);
	}
	
	@Override
	public List<User> getListeners() {
		final List<User> result = new ArrayList<>();
		
		for (final User user : Core.getCore().getUserHandler().getOnlineUsers()) {
			result.add(user);
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
	
}
