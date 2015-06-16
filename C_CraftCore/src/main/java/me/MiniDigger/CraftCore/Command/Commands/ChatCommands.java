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
package me.MiniDigger.CraftCore.Command.Commands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Chat.ChatChannel;
import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.User.User;

import mkremins.fanciful.FancyMessage;

public class ChatCommands {
	
	@Command(name = "chat", description = "Alles mit Chat", usage = "", permission = "chat")
	public void chat(final CommandArgs args) {
		final FancyMessage msg1 = Prefix.CHAT.getPrefix().then("Benutze ").color(ChatColor.GRAY).then("/chat join <channel>").suggest("/chat join ")
		        .tooltip("Oder klicke hier").color(ChatColor.YELLOW).then(" um einem Channel beizutreten").color(ChatColor.GRAY);
		final FancyMessage msg2 = Prefix.CHAT.getPrefix().then("Benutze ").color(ChatColor.GRAY).then("/chat leave <channel>").suggest("/chat leave ")
		        .tooltip("Oder klicke hier").color(ChatColor.YELLOW).then(" um einen Channel zu verlassen").color(ChatColor.GRAY);
		final FancyMessage msg3 = Prefix.CHAT.getPrefix().then("Benutze ").color(ChatColor.GRAY).then("/chat list").suggest("/chat join ").tooltip("Oder klicke hier")
		        .color(ChatColor.YELLOW).then(" um alle für dich verfügbaren Channel anzuzeigen").color(ChatColor.GRAY);
		
		msg1.send(args.getSender());
		msg2.send(args.getSender());
		msg3.send(args.getSender());
	}
	
	@Command(name = "chat.join", description = "Joint einem Channel", usage = "/chat join <channel>", permission = "chat.join", consol = false, min = 1, max = 1)
	public void join(final CommandArgs args) {
		String channel = "";
		try {
			channel = args.getArgs()[0];
		} catch (final Exception ex) {
			args.getUser().sendMessage(Prefix.CHAT.getPrefix().then("Unbekannter Channel!").color(ChatColor.RED));
			return;
		}
		
		final ChatChannel ch = Core.getCore().getChatHandler().getChannel(channel);
		if (ch == null) {
			args.getUser().sendMessage(Prefix.CHAT.getPrefix().then(" Unbekannter Channel!").color(ChatColor.RED));
			return;
		}
		
		if (!args.getUser().hasPermission(ch.getHearPerm())) {
			args.getUser().sendMessage(Prefix.CHAT.getPrefix().then(" Du hast keine Permissions diesen Channel zu betreten!").color(ChatColor.RED));
			return;
		}
		
		args.getUser().joinChannel(ch);
	}
	
	@Command(name = "chat.leave", description = "Verlässt einen Channel", usage = "/chat leave <channel>", permission = "chat.leave", consol = false, min = 1, max = 1)
	public void leave(final CommandArgs args) {
		String channel = "";
		try {
			channel = args.getArgs()[0];
		} catch (final Exception ex) {
			args.getUser().sendMessage(Prefix.CHAT.getPrefix().then("Unbekannter Channel!").color(ChatColor.RED));
			return;
		}
		
		final ChatChannel ch = Core.getCore().getChatHandler().getChannel(channel);
		if (ch == null) {
			args.getUser().sendMessage(Prefix.CHAT.getPrefix().then(" Unbekannter Channel!").color(ChatColor.RED));
			return;
		}
		
		args.getUser().leaveChannel(ch);
	}
	
	@Command(name = "chat.list", description = "Zeigt alle verfügbaren Channel", usage = "/chat list", permission = "chat.list", consol = false, min = 0, max = 0)
	public void list(final CommandArgs args) {
		final HashMap<ChatChannel, Boolean> channel = new HashMap<>();
		
		for (final ChatChannel ch : Core.getCore().getChatHandler().getChannels()) {
			if (args.getUser().hasPermission(ch.getHearPerm())) {
				if (args.getUser().hasPermission(ch.getSpeakPerm())) {
					channel.put(ch, true);
				} else {
					channel.put(ch, false);
				}
			}
		}
		
		for (final ChatChannel ch : channel.keySet()) {
			if (channel.get(ch)) {
				args.getUser().sendMessage(Prefix.CHAT.getPrefix().then(ch.getName()).color(ch.getColor() == ChatColor.RESET ? ChatColor.WHITE : ch.getColor()));
			} else {
				args.getUser().sendMessage(
				        Prefix.CHAT.getPrefix().then(ch.getName()).color(ch.getColor() == ChatColor.RESET ? ChatColor.WHITE : ch.getColor()).then(" hear-only")
				                .color(ChatColor.RED));
			}
		}
	}
	
