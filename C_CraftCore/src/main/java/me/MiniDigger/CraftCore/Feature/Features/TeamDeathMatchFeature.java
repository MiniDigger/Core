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

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerRespawnEvent;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Team.Team;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Event.Events.CoreUserJoinGameEvent;
import me.MiniDigger.CraftCore.Feature.CoreFeature;

public class TeamDeathMatchFeature extends CoreFeature {
	
	private final int	                 respawnCount;
	private final HashMap<UUID, Integer>	respawns	= new HashMap<UUID, Integer>();
	
	public TeamDeathMatchFeature(final Phase phase, final int respawns) {
		super(phase);
		respawnCount = respawns;
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.TEAM_DEATH_MATCH;
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
		for (final UUID id : getPhase().getGame().getPlayers()) {
			respawns.put(id, respawnCount);
		}
	}
	
	@Override
	public void end() {
		
	}
	
	public int getRespawns(final UUID id) {
		final Integer i = respawns.get(id);
		return i == null ? -1 : i;
	}
	
	public void setRespawns(final UUID id, final int count) {
		respawns.remove(id);
		respawns.put(id, count);
	}
	
	@EventHandler
	public void onUserJoin(CoreUserJoinGameEvent e) {
		if (e.getGame().getIdentifier() == getPhase().getGame().getIdentifier()) {
			respawns.put(e.getUser().getUUID(), respawnCount);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onDeath(final PlayerRespawnEvent e) {
		if (getPhase().getGame().getPlayers().contains(e.getPlayer().getUniqueId())) {
			final Integer count = respawns.remove(e.getPlayer().getUniqueId());
			if (count == null) {
				System.out.println(e.getPlayer().getName() + " died again!");
			} else {
				if (count == 0) {
					if (!isTeamAlive(e.getPlayer())) {
						final TeamFeature tf = (TeamFeature) getPhase().getFeature(FeatureType.TEAM);
						final Team t = tf.getTeam(e.getPlayer());
						System.out.println(t.getName() + "d raußen");
						getPhase().getGame().broadCastMessage(getPhase().getGame().getPrefix().then(t.getName()).color(t.getColor()).then(" ist draußen!"));
						checkEnd();
					} else {
						System.out.println("noch drinnen");
					}
					System.out.println("c");
				} else {
					respawns.put(e.getPlayer().getUniqueId(), count - 1);
				}
			}
		}
	}
	
	private boolean isTeamAlive(final Player p) {
		final TeamFeature tf = (TeamFeature) getPhase().getFeature(FeatureType.TEAM);
		final Team t = tf.getTeam(p);
		for (final UUID id : t.getPlayers()) {
			if (respawns.containsKey(id)) {
				return true;
			}
		}
		return false;
	}
	
	private void checkEnd() {
		final TeamFeature tf = (TeamFeature) getPhase().getFeature(FeatureType.TEAM);
		final List<String> alive = new ArrayList<String>();
		for (final Team t : tf.getTeams()) {
			for (final UUID id : t.getPlayers()) {
				if (respawns.containsKey(id)) {
					if (!alive.contains(t.getName())) {
						alive.add(t.getName());
					}
				}
			}
		}
		
		if (alive.size() < 2) {
			final List<User> user = new ArrayList<User>();
			for (final UUID id : tf.getTeam(alive.get(0)).getPlayers()) {
				user.add(Core.getCore().getUserHandler().get(id));
			}
			
			getPhase().getGame().end(user.toArray(new User[user.size()]));
		}
	}
}
