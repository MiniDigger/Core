package me.MiniDigger.CraftCore.Feature.Features;

import java.util.ArrayList;
import java.util.List;

import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.CraftCore.Feature.CoreFeature;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class MobFeature extends CoreFeature {
	
	private String	                              world;
	private List<EntityType>	allowed;
	
	public MobFeature(Phase phase, List<EntityType> allowed) {
		super(phase);
		this.allowed = allowed;
	}
	
	public List<EntityType> getAllowed(){
		return allowed;
	}
	
	public void setAllowed(List<EntityType> allowed){
		this.allowed = allowed;
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.MOB;
	}
	
	@Override
	public List<FeatureType> getDependencies() {
		List<FeatureType> result = new ArrayList<>();
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
		MapFeature m = (MapFeature) getPhase().getFeature(FeatureType.MAP);
		if (m == null) {
			System.out.println("m = null");
		} else if (m.getMap() == null) {
			System.out.println("map = null");
		}
		world = m.getMap().getName();
	}
	
	@Override
	public void end() {
		
	}
	
	@EventHandler
	public void onEntitySpawn(CreatureSpawnEvent e) {
		if (e.getLocation().getWorld().getName().equals(world)) {
			if (!allowed.contains(e.getEntityType())) {
				e.setCancelled(true);
			}
		}
	}
	
}
