package me.MiniDigger.CraftCore.Scoreboard;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;

import me.MiniDigger.Core.Scoreboard.Scoreboard;
import me.MiniDigger.Core.Scoreboard.ScoreboardLine;

public class CoreScoreboard implements Scoreboard {
	
	private List<ScoreboardLine>	lines	= new ArrayList<ScoreboardLine>();
	private String	             title;
	
	public CoreScoreboard(String title) {
		this.title = title;
	}
	
	@Override
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String getTitle() {
		return title;
	}
	
	@Override
	public void addLine(ScoreboardLine line) {
		lines.add(line);
	}
	
	@Override
	public void removeLine(String content) {
		if (getLine(content) != null) {
			lines.remove(getLine(content));
		}
	}
	
	@Override
	public void removeLine(int id) {
		if (getLine(id) != null) {
			lines.remove(getLine(id));
		}
	}
	
	@Override
	public ScoreboardLine getLine(int id) {
		for (ScoreboardLine line : lines) {
			if (line.getId() == id) {
				return line;
			}
		}
		return null;
	}
	
	@Override
	public ScoreboardLine getLine(String content) {
		for (ScoreboardLine line : lines) {
			if (line.getContent().equals(content)) {
				return line;
			}
		}
		return null;
	}
	
	@Override
	public List<ScoreboardLine> getLines(DisplaySlot slot) {
		List<ScoreboardLine> lines = new ArrayList<ScoreboardLine>();
		for (ScoreboardLine line : this.lines) {
			if (line.getSlot() == slot) {
				lines.add(line);
			}
		}
		return lines;
	}
	
	@Override
	public org.bukkit.scoreboard.Scoreboard toBukkitScoreboard() {
		org.bukkit.scoreboard.Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
		
		Objective tab = sb.registerNewObjective("tab", "dummy");
		tab.setDisplaySlot(DisplaySlot.PLAYER_LIST);
		for (ScoreboardLine line : getLines(DisplaySlot.PLAYER_LIST)) {
			tab.getScore(line.getContent()).setScore(line.getId());
		}
		
		Objective name = sb.registerNewObjective("name", "dummy");
		name.setDisplaySlot(DisplaySlot.BELOW_NAME);
		for (ScoreboardLine line : getLines(DisplaySlot.BELOW_NAME)) {
			name.getScore(line.getContent()).setScore(line.getId());
		}
		
		Objective side = sb.registerNewObjective(title, "dummy");
		side.setDisplaySlot(DisplaySlot.SIDEBAR);
		for (ScoreboardLine line : getLines(DisplaySlot.SIDEBAR)) {
			side.getScore(line.getContent()).setScore(line.getId());
		}
		
		return sb;
	}
}
