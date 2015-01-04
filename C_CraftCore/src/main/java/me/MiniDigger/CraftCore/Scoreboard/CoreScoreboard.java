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
package me.MiniDigger.CraftCore.Scoreboard;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Scoreboard.Scoreboard;
import me.MiniDigger.Core.Scoreboard.ScoreboardLine;

public class CoreScoreboard implements Scoreboard {
	
	private final List<ScoreboardLine>	lines	 = new ArrayList<ScoreboardLine>();
	private final List<ScoreboardLine>	scolling	= new ArrayList<ScoreboardLine>();
	private String	                   title;
	private final BukkitRunnable	   task;
	
	public CoreScoreboard(final String title) {
		this.title = title;
		task = new BukkitRunnable() {
			
			@Override
			public void run() {
				for (final ScoreboardLine line : scolling) {
					line.scroll();
				}
			}
		};
		task.runTaskTimer(Core.getCore().getInstance(), 1 * 20, 1 * 20);
	}
	
	@Override
	public void setTitle(final String title) {
		this.title = title;
	}
	
	@Override
	public String getTitle() {
		return title;
	}
	
	@Override
	public void addLine(final ScoreboardLine line) {
		lines.add(line);
	}
	
	@Override
	public void removeLine(final String content) {
		if (getLine(content) != null) {
			lines.remove(getLine(content));
		}
	}
	
	@Override
	public void removeLine(final int id) {
		if (getLine(id) != null) {
			lines.remove(getLine(id));
		}
	}
	
	@Override
	public ScoreboardLine getLine(final int id) {
		for (final ScoreboardLine line : lines) {
			if (line.getId() == id) {
				return line;
			}
		}
		return null;
	}
	
	@Override
	public ScoreboardLine getLine(final String content) {
		for (final ScoreboardLine line : lines) {
			if (line.getContent().equals(content)) {
				return line;
			}
		}
		return null;
	}
	
	@Override
	public List<ScoreboardLine> getLines(final DisplaySlot slot) {
		final List<ScoreboardLine> lines = new ArrayList<ScoreboardLine>();
		for (final ScoreboardLine line : this.lines) {
			if (line.getSlot() == slot) {
				lines.add(line);
			}
		}
		return lines;
	}
	
	@Override
	public org.bukkit.scoreboard.Scoreboard toBukkitScoreboard() {
		final org.bukkit.scoreboard.Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
		
		final Objective tab = sb.registerNewObjective("tab", "dummy");
		tab.setDisplaySlot(DisplaySlot.PLAYER_LIST);
		for (final ScoreboardLine line : getLines(DisplaySlot.PLAYER_LIST)) {
			if (line.scorlling()) {
				scolling.add(line);
			}
			tab.getScore(line.getContent()).setScore(line.getId());
		}
		
		if (getLines(DisplaySlot.PLAYER_LIST).size() == 0) {
			sb.clearSlot(DisplaySlot.PLAYER_LIST);
		}
		
		final Objective name = sb.registerNewObjective("name", "dummy");
		name.setDisplaySlot(DisplaySlot.BELOW_NAME);
		for (final ScoreboardLine line : getLines(DisplaySlot.BELOW_NAME)) {
			if (line.scorlling()) {
				scolling.add(line);
			}
			name.getScore(line.getContent()).setScore(line.getId());
		}
		
		if (getLines(DisplaySlot.BELOW_NAME).size() == 0) {
			sb.clearSlot(DisplaySlot.BELOW_NAME);
		}
		
		final Objective side = sb.registerNewObjective(title, "dummy");
		side.setDisplaySlot(DisplaySlot.SIDEBAR);
		for (final ScoreboardLine line : getLines(DisplaySlot.SIDEBAR)) {
			if (line.scorlling()) {
				scolling.add(line);
			}
			side.getScore(line.getContent()).setScore(line.getId());
		}
		
		if (getLines(DisplaySlot.SIDEBAR).size() == 0) {
			sb.clearSlot(DisplaySlot.SIDEBAR);
		}
		
		return sb;
	}
}
