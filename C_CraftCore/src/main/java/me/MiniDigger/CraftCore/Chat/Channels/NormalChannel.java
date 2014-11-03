/*******************************************************************************
 * Test
 *******************************************************************************/
package me.MiniDigger.CraftCore.Chat.Channels;

import me.MiniDigger.Core.User.User;
import me.MiniDigger.CraftCore.Chat.CoreChatChannel;
import mkremins.fanciful.FancyMessage;

import org.bukkit.ChatColor;

public class NormalChannel extends CoreChatChannel {
	
	public NormalChannel(final String name, final ChatColor color, final String hearPerm, final String speakPerm, final FancyMessage prefix) {
		init(name, color, hearPerm, speakPerm, prefix);
	}
	
	@Override
	public void join(final User chatUser) {
	}
	
	@Override
	public void leave(final User chatUser) {
	}
}
