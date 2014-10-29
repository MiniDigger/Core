package me.MiniDigger.CraftCore.Feature.Features;

import java.util.ArrayList;
import java.util.List;

import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.CraftCore.Feature.CoreFeature;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class MobFeature extends CoreFeature {
	
	private String	         world;
	private List<EntityType>	allowed;
	
	public MobFeature(final Phase phase, final List<EntityType> allowed) {
		super(phase);
		this.allowed = allowed;
	}
	
	public List<EntityType> getAllowed() {
		return allowed;
	}
	
	public void setAllowed(final List<EntityType> allowed) {
		this.allowed = allowed;
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.MOB;
	}
	
	@Override
	public List<FeatureType> getDependencies() {
		final List<FeatureType> result = new ArrayList<>();
		result.add(FeatureType.MAP);
		return result;
	}
	
	@Override
	public List<FeatureType> getSoftDependencies() {
		return new ArrayList<>();
	}
	
	@Override
	public List<FeatureType> getIncompabilities() {
		return new ArrayList<>();
	}
	
	@Override
	public void start() {
		final MapFeature m = (MapFeature) getPhase().getFeature(FeatureType.MAP);
		if (m == null) {
			System.out.println("m = null");
		} else if (m.getMap() == null) {
			System.out.println("map = null");
		}
		world = m.getMap().getName();
		
		for(Entity e :  Bukkit.getWorld(world).getEntities()){
			if(!allowed.contains(e.getType())){
				e.remove();
			}
		}
	}
	
	@Override
	public void end() {
		
	}
	
	@EventHandler
	public void onEntitySpawn(final CreatureSpawnEvent e) {
		if (e.getLocation().getWorld().getName().equals(world)) {
			if (!allowed.contains(e.getEntityType()) && e.getSpawnReason() != SpawnReason.CUSTOM) {
				e.setCancelled(true);
			}
		}
	}
	
}
