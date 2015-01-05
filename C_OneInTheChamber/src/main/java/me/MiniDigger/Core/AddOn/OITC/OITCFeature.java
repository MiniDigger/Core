package me.MiniDigger.Core.AddOn.OITC;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Event.Events.CoreUserDeathEvent;
import me.MiniDigger.CraftCore.Feature.CoreFeature;
import me.MiniDigger.CraftCore.Feature.Features.LivesFeature;

public class OITCFeature extends CoreFeature {
	
	public OITCFeature(Phase phase) {
		super(phase);
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.OITC;
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
		new BukkitRunnable() {
			
			@Override
			public void run() {
				for (UUID id : getPhase().getGame().getPlayers()) {
					User u = Core.getCore().getUserHandler().get(id);
					giveItems(u.getPlayer());
				}
			}
		}.runTaskLater(Core.getCore().getInstance(), 20);
	}
	
	@Override
	public void end() {
		
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onDeath(PlayerRespawnEvent e) {
		if (getPhase().getGame().getPlayers().contains(e.getPlayer().getUniqueId())) {
			giveItems(e.getPlayer());
		}
	}
	
	@EventHandler
	public void onkill(CoreUserDeathEvent e) {
		try {
			giveItems(e.getKiller().getPlayer());
		} catch (Exception ex) {
		}
	}
	
	@EventHandler
	public void onBowHit(EntityDamageByEntityEvent e) {
		if (e.getDamager().getType() == EntityType.ARROW) {
			e.setDamage(1000.0);
			System.out.println("huge dmg!");
		}
	}
	
	public void giveItems(Player p) {
		int lives = ((LivesFeature) getPhase().getFeature(FeatureType.LIVES)).getLives(p.getUniqueId());
		p.getInventory().addItem(new ItemStack(Material.REDSTONE, lives));
		p.getInventory().addItem(new ItemStack(Material.WOOD_SWORD));
		p.getInventory().addItem(new ItemStack(Material.BOW));
		p.getInventory().addItem(new ItemStack(Material.ARROW));
		p.updateInventory();
	}
}
