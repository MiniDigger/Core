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
package me.MiniDigger.CraftCore.Nametag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Nametag.NametagTeamHandler;
import me.MiniDigger.Core.User.User;

public class CoreNametagTeamHandler implements NametagTeamHandler {
	
	class Team {
		
		String	name;
		String	prefix;
		String	suffix;
		boolean	tag;
	}
	
	// Prefix to append to all team names (nothing to do with prefix/suffix)
	private final String	            TEAM_NAME_PREFIX	= "NTE";
	
	private final List<Integer>	        list	         = new ArrayList<Integer>();
	
	private final Map<Team, List<UUID>>	teams	         = new HashMap<Team, List<UUID>>();
	
	private void addToTeam(final Team team, final UUID id) {
		removeFromTeam(id);
		final List<UUID> list = teams.get(team);
		if (list != null) {
			list.add(id);
			sendPacketsAddToTeam(team, id);
		}
	}
	
	private void register(final Team team) {
		teams.put(team, new ArrayList<UUID>());
		sendPacketsAddTeam(team);
	}
	
	private void removeTeam(final Team team) {
		sendPacketsRemoveTeam(team);
		teams.remove(team);
	}
	
	private Team removeFromTeam(final UUID id) {
		for (final Team team : teams.keySet()) {
			final List<UUID> list = teams.get(team);
			for (final UUID id2 : list) {
				if (id.equals(id2)) {
					sendPacketsRemoveFromTeam(team, id);
					list.remove(id);
					return team;
				}
			}
		}
		return null;
	}
	
	private Team getTeam(final String name) {
		for (final Team team : teams.keySet()) {
			if (team.name.equals(name)) {
				return team;
			}
		}
		return null;
	}
	
	private Team[] getTeams() {
		final Team[] list = new Team[teams.size()];
		int at = 0;
		for (final Team team : teams.keySet()) {
			list[at] = team;
			at++;
		}
		return list;
	}
	
	private UUID[] getTeamPlayers(final Team team) {
		final List<UUID> list = teams.get(team);
		if (list != null) {
			return list.toArray(new UUID[list.size()]);
		} else {
			return new UUID[0];
		}
	}
	
	private String[] getTeamPlayerNames(final Team team) {
		final List<UUID> list = teams.get(team);
		final List<String> result = new ArrayList<String>();
		if (list != null) {
			for (final UUID id : list) {
				result.add(Core.getCore().getUserHandler().get(id).getDisplayName());
			}
			return result.toArray(new String[result.size()]);
		} else {
			return new String[0];
		}
	}
	
	@Override
	public void load() {
		for (final Team t : getTeams()) {
			int entry = -1;
			try {
				entry = Integer.parseInt(t.name);
			} catch (final Exception e) {}
			if (entry != -1) {
				list.add(entry);
			}
		}
	}
	
	@Override
	public void update(final UUID id, String prefix, String suffix, final boolean tag) {
		if (prefix == null || prefix.isEmpty()) {
			prefix = getPrefix(id);
			
		}
		
		if (suffix == null || suffix.isEmpty()) {
			suffix = getSuffix(id);
			
		}
		
		final Team t = get(prefix, suffix, tag);
		
		addToTeam(t, id);
	}
	
	@Override
	public void overlap(final UUID id, String prefix, String suffix, final boolean tag) {
		if (prefix == null) {
			prefix = "";
		}
		
		if (suffix == null) {
			suffix = "";
		}
		
		final Team t = get(prefix, suffix, tag);
		
		addToTeam(t, id);
	}
	
	@Override
	public void clear(final UUID id) {
		removeFromTeam(id);
	}
	
	@Override
	public boolean getTag(final UUID id) {
		for (final Team team : getTeams()) {
			for (final UUID id2 : getTeamPlayers(team)) {
				if (id2.equals(id)) {
					return team.tag;
				}
			}
		}
		return true;
	}
	
	@Override
	public String getPrefix(final UUID id) {
		for (final Team team : getTeams()) {
			for (final UUID id2 : getTeamPlayers(team)) {
				if (id2.equals(id)) {
					return team.prefix;
				}
			}
		}
		return "";
	}
	
	@Override
	public String getSuffix(final UUID id) {
		for (final Team team : getTeams()) {
			for (final UUID id2 : getTeamPlayers(team)) {
				if (id2.equals(id)) {
					return team.suffix;
				}
			}
		}
		return "";
	}
	
	@Override
	public String getFormattedName(final UUID id) {
		return getPrefix(id) + Core.getCore().getUserHandler().get(id).getDisplayName() + getSuffix(id);
	}
	
	/**
	 * Declares a new team in the scoreboard.dat of the given main world.
	 * 
	 * @param name
	 *            the team name
	 * @param prefix
	 *            the team's prefix
	 * @param suffix
	 *            the team's suffix
	 * @param tag
	 * @return the created team
	 */
	private Team declareTeam(final String name, final String prefix, final String suffix, final boolean tag) {
		if (getTeam(name) != null) {
			final Team team = getTeam(name);
			removeTeam(team);
		}
		
		final Team team = new Team();
		team.tag = tag;
		team.name = name;
		team.prefix = prefix;
		team.suffix = suffix;
		
		register(team);
		
		return team;
	}
	
