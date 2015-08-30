package me.MiniDigger.Core.Stats;

import java.util.Date;
import java.util.UUID;

import me.MiniDigger.Core.Game.GameType;

public interface Action {
	/**
	 * @return the date
	 */
	Date getDate();

	/**
	 * @param date
	 *            the date to set
	 */
	void setDate(Date date);

	/**
	 * @return the action
	 */
	ActionType getAction();

	/**
	 * @param action
	 *            the action to set
	 */
	void setAction(ActionType action);

	/**
	 * @return the data
	 */
	String getData();

	/**
	 * @param data
	 *            the data to set
	 */
	void setData(String data);

	/**
	 * @return the user
	 */
	UUID getUser();

	/**
	 * @param user
	 *            the user to set
	 */
	void setUser(UUID user);

	/**
	 * @return the gameType
	 */
	GameType getGameType();

	/**
	 * @param gameType
	 *            the gameType to set
	 */
	void setGameType(GameType gameType);

	/**
	 * @return the server
	 */
	String getServer();

	/**
	 * @param server
	 *            the server to set
	 */
	void setServer(String server);

}
