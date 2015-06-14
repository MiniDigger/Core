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
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Map.MapData;
import me.MiniDigger.Core.Phase.Phase;

import me.MiniDigger.CraftCore.Feature.CoreFeature;

public class MapFeature extends CoreFeature {
	
	private MapData	map;
	private boolean	shouldUnload;
	
	public MapFeature(final Phase phase, final String map, final boolean shouldUnload) {
		super(phase);
		setMap(map);
		this.shouldUnload = shouldUnload;
	}
	
	public MapData getMap() {
		return map;
	}
	
	public boolean shouldUnload() {
		return shouldUnload;
	}
	
	public void setShouldUnload(final boolean unload) {
		shouldUnload = unload;
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.MAP;
	}
	
	@Override
	public List<FeatureType> getDependencies() {
		return new ArrayList<>();
	}
	
	@Override
	public List<FeatureType> getSoftDependencies() {
		final List<FeatureType> result = new ArrayList<>();
		result.add(FeatureType.VOTE);
		return result;
	}
	
	@Override
	public List<FeatureType> getIncompabilities() {
		return new ArrayList<>();
	}
	
	@Override
	public void start() {
		if (map != null) {
			map.clearLocs();
			map.smothLocs();
		}
	}
	
	@Override
	public void end() {
		if (shouldUnload) {
			unload();
		}
	}
	
	public void setMap(final String map) {
		if (map == null) {
			return;
		}
		
		String newMap = getName(map);
		System.out.println("set map: " + map + " as " + newMap);
		if (Core.getCore().getMapHandler().getMap(map) == null) {
			System.out.println("not loaded");
			if (Bukkit.getWorld(newMap) == null) {
				System.out.println("not loaded2");
				Core.getCore().getWorldHandler().copyWorld(map, newMap);
				System.out.println("loadss");
				Core.getCore().getWorldHandler().loadWorld(map, newMap);
			}
		}
		
		this.map = Core.getCore().getMapHandler().getMap(newMap);
		if (this.map == null) {
			this.map = Core.getCore().getMapHandler().getMap(map);
			if (this.map == null) {
				System.out.println("how...");
			}
		}
	}
	
	public String getName(final String map) {
		String name = map;
		if (name.equals("Lobby")) {
			name = getPhase().getGame().getType().getAbk() + "_Lobby";
		}
		
		System.out.println("1phase: " + getPhase().getName());
		System.out.println("1name: " + name);
		return name;
	}
	
	public String getName() {
		String name = "";
		if (map == null) {
			name = getPhase().getGame().getGameData("Lobby");
			if (name == null) {
				System.out.println("idk what to do...");
			}
		} else {
			name = map.getName();
		}
		
		if (name.equals("Lobby")) {
			name = getPhase().getGame().getType().getAbk() + "_Lobby";
		}
		
		System.out.println("phase: " + getPhase().getName());
		System.out.println("name: " + name);
		return name;
	}
	
	public void unload() {
		Core.getCore().getWorldHandler()
		        .unloadWorld(map.getName(), (Location) Core.getCore().getMapHandler().getMap(getName()).getLocs(DyeColor.RED).values().toArray()[0]);
		Core.getCore().getWorldHandler().deleteWorld(map.getName());
	}
}
