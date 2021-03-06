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
package me.MiniDigger.CraftCore.Feature.Features;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.scheduler.BukkitRunnable;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Game.GameType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Team.Team;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Event.Events.CoreUserLeaveGameEvent;
import me.MiniDigger.CraftCore.Feature.CoreFeature;
import me.MiniDigger.CraftCore.Team.CoreTeam;

public class TeamSelectFeature extends CoreFeature {

	protected List<Team>	teams		= new ArrayList<>();
	protected int			teamSize;
	protected int			teamCount;
	protected Phase			next;
	private List<UUID>		exceptions	= new ArrayList<UUID>();

	public TeamSelectFeature(final Phase p, final Phase next, final int teamSize, final int teamCount) {
		super(p);
		this.teamSize = teamSize;
		this.teamCount = teamCount;
		this.next = next;
	}

	public TeamSelectFeature(final Phase p, final Phase next, final int teamSize, final int teamCount, final List<UUID> exceptions) {
		this(p, next, teamSize, teamCount);
		this.exceptions = exceptions;
	}

	public void addException(final UUID id) {
		exceptions.add(id);
	}

	public int getTeamSize() {
		return teamSize;
	}

	public int getTeamCount() {
		return teamCount;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public Team getTeam(final String name) {
		for (final Team t : teams) {
			if (t.getName().equalsIgnoreCase(name)) {
				return t;
			}
		}
		return null;
	}

	public Team getTeam(final Player p) {
		for (final Team t : teams) {
			if (t.getPlayers().contains(p.getUniqueId())) {
				return t;
			}
		}
		return null;
	}

	public Team getTeam(final User user) {
		for (final Team t : teams) {
			if (t.getPlayers().contains(user.getUUID())) {
				return t;
			}
		}
		return null;
	}

	@Override
	public FeatureType getType() {
		return FeatureType.TEAM_SELECT;
	}

	@Override
	public List<FeatureType> getDependencies() {
		return new ArrayList<FeatureType>();
	}

	@Override
	public List<FeatureType> getSoftDependencies() {
		return new ArrayList<FeatureType>();
	}

	@Override
	public List<FeatureType> getIncompabilities() {
		return new ArrayList<FeatureType>();
	}

	@Override
	public void start() {
		final List<String> teamNames = new ArrayList<String>();
		teamNames.add("BLUE");
		teamNames.add("RED");
		teamNames.add("GREEN");
		teamNames.add("YELLOW");
		teamNames.add("LIGHT_PURPLE");
		teamNames.add("BLACK");

		final List<ChatColor> teamColors = new ArrayList<ChatColor>();
		teamColors.add(ChatColor.BLUE);
		teamColors.add(ChatColor.RED);
		teamColors.add(ChatColor.GREEN);
		teamColors.add(ChatColor.YELLOW);
		teamColors.add(ChatColor.LIGHT_PURPLE);
		teamColors.add(ChatColor.BLACK);

		for (int i = 0; i < teamCount; i++) {
			final Team t = new CoreTeam(teamSize, teamNames.get(i), teamColors.get(i), getPhase().getGame());
			teams.add(t);
		}
	}

	@EventHandler
	public void onQuit(final CoreUserLeaveGameEvent e) {
		if (e.getGame().getIdentifier() == getPhase().getGame().getIdentifier()) {
			getTeam(e.getUser()).leave(e.getUser().getUUID());
		}
	}

	@Override
	public void end() {
		HashMap<String, Integer> sizes = calcSizes();

		if (getPhase().getGame().getType() == GameType.SUV) {
			new BukkitRunnable() {

				@Override
				public void run() {
					Core.getCore().getInstance().debug("starting suv part");

					for (final Team t : teams) {
						t.getPlayers().clear();
					}
					teams.clear();
					teams = new ArrayList<Team>();

					final List<UUID> exceptions2 = new ArrayList<UUID>();
					Core.getCore().getInstance().debug("size1: " + exceptions.size());
					for (final UUID id : exceptions) {
						if (id != null) {
							exceptions2.add(id);
							Core.getCore().getInstance().debug("add " + id);
						}
					}
					Core.getCore().getInstance().debug("size12: " + exceptions2.size());

					for (final UUID id : exceptions2) {
						final User u = Core.getCore().getUserHandler().get(id);
						final Team t = new CoreTeam(teamSize, u.getDisplayName(), ChatColor.GREEN, getPhase().getGame());
						Core.getCore().getInstance().debug("add team " + u.getDisplayName());
						teams.add(t);
					}

					try {
						teams.get(1).setColor(ChatColor.LIGHT_PURPLE);
						teams.get(0).setColor(ChatColor.GREEN);
					}
					catch (final Exception ex) {

					}

					HashMap<String, Integer> sizes = calcSizes();

					for (final UUID id : getPhase().getGame().getPlayers()) {
						if (exceptions2.contains(id)) {
							continue;
						}
						getTeam(findSmallest(sizes)).join(id);

						sizes = calcSizes();
					}

					balance();
					sizes = calcSizes();

					next.init();

					((TeamFeature) next.getFeature(FeatureType.TEAM)).setTeams(teams);
					((TeamFeature) next.getFeature(FeatureType.TEAM)).setExceptions(exceptions2);

					for (final Team t : teams) {
						Core.getCore().getInstance().debug("Team: " + t.getName());
						for (final UUID id : t.getPlayers()) {
							Core.getCore().getInstance().debug(Core.getCore().getUserHandler().get(id).getDisplayName());
						}
					}
				}
			}.runTaskLater(Core.getCore().getInstance(), 10);
			return;
		}

		for (final UUID id : getPhase().getGame().getPlayers()) {
			if (getTeam(Core.getCore().getUserHandler().get(id)) == null) {
				if (exceptions.contains(id)) {
					continue;
				}
				final Team t = getTeam(findSmallest(sizes));
				t.join(id);
				sizes = calcSizes();
			}
		}

		balance();

		sizes = calcSizes();

		next.init();

		((TeamFeature) next.getFeature(FeatureType.TEAM)).setTeams(teams);
		((TeamFeature) next.getFeature(FeatureType.TEAM)).setExceptions(exceptions);
	}

	public HashMap<String, Integer> calcSizes() {
		final HashMap<String, Integer> sizes = new HashMap<>();

		for (final Team t : teams) {
			sizes.put(t.getName(), t.getPlayers().size());
		}
		return sizes;
	}

	public void balance() {
		// TODO Balancing by stats
		final HashMap<String, Integer> sizes = calcSizes();
		final String large = findLargest(sizes);
		if (large == null || large.equals("")) {
			Core.getCore().getInstance().debug("no balancing");
			for (final Team t : teams) {
				Core.getCore().getInstance().debug(t.getName() + ": " + t.getPlayers().size());
			}
			return;
		}
		final int largeCount = sizes.get(large);

		final String small = findSmallest(sizes);
		final int smallCount = sizes.get(small);

		Core.getCore().getInstance().debug("LARGE: " + large + " : " + largeCount);
		Core.getCore().getInstance().debug("SMALL: " + small + " : " + smallCount);

		if (!(largeCount == smallCount || largeCount == smallCount + 1)) {
			final Team largeT = getTeam(large);
			final Team smallT = getTeam(small);

			boolean switched = false;
			for (int i = largeT.getPlayers().size() - 1; i > 0; i--) {
				final UUID player = largeT.getPlayers().get(i);
				Core.getCore().getInstance().debug("SWITCH: " + player + " from " + large + " to " + small);
				if (Core.getCore().getSquadHandler().getSquad(player) == null) {
					largeT.leave(player);
					smallT.join(player);
					switched = true;
					break;
				}
			}

			if (!switched) {
				Core.getCore().getInstance().debug(largeT.getName());
				Core.getCore().getInstance().debug(largeT.getPlayers().size() + "");
				final UUID player = largeT.getPlayers().get(largeT.getPlayers().size() - 1);
				largeT.leave(player);
				smallT.join(player);
			}

			balance();
		}
	}

	private String findLargest(final HashMap<String, Integer> sizes) {
		int largest = 0;
		String name = "";

		for (final String s : sizes.keySet()) {
			final int x = sizes.get(s);
			if (x >= largest) {
				largest = x;
				name = s;
			}
		}
		return name;
	}

	private String findSmallest(final HashMap<String, Integer> sizes) {
		int largest = 100000;
		String name = "";

		for (final String s : sizes.keySet()) {
			final int x = sizes.get(s);
			if (x <= largest) {
				largest = x;
				name = s;
			}
		}
		Core.getCore().getInstance().debug("smallest = " + name);
		return name;
	}
}
