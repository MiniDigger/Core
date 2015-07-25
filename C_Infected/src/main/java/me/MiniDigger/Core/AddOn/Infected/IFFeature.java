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
package me.MiniDigger.Core.AddOn.Infected;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.scheduler.BukkitRunnable;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Event.Events.CoreUserDeathEvent;
import me.MiniDigger.CraftCore.Feature.CoreFeature;

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;

public class IFFeature extends CoreFeature {
	
	private List<UUID>	infeced	= new ArrayList<>();
	private boolean		cure		= false;
	
	public IFFeature(final Phase phase) {
		super(phase);
	}
	
	@Override
	public FeatureType getType() {
		return FeatureType.IF;
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
		int count = getPhase().getGame().getPlayers().size() / 6;
		System.out.println("count " + count);
		
		if (count != 0) {
			for (int i = 0; i < count; i++) {
				infeced.add(getPhase().getGame().getPlayers().get(Core.getCore().getRandomUtil().nextInt(getPhase().getGame().getPlayers().size())));
			}
		}else{
			infeced.add(getPhase().getGame().getPlayers().get(Core.getCore().getRandomUtil().nextInt(getPhase().getGame().getPlayers().size())));
			System.out.println("no zombies....");
		}
		
		for (UUID id : infeced) {
			User user = Core.getCore().getUserHandler().get(id);
			Prefix.IF.getPrefix().then("Du bist einer der ersten Zombies!").color(ChatColor.GOLD).send(user.getPlayer());
			Prefix.IF.getPrefix().then("Töte alle Überlebenden um sie zu infizieren!").color(ChatColor.GOLD).send(user.getPlayer());
			
			MobDisguise zombie = new MobDisguise(DisguiseType.ZOMBIE);
			DisguiseAPI.disguiseToAll(user.getPlayer(), zombie);
		}
		
		startTimer();
	}
	
	public void startTimer() {
		Core.getCore().getTaskHandler().runTaskTimer(new BukkitRunnable() {
			
			private int time = 5 * 60;
			
			@Override
			public void run() {
				time--;
				
				if (time == 0) {
					cure();
					cancel();
				}
				
				for (UUID id : getPhase().getGame().getPlayers()) {
					final User user = Core.getCore().getUserHandler().get(id);
					if (user == null) {
						continue;
					}
					
					user.getPlayer().setLevel(time);
					user.getPlayer().setExp((float) (0.3 * time) / 10);
				}
			}
		}, 20, 20, getPhase());
	}
	
	public void cure() {
		cure = true;
		getPhase().getGame().broadCastMessage(Prefix.IF.getPrefix().then("Es wurde ein Heilmittel gefunden!").color(ChatColor.GOLD));
		getPhase().getGame().broadCastMessage(Prefix.IF.getPrefix().then("Die Zombies können jetzt nicht mehr wiederaufstehen!").color(ChatColor.GOLD));
	}
	
	@Override
	public void end() {
		for (UUID id : infeced) {
			User user = Core.getCore().getUserHandler().get(id);
			if (DisguiseAPI.isDisguised(user.getPlayer())) {
				DisguiseAPI.undisguiseToAll(user.getPlayer());
			}
		}
	}
	
	@EventHandler
	public void onUserDeath(final CoreUserDeathEvent e) {
		if (e.getGame().getIdentifier() == getPhase().getGame().getIdentifier()) {
			if (infeced.contains(e.getUser().getUUID())) {
				if (cure) {
					getPhase().getGame().leave(e.getUser());
				}
			} else {
				infeced.add(e.getUser().getUUID());
				Prefix.IF.getPrefix().then("Du bist jetzt ein Zombie!").color(ChatColor.GOLD).send(e.getUser().getPlayer());
				Prefix.IF.getPrefix().then("Töte die letzten Überlebenen!").color(ChatColor.GOLD).send(e.getUser().getPlayer());
				
				MobDisguise zombie = new MobDisguise(DisguiseType.ZOMBIE);
				DisguiseAPI.disguiseToAll(e.getUser().getPlayer(), zombie);
			}
			
			checkEnd();
		}
	}
	
	public void checkEnd() {
		// clear list
		List<UUID> newInf = new ArrayList<>();
		for (UUID id : infeced) {
			if (getPhase().getGame().getPlayers().contains(id)) {
				newInf.add(id);
			}
		}
		
		infeced = newInf;
		
		// count
		int ifcount = infeced.size();
		int playercount = getPhase().getGame().getPlayers().size();
		int alive = playercount - ifcount;
		
		if (alive == 0) {
			getPhase().getGame().broadCastMessage(Prefix.IF.getPrefix().then("Alle Überlebenden wurden ausgelöscht!").color(ChatColor.GOLD));
			getPhase().getGame().broadCastMessage(Prefix.IF.getPrefix().then("Die Menschheit ist untergegangen!").color(ChatColor.GOLD));
			
			getPhase().getGame().end();
		}
		
		if (cure) {
			if (ifcount == 0) {
				getPhase().getGame().broadCastMessage(Prefix.IF.getPrefix().then("Der Zombievirus wurde vernichtet!").color(ChatColor.GOLD));
				getPhase().getGame().broadCastMessage(Prefix.IF.getPrefix().then("Die Menschheit ist gerettet!").color(ChatColor.GOLD));
				
				getPhase().getGame().end();
			}
		}
	}
}
