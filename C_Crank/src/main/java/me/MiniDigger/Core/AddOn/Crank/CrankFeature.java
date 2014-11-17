package me.MiniDigger.Core.AddOn.Crank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Achievement;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Feature.CoreFeature;

public class CrankFeature extends CoreFeature {
	
	private int	                          crankTime;
	private HashMap<UUID, BukkitRunnable>	timers	= new HashMap<UUID, BukkitRunnable>();
	
	public CrankFeature(Phase phase, int crankTime) {
		super(phase);
		this.crankTime = crankTime;
	}
	
	public int getCrankTime() {
		return crankTime;
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.CRANK;
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
			reset(id);
		}
	}
	
	public void reset(final UUID id) {
		Bukkit.getPlayer(id).getActivePotionEffects().clear();
		Bukkit.getPlayer(id).addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 30, 1));
		try {
			timers.get(id).cancel();
		} catch (Exception ex) {}
		BukkitRunnable timer = new BukkitRunnable() {
			
			private int	time	= crankTime;
			
			@Override
			public void run() {
				time--;
				User user = Core.getCore().getUserHandler().get(id);
				if (time == 0) {
					explode(id);
					cancel();
				} else {
					user.getPlayer().setLevel(time);
					user.getPlayer().setExp(time / 100);
				}
			}
		};
		timer.runTaskTimer((Plugin) Core.getCore().getInstance(), 1 * 20, 1 * 20);
		timers.put(id, timer);
	}
	
	public void explode(UUID id) {
		Player p = Bukkit.getPlayer(id);
		p.getWorld().playEffect(p.getLocation(), Effect.EXPLOSION_LARGE, 2);
		p.getWorld().playSound(p.getLocation(), Sound.EXPLODE, 10, 1);
		p.setHealth(0D);
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		if (getPhase().getGame().getPlayers().contains(e.getEntity().getUniqueId())) {
			User user = Core.getCore().getUserHandler().get(e.getEntity().getUniqueId());
			
		}
	}
	
	@Override
	public void end() {
		timers = null;
	}
}
