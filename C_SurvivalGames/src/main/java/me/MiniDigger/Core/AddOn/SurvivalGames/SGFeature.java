package me.MiniDigger.Core.AddOn.SurvivalGames;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.WorldBorder;

import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Map.MapData;
import me.MiniDigger.Core.Phase.Phase;

import me.MiniDigger.CraftCore.Feature.CoreFeature;
import me.MiniDigger.CraftCore.Feature.Features.MapFeature;

public class SGFeature extends CoreFeature {
	
	public SGFeature(final Phase phase) {
		super(phase);
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.SG;
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
		MapData m = ((MapFeature) getPhase().getFeature(FeatureType.MAP)).getMap();
		WorldBorder b = Bukkit.getWorld(m.getName()).getWorldBorder();
		b.setCenter(m.getLocs(DyeColor.BLACK).values().iterator().next());
		b.setSize(b.getCenter().distance(m.getLocs(DyeColor.BLUE).values().iterator().next()),0);
		b.setDamageBuffer(5.0);
		b.setWarningDistance(5);
		
	}
	
	@Override
	public void end() {
		
	}
	
}
