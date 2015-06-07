package me.MiniDigger.Core.AddOn.SuchenUndVerstecken;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
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
import me.MiniDigger.Core.User.User;

import me.MiniDigger.CraftCore.Event.Events.CoreUserLeaveGameEvent;
import me.MiniDigger.CraftCore.Feature.CoreFeature;
import me.MiniDigger.CraftCore.Feature.Features.TeamFeature;
import me.MiniDigger.CraftCore.Scoreboard.CoreScoreboardLine;
import me.MiniDigger.CraftCore.Scoreboard.CoreScoreboardTitle;

public class SUVFeature extends CoreFeature {
	
	private UUID	sucher1;
	private UUID	sucher2;
	
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
			board.addLine(new CoreScoreboardLine(3, "Team " + Core.getCore().getUserHandler().get(sucher1).getDisplayName(), DisplaySlot.SIDEBAR));
			board.addLine(new CoreScoreboardLine(2, t.getTeams().get(0).getPlayers().size() + " Spieler", DisplaySlot.SIDEBAR));
		}
		if (sucher2 != null) {
			board.addLine(new CoreScoreboardLine(1, "Team " + Core.getCore().getUserHandler().get(sucher2).getDisplayName(), DisplaySlot.SIDEBAR));
			board.addLine(new CoreScoreboardLine(0, t.getTeams().get(1).getPlayers().size() + " Spieler", DisplaySlot.SIDEBAR));
		}
	}
	
	public void showLives() {
		final List<UUID> retry = new ArrayList<UUID>();
		
		new BukkitRunnable() {
			
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
		}.runTask(Core.getCore().getInstance());
		
		new BukkitRunnable() {
			
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
		}.runTaskLater(Core.getCore().getInstance(), 20);// WAit for respawn
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
		
		if (id1 == sucher1 || id1 == sucher2) {
			// User s = Core.getCore().getUserHandler().get(id1);
			final User v = Core.getCore().getUserHandler().get(id2);
			
			v.getPlayer().damage(9000.0);
			final Location l = v.getPlayer().getLocation();
			l.getWorld().createExplosion(l.getX(), l.getY(), l.getZ(), 1, false, false);
			
			if (id1 == sucher1 && t.getTeams().get(1).getPlayers().contains(id2)) {
				final User os = Core.getCore().getUserHandler().get(sucher2);
				t.getTeams().get(1).getPlayers().remove(id2);
				if (os.getPlayer() != null) {
					getPhase().getGame().broadCastMessage(
					        Prefix.getByGameType(getPhase().getGame().getType())
					                .getPrefix()
					                .then("Der Spieler " + v.getDisplayName() + " aus dem Team von " + os.getDisplayName()
					                        + (leave ? " hat das Spiel verlassen!" : " wurde gefunden!")));
					getPhase().getGame().broadCastMessage(
					        Prefix.getByGameType(getPhase().getGame().getType()).getPrefix()
					                .then("Es sind noch " + t.getTeams().get(1).getPlayers().size() + " Spieler in diesem Team am Leben!"));
				} else {
					getPhase().getGame().broadCastMessage(
					        Prefix.getByGameType(getPhase().getGame().getType()).getPrefix()
					                .then("Der Spieler " + v.getDisplayName() + (leave ? " hat das Spiel verlassen!" : " wurde gefunden!")));
					getPhase().getGame().broadCastMessage(
					        Prefix.getByGameType(getPhase().getGame().getType()).getPrefix()
					                .then("Es sind noch " + t.getTeams().get(1).getPlayers().size() + " Spieler am Leben!"));
				}
			} else if (id1 == sucher2 && t.getTeams().get(0).getPlayers().contains(id2)) {
				final User os = Core.getCore().getUserHandler().get(sucher1);
				t.getTeams().get(0).getPlayers().remove(id2);
				if (os.getPlayer() != null) {
					getPhase().getGame().broadCastMessage(
					        Prefix.getByGameType(getPhase().getGame().getType())
					                .getPrefix()
					                .then("Der Spieler " + v.getDisplayName() + " aus dem Team von " + os.getDisplayName()
					                        + (leave ? " hat das Spiel verlassen!" : " wurde gefunden!")));
					getPhase().getGame().broadCastMessage(
					        Prefix.getByGameType(getPhase().getGame().getType()).getPrefix()
					                .then("Es sind noch " + t.getTeams().get(0).getPlayers().size() + " Spieler in diesem Team am Leben!"));
				} else {
					getPhase().getGame().broadCastMessage(
					        Prefix.getByGameType(getPhase().getGame().getType()).getPrefix()
					                .then("Der Spieler " + v.getDisplayName() + (leave ? " hat das Spiel verlassen!" : " wurde gefunden!")));
					getPhase().getGame().broadCastMessage(
					        Prefix.getByGameType(getPhase().getGame().getType()).getPrefix()
					                .then("Es sind noch " + t.getTeams().get(0).getPlayers().size() + " Spieler am Leben!"));
				}
			}
			
			showLives();
			checkEnd();
		}
	}
	
	public void checkEnd() {
		final TeamFeature t = (TeamFeature) getPhase().getFeature(FeatureType.TEAM);
		
		if (t.getTeams().get(0).getPlayers().size() == 0) {
			final User w = Core.getCore().getUserHandler().get(sucher2);
			final User[] ww = new User[t.getTeams().get(1).getPlayers().size() + 1];
			for (int i = 1; i < ww.length - 1; i++) {
				ww[i] = Core.getCore().getUserHandler().get(t.getTeams().get(1).getPlayers().get(i));
			}
			ww[0] = w;
			getPhase().getGame().end(w);
		} else if (t.getTeamCount() > 1 && t.getTeams().get(1).getPlayers().size() == 0) {
			final User w = Core.getCore().getUserHandler().get(sucher1);
			final User[] ww = new User[t.getTeams().get(0).getPlayers().size() + 1];
			for (int i = 1; i < ww.length - 1; i++) {
				ww[i] = Core.getCore().getUserHandler().get(t.getTeams().get(0).getPlayers().get(i));
			}
			ww[0] = w;
			getPhase().getGame().end(w);
		}
	}
	
	@EventHandler
	public void onInteract(final PlayerInteractEntityEvent e) {
		if (e.getRightClicked() instanceof Player) {
			if (getPhase().getGame().getPlayers().contains(e.getPlayer().getUniqueId()) && getPhase().getGame().getPlayers().contains(e.getRightClicked().getUniqueId())) {
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
	
	public void setSucher1(final UUID id) {
		sucher1 = id;
	}
	
	public void setSucher2(final UUID id) {
		sucher2 = id;
	}
}
