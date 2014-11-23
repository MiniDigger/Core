package me.MiniDigger.Core.Scoreboard;

import java.util.List;

import org.bukkit.scoreboard.DisplaySlot;

public interface Scoreboard {
	
	/**
	 * @param line
	 *            the line to add to the board
	 */
	void addLine(ScoreboardLine line);
	
	/**
	 * @param content
	 *            the content of the line to remove
	 */
	void removeLine(String content);
	
	/**
	 * @param id
	 *            the id of the line to remove
	 */
	void removeLine(int id);
	
	/**
	 * @param id
	 *            the id of the line
	 * @return the line with that id<br>
	 *         MAY BE NULL
	 */
	ScoreboardLine getLine(int id);
	
	/**
	 * @param content
	 *            the content of the line
	 * @return the line with that content <br>
	 *         MAY BE NULL
	 */
	ScoreboardLine getLine(String content);
	
	/**
	 * @return this scoreboard as org.bukkit.scoreboard.Scoreboard
	 */
	org.bukkit.scoreboard.Scoreboard toBukkitScoreboard();
	
	/**
	 * @param slot
	 *            the slot
	 * @return all lines in that slot
	 */
	List<ScoreboardLine> getLines(DisplaySlot slot);
	
	/**
	 * @param title
	 *            the new title to set
	 */
	void setTitle(String title);
	
	/** 
	 * @return the title
	 */
	String getTitle();
	
}
