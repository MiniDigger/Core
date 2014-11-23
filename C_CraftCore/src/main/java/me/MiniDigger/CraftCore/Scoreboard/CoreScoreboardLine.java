package me.MiniDigger.CraftCore.Scoreboard;

import org.bukkit.scoreboard.DisplaySlot;

import me.MiniDigger.Core.Scoreboard.ScoreboardLine;


public class CoreScoreboardLine implements ScoreboardLine {
	private int id;
	private String content;
	private DisplaySlot slot;
	
    public CoreScoreboardLine(int id, String content,DisplaySlot slot) {
	    this.id = id;
	    this.content = content;
	    this.slot = slot;
    }

    @Override
	public int getId() {
    	return id;
    }

    @Override
    public void setId(int id) {
    	this.id = id;
    }

    @Override
    public String getContent() {
    	return content;
    }

    @Override
    public void setContent(String content) {
    	this.content = content;
    }
    
    @Override
    public DisplaySlot getSlot(){
    	return slot;
    }
    
    @Override
    public void setSlot(DisplaySlot slot){
    	this.slot = slot;
    }
}
