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
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Prefix.Prefix;

import me.MiniDigger.CraftCore.Event.Events.CoreUserDamageEvent;
import me.MiniDigger.CraftCore.Event.Events.CoreUserDeathEvent;
import me.MiniDigger.CraftCore.Feature.CoreFeature;

public class PvPFeature extends CoreFeature {

	private boolean	pvpEnabled;
	private boolean	broadCastMsg;

	public PvPFeature(final Phase phase, final boolean pvpEnabled) {
		super(phase);
		this.pvpEnabled = pvpEnabled;
	}

	public boolean isPvPEnabled() {
		return pvpEnabled;
	}

	public void setPvPEnabled(final boolean pvpenabled) {
		pvpEnabled = pvpenabled;
	}

	public void setbroadCastMsg(final boolean broadCastMsg) {
		this.broadCastMsg = broadCastMsg;
	}

	@Override
	public FeatureType getType() {
		return FeatureType.PVP;
	}

	@Override
	public List<FeatureType> getDependencies() {
		final List<FeatureType> result = new ArrayList<>();
		result.add(FeatureType.MAP);
		return result;
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
	public void onDmg(final CoreUserDamageEvent e) {
		if (e.getGame() != null && e.getGame().equals(getPhase().getGame())) {
			if (e.getCause() == DamageCause.ENTITY_ATTACK) {
				if (!pvpEnabled) {
					e.setCancelled(true);
					e.setDmg(0.0);
					return;
				}
			}
		}

		try {
			if (getPhase().getGame().getPlayers().contains(e.getDamager().getUUID())) {
				if (!pvpEnabled) {
					e.setCancelled(true);
					e.setDmg(0.0);
					return;
				}
			}
		}
		catch (final Exception ex) {
			// e.setCancelled(true);
			// e.setDmg(0.0); // SHOULD THIS BE HERE? IDK! NOPE
		}

		try {
			if (getPhase().getGame().getPlayers().contains(e.getDamaged().getUUID())) {
				if (!pvpEnabled) {
					e.setCancelled(true);
					e.setDmg(0.0);
					return;
				}
			}
		}
		catch (final Exception ex) {}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onDeath(final CoreUserDeathEvent e) {
		if (e.getGame() != null && e.getGame().getIdentifier().equals(getPhase().getGame().getIdentifier())) {
			if (broadCastMsg) {
				if (e.getKiller() != null) {
					getPhase().getGame().broadCastMessage(Prefix.getByGameType(getPhase().getGame().getType()).getPrefix().then("Der Spieler ").color(ChatColor.AQUA).then(e.getUser().getDisplayName())
							.color(ChatColor.DARK_BLUE).then(" wurde von ").color(ChatColor.AQUA).then(e.getKiller().getDisplayName()).color(ChatColor.DARK_BLUE).then(" getötet!").color(ChatColor.AQUA));
				} else {
					getPhase().getGame().broadCastMessage(Prefix.getByGameType(getPhase().getGame().getType()).getPrefix().then("Der Spieler ").color(ChatColor.AQUA).then(e.getUser().getDisplayName())
							.color(ChatColor.DARK_BLUE).then(" ist gestorben!").color(ChatColor.AQUA));
				}
			}
		}
	}

}
