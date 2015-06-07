package me.MiniDigger.Core.Event.Events;

import me.MiniDigger.Core.Chat.ChatChannel;
import me.MiniDigger.Core.User.User;

public interface UserChatEvent {
	
	/**
	 * @return the msg
	 */
	String getMsg();
	
	/**
	 * @param msg
	 *            the msg to set
	 */
	void setMsg(final String msg);
	
	/**
	 * @param channel
	 *            the channel to set
	 */
	void setChannel(final ChatChannel channel);
	
	/**
	 * @return the channel
	 */
	ChatChannel getChannel();
	
	/**
	 * @param user
	 *            the user to set
	 */
	void setUser(final User user);
	
	/**
	 * @return the user
	 */
	User getUser();
	
}
