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
 */
package me.MiniDigger.CraftCore.Command.Completer;

import java.util.ArrayList;
import java.util.List;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Chat.ChatChannel;
import me.MiniDigger.Core.Command.CommandArgs;
import me.MiniDigger.Core.Command.Completer;

public class ChatCompleter {
	
	@Completer(name = "chat")
	public List<String> chatC(final CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		if (args.getArgs().length == 0) {
			result.add("join");
			result.add("leave");
			result.add("list");
		}
		
		return result;
	}
	
	@Completer(name = "chat.join")
	public List<String> joinC(final CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		if (args.getArgs().length == 0) {
			for (final ChatChannel c : Core.getCore().getChatHandler().getChannels()) {
				if (args.getSender().hasPermission(c.getHearPerm()) || args.getSender().hasPermission(c.getSpeakPerm())) {
					result.add(c.getName());
				}
			}
		}
		
		return result;
	}
	
	@Completer(name = "chat.leave")
	public List<String> leaveC(final CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		if (args.getArgs().length == 0) {
			if (args.isUser()) {
				for (final ChatChannel c : args.getUser().getListenChannels()) {
					result.add(c.getName());
				}
			}
		}
		
		return result;
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
		
		if (args.getArgs().length == 0) {
			for (final ChatChannel c : Core.getCore().getChatHandler().getChannels()) {
				result.add(c.getName());
			}
		}
		
		return result;
	}
	
	@Completer(name = "chat.ban")
	public List<String> banC(final CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		if (args.getArgs().length == 0) {
			// TODO Ban Completer
		}
		
		return result;
	}
	
	@Completer(name = "ban.mute")
	public List<String> muteC(final CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		if (args.getArgs().length == 0) {
			// TODO Mute Completer
		}
		
		return result;
	}
	
	@Completer(name = "speak")
	public List<String> speakC(final CommandArgs args) {
		final List<String> result = new ArrayList<>();
		
		if (args.getArgs().length == 0) {
			for (final ChatChannel c : Core.getCore().getChatHandler().getChannels()) {
				if (args.getSender().hasPermission(c.getSpeakPerm())) {
					result.add(c.getName());
				}
			}
		}
		
		return result;
	}
}
