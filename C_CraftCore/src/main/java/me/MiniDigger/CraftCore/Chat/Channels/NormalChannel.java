package me.MiniDigger.CraftCore.Chat.Channels;

import org.bukkit.ChatColor;

import me.MiniDigger.Core.User.User;
import me.MiniDigger.CraftCore.Chat.CoreChatChannel;
import mkremins.fanciful.FancyMessage;

public class NormalChannel extends CoreChatChannel {
	
	public NormalChannel(String name, ChatColor color, String hearPerm, String speakPerm, FancyMessage prefix) {
		init(name, color, hearPerm, speakPerm, prefix);
	}
	
	@Override
	public void join(User chatUser) {
	}
	
	@Override
	public void leave(User chatUser) {
	}
}
