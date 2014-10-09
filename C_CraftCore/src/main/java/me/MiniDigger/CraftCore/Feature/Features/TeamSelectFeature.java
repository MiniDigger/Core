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
	
	protected List<Team>	teams	= new ArrayList<>();
	protected int	     teamSize;
	protected int	     teamCount;
	
	public TeamSelectFeature(final Phase next, final int teamSize, final int teamCount) {
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
		
		for (final Team t : teams) {
			sizes.put(t.getName(), t.getPlayers().size());
		}
		
		balance();
		
		sizes = calcSizes();
	}
	
	public HashMap<String, Integer> calcSizes() {
		final HashMap<String, Integer> sizes = new HashMap<>();
		
		for (final Team t : teams) {
			sizes.put(t.getName(), t.getPlayers().size());
		}
		return sizes;
	}
	
	public void balance() {
		final HashMap<String, Integer> sizes = calcSizes();
		final String large = findLargest(sizes);
		final int largeCount = sizes.get(large);
		
		final String small = findSmallest(sizes);
		final int smallCount = sizes.get(small);
		
		System.out.println("LARGE: " + large + " : " + largeCount);
		System.out.println("SMALL: " + small + " : " + smallCount);
		
		if (!(largeCount == smallCount || largeCount == smallCount + 1)) {
			final Team largeT = getTeam(large);
			final Team smallT = getTeam(small);
			
			boolean switched = false;
			for (int i = largeT.getPlayers().size() - 1; i > 0; i--) {
				final UUID player = largeT.getPlayers().get(i);
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
			if (x > largest) {
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
			if (x < largest) {
				largest = x;
				name = s;
			}
		}
		return name;
	}
	
}
