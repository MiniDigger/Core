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
package me.MiniDigger.Core.AddOn.BuildMyThing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Map.MapData;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Prefix.Prefix;
import me.MiniDigger.Core.Scoreboard.Scoreboard;
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Event.Events.CoreUserChatEvent;
import me.MiniDigger.CraftCore.Feature.CoreFeature;
import me.MiniDigger.CraftCore.Feature.Features.MapFeature;
import me.MiniDigger.CraftCore.Scoreboard.CoreScoreboardLine;
import me.MiniDigger.CraftCore.Scoreboard.CoreScoreboardTitle;

public class BMTFeature extends CoreFeature {

	public BMTFeature(final Phase phase) {
		super(phase);
	}

	private String				word;
	private String[]			words			= new String[0];
	private UUID				builder;
	private int					found			= 0;
	private List<UUID>			builded			= new ArrayList<UUID>();
	private Map<UUID, Integer>	points			= new HashMap<UUID, Integer>();
	private final List<UUID>	guessed			= new ArrayList<UUID>();
	private int					pointsneeded	= -1;

	@Override
	public FeatureType getType() {
		return FeatureType.BTM;
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
		// Resets
		word = "";
		found = 0;
		guessed.clear();

		// Calc Points needed
		if (pointsneeded == -1) {
			pointsneeded = getPhase().getGame().getPlayers().size() * 2 + 5;
		}

		// Words
		if (words.length == 0) {
			try {
				final List<String> l = new ArrayList<String>();

				final File f = new File(Core.getCore().getInstance().getDataFolder(), "words.txt");
				final BufferedReader r = new BufferedReader(new FileReader(f));

				String buffer;
				while ((buffer = r.readLine()) != null) {
					l.add(buffer);
				}

				r.close();
				words = l.toArray(new String[l.size()]);
			}
			catch (final Exception ex) {
				words = new String[] { "banane", "apfel", "pferd" };// FALLBACK
			}
		}

		// User
		List<UUID> available = new ArrayList<UUID>();
		for (final UUID id : getPhase().getGame().getPlayers()) {
			if (!builded.contains(id)) {
				available.add(id);
			}
		}

		if (available.size() == 0) {
			available.clear();
			available = getPhase().getGame().getPlayers();
		}

		// builder =
		// available.get(Core.getCore().getRandomUtil().nextInt(available.size()));
		builder = available.get(0);
		builded.add(builder);

		final User u = Core.getCore().getUserHandler().get(builder);
		u.getPlayer().getInventory().clear();
		u.getPlayer().updateInventory();
		getPhase().getGame().broadCastMessage(getPhase().getGame().getGamePrefix().getPrefix().then("Der Spieler " + u.getDisplayName() + " ist nun dran"));

		// Word
		word = words[Core.getCore().getRandomUtil().nextInt(words.length)];

		u.sendMessage(getPhase().getGame().getGamePrefix().getPrefix().then("Dein Wort ist: " + ChatColor.GOLD + word));

		Core.getCore().getBuildHandler().setBuilder(u, true);
		Core.getCore().getBuildHandler().disallow(u, Material.BARRIER);
		u.getPlayer().setGameMode(GameMode.CREATIVE);

		// TP
		final MapData data = ((MapFeature) getPhase().getFeature(FeatureType.MAP)).getMap();
		Core.getCore().getInstance().debug("map " + data.getName());
		if (data.getLocs(DyeColor.GREEN) == null) {
			Core.getCore().getInstance().debug("locs null");
		}
		u.getPlayer().teleport(data.getLocs(DyeColor.GREEN).values().iterator().next());

		final Location[] locs = data.getLocs(DyeColor.RED).values().toArray(new Location[data.getLocs(DyeColor.RED).size()]);
		for (final UUID id : getPhase().getGame().getPlayers()) {
			final Location loc = locs[(Core.getCore().getRandomUtil().nextInt(locs.length))];
			final User o = Core.getCore().getUserHandler().get(id);
			if (!o.getPlayer().getLocation().getWorld().getName().equalsIgnoreCase(loc.getWorld().getName())) {
				if (id != builder) {
					o.getPlayer().teleport(loc);
					o.getPlayer().getInventory().clear();
					o.getPlayer().updateInventory();
				}
			}
		}

		updateBoards();
	}