	@Command(name = "chat.switch", description = "Wechselt den primären Channel", usage = "/chat switch <channel>", permission = "chat.switch", consol = false, min = 1, max = 1)
	public void switchCmd(final CommandArgs args) {
		String channel = "";
		try {
			channel = args.getArgs()[0];
		} catch (final Exception ex) {
			args.getUser().sendMessage(Prefix.CHAT.getPrefix().then("Unbekannter Channel!").color(ChatColor.RED));
			return;
		}
		
		final ChatChannel ch = Core.getCore().getChatHandler().getChannel(channel);
		if (ch == null) {
			args.getUser().sendMessage(Prefix.CHAT.getPrefix().then(" Unbekannter Channel!").color(ChatColor.RED));
			return;
		}
		
		if (!args.getUser().hasPermission(ch.getSpeakPerm())) {
			args.getUser().sendMessage(Prefix.CHAT.getPrefix().then(" Du hast keine Permissions in diesen Channel zu reden!").color(ChatColor.RED));
			return;
		}
		
		args.getUser().setPrimaryChannel(ch);
	}
	
	@Command(name = "chat.ban", description = "Bannt einen Spieler von einem Channel", usage = "/chat ban <user> <channel>", permission = "chat.ban", consol = false, min = 2, max = 2)
	public void ban(final CommandArgs args) {
		String channel = "";
		try {
			channel = args.getArgs()[1];
		} catch (final Exception ex) {
			args.getUser().sendMessage(Prefix.CHAT.getPrefix().then("Unbekannter Channel!").color(ChatColor.RED));
			return;
		}
		
		final ChatChannel ch = Core.getCore().getChatHandler().getChannel(channel);
		if (ch == null) {
			args.getUser().sendMessage(Prefix.CHAT.getPrefix().then(" Unbekannter Channel!").color(ChatColor.RED));
			return;
		}
		// TODO Chat ban
	}
	
	@Command(name = "chat.mute", description = "Mutet einen Spieler in einem Channel", usage = "/chat mute <user> <channel>", permission = "chat.mute", consol = false, min = 2, max = 2)
	public void mute(final CommandArgs args) {
		
		String channel = "";
		try {
			channel = args.getArgs()[1];
		} catch (final Exception ex) {
			args.getUser().sendMessage(Prefix.CHAT.getPrefix().then("Unbekannter Channel!").color(ChatColor.RED));
			return;
		}
		
		final ChatChannel ch = Core.getCore().getChatHandler().getChannel(channel);
		if (ch == null) {
			args.getUser().sendMessage(Prefix.CHAT.getPrefix().then(" Unbekannter Channel!").color(ChatColor.RED));
			return;
		}
		// TODO Chat mute
	}
	
	@Command(name = "speak", description = "Sendet eine Nachricht in einen Channel", usage = "/speak <channel> <message>", permission = "speak", consol = false, min = 2, max = -1)
	public void speak(final CommandArgs args) {
		String channel = "";
		try {
			channel = args.getArgs()[0];
		} catch (final Exception ex) {
			args.getUser().sendMessage(Prefix.CHAT.getPrefix().then("Unbekannter Channel!").color(ChatColor.RED));
			return;
		}
		
		final ChatChannel ch = Core.getCore().getChatHandler().getChannel(channel);
		if (ch == null) {
			args.getUser().sendMessage(Prefix.CHAT.getPrefix().then(" Unbekannter Channel!").color(ChatColor.RED));
			return;
		}
		
		if (!args.getUser().hasPermission(ch.getSpeakPerm())) {
			args.getUser().sendMessage(Prefix.SECURITY.getPrefix().then("Du darfst in diesem Channel nicht reden!").color(ChatColor.RED));
		}
		
		String message = "";
		for (int i = 1; i < args.getArgs().length; i++) {
			message += args.getArgs()[i];
		}
		
		ch.chat(args.getUser(), message);
	}
	
	@Command(name = "name", description = "Ändert den Anzeigenamen", usage = "name <name> [player]", permission = "name", consol = false, min = 1, max = 2)
	public void name(final CommandArgs args) {
		if (args.getArgs().length == 1) {
			args.getUser().setDisplayName(args.getArgs()[0]);
			Prefix.CHAT.getPrefix().then("Dein Anzeigename ist nun " + args.getArgs()[0]).send(args.getPlayer());;
		} else {
			final User user = Core.getCore().getUserHandler().get(Bukkit.getPlayer(args.getArgs()[1]).getUniqueId());
			user.setDisplayName(args.getArgs()[1]);
			Prefix.CHAT.getPrefix().then("Dein Anzeigename ist nun " + args.getArgs()[0]).send(user.getPlayer());;
			Prefix.CHAT.getPrefix().then("Der Anzeigename von " + user.getRealName() + " ist nun " + args.getArgs()[0]).send(args.getPlayer());;
		}
	}
	
