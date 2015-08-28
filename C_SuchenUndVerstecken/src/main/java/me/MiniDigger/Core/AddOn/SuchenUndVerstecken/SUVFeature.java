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
package me.MiniDigger.Core.AddOn.SuchenUndVerstecken;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.Scoreboard.Scoreboard;
import me.MiniDigger.Core.Team.Team;
import me.MiniDigger.Core.User.User;
import me.MiniDigger.CraftCore.Event.Events.CoreUserDeathEvent;
import me.MiniDigger.CraftCore.Event.Events.CoreUserLeaveGameEvent;
import me.MiniDigger.CraftCore.Feature.CoreFeature;
import me.MiniDigger.CraftCore.Feature.Features.NoNameTagFeature;
import me.MiniDigger.CraftCore.Feature.Features.TeamFeature;
import me.MiniDigger.CraftCore.Item.CoreItemBuilder;
import me.MiniDigger.CraftCore.Scoreboard.CoreScoreboardLine;
import me.MiniDigger.CraftCore.Scoreboard.CoreScoreboardTitle;

public class SUVFeature extends CoreFeature {

	private UUID sucher1;
	private UUID sucher2;

	public SUVFeature(final Phase phase) {
		super(phase);
	}

	@Override
	public FeatureType getType() {
		return FeatureType.SUV;
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
		try {
			sucher1 = UUID.fromString(getPhase().getGame().getGameData("sucher1"));
			Bukkit.getPlayer(sucher1).getInventory().setHelmet(new CoreItemBuilder(Material.LEATHER_HELMET)
					.color(Core.getCore().getChatColorUtil().toColor(ChatColor.LIGHT_PURPLE)).build());
		} catch (final Exception ex) {
		}
		try {
			sucher2 = UUID.fromString(getPhase().getGame().getGameData("sucher2"));
			Bukkit.getPlayer(sucher2).getInventory().setHelmet(new CoreItemBuilder(Material.LEATHER_HELMET)
					.color(Core.getCore().getChatColorUtil().toColor(ChatColor.GREEN)).build());
		} catch (final Exception ex) {
		}
		
		((NoNameTagFeature) getPhase().getFeature(FeatureType.NONAMETAG)).addIgnore(sucher1);
		((NoNameTagFeature) getPhase().getFeature(FeatureType.NONAMETAG)).addIgnore(sucher2);

		showLives();
	}

	@Override
	public void end() {
		Core.getCore().getScoreboardHandler().clearAll();
	}

	private void modBoard(final Scoreboard board) {
		board.clear(DisplaySlot.SIDEBAR);
		board.setTitle(new CoreScoreboardTitle(ChatColor.GOLD + "Noch da", DisplaySlot.SIDEBAR));

		final TeamFeature t = (TeamFeature) getPhase().getFeature(FeatureType.TEAM);

		if (sucher1 != null) {
			board.addLine(new CoreScoreboardLine(3,
					"Team " + Core.getCore().getUserHandler().get(sucher1).getDisplayName(), DisplaySlot.SIDEBAR));
			board.addLine(new CoreScoreboardLine(2, t.getTeams().get(0).getPlayers().size() + " Spieler ",
					DisplaySlot.SIDEBAR));
		}
		if (sucher2 != null) {
			board.addLine(new CoreScoreboardLine(1,
					"Team " + Core.getCore().getUserHandler().get(sucher2).getDisplayName(), DisplaySlot.SIDEBAR));
			board.addLine(new CoreScoreboardLine(0, t.getTeams().get(1).getPlayers().size() + " Spieler",
					DisplaySlot.SIDEBAR));
		}
	}

	public void showLives() {
		final List<UUID> retry = new ArrayList<UUID>();

		Core.getCore().getTaskHandler().runTask(new BukkitRunnable() {

			@Override
			public void run() {

				for (final UUID uuid : getPhase().getGame().getPlayers()) {
					if (Bukkit.getPlayer(uuid) == null) {
						retry.add(uuid);
						continue;
					}
					modBoard(Core.getCore().getScoreboardHandler().getBoard(uuid));
					Core.getCore().getScoreboardHandler().update(uuid);
				}
			}
		}, getPhase());

		Core.getCore().getTaskHandler().runTaskLater(new BukkitRunnable() {

			@Override
			public void run() {
				for (final UUID uuid : retry) {
					if (Bukkit.getPlayer(uuid) == null) {
						continue;// Fuck you
					}
					modBoard(Core.getCore().getScoreboardHandler().getBoard(uuid));
					Core.getCore().getScoreboardHandler().update(uuid);
				}
			}
		}, 20, getPhase());// WAit for respawn
	}

