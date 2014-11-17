package me.MiniDigger.CraftCore.Feature.Features;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;

import me.MiniDigger.CraftCore.Feature.CoreFeature;

public class LivesFeature extends CoreFeature {
	
	private HashMap<UUID, Integer>	lives	= new HashMap<UUID, Integer>();
	private int	                   max;
	
	public LivesFeature(Phase phase, int lives) {
		super(phase);
		this.max = lives;
	}
	
	public int getLives(UUID id) {
		return lives.get(id) == null ? -1 : lives.get(id);
	}
	
	public int getMaxLives() {
		return max;
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.LIVES;
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
			lives.put(id, max);
		}
	}
	
	@Override
	public void end() {
		lives = null;
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		if (getPhase().getGame().getPlayers().contains(e.getEntity().getUniqueId())) {
			int lives = this.lives.remove(e.getEntity().getUniqueId());
			if (lives == 1) {
				System.out.println("out!");
				// TODO Handle if a player has 0 lives
			} else {
				this.lives.put(e.getEntity().getUniqueId(), lives - 1);
			}
		}
	}
}
