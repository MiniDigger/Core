package me.MiniDigger.Core.Scoreboard;

import java.util.UUID;

import org.bukkit.entity.Player;

public interface ScoreboardHandler {
	
	/**
	 * Gets the board for a player, create a new one if nothing was there
	 * 
	 * @param id
	 *            the id of the player
	 * @return the board for that player
	 */
	Scoreboard getBoard(UUID id);
	
	/**
	 * @param b
	 *            the scoreboard
	 * @param p
	 *            the player to set the scoreboard for
	 */
	void addToPlayer(Scoreboard b, Player p);
	
}
