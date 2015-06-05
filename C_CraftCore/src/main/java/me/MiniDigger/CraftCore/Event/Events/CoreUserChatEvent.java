package me.MiniDigger.CraftCore.Event.Events;

import me.MiniDigger.Core.Chat.ChatChannel;
import me.MiniDigger.Core.Event.Events.UserChatEvent;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Event.CoreCancelableEvent;

public class CoreUserChatEvent extends CoreCancelableEvent implements UserChatEvent {
	
	private User	    user;
	private ChatChannel	channel;
	private String	    msg;
	
	public CoreUserChatEvent(User user, ChatChannel channel, String msg) {
		super();
		this.user = user;
		this.channel = channel;
		this.msg = msg;
	}
	
	@Override
	public User getUser() {
		return user;
	}
	
	@Override
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public ChatChannel getChannel() {
		return channel;
	}
	
	@Override
	public void setChannel(ChatChannel channel) {
		this.channel = channel;
	}
	
	@Override
	public String getMsg() {
		return msg;
	}
	
	@Override
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