	@Override
	public void end() {
		final User u = Core.getCore().getUserHandler().get(builder);

		Core.getCore().getBuildHandler().allow(u, Material.BARRIER);
		Core.getCore().getBuildHandler().setBuilder(u, false);
		u.getPlayer().setGameMode(GameMode.SURVIVAL);

		final MapData data = ((MapFeature) getPhase().getFeature(FeatureType.MAP)).getMap();
		final Location[] locs = data.getLocs(DyeColor.RED).values().toArray(new Location[data.getLocs(DyeColor.RED).size()]);
		final Location loc = locs[(Core.getCore().getRandomUtil().nextInt(locs.length))];
		u.getPlayer().teleport(loc);
		u.getPlayer().getInventory().clear();
		u.getPlayer().updateInventory();

		getPhase().getGame().broadCastMessage(getPhase().getGame().getGamePrefix().getPrefix().then("Das Wort war: " + ChatColor.GREEN + word));

		getPhase().getNextPhase().init();

		final BMTFeature b = (BMTFeature) getPhase().getNextPhase().getFeature(FeatureType.BTM);
		b.setWords(words);
		b.setBuilded(builded);
		b.setBuilder(builder);
		b.setPoints(points);
	}

	@EventHandler
	public void onChat(final CoreUserChatEvent e) {
		if (getPhase().getGame().getPlayers().contains(e.getUser().getUUID())) {
			if (guessed.contains(e.getUser().getUUID())) {
				e.setCancelled(true);
				return;
			}

			if (builder == e.getUser().getUUID()) {
				e.setCancelled(true);
				return;
			}

			if (e.getMsg().equalsIgnoreCase(word)) {
				e.setCancelled(true);

				if (builder.equals(e.getUser().getUUID())) {
					e.getUser().sendMessage(Prefix.BMT.getPrefix().then("Du kannst nicht dein eigenes Wort erraten...").color(ChatColor.RED));
					return;
				}

				getPhase().getGame().broadCastMessage(getPhase().getGame().getGamePrefix().getPrefix().then("Der Spieler " + e.getUser().getDisplayName() + " hat das Wort erraten!"));

				if (found == 0) {
					final int p = points.remove(builder);
					points.put(builder, p + 2);

					if (points.get(builder) >= pointsneeded) {
						updateBoards();
						Core.getCore().getTaskHandler().runTask(new BukkitRunnable() {

							@Override
							public void run() {
								getPhase().getGame().end(Core.getCore().getUserHandler().get(builder));

							}
						}, getPhase());
						return;
					}
				}

				found++;
				int point = 3;
				switch (found) {
					case 1:
						point = 3;
						break;
					case 2:
						point = 2;
						break;
					default:
						point = 1;
						break;
				}

				final int p = points.remove(e.getUser().getUUID());
				points.put(e.getUser().getUUID(), p + point);
				guessed.add(e.getUser().getUUID());

				if (points.get(e.getUser().getUUID()) >= 20) {
					updateBoards();
					Core.getCore().getTaskHandler().runTask(new BukkitRunnable() {

						@Override
						public void run() {
							getPhase().getGame().end(Core.getCore().getUserHandler().get(e.getUser().getUUID()));

						}
					}, getPhase());
					return;
				}

				updateBoards();
			}
		}
	}

	private void modBoard(final Scoreboard board) {
		board.clear(DisplaySlot.SIDEBAR);
		board.setTitle(new CoreScoreboardTitle(ChatColor.GOLD + "Punkte(von " + pointsneeded + ")", DisplaySlot.SIDEBAR));

		for (final UUID id : getPhase().getGame().getPlayers()) {
			final User u = Core.getCore().getUserHandler().get(id);

			if (!points.containsKey(id)) {
				points.put(id, 0);
			}

			board.addLine(new CoreScoreboardLine(points.get(id), u.getDisplayName(), DisplaySlot.SIDEBAR));
		}
	}

	public void updateBoards() {
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

	public void setBuilder(final UUID builder) {
		this.builder = builder;
	}

	public void setBuilded(final List<UUID> builded) {
		this.builded = builded;
	}

	public void setPoints(final Map<UUID, Integer> points) {
		this.points = points;
	}

	private void setWords(final String[] words) {
		this.words = words;
	}
}
