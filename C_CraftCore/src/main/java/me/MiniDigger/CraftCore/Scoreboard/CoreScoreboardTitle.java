package me.MiniDigger.CraftCore.Scoreboard;

import org.bukkit.scoreboard.DisplaySlot;

import me.MiniDigger.Core.Scoreboard.ScoreboardTitle;

public class CoreScoreboardTitle implements ScoreboardTitle {
	
	private String	    title;
	private DisplaySlot	slot;
	@SuppressWarnings("unused")
    private boolean	    scrolling;
	@SuppressWarnings("unused")
    private String	    orig;
	
	public CoreScoreboardTitle(String title, DisplaySlot slot) {
		this.title = title;
		this.slot = slot;
		
		if (title.length() > 14) {
			scrolling = true;
			orig = title;
		}
	}
	
	@Override
	public void scroll() {
		// TODO Scoll
	}
	
	@Override
	public String getTitle() {
		return title;
	}
	
	@Override
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public DisplaySlot getSlot() {
		return slot;
	}
	
	@Override
	public void setSlot(DisplaySlot slot) {
		this.slot = slot;
	}
}
