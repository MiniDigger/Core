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
package me.MiniDigger.CraftCore.Feature.Features;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.Tasks.Task;
import me.MiniDigger.Core.User.User;
import me.MiniDigger.CraftCore.Feature.CoreFeature;

public class LadderKingFeature extends CoreFeature {

	private UUID					king;
	private String					kingname;
	private final Map<UUID, Task>	tasks	= new HashMap<UUID, Task>();

	public LadderKingFeature(final Phase phase) {
		super(phase);
	}

	@Override
	public FeatureType getType() {
		return FeatureType.LADDERKING;
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

	}

	@Override
	public void end() {

	}

	@EventHandler
	public void onPlayerMove(final PlayerMoveEvent e) {
		if (e.getTo().getBlock() != null && e.getTo().getBlock().getType() == Material.GOLD_PLATE) {
			if (getPhase().getGame().getPlayers().contains(e.getPlayer().getUniqueId())) {
				if (!e.getPlayer().getUniqueId().equals(king) && !e.getPlayer().getDisplayName().equals(kingname)) {
					if (king == null) {
						final User k = Core.getCore().getUserHandler().get(e.getPlayer().getUniqueId());
						getPhase().getGame().broadCastMessage(Prefix.API.getPrefix().then(k.getDisplayName()).color(ChatColor.YELLOW).then(" ist der neue König!").color(ChatColor.GOLD));
						king = k.getUUID();
						kingname = k.getPlayer().getDisplayName();
					} else {
						final User o = Core.getCore().getUserHandler().get(king);
						final User k = Core.getCore().getUserHandler().get(e.getPlayer().getUniqueId());
						getPhase().getGame().broadCastMessage(Prefix.API.getPrefix().then(o.getDisplayName()).color(ChatColor.YELLOW).then(" ist kein Ladderkönig mehr. ").color(ChatColor.GOLD).then(k.getDisplayName())
								.color(ChatColor.YELLOW).then(" ist der neue König!").color(ChatColor.GOLD));
						king = k.getUUID();
						kingname = k.getPlayer().getDisplayName();
					}

					startTask();
				}
			}
		}
	}

	public void startTask() {
		for (final Task r : tasks.values()) {
			Core.getCore().getTaskHandler().cancel(r);
		}
		tasks.clear();

		tasks.put(king, Core.getCore().getTaskHandler().runTaskLater(new BukkitRunnable() {

			@Override
			public void run() {
				if (king == null) {
					return;
				}
				final User o = Core.getCore().getUserHandler().get(king);
				if (o.getPlayer().getLocation().getBlock() == null || o.getPlayer().getLocation().getBlock().getType() != Material.GOLD_PLATE) {
					king = null;
					kingname = null;
					getPhase().getGame().broadCastMessage(Prefix.API.getPrefix().then(o.getDisplayName()).color(ChatColor.YELLOW).then(" hat seinen Thron verlassen!").color(ChatColor.GOLD));
				} else {
					start();
				}
			}
		}, 5 * 20, getPhase()));
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPvP(final EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			final User damager = Core.getCore().getUserHandler().get(((Player) e.getDamager()).getUniqueId());
			final User damaged = Core.getCore().getUserHandler().get(((Player) e.getEntity()).getUniqueId());

			if (getPhase().getGame().getPlayers().contains(damaged.getUUID()) && getPhase().getGame().getPlayers().contains(damager.getUUID())) {
				if (damager.getUUID().equals(king)) {
					e.setDamage(1.0);
					e.setCancelled(false);
					System.out.println("damager is king");
				} else if (damaged.getUUID().equals(king)) {
					e.setDamage(1.0);
					e.setCancelled(false);
					System.out.println("damaged is king");
				}
			}
		}
	}
}
