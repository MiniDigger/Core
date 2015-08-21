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
package me.MiniDigger.CraftCore.Phase;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Game.Game;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Phase.TimedPhase;
import me.MiniDigger.Core.Tasks.Task;

public abstract class CoreTimedPhase extends CorePhase implements TimedPhase {
	
	private int	  secs;
	private float	sub;
	private float	subbed;
	private Task	task;
	
	public CoreTimedPhase(final Game game, final Phase nextPhase, final int time) {
		super(game, nextPhase);
		secs = time;
	}
	
	public void setSecs(final int secs) {
		this.secs = secs;
	}
	
	public int getSecs() {
		return secs;
	}
	
	@Override
	public void startPhase() {
		super.startPhase();
		
		subbed = 100;
		sub = subbed / secs;
		
		task = Core.getCore().getTaskHandler().runTaskTimer(new BukkitRunnable() {
			
			int	passed	= 0;
			
			@Override
			public void run() {
				passed++;
				final int toGo = secs - passed;
				if (toGo <= 0) {
					if (getName().equals("GetTheDropTwo")) Core.getCore().getInstance().debug("cancel timedphase task");
					task.getTask().cancel();
					endPhase();
					Core.getCore().getBarHandler().removeAllStatusBars();
					return;
				}
				
				if (getName().equals("GetTheDropTwo")) Core.getCore().getInstance().debug("tick");
				
				tick(passed, toGo);
				
				if (toGo <= 5) {
					tickLast5secs(passed, toGo);
				}
			}
		}, 20, 20, this);
	}
	
	@Override
	public void endPhase() {
		try {
			task.getTask().cancel();
		} catch (Exception ex) {}
		
		for (final UUID u : getGame().getPlayers()) {
			final Player p = Bukkit.getPlayer(u);
			if (displayLevel()) {
				p.setLevel(0);
				p.setExp(0);
			}
			if (displayBar()) {
				Core.getCore().getBarHandler().removeBar(p);
			}
		}
		
		super.endPhase();
	}
	
	@Override
	public void tick(final int secsPassed, final int secsToGo) {
		subbed -= sub;
		if (displayLevel() || displayBar()) {
			
			final float percent = subbed;
			
			for (final UUID u : getGame().getPlayers()) {
				final Player p = Bukkit.getPlayer(u);
				if (displayLevel()) {
					p.setLevel(secsToGo);
					p.setExp(percent / 100);
				}
				if (displayBar()) {
					final double per = percent;
					final double perX2 = per * 2;
					// double perX4 = per * 4;
					// double perX2M100 = perX2 - 100;
					// double maxDper = 200 / per;
					// double perD2 = per / 2;
					// double perXmax = 200 * (per / 100);
					//
					// Core.getCore().getInstance().debug("===============================");
					// Core.getCore().getInstance().debug("PER: " + per);
					// Core.getCore().getInstance().debug("PERX2: " + perX2);
					// Core.getCore().getInstance().debug("PERX4: " + perX4);
					// Core.getCore().getInstance().debug("PERX2M100: " + perX2M100);
					// Core.getCore().getInstance().debug("PERD2: " + perD2);
					// Core.getCore().getInstance().debug("MAXDPER: " + maxDper);
					// Core.getCore().getInstance().debug("PERXMAX: " + perXmax);
					// Core.getCore().getInstance().debug("===============================");
					
					Core.getCore().getBarHandler().setBar(p, getBarMessage(), perX2);
				}
			}
		}
	}
	
	@Override
	public void tickLast5secs(final int secsPassed, final int secsToGo) {
		getGame().broadCastSound(Sound.CLICK, 10, 1);
	}
	
}
