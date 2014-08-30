package me.MiniDigger.CraftCore.Chat.Channels;

import me.MiniDigger.CraftCore.Chat.CoreChatChannel;
import mkremins.fanciful.FancyMessage;

import org.bukkit.ChatColor;

public class SupportChannel extends CoreChatChannel {
	
	public SupportChannel(final String name, final ChatColor color, final String hearPerm, final String speakPerm, final FancyMessage prefix) {
		init(name, color, hearPerm, speakPerm, prefix);
	}
	
}
