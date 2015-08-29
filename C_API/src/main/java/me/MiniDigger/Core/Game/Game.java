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
package me.MiniDigger.Core.Game;

import java.util.List;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Sound;

import me.MiniDigger.Core.Chat.ChatChannel;
import me.MiniDigger.Core.Error.Error;
import me.MiniDigger.Core.Lang.LangKeyType;
import me.MiniDigger.Core.Lang.MsgType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.User.User;
import mkremins.fanciful.FancyMessage;

public interface Game {

	/**
	 * Let the user join the game
	 *
	 * @param user
	 *            The user
	 * @return A error, that may occour
	 */
	public Error join(final User user);

	/**
	 * Let the user leave the game
	 *
	 * @param user
	 *            The user
	 * @return A error, that may occour
	 */
	public Error leave(final User user);

	/**
	 * Start the game
	 */
	public void start();

	/**
	 * @return A unique ID for the game
	 */
	public UUID getIdentifier();

	/**
	 * @return A list with the ID of all players
	 */
	public List<UUID> getPlayers();

	/**
	 * Send a message to all players
	 *
	 * @param msg
	 *            The message, to be send
	 */
	public void broadCastMessage(final FancyMessage msg);

	/**
	 * Sends a sound to all players. The sound will be played at the location of
	 * the player
	 *
	 * @param sound
	 *            The sound to be played
	 * @param volume
	 *            The volume of the sound
	 * @param pitch
	 *            The pitch of the sound
	 */
	public void broadCastSound(final Sound sound, final float volume, final float pitch);

	/**
	 * Sends a sound to all players. THe sound will be played at the given
	 * location
	 *
	 * @param sound
	 *            The sound to play
	 * @param volume
	 *            The volume of the sound
	 * @param pitch
	 *            The pitch of the sound
	 * @param loc
	 *            The location, the sound should be played at
	 */
	public void broadCastSoundAtLocation(final Sound sound, final float volume, final float pitch, final Location loc);

	/**
	 *
	 * @return The chat prefix for the game
	 */
	@Deprecated
	public FancyMessage getPrefix();

	/**
	 * Inits the game
	 */
	public void init();

	/**
	 * @return The phase currently played
	 */
	public Phase getPhase();

	/**
	 * Sets the next phase
	 *
	 * @param nextPhase
	 *            The nextPhase
	 */
	public void setPhase(final Phase nextPhase);

	/**
	 * @return The type of the game
	 */
	public GameType getType();

	/**
	 * @return If new users are allowed to join
	 */
	public boolean allowJoin();

	/**
	 * @return If new users are allowed to specate
	 */
	public boolean allowSpectate();

	/**
	 * Returns the data for the key
	 *
	 * @param key
	 *            The key
	 * @return The data stored unter this key. May be NULL
	 */
	public String getGameData(final String key);

	/**
	 * Stores some data unter a key
	 *
	 * @param key
	 *            The key
	 * @param data
	 *            The data to be stored
	 */
	public void setGameData(final String key, final String data);

	/**
	 * Finishs the game
	 *
	 * @param winner
	 *            A list with all winners
	 */
	public void end(final User... winner);

	/**
	 * adds a player to he list of specs
	 *
	 * @param id
	 */
	public void addSpec(final UUID id);

	/**
	 * removes the player from the list of specs
	 *
	 * @param id
	 */
	public void remSpec(final UUID id);

	/**
	 *
	 * @return a list with all spectators for the current game
	 */
	public List<UUID> getSpecs();

	/**
	 *
	 * @return the prefix for this game
	 */
	Prefix getGamePrefix();

	/**
	 * sends a msg to all players and sepcs
	 *
	 * @param type
	 * @param msg
	 * @param args
	 */
	void broadCastMessage(final LangKeyType type, final MsgType msg, final String... args);

	/**
	 * set weather or not players are allowed to join the game
	 *
	 * @param allow
	 */
	void setAllowJoin(final boolean allow);

	/**
	 * set weather or not players are allowed to specatete the game
	 *
	 * @param allow
	 */
	void setAllowSpectate(final boolean allow);

	/**
	 *
	 * @return the max amount of players that are allowed to play at one time
	 */
	public int getMaxPlayers();

	/**
	 *
	 * @return if the game is full (players.size >= maxplayers)
	 */
	public boolean isFull();

	/**
	 *
	 * @return the chatchannel of this game
	 */
	ChatChannel getChatChannel();

	/**
	 * removes a player to make him a real spec
	 *
	 * @param uuid
	 */
	public void remPlayer(UUID uuid);
}
