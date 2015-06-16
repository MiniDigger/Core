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
package me.MiniDigger.CraftCore.Command.Completer;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Chat.ChatChannel;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Command.Completer;
import me.MiniDigger.Core.User.User;

public class ChatCompleter {
	
	@Completer(name = "chat")
	public List<String> chatC(final CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		if (args.getArgs().length == 1) {
			result.add("join");
			result.add("leave");
			result.add("list");
			result.add("switch");
			result.add("ban");
			result.add("mute");
			
			return Core.getCore().getCommonMethods().completer(result, args.getArgs()[0]);
		} else {
			return new ArrayList<String>();
		}
	}
	
	@Completer(name = "chat.join")
	public List<String> joinC(final CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		if (args.getArgs().length == 1) {
			for (final ChatChannel c : Core.getCore().getChatHandler().getChannels()) {
				if (args.getUser().hasPermission(c.getHearPerm()) || args.getUser().hasPermission(c.getSpeakPerm())) {
					result.add(c.getName());
				}
			}
			
			return Core.getCore().getCommonMethods().completer(result, args.getArgs()[0]);
		} else {
			return new ArrayList<String>();
		}
	}
	
	@Completer(name = "chat.leave")
	public List<String> leaveC(final CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		if (args.getArgs().length == 1) {
			if (args.isUser()) {
				for (final ChatChannel c : args.getUser().getListenChannels()) {
					result.add(c.getName());
				}
			}
			
			return Core.getCore().getCommonMethods().completer(result, args.getArgs()[0]);
		} else {
			return new ArrayList<String>();
		}
	}
	
	@Completer(name = "chat.list")
	public List<String> listC(final CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		result.add("");
		
		return result;
	}
	
	@Completer(name = "chat.switch")
	public List<String> switchC(final CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		if (args.getArgs().length == 1) {
			for (final ChatChannel c : Core.getCore().getChatHandler().getChannels()) {
				result.add(c.getName());
			}
			
			return Core.getCore().getCommonMethods().completer(result, args.getArgs()[0]);
		} else {
			return new ArrayList<String>();
		}
	}
	
	@Completer(name = "chat.ban")
	public List<String> banC(final CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		if (args.getArgs().length == 1) {
			// TODO Ban Completer
			
			return Core.getCore().getCommonMethods().completer(result, args.getArgs()[0]);
		} else {
			return new ArrayList<String>();
		}
	}
	
	@Completer(name = "ban.mute")
	public List<String> muteC(final CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		if (args.getArgs().length == 1) {
			// TODO Mute Completer
			
			return Core.getCore().getCommonMethods().completer(result, args.getArgs()[0]);
		} else {
			return new ArrayList<String>();
		}
	}
	
	@Completer(name = "speak")
	public List<String> speakC(final CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		if (args.getArgs().length == 1) {
			for (final ChatChannel c : Core.getCore().getChatHandler().getChannels()) {
				if (args.getUser().hasPermission(c.getSpeakPerm())) {
					result.add(c.getName());
				}
			}
			
			return Core.getCore().getCommonMethods().completer(result, args.getArgs()[0]);
		} else {
			return new ArrayList<String>();
		}
	}
	
	@Completer(name = "name")
	public List<String> nameC(final CommandArgs args) {
		if (args.getArgs().length == 2) {
			final List<String> result = new ArrayList<>();
			for (final Player p : Core.getCore().getUserHandler().getOnlinePlayers()) {
				result.add(p.getName());
			}
			return Core.getCore().getCommonMethods().completer(result, args.getArgs()[1]);
		} else {
			return new ArrayList<String>();
		}
	}
	
	@Completer(name = "realname")
	public List<String> realnameC(final CommandArgs args) {
		if (args.getArgs().length == 2) {
			final List<String> result = new ArrayList<>();
			for (final User u : Core.getCore().getUserHandler().getOnlineUsers()) {
				result.add(u.getDisplayName());
			}
			return Core.getCore().getCommonMethods().completer(result, args.getArgs()[1]);
		} else {
			return new ArrayList<String>();
		}
	}
	
	@Completer(name = "prefix")
	public List<String> prefixC(final CommandArgs args) {
		if (args.getArgs().length == 2) {
			final List<String> result = new ArrayList<>();
			for (final Player p : Core.getCore().getUserHandler().getOnlinePlayers()) {
				result.add(p.getName());
			}
			return Core.getCore().getCommonMethods().completer(result, args.getArgs()[1]);
		} else {
			return new ArrayList<String>();
		}
	}
	
	@Completer(name = "suffix")
	public List<String> suffixC(final CommandArgs args) {
		if (args.getArgs().length == 2) {
			final List<String> result = new ArrayList<>();
			for (final Player p : Core.getCore().getUserHandler().getOnlinePlayers()) {
				result.add(p.getName());
			}
			return Core.getCore().getCommonMethods().completer(result, args.getArgs()[1]);
		} else {
			return new ArrayList<String>();
		}
	}
	
	@Completer(name = "pm")
	public List<String> pmC(final CommandArgs args) {
		if (args.getArgs().length == 2) {
			final List<String> result = new ArrayList<>();
			for (final Player p : Core.getCore().getUserHandler().getOnlinePlayers()) {
				result.add(p.getName());
			}
			return Core.getCore().getCommonMethods().completer(result, args.getArgs()[1]);
		} else {
			return new ArrayList<String>();
		}
	}
}
