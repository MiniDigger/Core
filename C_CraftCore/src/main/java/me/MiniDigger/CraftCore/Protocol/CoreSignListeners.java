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
 * Written by Martin Benndorf <admin@minidigger.me>, 2013-2014 and others
 */
package me.MiniDigger.CraftCore.Protocol;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Kit.Kit;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.Protocol.SignListeners;

import me.MiniDigger.CraftCore.Feature.Features.TeamFeature;
import me.MiniDigger.CraftCore.Feature.Features.TeamSelectFeature;

public class CoreSignListeners implements SignListeners {
	
	private final ArrayList<SignListener>	listener	= new ArrayList<SignListener>();
	
	@Override
	public void register(final SignListener l) {
		listener.add(l);
	}
	
	@Override
	public void init() {
		register(new SignListener("[TeamJoin]", new SignAction() {
			
			@Override
			public void run() {
				final String team = getSign().getLine(1);
				final TeamSelectFeature tf = (TeamFeature) Core.getCore().getGameHandler().getMainGame().getPhase().getFeature(FeatureType.TEAM);
				if (tf == null) {
					return;
				}
				if (tf.getTeam(getPlayer()) != null) {
					tf.getTeam(getPlayer()).leave(getPlayer().getUniqueId());
				}
				tf.getTeam(team).join(getPlayer().getUniqueId());
			}
		}, false));
		
		register(new SignListener("[TEST]", new SignAction() {
			
			@Override
			public void run() {
				System.out.println("Player " + getPlayer().getName() + " punshed sign with lines: " + Core.getCore().getStringUtil().toString(getSign().getLines()));
			}
		}, false));
		
		register(new SignListener("[Kit]", new SignAction() {
			
			@Override
			public void run() {
				final Kit k = Core.getCore().getKitHandler().getKit(getSign().getLine(1));
				if (k == null) {
					getUser().sendMessage(Prefix.API.getPrefix().then("Dieses Schild ist kaputt! Bitte berichte einem Teammitglied!").color(ChatColor.RED));
					return;
				}
				Core.getCore().getKitHandler().give(getPlayer(), k);
			}
		}, false));
	}
	
	@Override
	@EventHandler
	public void onInteract(final PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			final Block b = e.getClickedBlock();
			if (b.getState() instanceof Sign) {
				final Sign sign = (Sign) b.getState();
				final Thread t = new Thread() {
					
					@Override
					public void run() {
						for (final String line : sign.getLines()) {
							for (final SignListener l : listener) {
								if (l.getKey().equalsIgnoreCase(line)) {
									l.getAction().setSign(sign);
									l.getAction().setPlayer(e.getPlayer());
									l.getAction().setUser(Core.getCore().getUserHandler().get(e.getPlayer().getUniqueId()));
									l.doAction(sign);
									if (l.isFinal()) {
										return;
									}
								}
							}
						}
					}
				};
				t.setName("SignKeySearcher");
				t.start();
			}
		}
	}
}
