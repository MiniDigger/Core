package me.MiniDigger.Core.Scoreboard;

import org.bukkit.scoreboard.DisplaySlot;


public interface ScoreboardTitle {

	void scroll();

	String getTitle();

	void setTitle(String title);

	DisplaySlot getSlot();

	void setSlot(DisplaySlot slot);
	
}