	/**
	 * Gets the {@link net.minecraft.server.v1_5_R3.ScoreboardTeam} for the
	 * given prefix and suffix, and if none matches, creates a new team with the
	 * provided info. This also removes teams that currently have no players.
	 * 
	 * @param prefix
	 *            the team's prefix
	 * @param suffix
	 *            the team's suffix
	 * @param tag
	 * @return a team with the corresponding prefix/suffix
	 */
	private Team get(final String prefix, final String suffix, final boolean tag) {
		update();
		
		for (final int t : list) {
			if (getTeam(TEAM_NAME_PREFIX + t) != null) {
				final Team team = getTeam(TEAM_NAME_PREFIX + t);
				
				if (team.suffix.equals(suffix) && team.prefix.equals(prefix) && team.tag == tag) {
					return team;
				}
			}
		}
		
		return declareTeam(TEAM_NAME_PREFIX + nextName(), prefix, suffix, tag);
	}
	
	/**
	 * Returns the next available team name that is not taken.
	 * 
	 * @return an integer that for a team name that is not taken.
	 */
	private int nextName() {
		int at = 0;
		boolean cont = true;
		while (cont) {
			cont = false;
			for (final int t : list.toArray(new Integer[list.size()])) {
				if (t == at) {
					at++;
					cont = true;
				}
				
			}
		}
		list.add(at);
		return at;
	}
	
	/**
	 * Removes any teams that do not have any players in them.
	 */
	private void update() {
		for (final Team team : getTeams()) {
			int entry = -1;
			try {
				entry = Integer.parseInt(team.name);
			} catch (final Exception e) {}
			if (entry != -1) {
				if (getTeamPlayers(team).length == 0) {
					removeTeam(team);
					list.remove(new Integer(entry));
				}
			}
		}
	}
	
	@Override
	public void sendTeamsToPlayer(final Player p) {
		try {
			for (final Team team : getTeams()) {
				CoreNametagPacketHandler mod = new CoreNametagPacketHandler(team.name, team.prefix, team.suffix, new ArrayList<String>(), 0, team.tag);
				mod.sendToPlayer(p);
				mod = new CoreNametagPacketHandler(team.name, Arrays.asList(getTeamPlayerNames(team)), 3);
				mod.sendToPlayer(p);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sends packets out to players to add the given team
	 * 
	 * @param team
	 *            the team to add
	 */
	private void sendPacketsAddTeam(final Team team) {
		try {
			for (final Player p : Core.getCore().getUserHandler().getOnlinePlayers()) {
				if (p != null) {
					final CoreNametagPacketHandler mod = new CoreNametagPacketHandler(team.name, team.prefix, team.suffix, new ArrayList<String>(), 0, team.tag);
					mod.sendToPlayer(p);
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sends packets out to players to remove the given team
	 * 
	 * @param team
	 *            the team to remove
	 */
	private void sendPacketsRemoveTeam(final Team team) {
		boolean cont = false;
		for (final Team t : getTeams()) {
			if (t == team) {
				cont = true;
			}
		}
		if (!cont) {
			return;
		}
		
		try {
			for (final Player p : Core.getCore().getUserHandler().getOnlinePlayers()) {
				if (p != null) {
					final CoreNametagPacketHandler mod = new CoreNametagPacketHandler(team.name, team.prefix, team.suffix, new ArrayList<String>(), 1, team.tag);
					mod.sendToPlayer(p);
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sends out packets to players to add the given player to the given team
	 * 
	 * @param team
	 *            the team to use
	 * @param player
	 *            the player to add
	 */
	private void sendPacketsAddToTeam(final Team team, final UUID id) {
		boolean cont = false;
		for (final Team t : getTeams()) {
			if (t == team) {
				cont = true;
			}
		}
		if (!cont) {
			return;
		}
		
		try {
			for (final Player p : Core.getCore().getUserHandler().getOnlinePlayers()) {
				if (p != null) {
					final CoreNametagPacketHandler mod = new CoreNametagPacketHandler(team.name, Arrays.asList(Core.getCore().getUserHandler().get(id).getDisplayName()),
					        3);
					mod.sendToPlayer(p);
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sends out packets to players to remove the given player from the given
	 * team.
	 * 
	 * @param team
	 *            the team to remove from
	 * @param player
	 *            the player to remove
	 */
	private void sendPacketsRemoveFromTeam(final Team team, final UUID id) {
		boolean cont = false;
		for (final Team t : getTeams()) {
			if (t == team) {
				for (final UUID id2 : getTeamPlayers(t)) {
					if (id2.equals(id)) {
						cont = true;
					}
				}
			}
		}
		
		if (!cont) {
			return;
		}
		
		try {
			for (final Player p : Core.getCore().getUserHandler().getOnlinePlayers()) {
				if (p != null) {
					final CoreNametagPacketHandler mod = new CoreNametagPacketHandler(team.name, Arrays.asList(Core.getCore().getUserHandler().get(id).getDisplayName()),
					        4);
					
					mod.sendToPlayer(p);
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void reset() {
		for (final Team team : getTeams()) {
			removeTeam(team);
		}
	}
	
	@EventHandler
	public void onJoin(final PlayerJoinEvent e) {
		final Player p = e.getPlayer();
		
		sendTeamsToPlayer(p);
		clear(p.getUniqueId());
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				clear(p.getUniqueId());
				final User u = Core.getCore().getUserHandler().get(p.getUniqueId());
				overlap(u.getUUID(), u.getPrefix(), u.getSuffix(), true);
			}
		}.runTaskLater(Core.getCore().getInstance(), 1);
	}
}
