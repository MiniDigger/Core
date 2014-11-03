/*******************************************************************************
 * Test
 *******************************************************************************/
/**
 * ██████████████████████████████████████████████████████████████████████████████████████████████████████████████
 * █░░░░░░░░░░░░░░█░░░░░░██░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░░░███░░░░░░░░░░░░░░█
 * █░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █░░░░░░▄▀░░░░░░█░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░█░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░█████████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░████░░▄▀░░███░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░█░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░█░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░▄▀░░░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░█████████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░██░░▄▀░░█████░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░█░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░██░░▄▀░░░░░░█░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░░░░░█████░░░░░░██░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░██░░░░░░░░░░█░░░░░░░░░░░░░░█
 * ██████████████████████████████████████████████████████████████████████████████████████████████████████████████
 */

/**
 *
 * ██████████████████████████████████████████████████████████████████████████████████████████████████████████████
 * █░░░░░░░░░░░░░░█░░░░░░██░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░░░███░░░░░░░░░░░░░░█
 * █░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █░░░░░░▄▀░░░░░░█░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░█░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░█████████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░████░░▄▀░░███░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░█░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░█░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░▄▀░░░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░█████████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░██░░▄▀░░█████░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░█░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░██░░▄▀░░░░░░█░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░░░░░█████░░░░░░██░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░██░░░░░░░░░░█░░░░░░░░░░░░░░█
 * ██████████████████████████████████████████████████████████████████████████████████████████████████████████████
 */

package me.MiniDigger.Core.User;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import me.MiniDigger.Core.Chat.ChatChannel;
import me.MiniDigger.Core.SQL.Saveable;
import me.MiniDigger.Core.Stats.Stats;
import mkremins.fanciful.FancyMessage;

import org.bukkit.entity.Player;

public interface User extends Saveable {
	
	/**
	 * Intis all arguments
	 */
	public void init();
	
	/**
	 * Starts a session for that user
	 */
	public void startSession();
	
	/**
	 * Ends the the sessions
	 */
	public void stopSession();
	
	/**
	 * Set a user banned
	 */
	public void ban();
	
	/**
	 * Sets a user muted
	 */
	public void mute();
	
	/**
	 * @return If the user should be unbanned
	 */
	public boolean checkUnban();
	
	/**
	 * @return If the user should be unmuted
	 */
	public boolean checkUnMute();
	
	/**
	 * Unbans the user
	 */
	public void unBan();
	
	/**
	 * Unmutes the user
	 */
	public void unMute();
	
	/**
	 * Send a message to the user
	 * 
	 * @param msg
	 *            The message to send
	 */
	public void sendMessage(final FancyMessage msg);
	
	/**
	 * @return Bukkit player object
	 */
	public Player getPlayer();
	
	/**
	 * Checks if the user has the requiered permission
	 * 
	 * @param perm
	 *            The permission string to check
	 * @return If the user has the permission or not
	 */
	public boolean hasPermission(final String perm);
	
	/**
	 * @return The stats object for that player
	 */
	public Stats getStats();
	
	/**
	 * @return The real name of the player
	 */
	public String getRealName();
	
	/**
	 * @return The uuid for the player
	 */
	public UUID getUUID();
	
	/**
	 * Changes the display name
	 * 
	 * @param name
	 *            The new name;
	 */
	public void setDisplayName(final String name);
	
	/**
	 * @return The display name for that player
	 */
	public String getDisplayName();
	
	/**
	 * Sets the prefix for that player, mostly colors
	 * 
	 * @param prefix
	 *            The new prefix
	 */
	public void setPrefix(final String prefix);
	
	/**
	 * @return The prefix, mostly used for colors
	 */
	public String getPrefix();
	
	/**
	 * Sets the suffix for that player, mostly colors
	 * 
	 * @param suffix
	 *            The new suffix
	 */
	public void setSuffix(final String suffix);
	
	/**
	 * @return The suffix, mostly used for colors
	 */
	public String getSuffix();
	
	/**
	 * @return If the user is muted
	 */
	public boolean isMuted();
	
	/**
	 * @return The time the player is muted for
	 */
	public Date getMuteTime();
	
	/**
	 * @return If the user is banned
	 */
	public boolean isBanned();
	
	/**
	 * @return The time the player is banned for
	 */
	public Date getBanTime();
	
	/**
	 * @return The date the user was first seen
	 */
	public Date getFirstSeen();
	
	/**
	 * @return The date the user was last seen
	 */
	public Date getLastSeen();
	
	/**
	 * @return The total time the user played on the server
	 */
	public long getPlayTime();
	
	/**
	 * @return the channels the player is listening to
	 */
	public List<ChatChannel> getListenChannels();
	
	/**
	 * @return the channels the player is speaking in
	 */
	public ChatChannel getSpeakChannel();
	
	/**
	 * Let the user join a ChatChannel
	 * 
	 * @param ch
	 *            The channel to join
	 */
	public void joinChannel(final ChatChannel ch);
	
	/**
	 * Let the user leave a ChatChannel
	 * 
	 * @param ch
	 *            The channel to leave
	 */
	public void leaveChannel(final ChatChannel ch);
	
	/**
	 * Sets the primary channel of a user, the channel a user is speaking in
	 * 
	 * @param ch
	 *            The channel to set
	 */
	public void setPrimaryChannel(final ChatChannel ch);
	
}