	@Command(name = "realname", description = "Zeigt den richtigen Namen von einem Spieler an", usage = "realname <player>", permission = "realname", consol = true, min = 1, max = 1)
	public void realname(final CommandArgs args) {
		final User user = Core.getCore().getUserHandler().getFromDisplayName(args.getArgs()[0]);
		if (user == null) {
			Prefix.CHAT.getPrefix().then("Unbekannter User " + args.getArgs()[0]).send(args.getSender());
			return;
		}
		Prefix.CHAT.getPrefix().then("Der richtige Name von " + user.getDisplayName() + " ist " + user.getRealName()).send(args.getSender());;
	}
	
	@Command(name = "prefix", description = "Ändert das Prefix", usage = "prefix <prefix> [player]", permission = "prefix", consol = false, min = 1, max = 2)
	public void prefix(final CommandArgs args) {
		if (args.getArgs().length == 1) {
			args.getUser().setPrefix(args.getArgs()[0]);
			Prefix.CHAT.getPrefix().then("Dein Prefix ist nun " + args.getArgs()[0]).send(args.getPlayer());;
		} else {
			Player p = Bukkit.getPlayer(args.getArgs()[1]);
			if (p == null) {
				Prefix.CHAT.getPrefix().then("Unbekannter Spieler:  " + args.getArgs()[1]).color(ChatColor.RED).send(args.getSender());
				return;
			}
			final User user = Core.getCore().getUserHandler().get(p.getUniqueId());
			user.setPrefix(args.getArgs()[1]);
			Prefix.CHAT.getPrefix().then("Dein Prefix ist nun " + args.getArgs()[0]).send(user.getPlayer());;
			Prefix.CHAT.getPrefix().then("Das Prefix von " + user.getDisplayName() + " ist nun " + args.getArgs()[0]).send(args.getPlayer());;
		}
	}
	
	@Command(name = "suffix", description = "Ändert das Suffix", usage = "suffix <suffix> [player]", permission = "suffix", consol = false, min = 1, max = 2)
	public void suffix(final CommandArgs args) {
		if (args.getArgs().length == 1) {
			args.getUser().setSuffix(args.getArgs()[0]);
			Prefix.CHAT.getPrefix().then("Dein Suffix ist nun " + args.getArgs()[0]).send(args.getPlayer());;
		} else {
			Player p = Bukkit.getPlayer(args.getArgs()[1]);
			if (p == null) {
				Prefix.CHAT.getPrefix().then("Unbekannter Spieler:  " + args.getArgs()[1]).color(ChatColor.RED).send(args.getSender());
				return;
			}
			final User user = Core.getCore().getUserHandler().get(p.getUniqueId());
			user.setSuffix(args.getArgs()[1]);
			Prefix.CHAT.getPrefix().then("Dein Suffix ist nun " + args.getArgs()[0]).send(user.getPlayer());;
			Prefix.CHAT.getPrefix().then("Das Suffix von " + user.getDisplayName() + " ist nun " + args.getArgs()[0]).send(args.getPlayer());;
		}
	}
	
	@Command(name = "pm", description = "Sendet eine PM", usage = "pm <spieler> <nachricht>", permission = "pm", consol = false, min = 1, string = 2)
	public void pm(final CommandArgs args) {
		User u = Core.getCore().getUserHandler().getFromDisplayName(args.getArgs()[0]);
		String msg = args.getArgs()[1];
		
		if (args.getUser().hasPermission("chat.color")) {
			msg = Core.getCore().getChatColorUtil().replaceAndToMc(msg);
		}
		
		new FancyMessage(args.getUser().getPrefix() + args.getUser().getDisplayName())
		        .tooltip("Klicke hier um " + args.getUser().getDisplayName() + " eine Nachricht zu schreiben").suggest("/pm " + args.getUser().getDisplayName())
		        .then(" -> ").color(ChatColor.RED).then("DIR").color(ChatColor.RED).then(": " + msg).send(u.getPlayer());
		new FancyMessage("DU").color(ChatColor.RED).then(" -> ").then(u.getPrefix() + u.getDisplayName())
		        .tooltip("Klicke hier um " + u.getDisplayName() + " eine Nachricht zu schreiben").suggest("/pm " + u.getDisplayName()).then(": " + msg)
		        .send(args.getUser().getPlayer());
		
		Core.getCore().getInstance().info(args.getUser().getPrefix() + args.getUser().getDisplayName() + " -> " + u.getPrefix() + u.getDisplayName() + ": " + msg);
	}
}
