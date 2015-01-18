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
package me.MiniDigger.Core.Nametag;

import java.util.UUID;

import org.bukkit.entity.Player;

public interface NametagTeamHandler {
	
	/**
	 * Clears out all teams and removes them for all the players. Called when
	 * the plugin is disabled.
	 */
	void reset();
	
	/**
	 * Retrieves the player's entire name with both the prefix and suffix.
	 * 
	 * @param player
	 *            the specified player
	 * @return the entire nametag
	 */
	String getFormattedName(final UUID id);
	
	/**
	 * Retrieves a player's suffix
	 * 
	 * @param player
	 *            the specified player
	 * @return the player's suffix
	 */
	String getSuffix(final UUID id);
	
	/**
	 * Sends the current team setup and their players to the given player. This
	 * should be called when players join the server.
	 * 
	 * @param p
	 *            The player to send the packets to.
	 */
	void sendTeamsToPlayer(final Player p);
	
	/**
	 * Retrieves a player's prefix
	 * 
	 * @param player
	 *            the specified player
	 * @return the player's prefix
	 */
	String getPrefix(final UUID id);
	
	/**
	 * Clears a player's nametag.
	 * 
	 * @param player
	 *            the specified player
	 */
	void clear(final UUID id);
	
	/**
	 * Updates a player's prefix and suffix in the scoreboard and above their
	 * head.<br>
	 * <br>
	 * 
	 * If either the prefix or suffix is null or empty, it will be removed from
	 * the player's nametag.
	 * 
	 * @param player
	 *            the specified player
	 * @param prefix
	 *            the prefix to set for the given player
	 * @param suffix
	 *            the suffix to set for the given player
	 * @param tag
	 *            if the tag is shown
	 */
	void overlap(final UUID id, final String prefix, final String suffix, final boolean tag);
	
	/**
	 * Updates a player's prefix and suffix in the scoreboard and above their
	 * head.<br>
	 * <br>
	 * 
	 * If either the prefix or suffix is null or empty, it will be replaced with
	 * the current prefix/suffix
	 * 
	 * @param player
	 *            the specified player
	 * @param prefix
	 *            the prefix to set for the given player
	 * @param suffix
	 *            the suffix to set for the given player
	 * @param tag
	 *            if the tag is shown
	 */
	void update(final UUID id, final String prefix, final String suffix, final boolean tag);
	
	/**
	 * Initializes this class and loads current teams that are manipulated by
	 * this plugin.
	 */
	void load();
	
	/**
	 * 
	 * @param player
	 *            the specified player
	 * @return if the tag is shown or not
	 */
	boolean getTag(final UUID id);
	
}
