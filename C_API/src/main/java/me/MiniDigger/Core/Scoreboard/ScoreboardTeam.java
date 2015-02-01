package me.MiniDigger.Core.Scoreboard;

import java.util.List;
import java.util.UUID;

import org.bukkit.scoreboard.NameTagVisibility;

public interface ScoreboardTeam {
	
	/**
	 * @return the name
	 */
	public String getName();
	
	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name);
	
	/**
	 * @return the prefix
	 */
	public String getPrefix();
	
	/**
	 * @param prefix
	 *            the prefix to set
	 */
	public void setPrefix(String prefix);
	
	/**
	 * @return the suffix
	 */
	public String getSuffix();
	
	/**
	 * @param suffix
	 *            the suffix to set
	 */
	public void setSuffix(String suffix);
	
	/**
	 * adds a player to the team
	 * 
	 * @param id
	 */
	void addPlayer(UUID id);
	
	/**
	 * rem a player from the team
	 * 
	 * @param id
	 */
	void remPlayer(UUID id);
	
	/**
	 * 
	 * @param tag
	 *            how the tag should be displayed
	 */
	void setNameTagVisibility(NameTagVisibility tag);
	
	/**
	 * @return how the tag is displayed
	 */
	NameTagVisibility getNameTagVisibility();
	
	/**
	 * @return the players on the team
	 */
	List<UUID> getPlayers();
}
