package me.MiniDigger.AddOn.UltraSpleef;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Item.ItemType;
import me.MiniDigger.Core.Phase.Phase;

import me.MiniDigger.CraftCore.Feature.CoreFeature;

public class UltraSpleefFeature extends CoreFeature {
	
	public UltraSpleefFeature(final Phase phase) {
		super(phase);
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.ULTRASPLEEF;
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
			final Player p = Bukkit.getPlayer(id);
			p.getInventory().addItem(ItemType.GRAVITYGUN.getItem().getItem());
			p.updateInventory();
		}
	}
	
	@Override
	public void end() {
		
	}
	
	@EventHandler
	public void onMove(final PlayerMoveEvent e) {
		if (e.getFrom().getBlockY() >= 130 && e.getTo().getBlockY() <= 130) {
			if (getPhase().getGame().getPlayers().contains(e.getPlayer().getUniqueId())) {
				e.getPlayer().damage(1000.0);
			}
		}
	}
}
