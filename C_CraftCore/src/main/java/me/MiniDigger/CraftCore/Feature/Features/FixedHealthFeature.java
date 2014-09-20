package me.MiniDigger.CraftCore.Feature.Features;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.CraftCore.Feature.CoreFeature;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

public class FixedHealthFeature extends CoreFeature {
	
	public FixedHealthFeature(final Phase phase) {
		super(phase);
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.FIXEDHEALTH;
	}
	
	@Override
	public List<FeatureType> getDependencies() {
		return new ArrayList<>();
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
		for (final UUID id : getPhase().getGame().getPlayers()) {
			Bukkit.getPlayer(id).setHealth(20.0);
		}
	}
	
	@Override
	public void end() {
	}
	
	@EventHandler
	public void onDmg(final EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			final Player p = (Player) e.getEntity();
			if (getPhase().getGame().getPlayers().contains(p.getUniqueId())) {
				e.setCancelled(true);
				e.setDamage(0.0);
			}
		}
	}
	
}