	public void check(UUID id1, final UUID id2, final boolean leave) {
		final TeamFeature t = (TeamFeature) getPhase().getFeature(FeatureType.TEAM);

		if (id1 == null) {
			if (t.getTeams().get(0).getPlayers().contains(id2)) {
				id1 = sucher2;
			} else {
				id1 = sucher1;
			}
		}

		if (id1.equals(sucher1) || id1.equals(sucher2)) {
			Core.getCore().getInstance().debug("is sucher");
			if (id2.equals(sucher1) || id2.equals(sucher2)) {
				Core.getCore().getInstance().debug("both sucher");
				return;
			}
			// User s = Core.getCore().getUserHandler().get(id1);
			final User v = Core.getCore().getUserHandler().get(id2);

			if (id1.equals(sucher1) && t.getTeams().get(0).getPlayers().contains(id2)) {
				Core.getCore().getInstance().debug("id1 is sucher1 and id2 in team 0");
				final User os = Core.getCore().getUserHandler().get(sucher2);
				if (os == null || os.getPlayer() == null) {
					t.getTeams().get(0).getPlayers().remove(id2);
					getPhase().getGame()
							.broadCastMessage(Prefix.getByGameType(getPhase().getGame().getType()).getPrefix()
									.then("Der Spieler " + v.getDisplayName()
											+ (leave ? " hat das Spiel verlassen!" : " wurde gefunden!")));
					getPhase().getGame()
							.broadCastMessage(Prefix.getByGameType(getPhase().getGame().getType()).getPrefix().then(
									"Es sind noch " + t.getTeams().get(0).getPlayers().size() + " Spieler am Leben!"));
				} else {
					t.getTeams().get(0).getPlayers().remove(id2);
					getPhase().getGame()
							.broadCastMessage(Prefix.getByGameType(getPhase().getGame().getType()).getPrefix()
									.then("Der Spieler " + v.getDisplayName() + " aus dem Team von "
											+ os.getDisplayName()
											+ (leave ? " hat das Spiel verlassen!" : " wurde gefunden!")));
					getPhase().getGame()
							.broadCastMessage(Prefix.getByGameType(getPhase().getGame().getType()).getPrefix()
									.then("Es sind noch " + t.getTeams().get(0).getPlayers().size()
											+ " Spieler in diesem Team am Leben!"));
				}

				v.getPlayer().damage(9000.0);
				final Location l = v.getPlayer().getLocation();
				l.getWorld().createExplosion(l.getX(), l.getY(), l.getZ(), 1, false, false);
			} else if (id1.equals(sucher2) && t.getTeams().get(1).getPlayers().contains(id2)) {
				Core.getCore().getInstance().debug("id1 is sucher2 and id2 in team 1");
				final User os = Core.getCore().getUserHandler().get(sucher1);
				if (os == null || os.getPlayer() == null) {
					t.getTeams().get(1).getPlayers().remove(id2);
					getPhase().getGame()
							.broadCastMessage(Prefix.getByGameType(getPhase().getGame().getType()).getPrefix()
									.then("Der Spieler " + v.getDisplayName()
											+ (leave ? " hat das Spiel verlassen!" : " wurde gefunden!")));
					getPhase().getGame()
							.broadCastMessage(Prefix.getByGameType(getPhase().getGame().getType()).getPrefix().then(
									"Es sind noch " + t.getTeams().get(1).getPlayers().size() + " Spieler am Leben!"));
				} else {
					t.getTeams().get(1).getPlayers().remove(id2);
					getPhase().getGame()
							.broadCastMessage(Prefix.getByGameType(getPhase().getGame().getType()).getPrefix()
									.then("Der Spieler " + v.getDisplayName() + " aus dem Team von "
											+ os.getDisplayName()
											+ (leave ? " hat das Spiel verlassen!" : " wurde gefunden!")));
					getPhase().getGame()
							.broadCastMessage(Prefix.getByGameType(getPhase().getGame().getType()).getPrefix()
									.then("Es sind noch " + t.getTeams().get(1).getPlayers().size()
											+ " Spieler in diesem Team am Leben!"));
				}

				v.getPlayer().damage(9000.0);
				final Location l = v.getPlayer().getLocation();
				l.getWorld().createExplosion(l.getX(), l.getY(), l.getZ(), 1, false, false);
			} else {
				Core.getCore().getInstance().debug("cmon");
			}

			showLives();
			checkEnd();
		}
	}

