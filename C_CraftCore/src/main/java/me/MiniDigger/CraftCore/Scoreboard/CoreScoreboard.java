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
import me.MiniDigger.Core.Scoreboard.ScoreboardTitle;

public class CoreScoreboard implements Scoreboard {
	
	private final List<ScoreboardLine>	lines	 = new ArrayList<ScoreboardLine>();
	private final List<ScoreboardLine>	scolling	= new ArrayList<ScoreboardLine>();
	
	private ScoreboardTitle	           sidebar;
	private ScoreboardTitle	           belowname;
	private ScoreboardTitle	           playerlist;
	
	private final BukkitRunnable	   task;
	
	public CoreScoreboard() {
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
	public void setTitle(final ScoreboardTitle title) {
		switch (title.getSlot()) {
		case BELOW_NAME:
			belowname = title;
			return;
		case PLAYER_LIST:
			playerlist = title;
			return;
		case SIDEBAR:
			sidebar = title;
			return;
		}
		sidebar = title;
	}
	
	@Override
	public ScoreboardTitle getTitle(DisplaySlot slot) {
		switch (slot) {
		case BELOW_NAME:
			return belowname;
		case PLAYER_LIST:
			return playerlist;
		case SIDEBAR:
			return sidebar;
		}
		return sidebar;
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
		
		String add = "";
		if (sb.getObjective(playerlist.getTitle()) != null) {
			add += " ";
		}
		
		final Objective tab = sb.registerNewObjective(playerlist.getTitle() + add, "dummy");
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
		
		add = "";
		if (sb.getObjective(belowname.getTitle()) != null) {
			add += " ";
		}
		
		final Objective name = sb.registerNewObjective(belowname.getTitle() + add, "dummy");
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
		
		add = "";
		if (sb.getObjective(sidebar.getTitle()) != null) {
			add += " ";
		}
		
		final Objective side = sb.registerNewObjective(sidebar.getTitle() + add, "dummy");
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
