package me.MiniDigger.CraftCore.Chat.Channels;

import org.bukkit.ChatColor;

import me.MiniDigger.Core.User.User;
import me.MiniDigger.CraftCore.Chat.CoreChatChannel;
import mkremins.fanciful.FancyMessage;

public class SquadChannel extends CoreChatChannel {
	
	public SquadChannel(String name, ChatColor color, String hearPerm, String speakPerm, FancyMessage prefix) {
		init(name, color, hearPerm, speakPerm, prefix);
	}
	
	@Override
	public void join(User chatUser) {
		super.join(chatUser);
	}
	
	@Override
	public void leave(User chatUser) {
		super.leave(chatUser);
	}
	
	@Override
	public void chat(User chatUser, String message) {
		super.chat(chatUser, message);
	}
	
}
