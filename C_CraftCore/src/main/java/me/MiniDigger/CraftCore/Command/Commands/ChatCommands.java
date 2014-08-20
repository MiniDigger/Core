package me.MiniDigger.CraftCore.Command.Commands;

import java.util.HashMap;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Chat.ChatChannel;
import me.MiniDigger.Core.Command.Command;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Prefix.Prefix;
import mkremins.fanciful.FancyMessage;

import org.bukkit.ChatColor;

public class ChatCommands {
	
	@Command(name = "chat", description = "Alles mit Chat", usage = "", permission = "chat")
	public void chat(CommandArgs args) {
		FancyMessage msg1 = Prefix.CHAT.getPrefix().then("Benutze ").color(ChatColor.GRAY).then("/chat join <channel>").suggest("/chat join ")
		        .tooltip("Oder klicke hier").color(ChatColor.YELLOW).then(" um einem Channel beizutreten").color(ChatColor.GRAY);
		FancyMessage msg2 = Prefix.CHAT.getPrefix().then("Benutze ").color(ChatColor.GRAY).then("/chat leave <channel>").suggest("/chat leave ")
		        .tooltip("Oder klicke hier").color(ChatColor.YELLOW).then(" um einen Channel zu verlassen").color(ChatColor.GRAY);
		FancyMessage msg3 = Prefix.CHAT.getPrefix().then("Benutze ").color(ChatColor.GRAY).then("/chat list").suggest("/chat join ").tooltip("Oder klicke hier")
		        .color(ChatColor.YELLOW).then(" um alle für dich verfügbaren Channel anzuzeigen").color(ChatColor.GRAY);
		if (args.isUser()) {
			args.getUser().sendMessage(msg1);
			args.getUser().sendMessage(msg2);
			args.getUser().sendMessage(msg3);
		} else {
			args.getSender().sendMessage(msg1.toOldMessageFormat());
			args.getSender().sendMessage(msg2.toOldMessageFormat());
			args.getSender().sendMessage(msg3.toOldMessageFormat());
		}
	}
	
	@Command(name = "chat.join", description = "Joint einem Channel", usage = "/chat join <channel>", permission = "chat.join", consol = false, min = 1, max = 1)
	public void join(CommandArgs args) {
		String channel = "";
		try {
			channel = args.getArgs()[0];
		} catch (Exception ex) {
			args.getUser().sendMessage(Prefix.CHAT.getPrefix().then("Unbekannter Channel!").color(ChatColor.RED));
			return;
		}
		
		ChatChannel ch = Core.getCore().getChatHandler().getChannel(channel);
		if (ch == null) {
			args.getUser().sendMessage(Prefix.CHAT.getPrefix().then(" Unbekannter Channel!").color(ChatColor.RED));
			return;
		}
		
		args.getUser().joinChannel(ch);
	}
	
	@Command(name = "chat.leave", description = "Verlässt einen Channel", usage = "/chat leave <channel>", permission = "chat.leave", consol = false, min = 1, max = 1)
	public void leave(CommandArgs args) {
		String channel = "";
		try {
			channel = args.getArgs()[0];
		} catch (Exception ex) {
			args.getUser().sendMessage(Prefix.CHAT.getPrefix().then("Unbekannter Channel!").color(ChatColor.RED));
			return;
		}
		
		ChatChannel ch = Core.getCore().getChatHandler().getChannel(channel);
		if (ch == null) {
			args.getUser().sendMessage(Prefix.CHAT.getPrefix().then(" Unbekannter Channel!").color(ChatColor.RED));
			return;
		}
		
		args.getUser().leaveChannel(ch);
	}
	
