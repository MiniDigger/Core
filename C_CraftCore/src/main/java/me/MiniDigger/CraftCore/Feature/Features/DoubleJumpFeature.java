package me.MiniDigger.CraftCore.Feature.Features;

import java.util.ArrayList;
import java.util.List;

import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.CraftCore.Feature.CoreFeature;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;


public class DoubleJumpFeature extends CoreFeature {
	
	public DoubleJumpFeature(Phase phase) {
		super(phase);
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.DOUBLEJUMP;
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
		
	}
	
	@Override
	public void end() {
		
	}
	
	@EventHandler
	public void e(final PlayerToggleFlightEvent event) {
		Player player = event.getPlayer();
		if (getPhase().getGame().getPlayers().contains(player.getUniqueId()) && player.getGameMode() != GameMode.CREATIVE) {
			event.setCancelled(true);
			player.setAllowFlight(false);
			player.setFlying(false);
			player.setVelocity(player.getLocation().getDirection().multiply(1.6).setY(1));
			player.getWorld().playSound(player.getLocation(), Sound.ENDERDRAGON_WINGS, 4, 1);
		}
	}
	
	@EventHandler
	public void e(final PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if (getPhase().getGame().getPlayers().contains(player.getUniqueId())) {
			if (player.getLocation().getBlock().getRelative(0, -1, 0).getType() != Material.AIR && (!player.isFlying())) {
				player.setAllowFlight(true);
			}
		}
	}
}