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
import me.MiniDigger.CraftCore.Feature.CoreFeature;
import me.MiniDigger.CraftCore.Team.CoreTeam;

import org.bukkit.entity.Player;

public class TeamSelectFeature extends CoreFeature {
	
	private List<Team>	teams	= new ArrayList<>();
	private int	       teamSize;
	private int	       teamCount;
	
	public TeamSelectFeature(Phase next, int teamSize, int teamCount) {
		super(next);
		this.teamSize = teamSize;
		this.teamCount = teamCount;
	}
	
	public int getTeamSize() {
		return teamSize;
	}
	
	public int getTeamCount() {
		return teamCount;
	}
	
	public Team getTeam(String name) {
		for (Team t : teams) {
			if (t.getName().equalsIgnoreCase(name)) {
				return t;
			}
		}
		return null;
	}
	
	public Team getTeam(Player p) {
		for (Team t : teams) {
			if (t.getPlayers().contains(p.getUniqueId())) {
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
		for (int i = 0; i < teamCount; i++) {
			teams.add(new CoreTeam(teamSize));
		}
	}
	
	@Override
	public void end() {
		HashMap<String, Integer> sizes = new HashMap<>();
		
		for (Team t : teams) {
			sizes.put(t.getName(), t.getPlayers().size());
		}
		
		balance();
		
		sizes =  calcSizes();
	}
	
	public HashMap<String, Integer> calcSizes() {
		HashMap<String, Integer> sizes = new HashMap<>();
		
		for (Team t : teams) {
			sizes.put(t.getName(), t.getPlayers().size());
		}
		return sizes;
	}
	
	public void balance() {
		HashMap<String, Integer> sizes = calcSizes();
		String large = findLargest(sizes);
		int largeCount = sizes.get(large);
		
		String small = findSmallest(sizes);
		int smallCount = sizes.get(small);
		
		System.out.println("LARGE: " + large + " : " + largeCount);
		System.out.println("SMALL: " + small + " : " + smallCount);
		
		if (!(largeCount == smallCount || largeCount == smallCount + 1)) {
			Team largeT = getTeam(large);
			Team smallT = getTeam(small);
			
			boolean switched = false;
			for (int i = largeT.getPlayers().size() - 1; i > 0; i--) {
				UUID player = largeT.getPlayers().get(i);
				System.out.println("SWITCH: " + player + " from " + large + " to " + small);
				if (Core.getCore().getSquadHandler().getSquad(player) == null) {
					largeT.leave(player);
					smallT.join(player);
					switched = true;
					break;
				}
			}
			
			if (!switched) {
				System.out.println(largeT.getName());
				System.out.println(largeT.getPlayers().size());
				UUID player = largeT.getPlayers().get(largeT.getPlayers().size() - 1);
				largeT.leave(player);
				smallT.join(player);
			}
			
			balance();
		}
	}
	
	private String findLargest(HashMap<String, Integer> sizes) {
		int largest = 0;
		String name = "";
		
		for (String s : sizes.keySet()) {
			int x = sizes.get(s);
			if (x > largest) {
				largest = x;
				name = s;
			}
		}
		return name;
	}
	
	private String findSmallest(HashMap<String, Integer> sizes) {
		int largest = 100000;
		String name = "";
		
		for (String s : sizes.keySet()) {
			int x = sizes.get(s);
			if (x < largest) {
				largest = x;
				name = s;
			}
		}
		return name;
	}
	
}
