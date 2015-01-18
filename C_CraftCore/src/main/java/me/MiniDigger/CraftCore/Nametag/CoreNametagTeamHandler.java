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
	private final String	      TEAM_NAME_PREFIX	= "NTE";
	
	private List<Integer>	      list	           = new ArrayList<Integer>();
	
	private Map<Team, List<UUID>>	teams	       = new HashMap<Team, List<UUID>>();
	
	private void addToTeam(Team team, UUID id) {
		removeFromTeam(id);
		List<UUID> list = teams.get(team);
		if (list != null) {
			list.add(id);
			sendPacketsAddToTeam(team, id);
		}
	}
	
	private void register(Team team) {
		teams.put(team, new ArrayList<UUID>());
		sendPacketsAddTeam(team);
	}
	
	private void removeTeam(Team team) {
		sendPacketsRemoveTeam(team);
		teams.remove(team);
	}
	
	private Team removeFromTeam(UUID id) {
		for (Team team : teams.keySet()) {
			List<UUID> list = teams.get(team);
			for (UUID id2 : list) {
				if (id.equals(id2)) {
					sendPacketsRemoveFromTeam(team, id);
					list.remove(id);
					return team;
				}
			}
		}
		return null;
	}
	
	private Team getTeam(String name) {
		for (Team team : teams.keySet()) {
			if (team.name.equals(name)) {
				return team;
			}
		}
		return null;
	}
	
	private Team[] getTeams() {
		Team[] list = new Team[teams.size()];
		int at = 0;
		for (Team team : teams.keySet()) {
			list[at] = team;
			at++;
		}
		return list;
	}
	
	private UUID[] getTeamPlayers(Team team) {
		List<UUID> list = teams.get(team);
		if (list != null) {
			return list.toArray(new UUID[list.size()]);
		} else {
			return new UUID[0];
		}
	}
	
	@Override
	public void load() {
		for (Team t : getTeams()) {
			int entry = -1;
			try {
				entry = Integer.parseInt(t.name);
			} catch (Exception e) {}
			if (entry != -1) {
				list.add(entry);
			}
		}
	}
	
	@Override
	public void update(UUID id, String prefix, String suffix, boolean tag) {
		if (prefix == null || prefix.isEmpty()) {
			prefix = getPrefix(id);
			
		}
		
		if (suffix == null || suffix.isEmpty()) {
			suffix = getSuffix(id);
			
		}
		
		Team t = get(prefix, suffix);
		t.tag = tag;
		
		addToTeam(t, id);
	}
	
	@Override
	public void overlap(UUID id, String prefix, String suffix, boolean tag) {
		if (prefix == null) {
			prefix = "";
		}
		
		if (suffix == null) {
			suffix = "";
		}
		
		Team t = get(prefix, suffix);
		t.tag = tag;
		
		addToTeam(t, id);
	}
	
	@Override
	public void clear(UUID id) {
		removeFromTeam(id);
	}
	
	@Override
	public boolean getTag(UUID id) {
		for (Team team : getTeams()) {
			for (UUID id2 : getTeamPlayers(team)) {
				if (id2.equals(id)) {
					return team.tag;
				}
			}
		}
		return true;
	}
	
	@Override
	public String getPrefix(UUID id) {
		for (Team team : getTeams()) {
			for (UUID id2 : getTeamPlayers(team)) {
				if (id2.equals(id)) {
					return team.prefix;
				}
			}
		}
		return "";
	}
	
	@Override
	public String getSuffix(UUID id) {
		for (Team team : getTeams()) {
			for (UUID id2 : getTeamPlayers(team)) {
				if (id2.equals(id)) {
					return team.suffix;
				}
			}
		}
		return "";
	}
	
	@Override
	public String getFormattedName(UUID id) {
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
	 * @return the created team
	 */
	private Team declareTeam(String name, String prefix, String suffix) {
		if (getTeam(name) != null) {
			Team team = getTeam(name);
			removeTeam(team);
		}
		
		Team team = new Team();
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
	 * @return a team with the corresponding prefix/suffix
	 */
	private Team get(String prefix, String suffix) {
		update();
		
		for (int t : list) {
			if (getTeam(TEAM_NAME_PREFIX + t) != null) {
				Team team = getTeam(TEAM_NAME_PREFIX + t);
				
				if (team.suffix.equals(suffix) && team.prefix.equals(prefix)) {
					return team;
				}
			}
		}
		
		return declareTeam(TEAM_NAME_PREFIX + nextName(), prefix, suffix);
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
			for (int t : list.toArray(new Integer[list.size()])) {
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
		for (Team team : getTeams()) {
			int entry = -1;
			try {
				entry = Integer.parseInt(team.name);
			} catch (Exception e) {}
			if (entry != -1) {
				if (getTeamPlayers(team).length == 0) {
					removeTeam(team);
					list.remove(new Integer(entry));
				}
			}
		}
	}
	
	@Override
	public void sendTeamsToPlayer(Player p) {
		try {
			for (Team team : getTeams()) {
				CoreNametagPacketHandler mod = new CoreNametagPacketHandler(team.name, team.prefix, team.suffix, new ArrayList<String>(), 0, team.tag);
				mod.sendToPlayer(p);
				mod = new CoreNametagPacketHandler(team.name, Arrays.asList(getTeamPlayers(team)), 3);
				mod.sendToPlayer(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sends packets out to players to add the given team
	 * 
	 * @param team
	 *            the team to add
	 */
	private void sendPacketsAddTeam(Team team) {
		try {
			for (Player p : Core.getCore().getUserHandler().getOnlinePlayers()) {
				if (p != null) {
					CoreNametagPacketHandler mod = new CoreNametagPacketHandler(team.name, team.prefix, team.suffix, new ArrayList<String>(), 0, team.tag);
					mod.sendToPlayer(p);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sends packets out to players to remove the given team
	 * 
	 * @param team
	 *            the team to remove
	 */
	private void sendPacketsRemoveTeam(Team team) {
		boolean cont = false;
		for (Team t : getTeams()) {
			if (t == team) {
				cont = true;
			}
		}
		if (!cont) {
			return;
		}
		
		try {
			for (Player p : Core.getCore().getUserHandler().getOnlinePlayers()) {
				if (p != null) {
					CoreNametagPacketHandler mod = new CoreNametagPacketHandler(team.name, team.prefix, team.suffix, new ArrayList<String>(), 1, team.tag);
					mod.sendToPlayer(p);
				}
			}
		} catch (Exception e) {
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
	private void sendPacketsAddToTeam(Team team, UUID id) {
		boolean cont = false;
		for (Team t : getTeams()) {
			if (t == team) {
				cont = true;
			}
		}
		if (!cont) {
			return;
		}
		
		try {
			for (Player p : Core.getCore().getUserHandler().getOnlinePlayers()) {
				if (p != null) {
					CoreNametagPacketHandler mod = new CoreNametagPacketHandler(team.name, Arrays.asList(Core.getCore().getUserHandler().get(id).getDisplayName()), 3);
					mod.sendToPlayer(p);
				}
			}
		} catch (Exception e) {
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
	private void sendPacketsRemoveFromTeam(Team team, UUID id) {
		boolean cont = false;
		for (Team t : getTeams()) {
			if (t == team) {
				for (UUID id2 : getTeamPlayers(t)) {
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
			for (Player p : Core.getCore().getUserHandler().getOnlinePlayers()) {
				if (p != null) {
					CoreNametagPacketHandler mod = new CoreNametagPacketHandler(team.name, Arrays.asList(id), 4);
					
					mod.sendToPlayer(p);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void reset() {
		for (Team team : getTeams()) {
			removeTeam(team);
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		final Player p = e.getPlayer();
		
		sendTeamsToPlayer(p);
		clear(p.getUniqueId());
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				clear(p.getUniqueId());
				User u = Core.getCore().getUserHandler().get(p.getUniqueId());
				overlap(u.getUUID(), u.getPrefix(), u.getSuffix(), true);
			}
		}.runTaskLater(Core.getCore().getInstance(), 1);
	}
}