	public void checkEnd() {
		final TeamFeature t = (TeamFeature) getPhase().getFeature(FeatureType.TEAM);
		if (t.getTeams().size() > 1) {
			if (t.getTeams().get(0).getPlayers().size() == 0) {
				final User w = Core.getCore().getUserHandler().get(sucher1);
				final User[] ww = new User[t.getTeams().get(1).getPlayers().size() + 1];
				for (int i = 1; i < ww.length - 1; i++) {
					ww[i] = Core.getCore().getUserHandler().get(t.getTeams().get(1).getPlayers().get(i));
				}
				ww[0] = w;
				Core.getCore().getInstance().debug("end1");
				getPhase().getGame().end(w); // TODO it is not w but ww here,
												// but pssst ;D
			} else if (t.getTeamCount() > 1 && t.getTeams().get(1).getPlayers().size() == 0) {
				final User w = Core.getCore().getUserHandler().get(sucher2);
				final User[] ww = new User[t.getTeams().get(0).getPlayers().size() + 1];
				for (int i = 1; i < ww.length - 1; i++) {
					ww[i] = Core.getCore().getUserHandler().get(t.getTeams().get(0).getPlayers().get(i));
				}
				ww[0] = w;
				Core.getCore().getInstance().debug("end2");
				getPhase().getGame().end(w);
			}
		} else {
			final User w = Core.getCore().getUserHandler().get(sucher1);
			if (t.getTeams().get(0).getPlayers().size() == 0) {
				Core.getCore().getInstance().debug("end3");
				getPhase().getGame().end(w);
			}
		}
	}

	@EventHandler
	public void onInteract(final PlayerInteractEntityEvent e) {
		if (e.getRightClicked() instanceof Player) {
			Core.getCore().getInstance().debug("click");
			if (getPhase().getGame().getPlayers().contains(e.getPlayer().getUniqueId())
					&& getPhase().getGame().getPlayers().contains(e.getRightClicked().getUniqueId())) {
				Core.getCore().getInstance().debug("both ingame");
				check(e.getPlayer().getUniqueId(), e.getRightClicked().getUniqueId(), false);
			}
		}
	}

	@EventHandler
	public void onQuit(final CoreUserLeaveGameEvent e) {
		if (e.getGame().getIdentifier() == getPhase().getGame().getIdentifier()) {
			check(null, e.getUser().getUUID(), true);
			checkEnd();
		}
	}

	@EventHandler
	public void onDeath(final CoreUserDeathEvent e) {
		if (e.getGame().getIdentifier() == getPhase().getGame().getIdentifier()) {
			final TeamFeature t = (TeamFeature) getPhase().getFeature(FeatureType.TEAM);
			if (e.getUser().getUUID() == sucher1 || e.getUser().getUUID() == sucher2) {
				Core.getCore().getInstance().debug("sucher death");
				e.setShouldRespawn(true);
			} else if (t.getTeam(e.getUser()) == null) {
				Core.getCore().getInstance().debug("teamless death");
				e.setShouldRespawn(false);
			} else {
				Team team = t.getTeam(e.getUser());
				Core.getCore().getInstance().debug(team.getName() + " death");
			}
		}
	}

	public void setSucher1(final UUID id) {
		sucher1 = id;
	}

	public void setSucher2(final UUID id) {
		sucher2 = id;
	}
}
