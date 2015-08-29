package me.MiniDigger.Core.AddOn.Ehrenhalle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;

import me.MiniDigger.Core.Core;
import me.MiniDigger.Core.Feature.FeatureType;
import me.MiniDigger.Core.Phase.Phase;
import me.MiniDigger.Core.Scoreboard.Scoreboard;
import me.MiniDigger.Core.User.User;
import me.MiniDigger.CraftCore.Event.Events.CoreUserJoinGameEvent;
import me.MiniDigger.CraftCore.Event.Events.CoreUserLeaveGameEvent;
import me.MiniDigger.CraftCore.Feature.CoreFeature;
import me.MiniDigger.CraftCore.Scoreboard.CoreScoreboardLine;
import me.MiniDigger.CraftCore.Scoreboard.CoreScoreboardTitle;

public class EHFeature extends CoreFeature {

	public EHFeature(final Phase phase) {
		super(phase);
	}

	@Override
	public FeatureType getType() {
		return FeatureType.EH;
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
			final User u = Core.getCore().getUserHandler().get(id);
			u.getPlayer().setGameMode(GameMode.SPECTATOR);
			u.getPlayer().teleport(((EHPhase) getPhase()).getS().getSpawn());
		}

		updateScoreboard();
	}

	@Override
	public void end() {
		for (final UUID id : getPhase().getGame().getPlayers()) {
			final User u = Core.getCore().getUserHandler().get(id);
			u.getPlayer().setGameMode(GameMode.SURVIVAL);
		}
	}

	@EventHandler
	public void onJoin(final CoreUserJoinGameEvent e) {
		if (e.getGame().getIdentifier() == getPhase().getGame().getIdentifier()) {
			e.getUser().getPlayer().setGameMode(GameMode.SPECTATOR);
			e.getUser().getPlayer().teleport(((EHPhase) getPhase()).getS().getSpawn());

			modBoard(Core.getCore().getScoreboardHandler().getBoard(e.getUser().getUUID()));
			Core.getCore().getScoreboardHandler().update(e.getUser().getUUID());
		}
	}

	@EventHandler
	public void onQuit(final CoreUserLeaveGameEvent e) {
		if (e.getGame().getIdentifier() == getPhase().getGame().getIdentifier()) {
			e.getUser().getPlayer().setGameMode(GameMode.SURVIVAL);
			e.getUser().getPlayer().teleport(((EHPhase) getPhase()).getS().getSpawn());
		}
	}

	private void modBoard(final Scoreboard board) {
		board.clear(DisplaySlot.SIDEBAR);

		board.setTitle(new CoreScoreboardTitle("Euro", DisplaySlot.BELOW_NAME));

		final Map<String, Double> donations = ((EHPhase) getPhase()).getD().getDonations();
		for (final String name : donations.keySet()) {
			board.addLine(new CoreScoreboardLine(donations.get(name).intValue(), name, DisplaySlot.BELOW_NAME));
		}
	}

	public void updateScoreboard() {
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
}
