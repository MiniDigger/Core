/**
 * █████████████████████████████████████████████████████████████████████████████████████████████████████████████████
 * █░░░░░░░░░░░░░░█░░░░░░██░░░░░░█░░░░░░░░░░░░░░████░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░░░███░░░░░░░░░░░░░░█
 * █░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █░░░░░░▄▀░░░░░░█░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░████████████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░████░░▄▀░░███░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░▄▀░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀░░███░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░▄▀░░█████░░▄▀░░░░░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░▄▀░░░░███░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░████████████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░██░░▄▀░░█████░░▄▀░░█████████
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░░░░░████░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░██░░▄▀░░░░░░█░░▄▀░░░░░░░░░░█
 * █████░░▄▀░░█████░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░████░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█
 * █████░░░░░░█████░░░░░░██░░░░░░█░░░░░░░░░░░░░░████░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░██░░░░░░░░░░█░░░░░░░░░░░░░░█
 * █████████████████████████████████████████████████████████████████████████████████████████████████████████████████
 *
 * Copyright © MiniDigger and others - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Martin Benndorf <admin@minidigger.me>, 2013-2015 and others
 */
package me.MiniDigger.Core.AddOn.Crank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Tasks.Task;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Event.CoreEventListener;
import me.MiniDigger.CraftCore.Event.Events.CoreUserDeathEvent;
import me.MiniDigger.CraftCore.Event.Events.CoreUserJoinGameEvent;
import me.MiniDigger.CraftCore.Event.Events.CoreUserLeaveGameEvent;
import me.MiniDigger.CraftCore.Feature.CoreFeature;

public class CrankFeature extends CoreFeature {

	private final int			crankTime;
	private HashMap<UUID, Task>	timers	= new HashMap<UUID, Task>();

	public CrankFeature(final Phase phase, final int crankTime) {
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
			Core.getCore().getTaskHandler().cancel(timers.get(id));
		}
		catch (final Exception ex) {}
		final Task timer = Core.getCore().getTaskHandler().runTaskTimer(new BukkitRunnable() {

			private int time = crankTime;

			@Override
			public void run() {
				time--;
				final User user = Core.getCore().getUserHandler().get(id);
				if (user == null) {
					return;
				}
				if (time == 0) {
					explode(id);
					cancel();
				} else {
					user.getPlayer().setLevel(time);
					user.getPlayer().setExp((float) (0.3 * time) / 10);
				}
			}
		}, 20, 20, getPhase());
		timers.put(id, timer);
	}

	public void explode(final UUID id) {
		CoreEventListener.clearLastDmg(id);
		final Player p = Bukkit.getPlayer(id);
		p.getWorld().playEffect(p.getLocation(), Effect.EXPLOSION_LARGE, 2);
		p.getWorld().playSound(p.getLocation(), Sound.EXPLODE, 10, 1);
		p.setHealth(0D);
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onDeath(final CoreUserDeathEvent e) {
		if (getPhase().getGame().getPlayers().contains(e.getUser().getUUID())) {
			reset(e.getUser().getUUID());
			if (e.getKiller() != null) {
				reset(e.getKiller().getUUID());
			}
		} else if (timers.containsKey(e.getUser().getUUID())) {
			Core.getCore().getTaskHandler().cancel(timers.remove(e.getUser().getUUID()));
		}
	}

	@EventHandler
	public void onQuit(final CoreUserLeaveGameEvent e) {
		if (e.getGame().getIdentifier() == getPhase().getGame().getIdentifier()) {
			Core.getCore().getTaskHandler().cancel(timers.remove(e.getUser().getUUID()));
		}
	}

	@EventHandler
	public void onJoin(final CoreUserJoinGameEvent e) {
		if (e.getGame().getIdentifier() == getPhase().getGame().getIdentifier()) {
			reset(e.getUser().getUUID());
		}
	}

	@Override
	public void end() {
		for (final Task r : timers.values()) {
			Core.getCore().getTaskHandler().cancel(r);
		}
		timers = null;
	}
}
