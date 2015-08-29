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
package me.MiniDigger.CraftCore.Protocol;

import java.util.ArrayList;

import org.bukkit.Bukkit;
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
import me.MiniDigger.Core.Server.Server;
import me.MiniDigger.CraftCore.Feature.Features.TeamFeature;
import me.MiniDigger.CraftCore.Feature.Features.TeamSelectFeature;

public class CoreSignListeners implements SignListeners {

	private final ArrayList<SignListener> listener = new ArrayList<SignListener>();

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
				Core.getCore().getInstance().debug("Player " + getPlayer().getName() + " punshed sign with lines: " + Core.getCore().getStringUtil().toString(getSign().getLines()));
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

		register(new SignListener("Kit", new SignAction() {

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

		register(new SignListener("[Teleport]", new SignAction() {

			@Override
			public void run() {
				try {
					final Server s = Core.getCore().getServerHandler().getServerInfo(getSign().getLine(1));
					if (Core.getCore().getGameHandler().isDisabled(s.getPrimaryGameType())) {
						getUser().sendMessage(Prefix.API.getPrefix().then("Dieser Spielmodi ist temporär deaktiviert! Sorry!").color(ChatColor.RED));
						return;
					}
				}
				catch (final Exception ex) {}
				Core.getCore().getServerHandler().connect(getUser(), getSign().getLine(1));
			}
		}, false));

		register(new SignListener("[GameJ]", new SignAction() {

			@Override
			public void run() {
				Bukkit.getServer().dispatchCommand(getPlayer(), "game join " + getSign().getLine(1));
			}
		}, false));

		register(new SignListener("[GameL]", new SignAction() {

			@Override
			public void run() {
				Bukkit.getServer().dispatchCommand(getPlayer(), "game leave");
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
