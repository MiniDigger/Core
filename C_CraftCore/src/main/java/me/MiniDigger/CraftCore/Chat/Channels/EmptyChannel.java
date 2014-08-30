package me.MiniDigger.CraftCore.Chat.Channels;

import org.bukkit.ChatColor;

import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.User.User;
import me.MiniDigger.CraftCore.Chat.CoreChatChannel;
import mkremins.fanciful.FancyMessage;
import mkremins.fanciful.TextualComponent;

public class EmptyChannel extends CoreChatChannel {
	
	public EmptyChannel() {
		init("Empty", ChatColor.RESET, "", "", new FancyMessage(TextualComponent.rawText("§r")));
	}
	
	@Override
	public void join(User chatUser) {
		
	}
	
	@Override
	public void leave(User chatUser) {
		
	}
	
	@Override
	public void chat(User chatUser, String message) {
		chatUser.sendMessage(Prefix.CHAT.getPrefix().then("Du bist in keinem Channel!").color(ChatColor.DARK_RED));
		
		chatUser.sendMessage(Prefix.HINT.getPrefix().then("Betrete einen mit").color(ChatColor.RED).then("/chat join <channel>").color(ChatColor.YELLOW));
	}
	
}
