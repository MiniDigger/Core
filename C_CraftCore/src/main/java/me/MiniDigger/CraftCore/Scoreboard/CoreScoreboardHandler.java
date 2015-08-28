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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.NameTagVisibility;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Scoreboard.Scoreboard;
import me.MiniDigger.Core.Scoreboard.ScoreboardHandler;
import me.MiniDigger.Core.Scoreboard.ScoreboardTeam;
import me.MiniDigger.Core.User.User;

public class CoreScoreboardHandler implements ScoreboardHandler {

	private final Map<UUID, Scoreboard> boards = new HashMap<UUID, Scoreboard>();
	private final List<ScoreboardTeam> teams = new ArrayList<ScoreboardTeam>();

	@Override
	public Scoreboard getBoard(final UUID id) {
		if (!boards.containsKey(id)) {
			boards.put(id, new CoreScoreboard());
		}
		return boards.get(id);
	}

	@Override
	public Set<UUID> getIds() {
		return boards.keySet();
	}

	@Override
	public void update(final UUID id) {
		final Player p = Bukkit.getPlayer(id);
		p.setScoreboard(getBoard(id).toBukkitScoreboard(teams));
	}

	@Override
	public void recalc() {
		for (User user : Core.getCore().getUserHandler().getOnlineUsers()) {
			getTeam(user.getRealName());
		}
		updateAll();
	}

	@Override
	public void updateAll() {
		for (final Player p : Core.getCore().getUserHandler().getOnlinePlayers()) {
			update(p.getUniqueId());
		}
	}

	@Override
	public void clearAll() {
		for (final Player p : Core.getCore().getUserHandler().getOnlinePlayers()) {
			getBoard(p.getUniqueId()).clear();
			update(p.getUniqueId());
		}
	}

	@Override
	public void clearAll(final DisplaySlot slot) {
		for (final Player p : Core.getCore().getUserHandler().getOnlinePlayers()) {
			getBoard(p.getUniqueId()).clear(slot);
			update(p.getUniqueId());
		}
	}

	@Override
	public ScoreboardTeam getTeam(final String name) {
		for (final ScoreboardTeam t : teams) {
			if (t.getName().equalsIgnoreCase(name)) {
				return t;
			}
		}
		final User user = Core.getCore().getUserHandler().getFromDisplayName(name);
		final ScoreboardTeam t = new CoreScoreboardTeam();
		t.setName(name);
		t.setNameTagVisibility(NameTagVisibility.ALWAYS);
		t.addPlayer(user.getUUID());
//		t.setPrefix(user.getPrefix()); //TODO Fix prefix system first...
//		t.setSuffix(user.getSuffix());
//		Core.getCore().getInstance()
//				.debug("Added to sb: " + t.getPrefix() + " " + name + " " + t.getSuffix());
		teams.add(t);
		return t;
	}
}
