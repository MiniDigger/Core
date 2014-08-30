package me.MiniDigger.CraftCore.Chat.Channels;

import org.bukkit.ChatColor;

import me.MiniDigger.CraftCore.Chat.CoreChatChannel;
import mkremins.fanciful.FancyMessage;

public class SupportChannel extends CoreChatChannel {
	
	public SupportChannel(String name, ChatColor color, String hearPerm, String speakPerm, FancyMessage prefix) {
		init(name, color, hearPerm, speakPerm, prefix);
	}
	
}
