package me.MiniDigger.Core.Scoreboard;

import org.bukkit.scoreboard.DisplaySlot;

public interface ScoreboardLine {
	
	/**
	 * @param content
	 *            the new content
	 */
	void setContent(String content);
	
	/**
	 * @return the content of this line
	 */
	String getContent();
	
	/**
	 * @param id
	 *            the new id
	 */
	void setId(int id);
	
	/**
	 * @return the id of this line
	 */
	int getId();

	/**
	 * @return the slot the line is displayed
	 */
	DisplaySlot getSlot();

	/**
	 * @param slot the slot the line should get displayed
	 */
	void setSlot(DisplaySlot slot);
	
}