	@Command(name = "chat.list", description = "Zeigt alle verfügbaren Channel", usage = "/chat list", permission = "chat.list", consol = false, min = 0, max = 0)
	public void list(CommandArgs args) {
		HashMap<ChatChannel, Boolean> channel = new HashMap<>();
		
		for (ChatChannel ch : Core.getCore().getChatHandler().getChannels()) {
			if (args.getUser().hasPermission(ch.getHearPerm())) {
				if (args.getUser().hasPermission(ch.getSpeakPerm())) {
					channel.put(ch, true);
				} else {
					channel.put(ch, false);
				}
			}
		}
		
		for (ChatChannel ch : channel.keySet()) {
			if (channel.get(ch)) {
				args.getUser().sendMessage(Prefix.CHAT.getPrefix().then(ch.getName()).color(ch.getColor()));
			} else {
				args.getUser().sendMessage(Prefix.CHAT.getPrefix().then(ch.getName()).color(ch.getColor()).then(" hear-only").color(ChatColor.RED));
			}
		}
	}
	
	@Command(name = "chat.switch", description = "Wechselt den primären Channel", usage = "/chat switch <channel>", permission = "chat.switch", consol = false, min = 1, max = 1)
	public void switchCmd(CommandArgs args) {
		String channel = "";
		try {
			channel = args.getArgs()[0];
		} catch (Exception ex) {
			args.getUser().sendMessage(Prefix.CHAT.getPrefix().then("Unbekannter Channel!").color(ChatColor.RED));
			return;
		}
		
		ChatChannel ch = Core.getCore().getChatHandler().getChannel(channel);
		if (ch == null) {
			args.getUser().sendMessage(Prefix.CHAT.getPrefix().then(" Unbekannter Channel!").color(ChatColor.RED));
			return;
		}
		
		args.getUser().setPrimaryChannel(ch);
	}
	
	@Command(name = "chat.ban", description = "Bannt einen Spieler von einem Channel", usage = "/chat ban <user> <channel>", permission = "chat.ban", consol = false, min = 2, max = 2)
	public void ban(CommandArgs args) {
		String channel = "";
		try {
			channel = args.getArgs()[1];
		} catch (Exception ex) {
			args.getUser().sendMessage(Prefix.CHAT.getPrefix().then("Unbekannter Channel!").color(ChatColor.RED));
			return;
		}
		
		ChatChannel ch = Core.getCore().getChatHandler().getChannel(channel);
		if (ch == null) {
			args.getUser().sendMessage(Prefix.CHAT.getPrefix().then(" Unbekannter Channel!").color(ChatColor.RED));
			return;
		}
		// TODO Chat ban
	}
	
	@Command(name = "chat.mute", description = "Mutet einen Spieler in einem Channel", usage = "/chat mute <user> <channel>", permission = "chat.mute", consol = false, min = 2, max = 2)
	public void mute(CommandArgs args) {
		
		String channel = "";
		try {
			channel = args.getArgs()[1];
		} catch (Exception ex) {
			args.getUser().sendMessage(Prefix.CHAT.getPrefix().then("Unbekannter Channel!").color(ChatColor.RED));
			return;
		}
		
		ChatChannel ch = Core.getCore().getChatHandler().getChannel(channel);
		if (ch == null) {
			args.getUser().sendMessage(Prefix.CHAT.getPrefix().then(" Unbekannter Channel!").color(ChatColor.RED));
			return;
		}
		// TODO Chat mute
	}
	
	@Command(name = "speak", description = "Sendet eine Nachricht in einen Channel", usage = "/speak <channel> <message>", permission = "speak", consol = false, min = 2, max = -1)
	public void speak(CommandArgs args) {
		String channel = "";
		try {
			channel = args.getArgs()[0];
		} catch (Exception ex) {
			args.getUser().sendMessage(Prefix.CHAT.getPrefix().then("Unbekannter Channel!").color(ChatColor.RED));
			return;
		}
		
		ChatChannel ch = Core.getCore().getChatHandler().getChannel(channel);
		if (ch == null) {
			args.getUser().sendMessage(Prefix.CHAT.getPrefix().then(" Unbekannter Channel!").color(ChatColor.RED));
			return;
		}
		
		String message = "";
		for (int i = 1; i < args.getArgs().length; i++) {
			message += args.getArgs()[i];
		}
		
		ch.chat(args.getUser(), message);
	}
}
