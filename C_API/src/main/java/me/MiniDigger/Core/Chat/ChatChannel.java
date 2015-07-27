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
package me.MiniDigger.Core.Chat;

import java.util.List;

import org.bukkit.ChatColor;

import me.MiniDigger.Core.User.User;

import mkremins.fanciful.FancyMessage;

public interface ChatChannel {
	
	/**
	 * The consturctor for a new channel
	 *
	 * @param name
	 *            The name of the channel
	 * @param color
	 *            The color of the messages send in this channel
	 * @param hearPerm
	 *            The permission user need to join this channel
	 * @param speakPerm
	 *            The permission user need to speak in this channel
	 * @param prefix
	 *            The prefix used for this channel
	 */
	public void init(final String name, final ChatColor color, final String hearPerm, final String speakPerm, final FancyMessage prefix);
	
	/**
	 * Checks of two channels are identical
	 *
	 * @param channel
	 *            The channel to check with
	 * @return If the channels are identical
	 */
	public boolean equals(final ChatChannel channel);
	
	/**
	 * Sends the join message
	 *
	 * @param user
	 *            The user who joined
	 */
	public void join(final User user);
	
	/**
	 * Sends the leave message
	 *
	 * @param user
	 *            The user who left
	 */
	public void leave(final User user);
	
	/**
	 * Sends a message from the user into the channel
	 *
	 * @param user
	 *            The user speaking
	 * @param message
	 *            The message
	 */
	public void chat(final User user, final String message);
	
	/**
	 *
	 * @return A List of all users, who listen to this channel
	 */
	public List<User> getListeners();
	
	/**
	 * Broadcasts a message in this channel
	 *
	 * @param message
	 */
	public void broadcast(final FancyMessage message);
	
	/**
	 * @return the name of the channel
	 */
	public String getName();
	
	/**
	 * @return the default write color
	 */
	public ChatColor getColor();
	
	/**
	 * @return the perm, a player needs to receive the messages
	 */
	public String getHearPerm();
	
	/**
	 * @return the perm, a player needs to send messages
	 */
	public String getSpeakPerm();
	
	/**
	 * @return the chat prefix
	 */
	public FancyMessage getPrefix();
	
	/**
	 * @return if the channel should display msgs from other servers
	 */
	public boolean isGlobal();
	
	/**
	 * The consturctor for a new channel
	 *
	 * @param name
	 *            The name of the channel
	 * @param color
	 *            The color of the messages send in this channel
	 * @param hearPerm
	 *            The permission user need to join this channel
	 * @param speakPerm
	 *            The permission user need to speak in this channel
	 * @param prefix
	 *            The prefix used for this channel
	 * @param showMsg
	 *            If join and leave msgs should be shown
	 */
	void init(final String name, final ChatColor color, final String hearPerm, final String speakPerm, final FancyMessage prefix, final boolean showMsg);
}
