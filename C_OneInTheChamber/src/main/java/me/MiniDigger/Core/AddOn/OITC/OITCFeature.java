package me.MiniDigger.Core.AddOn.OITC;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

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
		for (UUID id : getPhase().getGame().getPlayers()) {
			User u = Core.getCore().getUserHandler().get(id);
			giveItems(u);
		}
	}
	
	@Override
	public void end() {
		
	}
	
	@EventHandler(priority=EventPriority.LOW)
	public void onDeath(CoreUserDeathEvent e) {
		if (e.shouldRespawn()) {
			e.setKeepDrops(true);
			giveItems(e.getUser());
		}
	}
	
	@EventHandler
	public void onBowHit(EntityDamageByEntityEvent e){
		if(e.getEntityType() == EntityType.ARROW){
			e.setDamage(1000.0);
		}
	}
	
	public void giveItems(User u) {
		int lives = ((LivesFeature) getPhase().getFeature(FeatureType.LIVES)).getLives(u.getUUID());
		u.getPlayer().getInventory().addItem(new ItemStack(Material.REDSTONE, lives));
		u.getPlayer().getInventory().addItem(new ItemStack(Material.WOOD_SWORD));
		u.getPlayer().getInventory().addItem(new ItemStack(Material.BOW));
		u.getPlayer().getInventory().addItem(new ItemStack(Material.ARROW));
	}
}
