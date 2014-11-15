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
 * Written by Martin Benndorf <admin@minidigger.me>, 2013-2014 and others
 */
package me.MiniDigger.CraftCore.Feature.Features;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Team.Team;
import me.MiniDigger.Core.User.User;
import me.MiniDigger.CraftCore.Feature.CoreFeature;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerRespawnEvent;

public class TeamDeathMatchFeature extends CoreFeature {
	
	private int	                   respawnCount;
	private HashMap<UUID, Integer>	respawns	= new HashMap<UUID, Integer>();
	
	public TeamDeathMatchFeature(final Phase phase, int respawns) {
		super(phase);
		this.respawnCount = respawns;
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
		for (UUID id : getPhase().getGame().getPlayers()) {
			respawns.put(id, respawnCount);
		}
	}
	
	@Override
	public void end() {
		
	}
	
	public int getRespawns(UUID id) {
		Integer i = respawns.get(id);
		return i == null ? -1 : i;
	}
	
	public void setRespawns(UUID id, int count) {
		respawns.remove(id);
		respawns.put(id, count);
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onDeath(PlayerRespawnEvent e) {
		if (getPhase().getGame().getPlayers().contains(e.getPlayer().getUniqueId())) {
			Integer count = respawns.remove(e.getPlayer().getUniqueId());
			if (count == null) {
				System.out.println(e.getPlayer().getName() + " died again!");
			} else {
				if (count == 0) {
					if (!isTeamAlive(e.getPlayer())) {
						TeamFeature tf = (TeamFeature) getPhase().getFeature(FeatureType.TEAM);
						Team t = tf.getTeam(e.getPlayer());
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
	
	private boolean isTeamAlive(Player p) {
		TeamFeature tf = (TeamFeature) getPhase().getFeature(FeatureType.TEAM);
		Team t = tf.getTeam(p);
		for (UUID id : t.getPlayers()) {
			if (respawns.containsKey(id)) {
				return true;
			}
		}
		return false;
	}
	
	private void checkEnd() {
		TeamFeature tf = (TeamFeature) getPhase().getFeature(FeatureType.TEAM);
		List<String> alive = new ArrayList<String>();
		for (Team t : tf.getTeams()) {
			for (UUID id : t.getPlayers()) {
				if (respawns.containsKey(id)) {
					if (!alive.contains(t.getName())) {
						alive.add(t.getName());
					}
				}
			}
		}
		
		if (alive.size() < 2) {
			List<User> user = new ArrayList<User>();
			for (UUID id : tf.getTeam(alive.get(0)).getPlayers()) {
				user.add(Core.getCore().getUserHandler().get(id));
			}
			
			getPhase().getGame().end(user.toArray(new User[user.size()]));
		}
	}
}
