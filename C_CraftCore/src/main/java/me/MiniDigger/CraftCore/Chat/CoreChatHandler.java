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

import org.bukkit.ChatColor;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Chat.ChatChannel;
import me.MiniDigger.Core.Chat.ChatHandler;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Chat.Channels.BroadcastChannel;
import me.MiniDigger.CraftCore.Chat.Channels.EmptyChannel;
import me.MiniDigger.CraftCore.Chat.Channels.GlobalChannel;
import me.MiniDigger.CraftCore.Chat.Channels.NormalChannel;
import me.MiniDigger.CraftCore.Chat.Channels.PremiumChannel;
import me.MiniDigger.CraftCore.Packet.Packets.ChatPacket;
import me.MiniDigger.CraftCore.Server.CoreServer;

import mkremins.fanciful.FancyMessage;
import mkremins.fanciful.TextualComponent;

public class CoreChatHandler implements ChatHandler {
	
	private final ArrayList<ChatChannel>	channels	= new ArrayList<>();
	
	@Override
	public ChatChannel getChannel(final String name) {
		for (final ChatChannel channel : channels) {
			if (channel.getName().equalsIgnoreCase(name)) {
				return channel;
			}
		}
		return null;
	}
	
	@Override
	public void registerChannel(final ChatChannel channel) {
		channels.add(channel);
	}
	
	@Override
	public void unregisterChannel(final ChatChannel channel) {
		channels.remove(channel);
	}
	
	@Override
	public void registerChannels() {
		registerChannel(new BroadcastChannel("BroadCast", ChatColor.AQUA, "chat.hear.broadcast", "chat.speak.broadcast", new FancyMessage(
		        TextualComponent.rawText(ChatColor.COLOR_CHAR + "" + ChatColor.RESET.getChar()))));
		registerChannel(new EmptyChannel());
		registerChannel(new GlobalChannel("Global", ChatColor.DARK_GREEN, "chat.hear.global", "chat.speak.global", new FancyMessage("[").color(ChatColor.GREEN)
		        .then("GLOBAL").color(ChatColor.AQUA).then("]").color(ChatColor.GREEN)));
		registerChannel(new NormalChannel("Default", ChatColor.GRAY, "chat.hear.default", "chat.speak.default", new FancyMessage(
		        TextualComponent.rawText(ChatColor.COLOR_CHAR + "" + ChatColor.RESET.getChar()))));
		registerChannel(new PremiumChannel("Premium", ChatColor.GOLD, "chat.hear.premium", "chat.speak.premium", new FancyMessage("[").color(ChatColor.GOLD)
		        .then("Premium").color(ChatColor.YELLOW).then("]").color(ChatColor.GOLD)));
	}
	
	@Override
	public void handleChat(final User user, final String message) {
		if (user.getSpeakChannel() == null) {
			user.joinChannel(getChannel("Default"));
		}
		
		user.getSpeakChannel().chat(user, message);
		
		final ChatPacket packet = new ChatPacket();
		packet.setUser(user.getUUID());
		packet.setServer(CoreServer.getForThis(true).getName());
		packet.setMessage(message);
		packet.setChannel(user.getSpeakChannel().getName());
		
		Core.getCore().getPacketHandler().sendPacket(packet);
	}
	
	@Override
	public List<ChatChannel> getChannels() {
		return channels;
	}
	
}
