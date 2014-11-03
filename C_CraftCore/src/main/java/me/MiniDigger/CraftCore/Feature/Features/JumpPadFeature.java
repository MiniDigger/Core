/*******************************************************************************
 * Test
 *******************************************************************************/
package me.MiniDigger.CraftCore.Feature.Features;

import java.util.ArrayList;
import java.util.List;

import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.CraftCore.Feature.CoreFeature;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class JumpPadFeature extends CoreFeature {
	
	public JumpPadFeature(final Phase phase) {
		super(phase);
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.JUMPPAD;
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
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onStep(final PlayerInteractEvent event) {
		if (!getPhase().getGame().getPlayers().contains(event.getPlayer().getUniqueId())) {
			return;
		}
		if (event.getAction() == Action.PHYSICAL) {
			if (event.getClickedBlock().getType() != Material.WOOD_PLATE && event.getClickedBlock().getType() != Material.STONE_PLATE) {
				return;
			}
			double strength = 1.5;
			double up = 1;
			if (event.getClickedBlock().getRelative(BlockFace.DOWN, 2).getState() instanceof Sign) {
				final Sign sign = (Sign) event.getClickedBlock().getRelative(BlockFace.DOWN, 2).getState();
				if (sign.getLine(0).contains("[Boom]")) {
					try {
						strength = Double.parseDouble(sign.getLine(1));
						up = Double.parseDouble(sign.getLine(2));
					} catch (final Exception ex) {
						
					}
				}
			}
			
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENDERDRAGON_HIT, 10.0F, 1.0F);
			event.getPlayer().playEffect(event.getPlayer().getLocation(), Effect.SMOKE, 10);
			final Vector v = event.getPlayer().getLocation().getDirection().multiply(strength).setY(up);
			event.getPlayer().setVelocity(v);
			event.setCancelled(true);
		}
	}
}
